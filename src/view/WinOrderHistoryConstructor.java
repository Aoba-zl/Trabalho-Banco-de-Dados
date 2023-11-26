package view;

import control.ChangeSceneController;
import control.OrderHistoryController;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import model.Item;
import model.Order;
import model.Product;
import utils.SceneName;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Esta é uma classe de Boundary que representa a tela histórico de pedidos.
 */
public class WinOrderHistoryConstructor implements GenericWindownInterface {
    Pane pWin;
    private Button btnSearch= new Button("\uD83D\uDD0D");
    private TextField tfSearch= new TextField();
    private Label lblTitle= new Label("Histórico de Pedidos");

    private TableView<Order> tableOrderHistory= new TableView<>();

    private ObservableList<Order> listOrder= FXCollections.observableArrayList();

    private FilteredList<Order> filteredList; //Filtra os dados da tabela

    private ChangeSceneController changeSceneController = new ChangeSceneController();
    
    OrderHistoryController controllerOrder= new OrderHistoryController();

    /**
     * Adiciona os elementos a tela Principal e possui os eventos dos elementos.
     * @param pane O painel usado para inserir o elementos.
     */
    public void addElements(Pane pane){
        this.pWin = pane;
        Button btnReturn= new Button();
        Button btnQuit= new Button("Sair❌");
        Button btnAccount= new Button("Conta");
        btnQuit.relocate(530, 0);
        btnAccount.relocate(580, 0);
        setBtnBackImage(btnReturn);
        String styleEnter = "-fx-border-color: rgba(255,255,255,0); -fx-cursor: hand; " +
                "-fx-background-color: rgba(94,94,94,0.26); -fx-background-radius: 1000px";
        String styleExit = "-fx-border-color: rgba(255,255,255,0); -fx-cursor: hand; " +
                "-fx-background-color: rgba(255,255,255,0);";
        setOverButtonStyle(btnReturn, styleEnter, styleExit);
        setOverButtonStyle(btnQuit, styleEnter, styleExit);
        setOverButtonStyle(btnAccount, styleEnter, styleExit);

        tfSearch.setMinSize(400, 30);
        tfSearch.relocate(115, 50);
        tfSearch.setPromptText("Pesquisar Pedido");

        lblTitle.setMinSize(150, 25);
        lblTitle.relocate(210, 10);
        lblTitle.setFont(Font.font(20));

        tableOrderHistory.setMinWidth(550);
        tableOrderHistory.setMaxWidth(550);
        tableOrderHistory.setMaxHeight(250);
        tableOrderHistory.relocate(45, 90);

        //Events --------------------------------------------

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

        btnReturn.setOnAction(e -> toHome());
        btnAccount.setOnAction(e -> toAccount());
        btnQuit.setOnAction(e -> toLogin());

        // ---------------------------------------------------------
        populateTable();

        pane.getChildren().addAll(btnAccount, btnQuit, btnReturn, tfSearch,lblTitle, tableOrderHistory);

    }

    @SuppressWarnings("unchecked")
    private void populateTable(){
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

        TableColumn<Order, String> columnPaymentDate= new TableColumn<>("Data do Pagamento");
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

        tableOrderHistory.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        tableOrderHistory.getColumns().addAll(columnIdOrder, columnProductName, columnPaymentDate, columnStatus);
        try {
            listOrder= controllerOrder.populateWinOrderHistory();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        filteredList= new FilteredList<>(listOrder, p -> true);
        tableOrderHistory.setItems(filteredList);
    }

    private void setBtnBackImage(Button btnBack) {
        Image imgGoBackBtn = new Image(getClass().getResource("image/goBack.png").toString());
        ImageView ivGoBackBtn = new ImageView(imgGoBackBtn);
        int widthHeight = 25;
        ivGoBackBtn.setFitHeight(widthHeight);
        ivGoBackBtn.setFitWidth(widthHeight);

        btnBack.setGraphic(ivGoBackBtn);
    }

    private void setBtnStyle(Button button, String style) {
        button.setStyle(style);
    }


    private void setOverButtonStyle(Button button, String styleEnter, String styleExit) {
        button.setOnMouseEntered(e -> setBtnStyle(button, styleEnter));
        button.setOnMouseExited(e -> setBtnStyle(button, styleExit));
        button.setStyle(styleExit);
    }

    private void toLogin()
    {
        tableOrderHistory.getColumns().clear();
        controllerOrder.getListHistory().clear();
        changeSceneController.changeScene(SceneName.LOGIN, this.pWin);
    }
    private void toAccount()
    {
        tableOrderHistory.getColumns().clear();
        controllerOrder.getListHistory().clear();
        changeSceneController.changeScene(SceneName.ACCOUNT_MENU, this.pWin);
    }

    private void toHome()
    {
        tableOrderHistory.getColumns().clear();
        controllerOrder.getListHistory().clear();
        changeSceneController.changeScene(SceneName.HOME_PAGE, this.pWin);
    }
}
