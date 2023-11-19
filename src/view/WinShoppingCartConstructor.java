package view;

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

public class WinShoppingCartConstructor {
    private Label lblTittle= new Label("Carrinho");
    private Label lblTotalPrice= new Label("Total:");
    private Label lblPortage= new Label("Frete:");
    private Label lblQuantity= new Label("Alterar \nQuantidade:");
    private Button btnRemove= new Button("Remover");
    private Button btnPlaceOrder= new Button("Realizar Pedido");
    private Button btnMinus= new Button("-");
    private Button btnPlus= new Button("+");


    private TableView<Item> tableCart= new TableView<>();

    CartController controllerCart= new CartController();

    WinPurchaseDetailsConstruct winPurchaseDetailsConstruct;

    public void bindings(){
        Bindings.bindBidirectional(lblPortage.textProperty(), controllerCart.portageProperty());
        Bindings.bindBidirectional(lblTotalPrice.textProperty(), controllerCart.totalCartProperty());
    }

    public void addElements(Pane pane){
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
        btnPlaceOrder.setDisable(true);
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
                        //TODO menssagem de erro informando que o produto não possui esse total no estoque
                    }
                });

                btnRemove.setOnMouseClicked(event -> {
                    controllerCart.clearCart(selectedProduct);
                    tableCart.getItems().remove(selectedProduct);
                });

            }
        });

        btnPlaceOrder.setOnMouseClicked(event -> {
            winPurchaseDetailsConstruct= new WinPurchaseDetailsConstruct();
            pane.getChildren().clear();
            winPurchaseDetailsConstruct.addElements(pane, controllerCart.getListCart(), true);
        });


        // ----------------------------------------------------------------


        populateTable();
        bindings();
        pane.getChildren().addAll(tableCart, btnAccount,btnQuit,btnReturn,btnRemove, btnPlaceOrder, btnMinus, btnPlus, lblTittle, lblTotalPrice, lblPortage, lblQuantity);


    }

    private void populateTable(){
        TableColumn<Item, String> columnProductName= new TableColumn<>("Nome");
        columnProductName.setCellValueFactory(itemData -> {
            Product product = itemData.getValue().getProduct();
            String nameProduct = String.valueOf(product.getName());
            return new ReadOnlyStringWrapper(nameProduct);
        });

        TableColumn<Item, String> columnDescription= new TableColumn<>("Descrição");
        columnDescription.setCellValueFactory(itemData -> {
            Product product = itemData.getValue().getProduct();
            String productDescription = String.valueOf(product.getDescription());
            return new ReadOnlyStringWrapper(productDescription);
        });

        TableColumn<Item, String> columnQuantity= new TableColumn<>("Quantidade");
        columnQuantity.setCellValueFactory(itemData -> {
            String productQuantity= String.valueOf(itemData.getValue().getQuantity());
            return new ReadOnlyStringWrapper(productQuantity);
        });

        DecimalFormat decimalFormat = new DecimalFormat("#0.00");
        TableColumn<Item, String> columnPrice= new TableColumn<>("Preço");
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

}
