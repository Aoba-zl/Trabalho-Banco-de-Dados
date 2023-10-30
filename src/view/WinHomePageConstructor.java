package view;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class WinHomePageConstructor 
{
	public void addElements(Pane pane)
	{
		Label lblHomePage = new Label("Página inicial");
		lblHomePage.setPrefHeight(35);
		lblHomePage.setPrefWidth(640);
		lblHomePage.setStyle("-fx-font-size: 24px; -fx-alignment: center; -fx-font-weight: bold");
		lblHomePage.setLayoutY(25);
		
		Label lblCart = new Label("Carrinho🛒");
		Label lblExit = new Label("Sair❌");
		Label lblAccount = new Label("Conta");
		lblCart.setStyle("-fx-font-size: 16px; -fx-cursor: hand;");
		lblExit.setStyle("-fx-font-size: 16px; -fx-cursor: hand;");
		lblAccount.setStyle("-fx-font-size: 16px; -fx-cursor: hand;");
		
		HBox hbOption = new HBox();
		hbOption.setPrefWidth(640);
		hbOption.setPrefHeight(30);
		hbOption.setPadding(new Insets(0, 15, 0, 0));
		hbOption.setStyle("-fx-alignment: top-right; -fx-spacing: 15px;");
		hbOption.getChildren().addAll(lblCart, lblExit, lblAccount);
		
		TextField tfSearch = new TextField();
		tfSearch.setPromptText("Buscar item");
		Button btnSearch = new Button("🔍");
		tfSearch.setPrefWidth(560);
		
		HBox hbSearch = new HBox();
		hbSearch.setPrefWidth(640);
		hbSearch.setPrefHeight(30);
		hbSearch.setLayoutY(64);
		hbSearch.setPadding(new Insets(0, 0, 0, 20));
		hbSearch.setStyle("-fx-spacing: 13px");
		hbSearch.getChildren().addAll(tfSearch, btnSearch);
		
		
		VBox vbProduct = new VBox();
		vbProduct.setMinHeight(276);
		vbProduct.setMinWidth(586);
		vbProduct.setMaxWidth(586);
		for(int i = 0; i < 2; i++)
		{
			HBox hbProductInfo = new HBox();
			hbProductInfo.setPrefWidth(586);
			hbProductInfo.setMaxWidth(586);
			hbProductInfo.setPrefHeight(100);
			for(int j = 0; j < 4; j++)
			{
				Label lblNameProduct = new Label("nome");
				Label lblDescProduct = new Label("Adoleta lepetipeticola nescafé com chocola, adoleta puxa o rabo do tatu");
				Label lblPrice = new Label("Price:");
				lblDescProduct.setPrefHeight(55);
				lblDescProduct.setWrapText(true);
				lblDescProduct.setStyle("-fx-alignment: top-left");
				
				HBox hbLblNameProduct = new HBox();
				hbLblNameProduct.setMinHeight(20);
				hbLblNameProduct.setStyle("-fx-alignment: center; -fx-font-weight: bold; -fx-font-size: 14px;");
				hbLblNameProduct.getChildren().addAll(lblNameProduct);
				
				HBox hbLblDescProduct = new HBox();
				hbLblDescProduct.setMinHeight(50);
				hbLblDescProduct.getChildren().add(lblDescProduct);
				
				HBox hbLblPrice = new HBox();
				hbLblPrice.getChildren().add(lblPrice);
				
				VBox vbProductInfo = new VBox();
				vbProductInfo.setPrefWidth(146);
				vbProductInfo.setPrefHeight(100);
				vbProductInfo.setPadding(new Insets(0, 0, 0, 5));
				vbProductInfo.setStyle("-fx-border-color: black; -fx-border-radius: 10px; -fx-border-width: 2px; -fx-cursor: hand;");
				vbProductInfo.getChildren().addAll(hbLblNameProduct, hbLblDescProduct, hbLblPrice);
				
				String test = "teste" + j;
				
				vbProductInfo.setOnMouseClicked(e -> System.out.println(test));
				
				hbProductInfo.getChildren().add(vbProductInfo);
			}
			
			vbProduct.getChildren().add(hbProductInfo);
		}
		
		
		ScrollPane spProduct = new ScrollPane();
		spProduct.setPrefHeight(282);
		spProduct.setPrefWidth(605);
		spProduct.setLayoutX(18);
		spProduct.setLayoutY(91);
		spProduct.setStyle("-fx-border-color: black; -fx-border-radius: 10px; -fx-border-width: 2px;");
		spProduct.setContent(vbProduct);
		
		pane.getChildren().addAll(hbOption, lblHomePage, hbSearch, spProduct);
		
	}
	

}