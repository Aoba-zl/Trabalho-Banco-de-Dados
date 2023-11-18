package control;

import java.text.DecimalFormat;
import java.util.List;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.*;
import persistence.CartDao;
import persistence.GenericDao;

public class CartController
{
    private Client client;


    private Cart cart;

    private Order order;

    private ObservableList<Item> listCart= FXCollections.observableArrayList();

    private GenericDao genericDao= new GenericDao();
    private CartDao cartDao= new CartDao(genericDao);

    private StringProperty portage= new SimpleStringProperty("Frete:");
    private StringProperty totalCart= new SimpleStringProperty("Total:");



    public void populateWinCart(){
        client= new Client();
        client.setLogin("teste2");

        order= new Order();
        order= cartDao.listCart(client);

        if (order.getItems() != null){
            List<Item> items= order.getItems();
            double portageCal= 0;
            double totalPrice= 0;

            for (int i = 0; i < items.size(); i++) {
                Item item= items.get(i);
                portageCal+= item.getProduct().getShipping();
                totalPrice+= item.getSubTotal();

                listCart.add(items.get(i));
            }
            totalPrice+= portageCal;
            DecimalFormat decimalFormat = new DecimalFormat("#0.00");
            String formatedvalue= decimalFormat.format(portageCal);
            String portageTotal= ("R$ " + formatedvalue);

            portage.set("Frete: " + portageTotal);

            formatedvalue= decimalFormat.format(totalPrice);
            String totalValue= ("R$ " + formatedvalue);

            totalCart.set("Total: " + totalValue);
        }

    }

    public int getOrderID(){
        client= new Client();
        client.setLogin("teste2");

        int orderId= cartDao.getOrderId(client.getLogin());

        return orderId;
    }

    public CartController(Client client) {
        this.client = client;
        this.cart = client.getCart();
    }

    public CartController() {
    }

    public ObservableList<Item> getListCart() {
        return listCart;
    }

    public void deleteOrder () {
        cartDao.deleteOrder(getOrderID());
    }
    
    public double calculateTotal (List<Item> select) {
        double totalSelect = 0;
        for (int i = 0; i < listCart.size(); i++) {
            Item item= listCart.get(i);
            totalSelect+= item.getSubTotal() + item.getProduct().getShipping();
        }
        return totalSelect;
    }
    public void clearCart(Item item) {
        cartDao.deleteItem(item.getProduct().getCod(), getOrderID());
        if (listCart.size() == 1){
            deleteOrder();
        }
    }
    
    public void alterQuantity (Item item) {
        cartDao.alterQuantity(getOrderID(), item);
        listCart.clear();
        populateWinCart();
    }
    
    public void placeOrder (Client client, Item item) {
        cartDao.insertNewOrder(client, item, calculateTotal(listCart));
        populateWinCart();
    }
    
    private Order createPedido () {
        Order newOrder = new Order();
        return newOrder;
    }

    public StringProperty portageProperty() {
        return portage;
    }

    public StringProperty totalCartProperty() {
        return totalCart;
    }
}
