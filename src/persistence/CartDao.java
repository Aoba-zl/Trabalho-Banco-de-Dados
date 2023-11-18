package persistence;

import model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CartDao {

    GenericDao genericDao;
    public CartDao(GenericDao genericDao) {
        this.genericDao= genericDao;
    }

    public Order listCart(Client client){
        Order order= new Order();
        try {
            Connection connection= genericDao.getConnection();
            String sql= "select prod.name_product,\n" +
                    "       prod.description,\n" +
                    "       order_prod.quantity,\n" +
                    "       prod.unity_price * order_prod.quantity as Price,\n" +
                    "       prod.id_product,\n" +
                    "       order_tab.id_order,\n" +
                    "       car.total,\n" +
                    "       prod.shipping,\n" +
                    "       prod.total_stock\n" +
                    "from order_tbl order_tab, order_product order_prod, product prod,\n" +
                    "     cart car, client cli\n" +
                    "where order_tab.id_order = order_prod.id_order\n" +
                    "      and order_prod.id_product = prod.id_product\n" +
                    "      and order_tab.id_order = car.id_order\n" +
                    "      and cli.user_name = order_tab.user_name_client\n" +
                    "      and cli.user_name= ?";
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

    public int getOrderId(String login){
        Connection connection= null;
        try {
            connection = genericDao.getConnection();
            String sql= "select car.id_order\n" +
                    "from order_tbl order_tab, cart car, client cli\n" +
                    "where order_tab.id_order = car.id_order\n" +
                    "  and cli.user_name = car.user_name\n" +
                    "  and cli.user_name= ?";
            PreparedStatement ps= connection.prepareStatement(sql);
            ps.setString(1, login);

            ResultSet rs= ps.executeQuery();

            int id = 0;
            while (rs.next()){
                id= rs.getInt(1);
            }

            connection.close();

            return id;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void insertNewOrder(Client client, Item item, Double total){
        Order order= new Order();
        try {
            Connection connection= genericDao.getConnection();
            String sql= "insert into order_tbl (user_name_client)\n" +
                    "values\n" +
                    "    (?)";
            PreparedStatement insertOrder= connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            insertOrder.setString(1, client.getLogin());

            insertOrder.executeUpdate();

            ResultSet generatedKey= insertOrder.getGeneratedKeys();
            int key= 0;

            if (generatedKey.next()){
                key= generatedKey.getInt(1);
            }

            order.setId(key);

            String sql2= "insert into cart (user_name, id_order, total)\n" +
                    "values\n" +
                    "    (?, ?, ?)";
            PreparedStatement insertCart= connection.prepareStatement(sql2);
            insertCart.setString(1, client.getLogin());
            insertCart.setInt(2, order.getId());
            insertCart.setDouble(3, total);

            insertCart.executeUpdate();


            String sql3= "insert into order_product (id_order, id_product, quantity, sub_total)\n" +
                    "values\n" +
                    "    (?, ?, ?, ?)";
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

    public void alterQuantity( int orderId, Item item){
        try {
            Connection connection= genericDao.getConnection();
            String sql= "update order_product\n" +
                    "set\n" +
                    "    quantity = ?,\n" +
                    "    sub_total= ?\n" +
                    "where id_product = ?\n" +
                    "    and id_order = ?";
            PreparedStatement ps= connection.prepareStatement(sql);
            ps.setInt(1, item.getQuantity());
            ps.setDouble(2, item.getQuantity() * item.getProduct().getPrice());
            ps.setInt(3, item.getProduct().getCod());
            ps.setInt(4, orderId);

            ps.executeUpdate();

            connection.close();

        } catch (SQLException e) {
            System.out.println("Erro em alterar quantidade do produto no banco de dados");
            throw new RuntimeException(e);
        }
    }

    public void deleteItem(int idProduct, int idOrder){
        try {
            Connection connection= genericDao.getConnection();
            String sql= "delete from order_product\n" +
                    "where id_product = ?\n" +
                    "    and id_order = ?";
            PreparedStatement ps= connection.prepareStatement(sql);
            ps.setInt(1, idProduct);
            ps.setInt(2, idOrder);

            ps.executeUpdate();

            connection.close();
        } catch (SQLException e) {
            System.out.println("Erro ao apagar o produto no order_product");
            throw new RuntimeException(e);
        }
    }

     public void deleteOrder(int idOrder){
        try {
            Connection connection= genericDao.getConnection();
            String sql= "delete from cart\n" +
                    "where id_order = ?";
            PreparedStatement deleteCart= connection.prepareStatement(sql);
            deleteCart.setInt(1,idOrder);

            deleteCart.executeUpdate();

            String sql3= "delete from order_tbl\n" +
                    "where id_order= ?";
            PreparedStatement deleteOrder= connection.prepareStatement(sql3);
            deleteOrder.setInt(1, idOrder);

            deleteOrder.executeUpdate();

            connection.close();

        } catch (SQLException e) {
            System.out.println("Erro ao deletar order apartir do carrinho!");
            throw new RuntimeException(e);
        }
    }
}
