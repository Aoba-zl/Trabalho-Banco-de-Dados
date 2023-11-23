package persistence;

import model.*;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Esta é uma classe de Persistence que realiza as operações com o banco de dados.
 */
public class OrderHistoryDao {

    GenericDao genericDao;

    /**
     * Adquire a conexão com o banco de dados.
     * @param genericDao A conexão com o banco de dados.
     */
    public OrderHistoryDao(GenericDao genericDao) {
        this.genericDao= genericDao;
    }

    /**
     * Retorna o histórico de pedidos de um determinado vendedor
     * @param store A loja
     * @return O histórico de pedidos
     * @throws SQLException Exceções de SQL
     */
    public List<Order> listOrderHistory(Store store) throws SQLException {
        List<Order> listHistory= new ArrayList<>();
        Connection connection= genericDao.getConnection();
        String sql= """
                select order_tb.id_order,
                       prod.name_product,
                       pay.date_pay,
                       case when (day(getdate()) - day(pay.date_pay) >= 5)
                                then
                                'Finalizado'
                            when (day(getdate()) - day(pay.date_pay) = 4)
                                then
                                'Chega amanhã'
                            when (getdate() = pay.date_pay)
                                then
                                'Preparando'
                            else
                                'A caminho'
                           end as status
                from payment pay, product prod, order_product order_prod, order_tbl order_tb
                where prod.id_product = order_prod.id_product
                  and order_prod.id_order = order_tb.id_order
                  and order_tb.id_order = pay.id_order
                  and prod.user_name = ?""";
        PreparedStatement ps= connection.prepareStatement(sql);
        ps.setString(1, store.getNameStore());
        ResultSet rs= ps.executeQuery();
        while (rs.next()){
            Order order= new Order();

            Product product= new Product();
            order.setId(rs.getInt(1));
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

            listHistory.add(0, order);
        }
        connection.close();
        rs.close();
        ps.close();

        return listHistory;
    }
}
