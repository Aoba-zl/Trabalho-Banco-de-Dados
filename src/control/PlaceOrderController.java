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

public class PlaceOrderController {

    Client client;
    private GenericDao genericDao= new GenericDao();

    private PurchaseDetailsDao purchaseDetailsDao= new PurchaseDetailsDao(genericDao);

    private StringProperty portage= new SimpleStringProperty("Frete:");

    private StringProperty totalPurchase= new SimpleStringProperty("Total:");


    public void populateWinPurchase(List<Item> listItems){
        if (!listItems.isEmpty()){
            double portageCal= 0;
            double totalPrice= 0;

            for (int i = 0; i < listItems.size(); i++) {
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

    public void placePayment(Order order, Boolean pix){
        purchaseDetailsDao.insertPayment(order, pix);
    }

    public void createOrderAndPayment(Client client, Item item, Boolean pix){
        purchaseDetailsDao.insertOrder(client, item, pix);
    }

    public Client getClient(){
        client.setLogin();
        return client;
    }



    public StringProperty portageProperty() {
        return portage;
    }

    public StringProperty totalPurchaseProperty() {
        return totalPurchase;
    }
}
