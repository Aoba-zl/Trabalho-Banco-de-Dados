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
public class PurchaseHistoryDao {

    GenericDao genericDao;

    /**
     * Adquire a conexão com o banco de dados.
     * @param genericDao A conexão com o banco de dados.
     */
    public PurchaseHistoryDao(GenericDao genericDao) {
        this.genericDao= genericDao;
    }

    /**
     * Retorna o histórico de compra de um determinado cliente.
     * @param client O cliente.
     * @return O historico de compra.
     * @throws SQLException Exceções de SQL.
     */
    public List<Order> listPurchaseHistory(Client client) throws SQLException{
        List<Order> history= new ArrayList<>();
        Connection connection= genericDao.getConnection();
        String sql= """
                select order_tb.id_order,
                       prod.name_product,
                       dateadd(day, 5, pay.date_pay) as Data_de_entrega,
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
                           end as status,\s
                           prod.id_product\s
                from payment pay, product prod, order_product order_prod, order_tbl order_tb,
                     client cli
                where prod.id_product = order_prod.id_product
                        and order_prod.id_order = order_tb.id_order
                        and order_tb.id_order = pay.id_order
                        and cli.user_name = order_tb.user_name_client
                        and cli.user_name = ?""";
        PreparedStatement ps= connection.prepareStatement(sql);
        ps.setString(1, client.getLogin());
        ResultSet rs= ps.executeQuery();

        while (rs.next()){
            List<Item> itemList= new ArrayList<>();
            Order order= new Order();

            Product product= new Product();
            order.setId(rs.getInt(1));
            product.setName(rs.getString(2));
            product.setCod(rs.getInt(5));

            Item item= new Item();
            item.setProduct(product);
            itemList.add(item);

            Payment payment= new Payment();
            payment.setDate(rs.getDate(3).toLocalDate());
            payment.setStatus(rs.getString(4));

            order.setItems(itemList);
            order.setPayment(payment);

            history.add(0, order);
        }
        connection.close();
        rs.close();
        ps.close();

        return history;
    }

    /**
     * Retorna o status de uma determinada compra de um produto, contendo nome, preço, quantidade, frete...
     * @param idOrder O codigo da compra.
     * @param idProduct O codigo do produto.
     * @return O status do produto.
     * @throws SQLException Exceções de SQL.
     */
    public Order returnStatusProduct(Integer idOrder, Integer idProduct) throws SQLException {
        Order order= new Order();
        Connection connection= genericDao.getConnection();
        String sql= """
                select prod.name_product,
                       prod.unity_price,
                       prod.shipping,
                       order_prod.quantity,
                       prod.shipping + prod.unity_price as Valor_Total_Produto,
                       case when (p.id_order = pay.id_order )
                                then
                                'Pix'
                                else
                                'Boleto'
                                end as Payment_Method,
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
                from product prod inner join order_product order_prod on prod.id_product = order_prod.id_product
                     inner join order_tbl order_tb on order_tb.id_order = order_prod.id_order
                     inner join payment pay on pay.id_order = order_tb.id_order
                     left outer join pix p on pay.id_order = p.id_order
                     left outer join payment_slip ps on ps.id_order = pay.id_order
                where pay.id_order= ? and prod.id_product= ?""";
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

    /**
     * Retorna o nome da loja do produto comprado.
     * @param idOrder O codigo da compra.
     * @param idProduct O codigo do produto.
     * @return A loja.
     * @throws SQLException Exceções de SQL.
     */
    public Store returnNameStore(Integer idOrder, Integer idProduct) throws SQLException {
        Store store= new Store();
        Connection connection= genericDao.getConnection();
        String sql= """
                select prod.user_name as store_name
                from product prod inner join order_product order_prod on prod.id_product = order_prod.id_product
                                  inner join order_tbl order_tb on order_tb.id_order = order_prod.id_order
                                  inner join payment pay on pay.id_order = order_tb.id_order,
                     payment pay2 inner join pix p on p.id_order = pay2.id_order,
                     payment pay3 inner join payment_slip pay_slip on pay_slip.id_order = pay3.id_order
                where pay.id_order= ? and prod.id_product= ?""";
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
