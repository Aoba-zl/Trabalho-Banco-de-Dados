package control;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Client;
import model.Item;
import model.Order;
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
    private GenericDao genericDao= new GenericDao();

    private PurchaseDetailsDao purchaseDetailsDao= new PurchaseDetailsDao(genericDao);

    private StringProperty portage= new SimpleStringProperty("Frete:");

    private StringProperty totalPurchase= new SimpleStringProperty("Total:");

    private ObservableList<Item> items= FXCollections.observableArrayList();

    /**
     * Carrega um lista de items para tela detalhes de compra e seus Labels
     */
    public void populateWinPurchase(){
        if (!items.isEmpty()){
            double portageCal= 0;
            double totalPrice= 0;

            int listsize= items.size();
            for (int i = 0; i < listsize; i++) {
                Item item= items.get(i);
                portageCal+= item.getProduct().getShipping();
                totalPrice+= item.getSubTotal();
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

    /**
     * Verifica se a tela veio da tela carrinho ou da tela do produto.
     * @return A verificação.
     */
    public Boolean cart(){
        if (items.size() > 1){
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Esvazia a lista de items.
     */
    public void clearItems(){
        items.clear();
    }

    /**
     * Adiciona o pagamento ao pedido, verificando o meio de pagamento.
     * @param order O pedido.
     * @param pix O meio de pagamento.
     */
    public void placePayment(Order order, Boolean pix){
        purchaseDetailsDao.insertPayment(order, pix);
    }

    /**
     * Cria um pedido e seu pagamento, verificando o meio de pagamento de um determinado cliente.
     * @param client O cliente.
     * @param item O item do pedido.
     * @param pix O meio de pagamento.
     */
    public void createOrderAndPayment(String username, Item item, Boolean pix){
        purchaseDetailsDao.insertOrder(username, item, pix);
    }

    public Client getClient(){
        client.setLogin(UserSession.getUserName());
        return client;
    }

    public ObservableList<Item> getItems() {
        return items;
    }

    public void setItems(ObservableList<Item> items) {
        this.items = items;
    }

    public StringProperty portageProperty() {
        return portage;
    }

    public StringProperty totalPurchaseProperty() {
        return totalPurchase;
    }
}
