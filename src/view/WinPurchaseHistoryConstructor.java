package view;

import control.OrderHistoryController;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.util.StringConverter;
import javafx.util.converter.NumberStringConverter;
import model.Order;

import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Date;


public class WinPurchaseHistoryConstructor {
    private final Button btnSearch= new Button("🔍");
    private final Button btnSeePurchase= new Button("Ver Compra");
    private final TextField tfSearch= new TextField();
    private final Label lblTitle= new Label("Histórico de Compra");

    OrderHistoryController controllerOrder= new OrderHistoryController();
    private final TableView<Order> tableHistory= new TableView<>();

    // TODO fazer o metodo bindings

    private final TextField tfProductName= new TextField();
    private final TextField tfShop= new TextField();
    private final TextField tfProductPrice = new TextField();
    private final TextField tfPortage= new TextField();
    private final TextField tfQuantity= new TextField();
    private final TextField tfTotalValue= new TextField();
    private final TextField tfMethodPayment = new TextField();
    private final TextField tfStatus= new TextField();

    public void bindings(){
        Bindings.bindBidirectional(tfSearch.textProperty(), controllerOrder.searchProperty());
        Bindings.bindBidirectional(tfProductName.textProperty(), controllerOrder.nameProductProperty());
        Bindings.bindBidirectional(tfShop.textProperty(), controllerOrder.nameStoreProperty());
        Bindings.bindBidirectional(tfProductPrice.textProperty(), controllerOrder.priceProductProperty(), new NumberStringConverter());
        Bindings.bindBidirectional(tfPortage.textProperty(), controllerOrder.portageProperty());
        Bindings.bindBidirectional(tfQuantity.textProperty(), controllerOrder.quantityProperty(), new NumberStringConverter());
        Bindings.bindBidirectional(tfTotalValue.textProperty(), controllerOrder.totalValueProperty());
        Bindings.bindBidirectional(tfMethodPayment.textProperty(), controllerOrder.methodPaymentProperty());
        Bindings.bindBidirectional(tfStatus.textProperty(), controllerOrder.statusProperty());
    }
    public void addElements(Pane pane) {
        Button btnReturn= new Button();
        Button btnQuit= new Button("Sair❌");
        Button btnAccount= new Button("Conta");
        btnSeePurchase.setMinSize(100, 30);
        btnSearch.setMinSize(30, 30);
        btnQuit.relocate(530, 0);
        btnAccount.relocate(580, 0);
        btnSearch.relocate(510, 50);
        btnSeePurchase.relocate(480, 350);
        setBtnBackImage(btnReturn);
        String styleEnter = "-fx-border-color: rgba(255,255,255,0); -fx-cursor: hand; " +
                "-fx-background-color: rgba(94,94,94,0.26); -fx-background-radius: 1000px";
        String styleExit = "-fx-border-color: rgba(255,255,255,0); -fx-cursor: hand; " +
                "-fx-background-color: rgba(255,255,255,0);";
        setOverButtonStyle(btnReturn, styleEnter, styleExit);
        setOverButtonStyle(btnQuit, styleEnter, styleExit);
        setOverButtonStyle(btnAccount, styleEnter, styleExit);

        tfSearch.setMinSize(380, 30);
        tfSearch.relocate(115, 50);

        lblTitle.setMinSize(150, 25);
        lblTitle.relocate(220, 10);
        lblTitle.setFont(Font.font(20));


        tableHistory.setMinWidth(550);
        tableHistory.setMaxHeight(250);
        tableHistory.relocate(45, 90);



        // ---------------------Events---------------------------

        btnSeePurchase.setOnMouseClicked(event -> {
            pane.getChildren().add(subWindowStatus());
        });


        // ----------------------------------------------------

        tableHistory.setItems(controllerOrder.getListHistory());

        bindings();
        populateTable();
        pane.getChildren().addAll(btnAccount, btnQuit, btnReturn,btnSeePurchase,btnSearch, tfSearch,lblTitle, tableHistory);

    }

    public BorderPane subWindowStatus(){
        Pane panePurchaseStatus= new Pane();
        GridPane gridPurchaseStatus = new GridPane();
        panePurchaseStatus.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 10px");
        panePurchaseStatus.setMaxHeight(350);
        panePurchaseStatus.setMaxWidth(315);
        gridPurchaseStatus.relocate(5, 20);

        Label lblTittle= new Label("Status Compra");
        Label lblProductName= new Label("Nome:");
        Label lblShop= new Label("Loja:");
        Label lblProductValue= new Label("Valor do Produto:");
        Label lblPortage= new Label("Frete:");
        Label lblQuantity= new Label("Quantidade:");
        Label lblTotalValue= new Label("Valor Total:");
        Label lblFormPayment= new Label("Forma de Pagamento:");
        Label lblStatus= new Label("Status");
        lblTittle.setFont(Font.font(20));
        lblProductName.setFont(Font.font(14));
        lblShop.setFont(Font.font(14));
        lblProductValue.setFont(Font.font(14));
        lblPortage.setFont(Font.font(14));
        lblQuantity.setFont(Font.font(14));
        lblTotalValue.setFont(Font.font(14));
        lblFormPayment.setFont(Font.font(14));
        lblStatus.setFont(Font.font(14));
        lblTittle.relocate(90, 5);



        tfProductName.setEditable(false);
        tfShop.setEditable(false);
        tfProductPrice.setEditable(false);
        tfPortage.setEditable(false);
        tfQuantity.setEditable(false);
        tfTotalValue.setEditable(false);
        tfMethodPayment.setEditable(false);
        tfStatus.setEditable(false);
        tfQuantity.setMaxWidth(35);


        Button btnReturnPurchaseStatus= new Button("Voltar");
        btnReturnPurchaseStatus.relocate(30, 318);

        gridPurchaseStatus.setVgap(10);
        gridPurchaseStatus.setHgap(10);

        gridPurchaseStatus.add(lblTittle, 1, 1);
        gridPurchaseStatus.add(lblProductName, 1, 2);
        gridPurchaseStatus.add(tfProductName, 2, 2);
        gridPurchaseStatus.add(lblShop, 1, 3);
        gridPurchaseStatus.add(tfShop, 2, 3);
        gridPurchaseStatus.add(lblProductValue, 1, 4);
        gridPurchaseStatus.add(tfProductPrice, 2, 4);
        gridPurchaseStatus.add(lblPortage, 1, 5);
        gridPurchaseStatus.add(tfPortage, 2, 5);
        gridPurchaseStatus.add(lblQuantity, 1, 6);
        gridPurchaseStatus.add(tfQuantity, 2, 6);
        gridPurchaseStatus.add(lblTotalValue, 1, 7);
        gridPurchaseStatus.add(tfTotalValue, 2, 7);
        gridPurchaseStatus.add(lblFormPayment, 1, 8);
        gridPurchaseStatus.add(tfMethodPayment, 2, 8);
        gridPurchaseStatus.add(lblStatus, 1, 9);
        gridPurchaseStatus.add(tfStatus, 2, 9);


        panePurchaseStatus.getChildren().addAll(lblTittle, gridPurchaseStatus, btnReturnPurchaseStatus);


        BorderPane paneTransp = new BorderPane();
        paneTransp.setPrefHeight(400);
        paneTransp.setPrefWidth(640);
        paneTransp.setStyle("-fx-background-color: rgba(0, 0, 0, 0.4)");


        paneTransp.setCenter(panePurchaseStatus);

        btnReturnPurchaseStatus.setOnMouseClicked(event -> {paneTransp.setVisible(false);});


        return paneTransp;
    }

    @SuppressWarnings("unchecked")
    public void populateTable(){
        // TODO fazer a populateTable do historico de pedidos
        TableColumn<Order, Integer> columnIdProduct= new TableColumn<>("ID");
        columnIdProduct.setCellValueFactory(new PropertyValueFactory<Order, Integer>("idProduct"));

        TableColumn<Order, String> columnProductName= new TableColumn<>("Nome do Produto");
        columnProductName.setCellValueFactory(new PropertyValueFactory<Order, String>("nameProduct"));

        TableColumn<Order, String> columnPaymentDate= new TableColumn<>("Data de Pagamento");
        columnPaymentDate.setCellValueFactory(
                itemData -> {
                    DateTimeFormatter dtf= DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    Date dt= itemData.getValue().getPayment().getDate();
                    String dataString= dtf.format((TemporalAccessor) dt);
                    return new ReadOnlyStringWrapper(dataString);
                }
        );

        TableColumn<Order, String> columnStatus = new TableColumn<>("Status");
        columnStatus.setCellValueFactory(
                itemData -> {
                    return new ReadOnlyStringWrapper(itemData.getValue().getPayment().getStatus());
                }
        );


        tableHistory.getColumns().addAll(columnIdProduct, columnProductName, columnPaymentDate, columnStatus);
        tableHistory.getSelectionModel().selectedItemProperty().addListener(
                (obs, antigo, novo) -> {
                    try {
                        controllerOrder.populateWinHistory();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
        );
    }

    private void setBtnBackImage(Button btnBack) {
        Image imgGoBackBtn = new Image(getClass().getResource("image/goBack.png").toString());
        ImageView ivGoBackBtn = new ImageView(imgGoBackBtn);
        int widthHeight = 25;
        ivGoBackBtn.setFitHeight(widthHeight);
        ivGoBackBtn.setFitWidth(widthHeight);

        btnBack.setGraphic(ivGoBackBtn);
    }

    private void setBtnStyle(Button button, String style)
    {
        button.setStyle(style);
    }


    private void setOverButtonStyle(Button button, String styleEnter, String styleExit) {
        button.setOnMouseEntered(e -> setBtnStyle(button, styleEnter));
        button.setOnMouseExited(e -> setBtnStyle(button, styleExit));
        button.setStyle(styleExit);
    }

}
