package view;

import control.ChangeSceneController;
import control.OrderHistoryController;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.util.StringConverter;
import javafx.util.converter.NumberStringConverter;
import model.Item;
import model.Order;
import model.Product;
import utils.SceneName;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParsePosition;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Date;
import java.util.List;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

/**
 * Esta é uma classe de Boundary que representa a tela de Historico de Compras.
 */
public class WinPurchaseHistoryConstructor implements GenericWindownInterface {
    Pane pWin;
    private Button btnSearch= new Button("Buscar");
    private Button btnSeePurchase= new Button("Ver Compra");
    private TextField tfSearch= new TextField();
    private Label lblTitle= new Label("Histórico de Compra");

    OrderHistoryController controllerOrder= new OrderHistoryController();
    private TableView<Order> tableHistory= new TableView<>();
    private ObservableList<Order> listHistory= FXCollections.observableArrayList();

    private FilteredList<Order> filteredList; //Filtra os dados da tabela

    private TextField tfProductName= new TextField();
    private TextField tfShop= new TextField();
    private TextField tfProductPrice = new TextField();
    private TextField tfPortage= new TextField();
    private TextField tfQuantity= new TextField();
    private TextField tfTotalValue= new TextField();
    private TextField tfMethodPayment = new TextField();
    private TextField tfStatus= new TextField();

    private void bindings(){
        Bindings.bindBidirectional(tfProductName.textProperty(), controllerOrder.nameProductProperty());
        Bindings.bindBidirectional(tfShop.textProperty(), controllerOrder.nameStoreProperty());
        Bindings.bindBidirectional(tfProductPrice.textProperty(), controllerOrder.priceProductProperty());
        Bindings.bindBidirectional(tfPortage.textProperty(), controllerOrder.portageProperty());
        Bindings.bindBidirectional(tfQuantity.textProperty(), controllerOrder.quantityProperty(), new NumberStringConverter());
        Bindings.bindBidirectional(tfTotalValue.textProperty(), controllerOrder.totalValueProperty());
        Bindings.bindBidirectional(tfMethodPayment.textProperty(), controllerOrder.methodPaymentProperty());
        Bindings.bindBidirectional(tfStatus.textProperty(), controllerOrder.statusProperty());

    }

    /**
     * Adiciona os elementos a tela Principal e possui os eventos dos elementos.
     * @param pane O painel usado para inserir o elementos.
     */
    public void addElements(Pane pane) {
        this.pWin = pane;
        Button btnReturn= new Button();
        Button btnQuit= new Button("Sair❌");
        Button btnAccount= new Button("Conta");
        btnSeePurchase.setMinSize(100, 30);
        btnQuit.relocate(530, 0);
        btnAccount.relocate(580, 0);
        btnSeePurchase.relocate(480, 350);
        setBtnBackImage(btnReturn);
        String styleEnter = "-fx-border-color: rgba(255,255,255,0); -fx-cursor: hand; " +
                "-fx-background-color: rgba(94,94,94,0.26); -fx-background-radius: 1000px";
        String styleExit = "-fx-border-color: rgba(255,255,255,0); -fx-cursor: hand; " +
                "-fx-background-color: rgba(255,255,255,0);";
        setOverButtonStyle(btnReturn, styleEnter, styleExit);
        setOverButtonStyle(btnQuit, styleEnter, styleExit);
        setOverButtonStyle(btnAccount, styleEnter, styleExit);
        btnSeePurchase.setDisable(true);

        tfSearch.setMinSize(400, 30);
        tfSearch.relocate(115, 50);
        tfSearch.setPromptText("Pesquisar Produto");

        lblTitle.setMinSize(150, 25);
        lblTitle.relocate(220, 10);
        lblTitle.setFont(Font.font(20));


        tableHistory.setMinWidth(550);
        tableHistory.setMaxWidth(550);
        tableHistory.setMaxHeight(250);
        tableHistory.relocate(45, 90);



        // ---------------------Events---------------------------

        btnSeePurchase.setOnMouseClicked(event -> {
            pane.getChildren().add(subWindowStatus());
        });

        tfSearch.textProperty().addListener((obs, oldValue, newValue) -> {
            filteredList.setPredicate(order -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true; // Mostra todos os pedidos se o texto estiver vazio
                }

                String lowerCaseFilter = newValue.toLowerCase();

                return order.getItems().stream()
                        .anyMatch(item -> item.getProduct().getName().toLowerCase().contains(lowerCaseFilter));
            });

        });

        pane.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
            if (!tableHistory.getBoundsInParent().contains(event.getSceneX(), event.getSceneY())) {
                tableHistory.getSelectionModel().clearSelection();
            }
        });


        tableHistory.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection == null) {
                btnSeePurchase.setDisable(true);


            } else {
                Order selectedProduct = tableHistory.getSelectionModel().getSelectedItem();
                btnSeePurchase.setDisable(false);

                try {
                    controllerOrder.populateWinStatus(selectedProduct);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

            }
        });

        btnReturn.setOnAction(e -> toHome());
        btnAccount.setOnAction(e -> toAccount());
        btnQuit.setOnAction(e -> toLogin());

        // ----------------------------------------------------

        bindings();
        populateTable();

        pane.getChildren().addAll(btnAccount, btnQuit, btnReturn,btnSeePurchase, tfSearch,lblTitle, tableHistory);

    }

    private BorderPane subWindowStatus(){
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
        Label lblStatus= new Label("Status:");
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
        tfQuantity.setMaxWidth(30);
        tfQuantity.setStyle("-fx-alignment: CENTER");


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
    private void populateTable() {
        TableColumn<Order, String> columnIdOrder = new TableColumn<>("ID");
        columnIdOrder.setCellValueFactory(itemData -> {
            String idOrder = String.valueOf(itemData.getValue().getId());
            return new ReadOnlyStringWrapper(idOrder);
        });

        TableColumn<Order, String> columnProductName= new TableColumn<>("Nome do Produto");
        columnProductName.setCellValueFactory(itemData -> {
            List<Item> items = itemData.getValue().getItems();
            Item item = items.get(0);
            Product product = item.getProduct();
            String nameProduct = String.valueOf(product.getName());
            return new ReadOnlyStringWrapper(nameProduct);
        });

        TableColumn<Order, String> columnPaymentDate= new TableColumn<>("Data de Entrega");
        columnPaymentDate.setCellValueFactory(itemData -> {
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    LocalDate dt = itemData.getValue().getPayment().getDate();
                    String dataStr = dtf.format(dt);
                    return new ReadOnlyStringWrapper(dataStr);
                }
        );

        TableColumn<Order, String> columnStatus = new TableColumn<>("Status");
        columnStatus.setCellValueFactory(itemData -> {
            return new ReadOnlyStringWrapper(itemData.getValue().getPayment().getStatus());
        });

        columnIdOrder.setMinWidth(60);
        columnProductName.setMinWidth(200);
        columnPaymentDate.setMinWidth(160);
        columnStatus.setMinWidth(128);
        columnIdOrder.setStyle("-fx-alignment: CENTER;");
        columnProductName.setStyle("-fx-alignment: CENTER;");
        columnPaymentDate.setStyle("-fx-alignment: CENTER;");
        columnStatus.setStyle("-fx-alignment: CENTER;");

        tableHistory.getColumns().addAll(columnIdOrder, columnProductName, columnPaymentDate, columnStatus);
        try {
            listHistory= controllerOrder.populateWinHistory();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        filteredList= new FilteredList<>(listHistory, p -> true);
        tableHistory.setItems(filteredList);

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

    private void toLogin()
    {
        tableHistory.getColumns().clear();
        controllerOrder.getListHistory().clear();
        ChangeSceneController.changeScene(SceneName.LOGIN, this.pWin);
    }
    private void toAccount()
    {
        tableHistory.getColumns().clear();
        controllerOrder.getListHistory().clear();
        ChangeSceneController.changeScene(SceneName.ACCOUNT_MENU, this.pWin);
    }

    private void toHome()
    {
        tableHistory.getColumns().clear();
        controllerOrder.getListHistory().clear();
        ChangeSceneController.changeScene(SceneName.HOME_PAGE, this.pWin);
    }

}
