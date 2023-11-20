package control;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
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

    /**
     * Carrega um lista de items para tela detalhes de compra.
     * @param listItems A lista.
     */
    public void populateWinPurchase(List<Item> listItems){
        if (!listItems.isEmpty()){
            double portageCal= 0;
            double totalPrice= 0;

            int listsize= listItems.size();
            for (int i = 0; i < listsize; i++) {
                Item item= listItems.get(i);
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
    public void createOrderAndPayment(Client client, Item item, Boolean pix){
        purchaseDetailsDao.insertOrder(client, item, pix);
    }

    public Client getClient(){
//        client.setLogin(); todo adicionar metodo para pegar login
        return client;
    }



    public StringProperty portageProperty() {
        return portage;
    }

    public StringProperty totalPurchaseProperty() {
        return totalPurchase;
    }
}
