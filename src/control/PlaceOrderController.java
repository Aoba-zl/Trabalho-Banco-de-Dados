package control;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Client;
import model.Item;
import model.Order;
import persistence.CartDao;
import persistence.GenericDao;
import persistence.PurchaseDetailsDao;
import persistence.PurchaseHistoryDao;
import utils.UserSession;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Esta é uma classe de Controller que realiza as operações da tela WinPurchaseDetailsConstruct.
 */
public class PlaceOrderController {

    Client client;

    static Order order;
    private GenericDao genericDao= new GenericDao();


    private StringProperty portage= new SimpleStringProperty("Frete:");

    private StringProperty totalPurchase= new SimpleStringProperty("Total:");

    private ObservableList<Item> itemsList = FXCollections.observableArrayList();

    private CartDao cartDao= new CartDao(genericDao);
    private PurchaseDetailsDao purchaseDetailsDao= new PurchaseDetailsDao(genericDao);

    private static boolean cart;



    /**
     * Carrega um lista de items para tela detalhes de compra e seus Labels
     */
    public void populateWinPurchase(){
        order= purchaseDetailsDao.selectOrder(UserSession.getUserName());

        if (order.getItems() != null){
            cart= false;
            itemsList.add(order.getItems().get(0));
        }
        else {
            order= cartDao.getOrder(UserSession.getUserName());
            if (order.getItems() != null){
                cart= true;
                List<Item> items= order.getItems();
                double portageCal= 0;
                double totalPrice= 0;

                int listSize= items.size();
                for (int i = 0; i < listSize; i++) {
                    Item item= items.get(i);
                    portageCal+= item.getProduct().getShipping();
                    totalPrice+= item.getSubTotal();

                    itemsList.add(item);
                }
                totalPrice+= portageCal;
                DecimalFormat decimalFormat = new DecimalFormat("#0.00");
                String formatedvalue= decimalFormat.format(portageCal);
                String portageTotal= ("R$ " + formatedvalue);

                portage.set("Frete: " + portageTotal);

                formatedvalue= decimalFormat.format(totalPrice);
                String totalValue= ("R$ " + formatedvalue);

                totalPurchase.set("Total: " + totalValue);

            }

        }


    }

    /**
     * Verifica se a tela veio da tela carrinho ou da tela do produto.
     * @return A verificação.
     */
    public static boolean isCart() {
        return cart;
    }

    /**
     * Esvazia a lista de items.
     */
    public void clearItems(){
        itemsList.clear();
    }

    /**
     * Adiciona o pagamento ao pedido, verificando o meio de pagamento.
     * @param pix O meio de pagamento.
     */
    public void placePayment(Boolean pix){
        purchaseDetailsDao.insertPayment(order, pix, cart);
    }

    /**
     * Cria um pedido e seu pagamento, verificando o meio de pagamento de um determinado cliente.
     * @param item O item do pedido.
     */
    public void createOrder(Item item){
        purchaseDetailsDao.insertOrder(UserSession.getUserName(), item);
    }

    public Client getClient(){
        client.setLogin(UserSession.getUserName());
        return client;
    }

    public ObservableList<Item> getItems() {
        return itemsList;
    }



    public StringProperty portageProperty() {
        return portage;
    }

    public StringProperty totalPurchaseProperty() {
        return totalPurchase;
    }
}
