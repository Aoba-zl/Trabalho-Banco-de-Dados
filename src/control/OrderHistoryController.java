package control;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.scene.control.cell.TextFieldTableCell;
import model.*;
import persistence.GenericDao;
import persistence.PurchaseHistoryDao;
import view.WinPurchaseHistoryConstructor;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderHistoryController extends TextFieldTableCell<Order, List<Item>> {

    private ObservableList<Order> listHistory= FXCollections.observableArrayList();
    private StringProperty nameProduct= new SimpleStringProperty("");
    private StringProperty nameStore= new SimpleStringProperty("");
    private IntegerProperty quantity= new SimpleIntegerProperty(0);
    private StringProperty priceProduct= new SimpleStringProperty("00.00");
    private StringProperty methodPayment= new SimpleStringProperty("");
    private ObjectProperty<LocalDate> dateDelivery= new SimpleObjectProperty<>();
    private StringProperty totalValue= new SimpleStringProperty("00.00");
    private StringProperty portage = new SimpleStringProperty("00.00");
    private StringProperty status= new SimpleStringProperty("");
    private StringProperty search= new SimpleStringProperty("");

    public ObservableList populateWinHistory() throws SQLException {
        Client client= new Client();
        client.setLogin("teste1");
        GenericDao genericDao= new GenericDao();
        PurchaseHistoryDao purchaseHistoryDao= new PurchaseHistoryDao(genericDao);

        List<Order> listOrder= purchaseHistoryDao.listPurchaseHistory(client);

        if (listOrder != null){
            for (int i = 0; i < listOrder.size(); i++) {
                Order order= (Order) listOrder.get(i);

                listHistory.add(order);
            }
        }
        return listHistory;
    }

    public void populateWinStatus(Order order) throws SQLException {
        List<Item> itemList= order.getItems();
        Item item= itemList.get(0);

        Integer idOrder= order.getId();
        Integer idProduct= item.getProduct().getCod();
        GenericDao genericDao= new GenericDao();
        PurchaseHistoryDao purchaseHistoryDao= new PurchaseHistoryDao(genericDao);
        order= purchaseHistoryDao.returnStatusProduct(idOrder, idProduct);
        Store store= purchaseHistoryDao.returnNameStore(idOrder, idProduct);

        itemList= order.getItems();
        item= itemList.get(0);

        DecimalFormat decimalFormat = new DecimalFormat("#0.00");

        nameProduct.set(item.getProduct().getName());
        nameStore.set(store.getNameStore());
        quantity.set(item.getQuantity());
        String formatedValue= decimalFormat.format(item.getProduct().getPrice());
        priceProduct.set("R$ " +formatedValue);
        methodPayment.set(order.getPayment().getPaymentMethod());
        dateDelivery.set(order.getPayment().getDate());
        formatedValue= decimalFormat.format(item.getSubTotal());
        totalValue.set("R$ " +formatedValue);
        status.set(order.getPayment().getStatus());
        formatedValue= decimalFormat.format(item.getProduct().getShipping());
        portage.set("R$ " + formatedValue);



    }




    public ObservableList<Order> getListHistory() {
        return listHistory;
    }


    public StringProperty nameProductProperty() {
        return nameProduct;
    }

    public StringProperty nameStoreProperty() {
        return nameStore;
    }

    public IntegerProperty quantityProperty() {
        return quantity;
    }

    public StringProperty priceProductProperty() {
        return priceProduct;
    }

    public StringProperty methodPaymentProperty() {
        return methodPayment;
    }

    public ObjectProperty<LocalDate> dateDeliveryProperty() {
        return dateDelivery;
    }

    public StringProperty totalValueProperty() {
        return totalValue;
    }

    public StringProperty portageProperty() {
        return portage;
    }

    public StringProperty searchProperty() {
        return search;
    }

    public StringProperty statusProperty() {
        return status;
    }
}
