package view;

import java.sql.SQLException;
import java.util.List;

import control.ChangeSceneController;
import control.ProductController;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import model.Product;
import utils.SceneName;

public class WinStoreConstructor 
{
	private Pane pWin;
	
	public void addElements(Pane pane)
	{
		pWin = pane;
		
		Label lblHomePage = new Label("Sua Loja");
		lblHomePage.setPrefHeight(35);
		lblHomePage.setPrefWidth(640);
		lblHomePage.setStyle("-fx-font-size: 24px; -fx-alignment: center; -fx-font-weight: bold");
		lblHomePage.setLayoutY(25);
		
		Image imgGoBack = new Image(getClass().getResource("image/goBack.png").toString());
		ImageView imgViewGoBack = new ImageView(imgGoBack);
		imgViewGoBack.setFitWidth(24);
		imgViewGoBack.setFitHeight(24);
		imgViewGoBack.setStyle("-fx-cursor: hand");
		imgViewGoBack.setPickOnBounds(true);
		
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
		
		TextField tfSearch = new TextField();
		tfSearch.setPromptText("Buscar item");
		Button btnSearch = new Button("🔍");
		tfSearch.setPrefWidth(560);
		
		HBox hbSearch = new HBox();
		hbSearch.setPrefWidth(640);
		hbSearch.setPrefHeight(30);
		hbSearch.setLayoutY(60);
		hbSearch.setPadding(new Insets(0, 0, 0, 20));
		hbSearch.setStyle("-fx-spacing: 13px");
		hbSearch.getChildren().addAll(tfSearch, btnSearch);
		
		ProductController pControl = new ProductController();
		List<Product> listProduct = null;
		
		try {
			listProduct = pControl.listProductStore();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		int sizeProductRow = listProduct.size();
		int sizeProductColumn = sizeProductRow;
		int countProduct = 0;
		
		
		VBox vbProduct = new VBox();
		vbProduct.setMinHeight(254);
		vbProduct.setMinWidth(586);
		vbProduct.setMaxWidth(586);
		for(int i = 0; i < (sizeProductRow / 4) + 1; i++)
		{
			HBox hbProductInfo = new HBox();
			hbProductInfo.setPrefWidth(586);
			hbProductInfo.setMaxWidth(586);
			hbProductInfo.setPrefHeight(100);
			for(int j = 0; j < 4; j++)
			{
				if(sizeProductColumn > 0)
				{
					Product p = listProduct.get(countProduct);
					Label lblNameProduct = new Label(p.getName());
					Label lblDescProduct = new Label(p.getDescription());
					Label lblQuantityProduct = new Label("Quantidade:");
					Label lblPriceProduct = new Label("Preço: " + String.valueOf(p.getPrice()).replace(".", ","));
					lblDescProduct.setPrefHeight(35);
					lblDescProduct.setWrapText(true);
					lblDescProduct.setStyle("-fx-alignment: top-left");
					
					HBox hbLblNameProduct = new HBox();
					hbLblNameProduct.setMinHeight(35);
					hbLblNameProduct.setStyle("-fx-alignment: center; -fx-font-weight: bold; -fx-font-size: 14px;");
					hbLblNameProduct.getChildren().addAll(lblNameProduct);
					
					HBox hbLblDescProduct = new HBox();
					hbLblDescProduct.setMinHeight(35);
					hbLblDescProduct.getChildren().add(lblDescProduct);
					
					HBox hbLblQuantityProduct = new HBox();
					hbLblQuantityProduct.getChildren().add(lblQuantityProduct);
					
					HBox hbLblPrice = new HBox();
					hbLblPrice.getChildren().add(lblPriceProduct);
					
					VBox vbProductInfo = new VBox();
					vbProductInfo.setPrefWidth(146);
					vbProductInfo.setPrefHeight(100);
					vbProductInfo.setPadding(new Insets(0, 0, 0, 5));
					vbProductInfo.setStyle("-fx-border-color: black; -fx-border-radius: 10px; -fx-border-width: 2px; -fx-cursor: hand;");
					vbProductInfo.getChildren().addAll(hbLblNameProduct, hbLblDescProduct, hbLblQuantityProduct, hbLblPrice);
					
					vbProductInfo.setOnMouseClicked(e -> toProductStore(p.getCod(), p.getName(), p.getDescription(), p.getPrice()));
					
					hbProductInfo.getChildren().add(vbProductInfo);
					
					sizeProductColumn--;
					countProduct++;
				}
				else
				{
					break;
				}
			}
			
			vbProduct.getChildren().add(hbProductInfo);
		}
		
		
		ScrollPane spProduct = new ScrollPane();
		spProduct.setPrefHeight(260);
		spProduct.setPrefWidth(605);
		spProduct.setLayoutX(18);
		spProduct.setLayoutY(91);
		spProduct.setStyle("-fx-border-color: black; -fx-border-radius: 10px; -fx-border-width: 2px;");
		spProduct.setContent(vbProduct);
		
		Button btnAdd = new Button("Adicionar novo produto");
		btnAdd.setStyle("-fx-background-color: #C2FFC2; -fx-background-radius: 10px;");
		
		HBox hbBtnAdd = new HBox();
		hbBtnAdd.setStyle("-fx-alignment: center;");
		hbBtnAdd.setPrefWidth(640);
		hbBtnAdd.setPrefHeight(20);
		hbBtnAdd.setLayoutY(360);
		hbBtnAdd.getChildren().add(btnAdd);
		
		//------------mudança de scene---------------
		imgViewGoBack.setOnMouseClicked(e -> toHomePage());
		lblExit.setOnMouseClicked(e -> toLogin());
		lblAccount.setOnMouseClicked(e -> toAccount());
		btnAdd.setOnMouseClicked(e -> toRegProduct()); //Assim que o registro for concluído, mostrará uma mensagem de conclusão e voltara para a tela de store.
		
		pane.getChildren().addAll(hbOption, lblHomePage, hbSearch, spProduct, hbBtnAdd, imgViewGoBack);
		
	}
	

	private void toHomePage()
	{
		ChangeSceneController.changeScene(SceneName.HOME_PAGE, pWin);
	}
	
	private void toLogin() 
	{
		ChangeSceneController.changeScene(SceneName.LOGIN, pWin);
	}
	
	private void toAccount()
	{
		ChangeSceneController.changeScene(SceneName.ACCOUNT_MENU, pWin);
	}
	
	private void toRegProduct()
	{
		ChangeSceneController.changeScene(SceneName.REG_PRODUCT, pWin);
	}
	
	private void toProductStore(int cod, String name, String description, double price)
	{
		System.out.println(cod + " " + name + " " + description + " " + price); //test
		
		ChangeSceneController.changeScene(SceneName.ALTER_PRODUCT, pWin);
	}
}
