package view;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class WinRegProductConstructor 
{
	public void addElements(Pane pane)
	{
		Label lblHomePage = new Label("Cadastro de Produto");
		lblHomePage.setPrefHeight(35);
		lblHomePage.setPrefWidth(640);
		lblHomePage.setStyle("-fx-font-size: 24px; -fx-alignment: center; -fx-font-weight: bold");
		lblHomePage.setLayoutY(25);
		
		Label lblExit = new Label("Sair❌");
		Label lblAccount = new Label("Conta");
		lblExit.setStyle("-fx-font-size: 16px; -fx-cursor: hand;");
		lblAccount.setStyle("-fx-font-size: 16px; -fx-cursor: hand;");
		
		HBox hbOption = new HBox();
		hbOption.setPrefWidth(640);
		hbOption.setPrefHeight(30);
		hbOption.setPadding(new Insets(0, 15, 0, 0));
		hbOption.setStyle("-fx-alignment: top-right; -fx-spacing: 15px;");
		hbOption.getChildren().addAll(lblExit, lblAccount);
		
		
		//TODO Add label e textfield
		
		
		
		VBox vbDescProduct = new VBox();
		vbDescProduct.setPrefWidth(250);
		vbDescProduct.setPrefHeight(320);
		vbDescProduct.setStyle("-fx-border-color: black; -fx-border-radius: 10px; -fx-border-width: 2px; -fx-border-style: dashed;");
		
		VBox vbRegProduct = new VBox();
		vbRegProduct.setPrefWidth(355);
		vbRegProduct.setPrefHeight(320);
		vbRegProduct.setLayoutX(250);
		
		Pane pProduct = new Pane();
		pProduct.setPrefHeight(320);
		pProduct.setPrefWidth(605);
		pProduct.setLayoutX(18);
		pProduct.setLayoutY(60);
		pProduct.setStyle("-fx-border-color: black; -fx-border-radius: 10px; -fx-border-width: 2px;");
		pProduct.getChildren().addAll(vbDescProduct, vbRegProduct);
		
		
		
		pane.getChildren().addAll(hbOption, lblHomePage, pProduct);
		
	}
	

}
