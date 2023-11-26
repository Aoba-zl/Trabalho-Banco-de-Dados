package persistence;

import model.Client;
import model.Item;
import model.Order;
import model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Esta é uma Classe de Persistence que realiza operações com o banco de dados.
 */
public class PurchaseDetailsDao {

    GenericDao genericDao;

    /**
     * Adquire a conexão com o banco de dados.
     * @param genericDao A conexão com o banco de dados.
     */
    public PurchaseDetailsDao(GenericDao genericDao) {
        this.genericDao= genericDao;
    }

    /**
     * Consulta um pedido apartir do carrinho ou da tela do produto, de um determinado cliente.
     * @param login O login do cliente.
     * @return O pedido.
     */
    public Order selectOrder(String login){
        Order order= new Order();
        try {
            Connection connection= genericDao.getConnection();
            String sql= """
                    select prod.id_product,
                           prod.name_product,
                           prod.description,
                           order_prod.quantity,
                           order_prod.sub_total,
                           order_prod.sub_total + prod.shipping as total,
                           order_tab.id_order,
                           prod.shipping
                    from order_tbl order_tab inner join order_product order_prod on order_tab.id_order = order_prod.id_order
                         inner join product prod on order_prod.id_product = prod.id_product
                         inner join client cli on cli.user_name = order_tab.user_name_client
                         left join cart c on order_tab.id_order = c.id_order
                         left join payment p on p.id_order = order_tab.id_order
                    where c.id_order is null
                        and p.id_order is null
                        and cli.user_name = ?""";
            PreparedStatement ps= connection.prepareStatement(sql);
            ps.setString(1, login);

            ResultSet rs= ps.executeQuery();

            List<Item> items= new ArrayList<>();
            while(rs.next()){
                Product product= new Product();
                Item item= new Item();

                product.setCod(rs.getInt(1));
                product.setName(rs.getString(2));
                product.setDescription(rs.getString(3));
                item.setQuantity(rs.getInt(4));
                item.setSubTotal(rs.getDouble(5));
                order.setTotal(rs.getDouble(6));
                order.setId(rs.getInt(7));
                product.setShipping(rs.getDouble(8));

                item.setProduct(product);
                items.add(0, item);
                order.setItems(items);
            }

            connection.close();


        } catch (SQLException e) {
            System.out.println("Erro ao consultar o pedido sem pagamento e sem interação com o carrinho" + e.getMessage());
        }
        return order;
    }

    /**
     * Inseri o pagamento de um pedido, verificando o meio de pagamento.
     * @param order O pedido.
     * @param pix O meio de pagamento.
     */
    public void insertPayment(Order order, Boolean pix, Boolean cart){
        try {
            Connection connection= genericDao.getConnection();
            String sql= """
                    insert into payment (id_order, total_pay, date_pay, status)
                    values
                        (?, ?, getdate(), 'Preparando')""";
            PreparedStatement insertPay= connection.prepareStatement(sql);
            insertPay.setInt(1, order.getId());
            insertPay.setDouble(2, order.getTotal());

            insertPay.executeUpdate();

            if (pix){
                String sqlPix= """
                        insert into pix (id_order, qr_code)
                        values
                            (?, ?)""";
                PreparedStatement insertPix= connection.prepareStatement(sqlPix);
                insertPix.setInt(1, order.getId());
                insertPix.setString(2, "pixCodigo");

                insertPix.executeUpdate();
            }
            else {
                String sqlPaySlip= """
                        insert into payment_slip (id_order, qr_code)
                        values
                            (?, ?)""";
                PreparedStatement insertPaySlip= connection.prepareStatement(sqlPaySlip);
                insertPaySlip.setInt(1, order.getId());
                insertPaySlip.setString(2, "boletoCodigo");

                insertPaySlip.executeUpdate();
            }

            connection.close();

            reduceStock(order.getItems());
            if (cart){
                deleteCart(order.getId());
            }
        } catch (SQLException e) {
            System.out.println("Erro ao inserir payment");
            throw new RuntimeException(e);
        }
    }

    /**
     * Deleta o pedido do carrinho.
     * @param idOrder O codigo do pedido.
     */
    public void deleteCart(int idOrder){
        try {
            Connection connection= genericDao.getConnection();
            String sql= "delete from cart \n" +
                    "where id_order = ?";
            PreparedStatement ps= connection.prepareStatement(sql);
            ps.setInt(1, idOrder);

            ps.executeUpdate();

            connection.close();
        } catch (SQLException e) {
            System.out.println("Erro em deletar o Carrinho" + e.getMessage());

        }
    }

    /**
     * Reduz o stock dos produtos comprados, utilizando a quantidade dos items.
     * @param items A lista de produtos.
     */
    public void reduceStock(List<Item> items){
        try {
            Connection connection= genericDao.getConnection();
            String sql= """
                    update product
                     set
                         total_stock= total_stock - ?
                     where id_product = ?
                    """;

            int listLenght= items.size();
            for (int i = 0; i < items.size(); i++) {
                PreparedStatement ps= connection.prepareStatement(sql);
                Item item= items.get(i);
                ps.setInt(1, item.getQuantity());
                ps.setInt(2, item.getProduct().getCod());

                ps.executeUpdate();
            }
            connection.close();

        } catch (SQLException e) {
            System.out.println("Erro ao reduzir estoque dos produtos comprados!" + e.getMessage());
        }
    }

    /**
     * Inseri um pedido sem pagamento no banco de dados de um determinado cliente.
     * @param username O username do cliente.
     * @param item O item do pedido.
     */
    public void insertOrder(String username, Item item){
        Order order= new Order();
        try {
            Connection connection= genericDao.getConnection();
            String sql= """
                    insert into order_tbl (user_name_client)
                    values
                        (?)""";
            PreparedStatement insertOrder= connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            insertOrder.setString(1, username);

            insertOrder.executeUpdate();

            ResultSet generatedKey= insertOrder.getGeneratedKeys();
            int key= 0;

            if (generatedKey.next()){
                key= generatedKey.getInt(1);
            }

            order.setId(key);

            String sql2= """
                    insert into order_product (id_order, id_product, quantity, sub_total)
                    values
                        (?, ?, ?, ?)""";
            PreparedStatement insertItems= connection.prepareStatement(sql2);
            insertItems.setInt(1, order.getId());
            insertItems.setInt(2, item.getProduct().getCod());
            insertItems.setInt(3, item.getQuantity());
            insertItems.setDouble(4, item.getProduct().getPrice() * item.getQuantity());

            insertItems.executeUpdate();

            connection.close();


        } catch (SQLException e) {
            System.out.println("Erro ao inserir Order pela tela de produto" + e.getMessage());
        }
    }

    /**
     * Consulta um pedido e deleta esse pedido de um determinado cliente.
     * @param login O login do cliente.
     */
    public void deleteOrder(String login){
        try {
            Connection connection= genericDao.getConnection();
            String sql= """
                    select ot.id_order
                    from order_tbl ot inner join order_product op on ot.id_order = op.id_order
                          inner join client cli on ot.user_name_client = cli.user_name
                          left join payment pay on pay.id_order = ot.id_order
                          left join cart ca on ca.id_order = ot.id_order
                    where ca.id_order is null
                        and pay.id_order is null
                        and cli.user_name = ?""";
            PreparedStatement ps= connection.prepareStatement(sql);
            ps.setString(1, login);

            int orderId = 0;
            ResultSet rs= ps.executeQuery();
            if (rs.next()){
                orderId= rs.getInt(1);
            }

            String sql2= "delete from order_product\n" +
                    "where id_order = ?";
            PreparedStatement ps2= connection.prepareStatement(sql2);
            ps2.setInt(1, orderId);

            ps2.executeUpdate();

            String sql3= "delete from order_tbl\n" +
                    "where id_order = ?";
            PreparedStatement ps3= connection.prepareStatement(sql3);
            ps3.setInt(1, orderId);

            ps3.executeUpdate();

            connection.close();


        } catch (SQLException e) {
            System.out.println("Erro ao deletar o pedido apartir da tela de compra!");
        }
    }


}
