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
import utils.UserSession;

/**
 * Esta é uma classe de Controller que realiza as operações da classe WinShoppingCartConstructor.
 */
public class CartController {
    private Client client;
    private Order order;

    private ObservableList<Item> listCart= FXCollections.observableArrayList();

    private GenericDao genericDao= new GenericDao();
    private CartDao cartDao= new CartDao(genericDao);

    private StringProperty portage= new SimpleStringProperty("Frete:");
    private StringProperty totalCart= new SimpleStringProperty("Total:");


    /**
     * Carrega os dados para tela do carrinho de compras.
     */
    public void populateWinCart(){
        client= new Client();
        client.setLogin(UserSession.getUserName());

        order= new Order();
        order= cartDao.listCart(client);

        if (order.getItems() != null){
            List<Item> items= order.getItems();

            int listSize= items.size();
            for (int i = 0; i < listSize; i++) {
                Item item= items.get(i);

                listCart.add(items.get(i));
            }

            DecimalFormat decimalFormat = new DecimalFormat("#0.00");
            String formatedvalue= decimalFormat.format(order.getTotalPortage());
            String portageTotal= ("R$ " + formatedvalue);

            portage.set("Frete: " + portageTotal);

            formatedvalue= decimalFormat.format(order.getTotal());
            String totalValue= ("R$ " + formatedvalue);

            totalCart.set("Total: " + totalValue);

            cartDao.setTotalCart(order.getId(), order.getTotal());
        }

    }

    /**
     * Obtem o codigo e total do pedido.
     * @return O pedido.
     */
    public Order getOrder(){
        client= new Client();
        client.setLogin(UserSession.getUserName());

        order= cartDao.getOrder(client.getLogin());

        return order;
    }

    /**
     * Obtêm o Id de um pedido.
     * @return O pedido.
     */
    public Order getIdOrder(){
        order= cartDao.getIdOrder(UserSession.getUserName());
        return order;
    }

    /**
     * Obtêm a lista de items do pedido.
     * @return A lista de items.
     */
    public ObservableList<Item> getListCart() {
        return listCart;
    }

    /**
     * Remove um item do carrinho.
     * @param item O item.
     */
    public void clearCart(Item item) {
        cartDao.deleteItem(item.getProduct().getCod(), getOrder());
        listCart.clear();
        populateWinCart();
        if (listCart.isEmpty()){
            deleteOrder();
        }
    }

    /**
     * Deleta o pedido.
     */
    public void deleteOrder () {
        cartDao.deleteOrder(getIdOrder());
        portage.set("Frete:");
        totalCart.set("Total:");
    }

    /**
     * Altera a quantidade do item no carrinho.
     * @param item O item.
     */
    public void alterQuantity (Item item) {
        cartDao.alterQuantity(getOrder(), item);
        listCart.clear();
        populateWinCart();
    }

    /**
     * Adiciona um novo item ao carrinho.
     * @param item O item.
     */
    public void placeOrder (Item item) {
        cartDao.insertNewItem(getOrder(), item);
    }

    /**
     * Cria um novo pedido de um determinado cliente.
     * @param client O cliente.
     * @param item O item.
     */
    public void createOrder (Client client, Item item) {
        cartDao.insertNewOrder(client, item);
        populateWinCart();
    }

    /**
     * Obtêm o campo de frete.
     * @return o campo frete.
     */
    public StringProperty portageProperty() {
        return portage;
    }

    /**
     * Obtêm o campo de valor total.
     * @return o campo total.
     */
    public StringProperty totalCartProperty() {
        return totalCart;
    }
}
