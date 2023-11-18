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
        String sql= "select order_tb.id_order,\n" +
                "       prod.name_product,\n" +
                "       dateadd(day, 5, pay.date_pay) as Data_de_entrega,\n" +
                "       case when (day(getdate()) - day(pay.date_pay) >= 5)\n" +
                "           then\n" +
                "                'Finalizado'\n" +
                "           when (day(getdate()) - day(pay.date_pay) = 4)\n" +
                "           then\n" +
                "                'Chega amanhã'\n" +
                "           when (getdate() = pay.date_pay)\n" +
                "           then\n" +
                "                'Preparando'\n" +
                "           else\n" +
                "                'A caminho'\n" +
                "           end as status, \n" +
                "           prod.id_product \n" +
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

        List<Item> itemList= new ArrayList<>();
        while (rs.next()){
            Order order= new Order();

            Product product= new Product();
            order.setId(rs.getInt(1));
            product.setName(rs.getString(2));

            Item item= new Item();
            item.setProduct(product);
            itemList.add(item);

            Payment payment= new Payment();
            payment.setDate(rs.getDate(3).toLocalDate());
            payment.setStatus(rs.getString(4));
            product.setCod(rs.getInt(5));

            order.setItems(itemList);
            order.setPayment(payment);

            history.add(0, order);
        }
        connection.close();
        rs.close();
        ps.close();

        return history;
    }

    public Order returnStatusProduct(Integer idOrder, Integer idProduct) throws SQLException {
        Order order= new Order();
        Connection connection= genericDao.getConnection();
        String sql= "select prod.name_product,\n" +
                "       prod.unity_price,\n" +
                "       prod.shipping,\n" +
                "       order_prod.quantity,\n" +
                "       prod.shipping + prod.unity_price as Valor_Total_Produto,\n" +
                "       case when (p.id_order = pay.id_order )\n" +
                "                then\n" +
                "                'Pix'\n" +
                "                else\n" +
                "                'Boleto'\n" +
                "                end as Payment_Method,\n" +
                "       case when (day(getdate()) - day(pay.date_pay) >= 5)\n" +
                "                then\n" +
                "                'Finalizado'\n" +
                "            when (day(getdate()) - day(pay.date_pay) = 4)\n" +
                "                then\n" +
                "                'Chega amanhã'\n" +
                "            when (getdate() = pay.date_pay)\n" +
                "                then\n" +
                "                'Preparando'\n" +
                "            else\n" +
                "                'A caminho'\n" +
                "           end as status\n" +
                "from product prod inner join order_product order_prod on prod.id_product = order_prod.id_product\n" +
                "     inner join order_tbl order_tb on order_tb.id_order = order_prod.id_order\n" +
                "     inner join payment pay on pay.id_order = order_tb.id_order\n" +
                "     left outer join pix p on pay.id_order = p.id_order\n" +
                "     left outer join payment_slip ps on ps.id_order = pay.id_order\n" +
                "where pay.id_order= ? and prod.id_product= ?";
        PreparedStatement ps= connection.prepareStatement(sql);
        ps.setInt(1, idOrder);
        ps.setInt(2, idProduct);
        ResultSet rs= ps.executeQuery();

        while (rs.next()){

            Product product= new Product();
            product.setName(rs.getString(1));
            product.setPrice(rs.getDouble(2));
            product.setShipping(rs.getDouble(3));

            Item item= new Item();
            item.setProduct(product);
            item.setQuantity(rs.getInt(4));
            item.setSubTotal(rs.getDouble(5));
            List<Item> items= new ArrayList<>();
            items.add(item);

            Payment payment= new Payment();
            payment.setPaymentMethod(rs.getString(6));
            payment.setStatus(rs.getString(7));

            order.setPayment(payment);
            order.setItems(items);
        }

        connection.close();
        rs.close();
        ps.close();


        return order;
    }

    public Store returnNameStore(Integer idOrder, Integer idProduct) throws SQLException {
        Store store= new Store();
        Connection connection= genericDao.getConnection();
        String sql= "select prod.user_name as store_name\n" +
                "from product prod inner join order_product order_prod on prod.id_product = order_prod.id_product\n" +
                "                  inner join order_tbl order_tb on order_tb.id_order = order_prod.id_order\n" +
                "                  inner join payment pay on pay.id_order = order_tb.id_order,\n" +
                "     payment pay2 inner join pix p on p.id_order = pay2.id_order,\n" +
                "     payment pay3 inner join payment_slip pay_slip on pay_slip.id_order = pay3.id_order\n" +
                "where pay.id_order= ? and prod.id_product= ?";
        PreparedStatement ps= connection.prepareStatement(sql);
        ps.setInt(1, idOrder);
        ps.setInt(2, idProduct);
        ResultSet rs= ps.executeQuery();

        while (rs.next()){
            store.setNameStore(rs.getString(1));
        }


        connection.close();
        ps.close();
        rs.close();

        return store;
    }
}
