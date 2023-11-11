package persistence;

import model.Client;
import model.Payment;
import model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PurchaseHistoryDao {

    GenericDao genericDao;

    public List listPurchaseHistory(Client client) throws SQLException{
        List history= new ArrayList();
        Connection connection= genericDao.getConnection();
        String sql= "select prod.id_product, prod.name_product, pay.date_pay, pay.status\n" +
                "from payment pay, product prod, order_product order_prod, order_tbl order_tb,\n" +
                "     client cli\n" +
                "where prod.id_product = order_prod.id_product\n" +
                "        and order_prod.id_order = order_tb.id_order\n" +
                "        and order_tb.id_order = pay.id_order\n" +
                "        and cli.user_name = order_tb.user_name_client\n" +
                "        and cli.user_name = ?";
        PreparedStatement ps= connection.prepareStatement(sql);
        ps.setString(1, client.getLogin());
        ResultSet rs= ps.executeQuery();
        while (rs.next()){

        }
        connection.close();
        return history;
    }

//    public
}
