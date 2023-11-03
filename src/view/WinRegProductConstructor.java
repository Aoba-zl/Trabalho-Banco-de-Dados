package view;

import java.sql.SQLException;

import control.ProductController;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
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
		
		Image imgGoBack = new Image(getClass().getResource("image/goBack.png").toString());
		ImageView imgViewGoBack = new ImageView(imgGoBack);
		imgViewGoBack.setFitWidth(24);
		imgViewGoBack.setFitHeight(24);
		imgViewGoBack.setStyle("-fx-cursor: hand");
		imgViewGoBack.setPickOnBounds(true);
		
		HBox hbOption = new HBox();
		hbOption.setPrefWidth(640);
		hbOption.setPrefHeight(30);
		hbOption.setPadding(new Insets(0, 15, 0, 0));
		hbOption.setStyle("-fx-alignment: top-right; -fx-spacing: 15px;");
		hbOption.getChildren().addAll(lblExit, lblAccount);
		
		Label lblDesc = new Label("Descrição");
		TextArea taDesc = new TextArea();
		taDesc.setWrapText(true);
		
		HBox hbLblDesc = new HBox();
		hbLblDesc.setMinHeight(50);
		hbLblDesc.setStyle("-fx-alignment: center; -fx-font-size: 18px; -fx-font-weight: bold;");
		hbLblDesc.getChildren().add(lblDesc);
		
		HBox hbTaDesc = new HBox();
		hbTaDesc.setMinHeight(250);
		hbTaDesc.setPadding(new Insets(0, 10, 0, 10));
		hbTaDesc.getChildren().add(taDesc);
		
		VBox vbDescProduct = new VBox();
		vbDescProduct.setPrefWidth(250);
		vbDescProduct.setPrefHeight(320);
		vbDescProduct.setStyle("-fx-border-color: black; -fx-border-radius: 10px; -fx-border-width: 2px; -fx-border-style: dashed;");
		vbDescProduct.getChildren().addAll(hbLblDesc, hbTaDesc);
		
		Label lblName = new Label("Nome:  ");
		Label lblPrice = new Label("Preço:  ");
		Label lblInStock = new Label("Em estoque:  ");
		Label lblShipping = new Label("Frete:  ");
		Label lblCategory = new Label("Categoria:  ");
		Label lblCod = new Label("Código:  ");
		lblName.setPrefWidth(75);
		lblName.setStyle("-fx-alignment: center-right;");
		lblPrice.setPrefWidth(75);
		lblPrice.setStyle("-fx-alignment: center-right;");
		lblInStock.setPrefWidth(75);
		lblInStock.setStyle("-fx-alignment: center-right;");
		lblShipping.setPrefWidth(75);
		lblShipping.setStyle("-fx-alignment: center-right;");
		lblCategory.setPrefWidth(75);
		lblCategory.setStyle("-fx-alignment: center-right;");
		lblCod.setPrefWidth(75);
		lblCod.setStyle("-fx-alignment: center-right;");
		
		TextField tfName = new TextField();
		TextField tfPrice = new TextField();
		TextField tfInStock = new TextField();
		TextField tfShipping = new TextField();
		TextField tfCategory = new TextField();
		TextField tfCod = new TextField(); //é automático pelo banco de dados.
		tfName.setPrefWidth(225);
		tfPrice.setPrefWidth(225);
		tfInStock.setPrefWidth(225);
		tfShipping.setPrefWidth(225);
		tfCategory.setPrefWidth(225);
		tfCod.setPrefWidth(225);
		tfCod.setEditable(false);
		tfCod.setStyle("-fx-background-color: #D3D3D3;");
		
		Button btnConf = new Button("Confirmar");
		btnConf.setStyle("-fx-background-color: #C2FFC2; -fx-border-color: #ADFF2F; -fx-border-radius: 10px; -fx-background-radius: 10px;");
		
		Label lblMessage = new Label("teste");
		lblMessage.setMinHeight(30);
		lblMessage.setMinWidth(120);
		
		HBox hbName = new HBox();
		HBox hbPrice = new HBox();
		HBox hbInStock = new HBox();
		HBox hbShipping = new HBox();
		HBox hbCategory = new HBox();
		HBox hbCod = new HBox();
		HBox hbBtnConfAndMessage = new HBox();
		hbName.getChildren().addAll(lblName, tfName);
		hbPrice.getChildren().addAll(lblPrice, tfPrice);
		hbInStock.getChildren().addAll(lblInStock, tfInStock);
		hbShipping.getChildren().addAll(lblShipping, tfShipping);
		hbCategory.getChildren().addAll(lblCategory, tfCategory);
		hbCod.getChildren().addAll(lblCod, tfCod);
		hbBtnConfAndMessage.getChildren().addAll(lblMessage, btnConf);
		hbBtnConfAndMessage.setStyle("-fx-alignment: bottom-right; -fx-spacing: 100px");
		hbBtnConfAndMessage.setPadding(new Insets(0, 45, 0, 0));
		hbBtnConfAndMessage.setMinHeight(50);
		
		VBox vbRegProduct = new VBox(14);
		vbRegProduct.setPrefWidth(355);
		vbRegProduct.setPrefHeight(320);
		vbRegProduct.setLayoutX(250);
		vbRegProduct.setPadding(new Insets(20, 0, 0, 10));
		vbRegProduct.getChildren().addAll(hbName, hbPrice, hbInStock, hbShipping, hbCategory, hbCod, hbBtnConfAndMessage);
		
		Pane pProduct = new Pane();
		pProduct.setPrefHeight(320);
		pProduct.setPrefWidth(605);
		pProduct.setLayoutX(18);
		pProduct.setLayoutY(60);
		pProduct.setStyle("-fx-border-color: black; -fx-border-radius: 10px; -fx-border-width: 2px;");
		pProduct.getChildren().addAll(vbDescProduct, vbRegProduct);
		
		//TODO quando der insert, deve retornar ao store
		btnConf.setOnAction(e -> insertProduct(tfName, tfPrice, tfInStock, tfShipping, tfCategory, taDesc, tfCod, lblMessage, pane));
		
		//------------mudança de scene---------------
		imgViewGoBack.setOnMouseClicked(e -> toStore());
		lblExit.setOnMouseClicked(e -> toLogin());
		lblAccount.setOnMouseClicked(e -> toAccount());
		
		pane.getChildren().addAll(lblHomePage, pProduct, hbOption, imgViewGoBack);
		
	}
	
	private void toLogin() 
	{
		Main m = new Main();
		m.changeScene("login");
	}
	
	private void toAccount()
	{
		Main m = new Main();
		m.changeScene("account");
	}
	
	private void toStore()
	{
		Main m = new Main();
		m.changeScene("store");
	}
	
	private void insertProduct(TextField tfName, TextField tfPrice, TextField tfInStock, TextField tfShipping, TextField tfCategory, TextArea taDescription, TextField tfCod, Label lblMessage, Pane pane)
	{
		ProductController pControll = new ProductController(tfName, tfPrice, tfInStock, tfShipping, tfCategory, taDescription, tfCod, lblMessage);
		
		try {
			if(pControll.insert())
			{
				Label concluded = new Label("Cadastro Concluido");
				concluded.setStyle("-fx-font-size: 15px; -fx-font-weight: bold;");
				
				Button btnConfirmed = new Button("Confirmar");
				btnConfirmed.setStyle("-fx-background-color: #C2FFC2; -fx-border-color: #ADFF2F; -fx-border-radius: 10px; -fx-background-radius: 10px; -fx-font-size: 14px;");
				
				VBox vbRegister = new VBox(20);
				vbRegister.setPrefHeight(130);
				vbRegister.setPrefWidth(180);
				vbRegister.setLayoutX(220.5);
				vbRegister.setLayoutY(156);
				vbRegister.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 10px; -fx-border-radius: 10px; -fx-border-color: BLACK; -fx-alignment: center; -fx-spacing: 50px;");
				vbRegister.getChildren().addAll(concluded, btnConfirmed);
				
				Pane pTransp = new Pane();
				pTransp.setPrefWidth(640);
				pTransp.setPrefHeight(400);
				pTransp.setStyle("-fx-background-color: rgba(0, 0, 0, 0.4);");
				pTransp.getChildren().add(vbRegister);
				
				pane.getChildren().add(pTransp);
				
				btnConfirmed.setOnAction(e -> toStore());
			}
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
}
