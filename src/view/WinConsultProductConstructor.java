package view;

import control.ChangeSceneController;
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
import utils.SceneName;


public class WinConsultProductConstructor implements GenericWindownInterface {
	Pane pWin;
	
	private static int quant = 0;
	private FlowPane fpCategory = new FlowPane();
	public void addElements(Pane pane) {
		this.pWin = pane;
		
		// ----- Creating General Bord ----- //
		Pane paneConsult = new Pane();
		paneConsult.setStyle("-fx-border-width: 2; -fx-border-radius: 10; -fx-border-color: black;");
		paneConsult.setPrefHeight(390);
		paneConsult.setPrefWidth(630);
		paneConsult.relocate(5, 5);
		
		// ----- Creating Info Bord ----- //
		
		Pane paneInfo = new Pane();
		paneInfo.setStyle("-fx-border-width: 2; -fx-border-radius: 10; -fx-border-color: black;");
		paneInfo.setPrefHeight(380);
		paneInfo.setPrefWidth(280);
		paneInfo.relocate(5, 5);

		
		// ----- Creating paneInfo ----- //

			// ----- Creating Labels ----- //
		
			Label lbNameProd = new Label("Goiaba");
			lbNameProd.setPrefWidth(277);
			lbNameProd.setAlignment(Pos.CENTER);
			lbNameProd.setStyle("-fx-font-size: 16px;");
			lbNameProd.relocate(5, 20);
			Label lbPrice = new Label("23,48");
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
			
			TextField txShop = new TextField();
			setAlignment(txShop);
			txShop.setDisable(true);
			TextField txPurchase = new TextField();
			setAlignment(txPurchase);
			txPurchase.setDisable(true);
			TextField txFreight = new TextField();
			setAlignment(txFreight);
			txFreight.setDisable(true);
			TextField txRemains = new TextField();
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
	        	 if (quant > 0) {
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
			
			// ----- Creating Label ----- //
			
			Label lbDescription = new Label("Descrição:");
			lbDescription.relocate(282.9, 20);
			lbDescription.setPrefWidth(345);
			lbDescription.setAlignment(Pos.CENTER);
			lbDescription.setStyle("-fx-font-size: 18px;-fx-font-weight: bold");
			// ----- Creating TextArea ----- //
			
			TextArea txDescription = new TextArea();
			txDescription.relocate(290,50);
			txDescription.setPrefHeight(300);
			txDescription.setPrefWidth(332); 
			txDescription.setStyle("-fx-border-width: 2; -fx-border-radius: 10; -fx-border-color: black;");
			txDescription.setWrapText(true);
			txDescription.setDisable(true);
			
		// ----- Add to pane ----- //
			
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
		ChangeSceneController.changeScene(SceneName.HOME_PAGE, this.pWin);
	}
	
	private void toCart(){
		ChangeSceneController.changeScene(SceneName.CART, this.pWin);
	}
	
	private void toDetails() { //não sei como seria direito como seria, mas acho que adicionaria no carrinho e iria para o details direto. Isso depende da forma como o luan vai fazer.
		ChangeSceneController.changeScene(SceneName.PURCHASE_DETAILS, this.pWin);		
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
}
