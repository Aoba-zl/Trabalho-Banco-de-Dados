package persistence;

import model.Client;
import model.Item;
import model.Order;

import java.sql.*;
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
     * Inseri o pagamento de um pedido, verificando o meio de pagamento.
     * @param order O pedido.
     * @param pix O meio de pagamento.
     */
    public void insertPayment(Order order, Boolean pix){
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
            deleteCart(order.getId());
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
     * Reduz o stock dos produtos comprados.
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
     * Inseri um pagamento de um pedido de um determinado cliente, verificando o meio de pagamento.
     * @param client O cliente.
     * @param item O item do pedido.
     * @param pix O meio de pagamento.
     */
    public void insertOrder(Client client, Item item, Boolean pix){
        Order order= new Order();
        try {
            Connection connection= genericDao.getConnection();
            String sql= """
                    insert into order_tbl (user_name_client)
                    values
                        (?)""";
            PreparedStatement insertOrder= connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            insertOrder.setString(1, client.getLogin());

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


        } catch (SQLException e) {
            System.out.println("Erro ao inserir Order e pagamento pelo pagamento" + e.getMessage());
        }
    }
}
