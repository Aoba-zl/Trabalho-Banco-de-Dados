package view;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

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
		lblCart.setStyle("-fx-font-size: 16px;");
		lblExit.setStyle("-fx-font-size: 16px;");
		lblAccount.setStyle("-fx-font-size: 16px;");
		
		HBox hbOption = new HBox();
		hbOption.setPrefWidth(640);
		hbOption.setPrefHeight(30);
		hbOption.setPadding(new Insets(0, 15, 0, 0));
		hbOption.setStyle("-fx-border-color: Black; -fx-alignment: top-right; -fx-spacing: 15px;");
		
		hbOption.getChildren().addAll(lblCart, lblExit, lblAccount);
		
		
		pane.getChildren().addAll(hbOption, lblHomePage);
		
	}
	

}
