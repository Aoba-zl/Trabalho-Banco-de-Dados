package persistence;

import model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PurchaseHistoryDao {

    GenericDao genericDao;

    public PurchaseHistoryDao(GenericDao genericDao) {
        this.genericDao= genericDao;
    }

    public List<Order> listPurchaseHistory(Client client) throws SQLException{
        List<Order> history= new ArrayList<>();
        Connection connection= genericDao.getConnection();
        String sql= "select prod.id_product,\n" +
                "       prod.name_product,\n" +
                "       pay.date_pay as Data_de_entrega,\n" +
                "       case when (getdate() + 1 = pay.date_pay)\n" +
                "           then\n" +
                "                'Chega amanhã'\n" +
                "           else\n" +
                "                'A caminho'\n" +
                "           end as status\n" +
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
            Order order= new Order();

            Product product= new Product();
            product.setCod(rs.getInt(1));
            product.setName(rs.getString(2));

            Item item= new Item();
            item.setProduct(product);
            List<Item> itemList= new ArrayList<>();
            itemList.add(item);

            Payment payment= new Payment();
            payment.setDate(rs.getDate(3).toLocalDate());
            payment.setStatus(rs.getString(4));

            order.setItems(itemList);
            order.setPayment(payment);

            history.add(order);
        }
        connection.close();
        rs.close();
        ps.close();

        return history;
    }

//    public
}
