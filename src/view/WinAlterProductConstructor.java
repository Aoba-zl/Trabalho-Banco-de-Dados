package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;


public class WinAlterProductConstructor implements GenericWindownInterface
{
	private static int quant = 0;
	private FlowPane fpCategory = new FlowPane();
	public void addElements(Pane pane) {
		
		Label lblExit = new Label("Sair ❌");
		lblExit.setStyle("-fx-font-size: 16px; -fx-cursor: hand;");
		Label lblAccount = new Label("Conta");
		lblAccount.setStyle("-fx-font-size: 16px; -fx-cursor: hand;");
		
		// ----- Menu Bar ----- //
		HBox hbOption = new HBox();
		hbOption.setPrefWidth(640);
		hbOption.setPrefHeight(30);
		hbOption.setPadding(new Insets(5, 10, 0, 0));
		hbOption.setStyle("-fx-alignment: top-right; -fx-spacing: 15px;");
		hbOption.getChildren().addAll(lblExit, lblAccount);
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
			Label lbShop = new Label("Compras:");
			setAlignment(lbShop);
			Label lbPurchase = new Label("Frete:");
			setAlignment(lbPurchase);
			Label lbFreight = new Label("Estoque:");
			setAlignment(lbFreight);
			Label lbRemains = new Label("Reembolsos:");
			setAlignment(lbRemains);
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
			
			
			// ----- Add Info into Screen ----- //
			VBox vbInfoLb = new VBox(27,lbShop,lbPurchase,lbFreight,lbRemains);
			VBox vbInfoTx = new VBox(20,txShop, txPurchase, txFreight, txRemains);
			HBox hbInfo = new HBox(10,vbInfoLb,vbInfoTx);
			hbInfo.setPrefHeight(170);
			hbInfo.setPrefWidth(250);
			hbInfo.relocate(10, 80);
			
			// ----- Creating Description ----- //
			
			fpCategory.relocate(20, 260);
			fpCategory.setPrefHeight(80);
			fpCategory.setPrefWidth(250);
			
			
		// ----- Creating paneDescription ----- //
			
			// ----- Creating GoBack Button ----- //
			
			Image imgGoBack = new Image(getClass().getResource("image/goBack.png").toString());
			ImageView imgViewGoBack = new ImageView(imgGoBack);
			imgViewGoBack.setFitWidth(24);
			imgViewGoBack.setFitHeight(24);
			imgViewGoBack.setStyle("-fx-cursor: hand");
			imgViewGoBack.setPickOnBounds(true);
			imgViewGoBack.relocate(4, 4);
			// ----- Creating Buttons ----- //
			
			Button btnBuy = new Button("Excluir");
			btnBuy.relocate(482, 360);
			btnBuy.setPrefWidth(140);
			Button btnAddCart = new Button("Editar");
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
			
		paneInfo.getChildren().addAll(hbInfo,lbNameProd,lbPrice,imgViewGoBack,fpCategory);
		paneConsult.getChildren().addAll(paneInfo,btnAddCart,btnBuy,lbDescription,txDescription);
		pane.getChildren().addAll(paneConsult,hbOption);
	}
	
	public void addCategory (String categoria) {
		Label lblCategory = new Label("("+categoria+") ");
		fpCategory.getChildren().add(lblCategory);
	}
	
	private void setAlignment(Label label) {
		label.setPrefWidth(120);
		label.setAlignment(Pos.CENTER_RIGHT);
		label.setStyle("-fx-font-size: 13px;");
	}
	private void setAlignment(TextField tf) {
		tf.setPrefWidth(206);
		tf.setStyle("-fx-font-size: 12px;");
	}
}
