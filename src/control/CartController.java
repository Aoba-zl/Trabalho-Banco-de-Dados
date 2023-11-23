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

/**
 * Esta é uma classe de Controller que realiza as operações da classe WinShoppingCartConstructor.
 */
public class CartController {
    private Client client;


    private Cart cart;

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
        client.setLogin("teste2"); //todo alterar -------------------

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

            cartDao.setTotalCart(order.getId(), totalPrice);
        }

    }

    /**
     * Obtem o codigo e total do pedido.
     * @return O pedido.
     */
    public Order getOrder(){
        client= new Client();
        client.setLogin("teste2"); //todo alterar ---------

        order= cartDao.getOrder(client.getLogin());

        return order;
    }

    public CartController(Client client) {
        this.client = client;
        this.cart = client.getCart();
    }

    public CartController() {
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
        if (listCart.size() == 1){
            deleteOrder();
        }
    }

    /**
     * Deleta o pedido.
     */
    public void deleteOrder () {
        cartDao.deleteOrder(getOrder());
    }

    public double calculateTotal (List<Item> select) {
        double totalSelect = 0;
        for (int i = 0; i < listCart.size(); i++) {
            Item item= listCart.get(i);
            totalSelect+= item.getSubTotal() + item.getProduct().getShipping();
        }
        return totalSelect;
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
