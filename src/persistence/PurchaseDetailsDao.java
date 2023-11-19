package persistence;

import model.Client;
import model.Item;
import model.Order;

import java.sql.*;

public class PurchaseDetailsDao {

    GenericDao genericDao;
    public PurchaseDetailsDao(GenericDao genericDao) {
        this.genericDao= genericDao;
    }

    public void insertPayment(Order order, Boolean pix){
        try {
            Connection connection= genericDao.getConnection();
            String sql= "insert into payment (id_order, total_pay, date_pay, status)\n" +
                    "values\n" +
                    "    (?, ?, getdate(), 'Preparando')";
            PreparedStatement insertPay= connection.prepareStatement(sql);
            insertPay.setInt(1, order.getId());
            insertPay.setDouble(2, order.getTotal());

            insertPay.executeUpdate();

            if (pix){
                String sqlPix= "insert into pix (id_order, qr_code)\n" +
                        "values\n" +
                        "    (?, ?)";
                PreparedStatement insertPix= connection.prepareStatement(sqlPix);
                insertPix.setInt(1, order.getId());
                insertPix.setString(2, "pixCodigo");

                insertPix.executeUpdate();
            }
            else {
                String sqlPaySlip= "insert into payment_slip (id_order, qr_code)\n" +
                        "values\n" +
                        "    (?, ?)";
                PreparedStatement insertPaySlip= connection.prepareStatement(sqlPaySlip);
                insertPaySlip.setInt(1, order.getId());
                insertPaySlip.setString(2, "boletoCodigo");

                insertPaySlip.executeUpdate();
            }

            connection.close();

            deleteCart(order.getId());
        } catch (SQLException e) {
            System.out.println("Erro ao inserir payment");
            throw new RuntimeException(e);
        }
    }

    public void deleteCart(int idOrder){
        try {
            Connection connection= genericDao.getConnection();
            String sql= "delete from cart\n" +
                    "where id_order = ?";
            PreparedStatement ps= connection.prepareStatement(sql);
            ps.setInt(1, idOrder);

            ps.executeUpdate();

            connection.close();
        } catch (SQLException e) {
            System.out.println("Erro em deletar o Carrinho");
            throw new RuntimeException(e);
        }
    }

    public void insertOrder(Client client, Item item, Boolean pix){
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

            String sql2= "insert into order_product (id_order, id_product, quantity, sub_total)\n" +
                    "values\n" +
                    "    (?, ?, ?, ?)";
            PreparedStatement insertItems= connection.prepareStatement(sql2);
            insertItems.setInt(1, order.getId());
            insertItems.setInt(2, item.getProduct().getCod());
            insertItems.setInt(3, item.getQuantity());
            insertItems.setDouble(4, item.getProduct().getPrice() * item.getQuantity());

            insertItems.executeUpdate();

            if (pix){
                String sqlPix= "insert into pix (id_order, qr_code)\n" +
                        "values\n" +
                        "    (?, ?)";
                PreparedStatement insertPix= connection.prepareStatement(sqlPix);
                insertPix.setInt(1, order.getId());
                insertPix.setString(2, "pixCodigo");

                insertPix.executeUpdate();
            }
            else {
                String sqlPaySlip= "insert into payment_slip (id_order, qr_code)\n" +
                        "values\n" +
                        "    (?, ?)";
                PreparedStatement insertPaySlip= connection.prepareStatement(sqlPaySlip);
                insertPaySlip.setInt(1, order.getId());
                insertPaySlip.setString(2, "boletoCodigo");

                insertPaySlip.executeUpdate();
            }

            connection.close();


        } catch (SQLException e) {
            System.out.println("Erro ao inserir Order e pagamento pelo pagamento");
            throw new RuntimeException(e);
        }
    }
}
