package persistence;

import model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Esta é uma classe de Persistence que realizar as operações com o banco de dados.
 */
public class CartDao {

    GenericDao genericDao;

    /**
     * Adquire a conexão com o banco de dados.
     * @param genericDao A conexão com o banco de dados.
     */
    public CartDao(GenericDao genericDao) {
        this.genericDao= genericDao;
    }

    /**
     * Retorna o pedido que esta armazenado no carrinho de um determinado cliente.
     * @param client O cliente
     * @return O pedido.
     */
    public Order listCart(Client client){
        Order order= new Order();
        try {
            Connection connection= genericDao.getConnection();
            String sql= """
                    select prod.name_product,
                           prod.description,
                           order_prod.quantity,
                           prod.unity_price * order_prod.quantity as Price,
                           prod.id_product,
                           order_tab.id_order,
                           car.total,
                           prod.shipping,
                           prod.total_stock
                    from order_tbl order_tab, order_product order_prod, product prod,
                         cart car, client cli
                    where order_tab.id_order = order_prod.id_order
                          and order_prod.id_product = prod.id_product
                          and order_tab.id_order = car.id_order
                          and cli.user_name = order_tab.user_name_client
                          and cli.user_name= ?""";
            PreparedStatement ps= connection.prepareStatement(sql);
            ps.setString(1, client.getLogin());
            ResultSet rs= ps.executeQuery();

            List<Item> items= new ArrayList<>();
            while (rs.next()){
                Product product= new Product();
                Item item= new Item();

                product.setName(rs.getString(1));
                product.setDescription(rs.getString(2));
                product.setCod(rs.getInt(5));
                product.setShipping(rs.getDouble(8));
                product.setTotalStock(rs.getInt(9));
                item.setProduct(product);
                item.setQuantity(rs.getInt(3));
                item.setSubTotal(rs.getDouble(4));

                items.add(0, item);

                order.setId(rs.getInt(6));
                order.setItems(items);
                order.setTotal(rs.getDouble(7));

            }

            connection.close();

            return order;
        } catch (SQLException e) {
            System.out.println("Erro ao carregar os dados do banco de dados no carrinho!");
            throw new RuntimeException(e);
        }
    }

    /**
     * Obtêm o pedido de um determinado cliente.
     * @param login O username do cliente.
     * @return O pedido.
     */
    public Order getOrder(String login){
        Connection connection= null;
        try {
            connection = genericDao.getConnection();
            String sql= """
                    select order_prod.quantity,
                           prod.unity_price * order_prod.quantity as Price,
                           prod.id_product,
                           order_tab.id_order,
                           prod.shipping,
                           prod.total_stock
                    from order_tbl order_tab, order_product order_prod, product prod,
                         cart car, client cli
                    where order_tab.id_order = order_prod.id_order
                      and order_prod.id_product = prod.id_product
                      and order_tab.id_order = car.id_order
                      and cli.user_name = order_tab.user_name_client
                      and cli.user_name= ?""";
            PreparedStatement ps= connection.prepareStatement(sql);
            ps.setString(1, login);

            ResultSet rs= ps.executeQuery();

            Order order= new Order();
            List<Item> items= new ArrayList<>();
            while (rs.next()){
                Product product= new Product();
                Item item= new Item();
                item.setQuantity(1);
                item.setSubTotal(2);
                product.setCod(3);
                order.setId(rs.getInt(4));
                product.setShipping(5);
                product.setTotalStock(6);

                item.setProduct(product);
                items.add(0, item);

            }
            order.setItems(items);


            String sql2= """
                    select SUM((prod.unity_price * order_prod.quantity) + prod.shipping) as Price
                    from order_tbl order_tab, order_product order_prod, product prod,
                         cart car, client cli
                    where order_tab.id_order = order_prod.id_order
                      and order_prod.id_product = prod.id_product
                      and order_tab.id_order = car.id_order
                      and cli.user_name = order_tab.user_name_client
                      and cli.user_name= ?""";
            PreparedStatement ps2= connection.prepareStatement(sql2);
            ps2.setString(1, login);
            ResultSet rs2= ps2.executeQuery();

            while (rs2.next()){
                order.setTotal(rs2.getDouble(1));
            }

            connection.close();

            return order;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Inseri o total do pedido no total do carrinho.
     * @param orderId O codigo do pedido.
     * @param total O total do pedido.
     */
    public void setTotalCart(int orderId, double total){
        try {
            Connection connection= genericDao.getConnection();
            String sql= """
                    update cart
                    set
                        total= ?
                    where id_order = ?
                    """;
            PreparedStatement ps= connection.prepareStatement(sql);
            ps.setDouble(1, total);
            ps.setInt(2, orderId);

            ps.executeUpdate();

            connection.close();
        } catch (SQLException e) {
            System.out.println("Erro em inserir total do carrinho");
            throw new RuntimeException(e);
        }
    }

    /**
     * Inseri um novo pedido de um determinado cliente.
     * @param client O cliente.
     * @param item O item.
     */
    public void insertNewOrder(Client client, Item item){
        Order order= new Order();
        try {
            Connection connection= genericDao.getConnection();
            String sql= """
                    insert into order_tbl (user_name_client)
                    values
                        (?)
                        """;
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
                    insert into cart (user_name, id_order, total)
                    values
                        (?, ?, ?)
                        """;
            PreparedStatement insertCart= connection.prepareStatement(sql2);
            insertCart.setString(1, client.getLogin());
            insertCart.setInt(2, order.getId());
            insertCart.setDouble(3, item.getSubTotal());

            insertCart.executeUpdate();


            String sql3= """
                    insert into order_product (id_order, id_product, quantity, sub_total)
                    values
                       (?, ?, ?, ?)
                       """;
            PreparedStatement insertItems= connection.prepareStatement(sql3);
            insertItems.setInt(1, order.getId());
            insertItems.setInt(2, item.getProduct().getCod());
            insertItems.setInt(3, item.getQuantity());
            insertItems.setDouble(4, item.getProduct().getPrice() * item.getQuantity());

            insertItems.executeUpdate();



            connection.close();


        } catch (SQLException e) {
            System.out.println("Erro ao inserir nova order pelo carrinho!");
            throw new RuntimeException(e);
        }
    }

    /**
     * Inseri um novo item ao pedido.
     * @param order O pedido.
     * @param item O item.
     */
    public void insertNewItem(Order order, Item item){
        try {
            Connection connection= genericDao.getConnection();
            String sql= """
                    insert into order_product (id_order, id_product, quantity, sub_total)
                    values
                        (?, ?, ?, ?)
                    """;
            PreparedStatement insertOrder= connection.prepareStatement(sql);
            insertOrder.setInt(1, order.getId());
            insertOrder.setInt(2, item.getProduct().getCod());
            insertOrder.setInt(3, item.getQuantity());
            insertOrder.setDouble(4, item.getProduct().getPrice() * item.getQuantity());

            insertOrder.executeUpdate();



            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Altera a quantidade de um item ao pedido.
     * @param order O pedido.
     * @param item O item.
     */
    public void alterQuantity(Order order, Item item){
        try {
            Connection connection= genericDao.getConnection();
            String sql= """
                    update order_product
                    set
                        quantity = ?,
                        sub_total= ?
                    where id_product = ?
                        and id_order = ?
                        """;
            PreparedStatement ps= connection.prepareStatement(sql);
            ps.setInt(1, item.getQuantity());
            ps.setDouble(2, item.getQuantity() * item.getProduct().getPrice());
            ps.setInt(3, item.getProduct().getCod());
            ps.setInt(4, order.getId());

            ps.executeUpdate();



        } catch (SQLException e) {
            System.out.println("Erro em alterar quantidade do produto no banco de dados");
            throw new RuntimeException(e);
        }
    }

    /**
     * Deleta um produto do item de um pedido.
     * @param idProduct O codigo do produto.
     * @param order O pedido.
     */
    public void deleteItem(int idProduct, Order order){
        try {
            Connection connection= genericDao.getConnection();
            String sql= """
                    delete from order_product
                    where id_product = ?
                        and id_order = ?""";
            PreparedStatement ps= connection.prepareStatement(sql);
            ps.setInt(1, idProduct);
            ps.setInt(2, order.getId());

            ps.executeUpdate();

            connection.close();
        } catch (SQLException e) {
            System.out.println("Erro ao apagar o produto no order_product");
            throw new RuntimeException(e);
        }
    }

    /**
     * Deleta um pedido.
     * @param order O pedido.
     */
     public void deleteOrder(Order order){
        try {
            Connection connection= genericDao.getConnection();
            String sql= "delete from cart\n" +
                    "where id_order = ?";
            PreparedStatement deleteCart= connection.prepareStatement(sql);
            deleteCart.setInt(1,order.getId());

            deleteCart.executeUpdate();

            String sql3= "delete from order_tbl\n" +
                    "where id_order= ?";
            PreparedStatement deleteOrder= connection.prepareStatement(sql3);
            deleteOrder.setInt(1, order.getId());

            deleteOrder.executeUpdate();

            connection.close();

        } catch (SQLException e) {
            System.out.println("Erro ao deletar order apartir do carrinho!");
            throw new RuntimeException(e);
        }
    }
}