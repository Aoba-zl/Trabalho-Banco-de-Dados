package control;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.TextFieldTableCell;
import model.*;
import persistence.GenericDao;
import persistence.PurchaseHistoryDao;

import java.awt.*;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class OrderHistoryController extends TextFieldTableCell<Order, List<Item>> {

    private ObservableList<Order> listHistory= FXCollections.observableArrayList();
    private StringProperty nameProduct= new SimpleStringProperty("");
    private StringProperty nameStore= new SimpleStringProperty("");
    private IntegerProperty quantity= new SimpleIntegerProperty(0);
    private DoubleProperty priceProduct= new SimpleDoubleProperty(0);
    private StringProperty methodPayment= new SimpleStringProperty("");
    private ObjectProperty<Date> dateDelivery= new SimpleObjectProperty<>();
    private StringProperty totalValue= new SimpleStringProperty("");
    private StringProperty portage = new SimpleStringProperty("");
    private StringProperty status= new SimpleStringProperty("");
    private StringProperty search= new SimpleStringProperty("");

    public void populateWinHistory() throws SQLException {
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

    public DoubleProperty priceProductProperty() {
        return priceProduct;
    }

    public StringProperty methodPaymentProperty() {
        return methodPayment;
    }

    public ObjectProperty<Date> dateDeliveryProperty() {
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
