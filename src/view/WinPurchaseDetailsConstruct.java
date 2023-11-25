package view;

import control.ChangeSceneController;
import control.CartController;
import control.PlaceOrderController;
import control.ProductController;
import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import utils.SceneName;
import model.Item;
import model.Product;
import utils.UserSession;

import java.text.DecimalFormat;

/**
 * Esta é uma classe de Boundary que representa a tela de detalhes da compra.
 */
public class WinPurchaseDetailsConstruct implements GenericWindownInterface {
    //a
	Pane pWin;
	private IntegerProperty ipCod = new SimpleIntegerProperty(0); //id do produto
    private Button btnBuy= new Button("Comprar");
    private Label lblTittle= new Label("Detalhes da Compra");
    private Label lblTotalPurchaseValue= new Label("Total:");
    private Label lblPortage= new Label("Frete:");
    private Label lblPaymentMethod= new Label("Método de Pagamento:");
    // 
	private ProductController pCon = new ProductController();
	private Product product = new Product();
	//
    private TableView<Item> tablePurchase= new TableView<>();
    
    private ChangeSceneController changeSceneController = new ChangeSceneController();

    PlaceOrderController controllerPlaceOrder= new PlaceOrderController();

    CartController cartController= new CartController();

    WinShoppingCartConstructor winShoppingCartConstructor;


    private void bindings(){
        Bindings.bindBidirectional(lblPortage.textProperty(), controllerPlaceOrder.portageProperty());
        Bindings.bindBidirectional(lblTotalPurchaseValue.textProperty(), controllerPlaceOrder.totalPurchaseProperty());
    }

    /**
     * Adiciona os elementos ao Painel.
     * @param pane O painel.
     */
    public void addElements(Pane pane) {
		// ----- Carregando ----- // 
		product.setCod(ipCod.get()); // Procurando e carregando o produto mandado
		product = pCon.consulta(product); // Este product é o produto mandado
    	this.pWin = pane;

        Button btnReturn= new Button();
        btnBuy.relocate(520, 330);
        btnBuy.setPrefHeight(40);
        btnBuy.setPrefWidth(90);
        setBtnBackImage(btnReturn);
        String styleEnter = "-fx-border-color: rgba(255,255,255,0); -fx-cursor: hand; " +
                "-fx-background-color: rgba(94,94,94,0.26); -fx-background-radius: 1000px";
        String styleExit = "-fx-border-color: rgba(255,255,255,0); -fx-cursor: hand; " +
                "-fx-background-color: rgba(255,255,255,0);";
        setOverButtonStyle(btnReturn, styleEnter, styleExit);


        lblTittle.setFont(Font.font(24));
        lblTittle.relocate(210, 20);
        lblTotalPurchaseValue.setFont(Font.font(15));
        lblTotalPurchaseValue.relocate(380, 340);
        lblPortage.setFont(Font.font(15));
        lblPortage.relocate(265, 340);
        lblPaymentMethod.setFont(Font.font(13));
        lblPaymentMethod.relocate(30, 340);

        ComboBox cbPaymentMethod= new ComboBox<>();
        cbPaymentMethod.setPrefHeight(20);
        cbPaymentMethod.setPrefWidth(80);
        cbPaymentMethod.relocate(175, 338);
        cbPaymentMethod.getItems().addAll("Pix", "Boleto");

        tablePurchase.setMinWidth(575);
        tablePurchase.setMaxHeight(250);
        tablePurchase.relocate(30, 60);

        //EVENTS --------------------------------------------------------------

        btnReturn.setOnMouseClicked(event -> {
            if (PlaceOrderController.isCart()){
                toCart();
            }
            else {
                toProduct();
            }
        });

        btnBuy.setOnMouseClicked(event -> {

            if (PlaceOrderController.isCart()){
                if (cbPaymentMethod.getValue() == "Pix"){
                    controllerPlaceOrder.placePayment(true);
                    Alert alert= new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Informação");
                    alert.setHeaderText(null);
                    alert.setContentText("Código do Pix enviado ao seu email.");
                    alert.getDialogPane().setStyle("-fx-font-size: 15");
                    alert.showAndWait();
                    controllerPlaceOrder.clearItems();
                    pane.getChildren().clear();
                    winShoppingCartConstructor= new WinShoppingCartConstructor();
                    winShoppingCartConstructor.addElements(pane);
                }
                else if (cbPaymentMethod.getValue() == "Boleto") {
                    controllerPlaceOrder.placePayment(false);
                    Alert alert= new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Informação");
                    alert.setHeaderText(null);
                    alert.setContentText("Código do Boleto enviado ao seu email.");
                    alert.getDialogPane().setStyle("-fx-font-size: 15");
                    alert.showAndWait();
                    controllerPlaceOrder.clearItems();
                    pane.getChildren().clear();
                    winShoppingCartConstructor= new WinShoppingCartConstructor();
                    winShoppingCartConstructor.addElements(pane);
                }
                else if (cbPaymentMethod.getValue() == null){
                    Alert alert= new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Informação");
                    alert.setHeaderText(null);
                    alert.setContentText("Selecione o método de pagamento!");
                    alert.getDialogPane().setStyle("-fx-font-size: 15");
                    alert.showAndWait(); // Exibe o alerta e espera até que ele seja fechado
                }
            }
            else{
                if (cbPaymentMethod.getValue() == "Pix"){
                    controllerPlaceOrder.placePayment(true);
                    Alert alert= new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Informação");
                    alert.setHeaderText(null);
                    alert.setContentText("Código do Pix enviado ao seu email.");
                    alert.getDialogPane().setStyle("-fx-font-size: 15");
                    alert.showAndWait();
                    controllerPlaceOrder.clearItems();
                    pane.getChildren().clear();
                    winShoppingCartConstructor= new WinShoppingCartConstructor();
                    winShoppingCartConstructor.addElements(pane);
                }
                else if (cbPaymentMethod.getValue() == "Boleto") {
                    controllerPlaceOrder.placePayment(false);
                    Alert alert= new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Informação");
                    alert.setHeaderText(null);
                    alert.setContentText("Código do Boleto enviado ao seu email.");
                    alert.getDialogPane().setStyle("-fx-font-size: 15");
                    alert.showAndWait();
                    controllerPlaceOrder.clearItems();
                    pane.getChildren().clear();
                    winShoppingCartConstructor= new WinShoppingCartConstructor();
                    winShoppingCartConstructor.addElements(pane);
                }
                else if (cbPaymentMethod.getValue() == null){
                    Alert alert= new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Informação");
                    alert.setHeaderText(null);
                    alert.setContentText("Selecione o método de pagamento!");
                    alert.getDialogPane().setStyle("-fx-font-size: 15");
                    alert.showAndWait(); // Exibe o alerta e espera até que ele seja fechado
                }
            }


        });

        btnReturn.setOnAction(e -> toCart());



        //---------------------------------------------------------------------


        bindings();
        populateTable();
        pane.getChildren().addAll(tablePurchase, btnBuy, btnReturn, lblPaymentMethod,lblPortage,lblTotalPurchaseValue,lblTittle, cbPaymentMethod);

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

        tablePurchase.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tablePurchase.getColumns().addAll(columnProductName, columnDescription, columnQuantity, columnPrice);
        controllerPlaceOrder.populateWinPurchase();
        tablePurchase.setItems(controllerPlaceOrder.getItems());
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


    private void toCart() {
        tablePurchase.getColumns().clear();
        controllerPlaceOrder.getItems().clear();
        changeSceneController.changeScene(SceneName.CART, this.pWin);
    }


    private void toProduct(){
        changeSceneController.changeScene(SceneName.REG_PRODUCT, this.pWin);
    }

    public void setCodValue(IntegerProperty cod) { ipCod.bindBidirectional(cod); } // set do id do produto
}
