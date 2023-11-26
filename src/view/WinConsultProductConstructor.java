package view;

import control.CartController;
import control.ChangeSceneController;
import control.PlaceOrderController;
import control.ProductController;
import control.RegisterUserController;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import model.Client;
import model.Item;
import model.Order;
import model.Product;
import model.Store;
import utils.SceneName;
import utils.UserSession;

public class WinConsultProductConstructor implements GenericWindownInterface {
	Pane pWin;
	private CartController cCon = new CartController();
	private ProductController pCon = new ProductController();
	private RegisterUserController uCon = new RegisterUserController();
	private PlaceOrderController poCon = new PlaceOrderController();
	private int quant;
	private FlowPane fpCategory = new FlowPane();
	private Product product = new Product();
	private Store store = new Store();
	private IntegerProperty ipCod = new SimpleIntegerProperty(0);
	
	private ChangeSceneController changeSceneController = new ChangeSceneController();
	
	public void addElements(Pane pane) {
		this.pWin = pane;
		
		// ----- init variable ----- //
		quant = 1;
		
		// ----- Creating General Bord ----- //
		Pane paneConsult = new Pane();
		paneConsult.setStyle("-fx-border-width: 2; -fx-border-radius: 10; -fx-border-color: black;");
		paneConsult.setPrefHeight(390);
		paneConsult.setPrefWidth(630);
		paneConsult.relocate(5, 5);
		
		// ----- Carregando ----- //

		product.setCod(ipCod.get());
		product = pCon.consulta(product);
		store.setLogin(product.getLogin());
		store = uCon.consultStore(store);
		// ----- Creating Info Bord ----- //
		
		Pane paneInfo = new Pane();
		paneInfo.setStyle("-fx-border-width: 2; -fx-border-radius: 10; -fx-border-color: black;");
		paneInfo.setPrefHeight(380);
		paneInfo.setPrefWidth(280);
		paneInfo.relocate(5, 5);

		
		// ----- Creating paneInfo ----- //

			// ----- Creating Labels ----- //
		
			Label lbNameProd = new Label(product.getName());
			lbNameProd.setPrefWidth(277);
			lbNameProd.setAlignment(Pos.CENTER);
			lbNameProd.setStyle("-fx-font-size: 16px;");
			lbNameProd.relocate(5, 20);
			Label lbPrice = new Label(product.getPrice()+" $");
			lbPrice.setPrefWidth(277);
			lbPrice.setAlignment(Pos.CENTER);
			lbPrice.setStyle("-fx-font-size: 14px;-fx-font-weight: bold");
			lbPrice.relocate(5, 39);
			Label lbShop = new Label("Loja:");
			setAlignment(lbShop);
			Label lbPurchase = new Label("Compras:");
			setAlignment(lbPurchase);
			Label lbFreight = new Label("Frete:");
			setAlignment(lbFreight);
			Label lbRemains = new Label("Restantes:");
			setAlignment(lbRemains);
			Label lbQuantity = new Label("Quantidade:");
			setAlignment(lbQuantity);
			Label lbCategory = new Label("Categoria:");
			setAlignment(lbCategory);
			fpCategory.getChildren().add(lbCategory);
			lbCategory.setAlignment(Pos.BASELINE_LEFT);
			lbCategory.setPrefWidth(71);
			
			// ----- Creating TextField ----- //
			
			TextField txShop = new TextField(store.getNameStore());
			setAlignment(txShop);
			txShop.setDisable(true);
			TextField txPurchase = new TextField(pCon.quant(product)+"");
			setAlignment(txPurchase);
			txPurchase.setDisable(true);
			TextField txFreight = new TextField(product.getShipping()+"");
			setAlignment(txFreight);
			txFreight.setDisable(true);
			TextField txRemains = new TextField(product.getTotalStock()+"");
			setAlignment(txRemains);
			txRemains.setDisable(true);
			TextField txQuantity = new TextField();
			setAlignment(txQuantity);
			txQuantity.setPrefWidth(118);
			txQuantity.setDisable(true);
			txQuantity.setAlignment(Pos.CENTER);
			txQuantity.setText(quant+"");
			
			// ----- Add Button into Screen ----- //
			
			Button btnAdd = new Button("+");
			btnAdd.setPrefWidth(24.1);
			btnAdd.addEventFilter(MouseEvent.MOUSE_CLICKED,
			         (MouseEvent ev) -> {
			        	 addQuant(1,txQuantity);
			         });
			Button btnMinus = new Button("-");
			btnMinus.setPrefWidth(24.1);
			btnMinus.addEventFilter(MouseEvent.MOUSE_CLICKED,
	         (MouseEvent ev) -> {
	        	 if (quant > 1) {
	        		 addQuant(-1,txQuantity);
	        	 }
	         });
			
			// ----- Add Info into Screen ----- //
			HBox hbBuy = new HBox(btnMinus,txQuantity,btnAdd);
			VBox vbInfoLb = new VBox(27,lbShop,lbPurchase,lbFreight,lbRemains,lbQuantity);
			VBox vbInfoTx = new VBox(20,txShop, txPurchase, txFreight, txRemains, hbBuy);
			HBox hbInfo = new HBox(10,vbInfoLb,vbInfoTx);
			hbInfo.setPrefHeight(170);
			hbInfo.setPrefWidth(250);
			hbInfo.relocate(10, 80);
			
			// ----- Creating Description ----- //
			
			fpCategory.relocate(20, 300);
			fpCategory.setPrefHeight(80);
			fpCategory.setPrefWidth(250);
			
			
		// ----- Creating paneDescription ----- //
			
			// ----- Creating GoBack Button ----- //
			
			Button btnReturn = new Button();
			setBtnBackImage(btnReturn);
			setOverButtonStyle(btnReturn);
			btnReturn.relocate(4, 4);
			
			// ----- Creating Buttons ----- //
			
			Button btnBuy = new Button("Comprar");
			btnBuy.relocate(482, 360);
			btnBuy.setPrefWidth(140);
			Button btnAddCart = new Button("Adicionar ao Carrinho");
			btnAddCart.relocate(291, 360);
			btnAddCart.setPrefWidth(140);
			
			if(UserSession.getUserType().contains("store")) {
				btnBuy.setDisable(true);
				btnAddCart.setDisable(true);
			}
			
			// ----- Creating Label ----- //
			
			Label lbDescription = new Label("Descrição:");
			lbDescription.relocate(282.9, 20);
			lbDescription.setPrefWidth(345);
			lbDescription.setAlignment(Pos.CENTER);
			lbDescription.setStyle("-fx-font-size: 18px;-fx-font-weight: bold");
			// ----- Creating TextArea ----- //
			
			TextArea txDescription = new TextArea(product.getDescription());
			txDescription.relocate(290,50);
			txDescription.setPrefHeight(300);
			txDescription.setPrefWidth(332); 
			txDescription.setStyle("-fx-border-width: 2; -fx-border-radius: 10; -fx-border-color: black;");
			txDescription.setWrapText(true);
			txDescription.setDisable(true);
			

		// ----- Add to pane ----- //
		addCategory(product.getCategory());
			
		paneInfo.getChildren().addAll(hbInfo,lbNameProd,lbPrice,btnReturn,fpCategory);
		paneConsult.getChildren().addAll(paneInfo,btnAddCart,btnBuy,lbDescription,txDescription);
		
		//ChangeScene
		btnReturn.setOnAction(e -> toHomePage());
		btnAddCart.setOnAction(e -> toCart()); //Deixei dessa forma, sinta-se a vontade para fazer do jeito que quiser
		btnBuy.setOnMouseClicked(e -> toDetails());
		
		pane.getChildren().addAll(paneConsult);
	}
	
	public void addCategory (String categoria) {
		Label lblCategory = new Label("("+categoria+") ");
		fpCategory.getChildren().add(lblCategory);
	}
	
	private void setAlignment(Label label) {
		label.setPrefWidth(110);
		label.setAlignment(Pos.CENTER_RIGHT);
		label.setStyle("-fx-font-size: 13px;");
	}
	private void setAlignment(TextField tf) {
		tf.setPrefWidth(206);
		tf.setStyle("-fx-font-size: 12px;");
	}
	private void addQuant (int num, TextField txQuantity) {
		quant = quant+num;
		txQuantity.setText(quant+"");
	}
	
	private void toHomePage(){
		//quant = 1;
		fpCategory.getChildren().clear();
		changeSceneController.changeScene(SceneName.HOME_PAGE, this.pWin);
	}
	
	private void toCart(){
		if (quant <= product.getTotalStock()) {
			Item item = new Item(product, quant);
			Client client = new Client(UserSession.getUserName());
			//quant = 1;
			if(cCon.verifyCart(item, client.getLogin())) {
				showPopup("Item Já Esta No Carrinho",2);
			} else {
				Order o =  cCon.getIdOrder();
				if (o.getId() == null) {
					cCon.createOrder(client, item);			// --- Criando Order --- //
				}else {
					cCon.placeOrder(item);
				}
				fpCategory.getChildren().clear();
				changeSceneController.changeScene(SceneName.CART, this.pWin);
			}
		}else {
			showPopup("Quantidade Maior Que Estoque",1);
		}
	}
	
	private void toDetails() {
		if (quant <= product.getTotalStock()) {
			Item item = new Item(product, quant);
			Client client = new Client(UserSession.getUserName());
			if(cCon.verifyCart(item, client.getLogin())) {
				showPopup("Item Já Esta No Carrinho",2);
			}else {
				poCon.deleteOrder();
				poCon.createOrder(item);
				fpCategory.getChildren().clear();
				changeSceneController.changeScene(SceneName.PURCHASE_DETAILS, this.pWin);
			}
		}else {
			showPopup("Quantidade Maior Que Estoque",1);
		}
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


    private void setOverButtonStyle(Button button) {
    	String styleEnter = "-fx-border-color: rgba(255,255,255,0); -fx-cursor: hand; " +
                "-fx-background-color: rgba(94,94,94,0.26); -fx-background-radius: 1000px";
    	String styleExit = "-fx-border-color: rgba(255,255,255,0); -fx-cursor: hand; " +
                "-fx-background-color: rgba(255,255,255,0);";
        button.setOnMouseEntered(e -> setBtnStyle(button, styleEnter));
        button.setOnMouseExited(e -> setBtnStyle(button, styleExit));
        button.setStyle(styleExit);
    }
    
    /**
     * Obtém o valor de código de outra tela
     * @param cod O codigo do produto.
     */
    private void showPopup(String menssagem, int act) {
		Label concluded = new Label(menssagem);
		concluded.setMinHeight(40);
        concluded.setStyle("-fx-font-size: 15px; -fx-font-weight: bold; -fx-text-alignment: center;");
        concluded.setWrapText(true);
        
        Button btnConfirmed = new Button("Entendido!");
        btnConfirmed.setStyle("-fx-background-color: #C2FFC2; -fx-background-radius: 10px; -fx-font-size: 14px;");
        
        VBox vbRegister = new VBox(20);
        vbRegister.setPrefHeight(100);
        vbRegister.setPrefWidth(180);
        vbRegister.setLayoutX(220.5);
        vbRegister.setLayoutY(156);
        vbRegister.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 10px; -fx-border-radius: 10px; -fx-border-color: BLACK; -fx-alignment: center;");
        vbRegister.getChildren().addAll(concluded, btnConfirmed);
        
        Pane pTransp = new Pane();
        pTransp.setPrefWidth(640);
        pTransp.setPrefHeight(400);
        pTransp.setStyle("-fx-background-color: rgba(0, 0, 0, 0.4);");
        pTransp.getChildren().add(vbRegister);
        
        pWin.getChildren().add(pTransp);
		if (act == 1){
	        btnConfirmed.setOnAction(e -> pTransp.setVisible(false));
		} else {
			fpCategory.getChildren().clear();
	        btnConfirmed.setOnAction(e -> changeSceneController.changeScene(SceneName.HOME_PAGE, pWin));
		}

        
    }
    
    /**
     * Obtém o codigo de produto de outra tela.
     * @param cod
     */
    public void setCodValue(IntegerProperty cod) { ipCod.bindBidirectional(cod); }
}
