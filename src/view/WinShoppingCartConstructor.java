package view;
import control.PlaceOrderController;
import utils.SceneName;
import control.ChangeSceneController;
import control.CartController;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import model.Cart;
import model.Item;
import model.Order;
import model.Product;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.List;

/**
 * Esta é uma classe de Boundary que representa a tela do carrinho de compra.
 */
public class WinShoppingCartConstructor implements GenericWindownInterface {
	Pane pWin;
	
    private final Label lblTittle= new Label("Carrinho");
    private final Label lblTotalPrice= new Label("Total:");
    private final Label lblPortage= new Label("Frete:");
    private final Label lblQuantity= new Label("Alterar \nQuantidade:");
    private final Button btnRemove= new Button("Remover");
    private final Button btnPlaceOrder= new Button("Realizar Pedido");
    private final Button btnMinus= new Button("-");
    private final Button btnPlus= new Button("+");


    private final TableView<Item> tableCart= new TableView<>();

    CartController controllerCart= new CartController();

    PlaceOrderController placeOrderController= new PlaceOrderController();

    WinPurchaseDetailsConstruct winPurchaseDetailsConstruct;

    private void bindings(){
        Bindings.bindBidirectional(lblPortage.textProperty(), controllerCart.portageProperty());
        Bindings.bindBidirectional(lblTotalPrice.textProperty(), controllerCart.totalCartProperty());
    }

    /**
     * Adiciona os elementos a tela Principal e possui os eventos dos elementos.
     * @param pane O painel usado para inserir o elementos.
     */
    public void addElements(Pane pane) {
    	this.pWin = pane;

        Button btnReturn= new Button();
        Button btnAccount= new Button("Conta");
        Button btnQuit= new Button("Sair❌");
        btnQuit.relocate(530, 0);
        btnAccount.relocate(580, 0);
        btnRemove.setMinSize(100, 30);
        btnRemove.setStyle("-fx-background-color: #9f3f3f");
        btnRemove.relocate(30, 350);
        btnPlaceOrder.setMinSize(130, 30);
        btnPlaceOrder.relocate(460, 350);
//        btnPlaceOrder.setDisable(true);
        btnMinus.relocate(230, 355);
        btnMinus.setFont(Font.font(13));
        btnMinus.setDisable(true);
        btnPlus.relocate(255, 355);
        btnPlus.setFont(Font.font(13));
        btnPlus.setDisable(true);
        setBtnBackImage(btnReturn);
        String styleEnter = "-fx-border-color: rgba(255,255,255,0); -fx-cursor: hand; " +
                "-fx-background-color: rgba(94,94,94,0.26); -fx-background-radius: 1000px";
        String styleExit = "-fx-border-color: rgba(255,255,255,0); -fx-cursor: hand; " +
                "-fx-background-color: rgba(255,255,255,0);";
        setOverButtonStyle(btnReturn, styleEnter, styleExit);
        setOverButtonStyle(btnQuit, styleEnter, styleExit);
        setOverButtonStyle(btnAccount, styleEnter, styleExit);


        lblTittle.setFont(Font.font(26));
        lblTittle.relocate(260, 15);
        lblTotalPrice.setFont(Font.font(15));
        lblTotalPrice.relocate(310, 355);
        lblPortage.setFont(Font.font(15));
        lblPortage.relocate(310, 325);
        lblQuantity.setFont(Font.font(15));
        lblQuantity.relocate(145, 335);


        tableCart.setMinWidth(575);
        tableCart.setMaxHeight(250);
        tableCart.relocate(30, 60);

        // Events ---------------------------------------------------------

        pane.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
            if (!tableCart.getBoundsInParent().contains(event.getSceneX(), event.getSceneY()) &&
                    !btnPlus.getBoundsInParent().contains(event.getSceneX(), event.getSceneY()) &&
                    !btnMinus.getBoundsInParent().contains(event.getSceneX(), event.getSceneY()) &&
                    !btnRemove.getBoundsInParent().contains(event.getSceneX(), event.getSceneY())) {
                tableCart.getSelectionModel().clearSelection();
            }

        });

        tableCart.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection == null) {
                btnMinus.setDisable(true);
                btnPlus.setDisable(true);

            } else {
                Item selectedProduct = tableCart.getSelectionModel().getSelectedItem();
                btnMinus.setDisable(false);
                btnPlus.setDisable(false);

                btnMinus.setOnMouseClicked(event -> {
                    if (selectedProduct.getQuantity() <= selectedProduct.getProduct().getTotalStock() &&
                        selectedProduct.getQuantity() > 1){
                        selectedProduct.setQuantity(selectedProduct.getQuantity() - 1);
                        controllerCart.alterQuantity(selectedProduct);
                    }

                });

                btnPlus.setOnMouseClicked(event -> {
                    if (selectedProduct.getQuantity() < selectedProduct.getProduct().getTotalStock()){
                        selectedProduct.setQuantity(selectedProduct.getQuantity() + 1);
                        controllerCart.alterQuantity(selectedProduct);
                    }
                    else {
                        Alert alert= new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Informação");
                        alert.setHeaderText(null);
                        alert.setContentText("Não é possivel addicionar mais desse produto!");
                        alert.getDialogPane().setStyle("-fx-font-size: 15");
                        alert.showAndWait();
                    }
                });

                btnRemove.setOnMouseClicked(event -> {
                    controllerCart.clearCart(selectedProduct);
                    tableCart.getItems().remove(selectedProduct);
                });

            }
        });


        btnReturn.setOnAction(e -> toHome());
        btnAccount.setOnAction(e -> toAccount());
        btnQuit.setOnAction(e -> toLogin());
        btnPlaceOrder.setOnMouseClicked(e -> {
            if (!controllerCart.getListCart().isEmpty()){

                toDetails();
            }
            else {
                Alert alert= new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Informação");
                alert.setHeaderText(null);
                alert.setContentText("Não há items do carrinho!");
                alert.getDialogPane().setStyle("-fx-font-size: 15");
                alert.showAndWait();
            }
        });


        // ----------------------------------------------------------------


        populateTable();
        bindings();
        pane.getChildren().addAll(tableCart, btnAccount,btnQuit,btnReturn,btnRemove, btnPlaceOrder, btnMinus, btnPlus, lblTittle, lblTotalPrice, lblPortage, lblQuantity);


    }

    private final TableColumn<Item, String> columnProductName= new TableColumn<>("Nome");
    private final TableColumn<Item, String> columnDescription= new TableColumn<>("Descrição");
    private final TableColumn<Item, String> columnQuantity= new TableColumn<>("Quantidade");
    private final TableColumn<Item, String> columnPrice= new TableColumn<>("Preço");

    private void populateTable(){
        columnProductName.setCellValueFactory(itemData -> {
            Product product = itemData.getValue().getProduct();
            String nameProduct = String.valueOf(product.getName());
            return new ReadOnlyStringWrapper(nameProduct);
        });

        columnDescription.setCellValueFactory(itemData -> {
            Product product = itemData.getValue().getProduct();
            String productDescription = String.valueOf(product.getDescription());
            return new ReadOnlyStringWrapper(productDescription);
        });

        columnQuantity.setCellValueFactory(itemData -> {
            String productQuantity= String.valueOf(itemData.getValue().getQuantity());
            return new ReadOnlyStringWrapper(productQuantity);
        });

        DecimalFormat decimalFormat = new DecimalFormat("#0.00");
        columnPrice.setCellValueFactory(itemData -> {
            String formatedvalue= decimalFormat.format(itemData.getValue().getSubTotal());
            String productPrice= ("R$ " + formatedvalue);
            return new ReadOnlyStringWrapper(productPrice);
        });

        columnProductName.setMinWidth(160);
        columnDescription.setMinWidth(205);
        columnQuantity.setMinWidth(80);
        columnPrice.setMinWidth(128);
        columnProductName.setStyle("-fx-alignment: CENTER; -fx-font-size: 13;");
        columnDescription.setStyle("-fx-font-size: 13;");
        columnQuantity.setStyle("-fx-alignment: CENTER; -fx-font-size: 12;");
        columnPrice.setStyle("-fx-alignment: CENTER; -fx-font-size: 13;");

        tableCart.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        tableCart.getColumns().addAll(columnProductName, columnDescription, columnQuantity, columnPrice);
        controllerCart.populateWinCart();
        tableCart.setItems(controllerCart.getListCart());

        BooleanBinding isTableEmpty = Bindings.isEmpty(tableCart.getItems());
        btnPlaceOrder.disableProperty().bind(isTableEmpty);


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
        tableCart.getColumns().clear();
        controllerCart.getListCart().clear();
		ChangeSceneController.changeScene(SceneName.LOGIN, this.pWin);
	}

	private void toAccount()
	{
        tableCart.getColumns().clear();
        controllerCart.getListCart().clear();
		ChangeSceneController.changeScene(SceneName.ACCOUNT_MENU, this.pWin);
	}

    private void toHome()
    {
        tableCart.getColumns().clear();
        controllerCart.getListCart().clear();
    	ChangeSceneController.changeScene(SceneName.HOME_PAGE, this.pWin);
    }

    private void toDetails()
    {
        tableCart.getColumns().clear();
        controllerCart.getListCart().clear();
		ChangeSceneController.changeScene(SceneName.PURCHASE_DETAILS, this.pWin);
	}
}
