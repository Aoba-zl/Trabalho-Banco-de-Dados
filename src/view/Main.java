package view;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;


public class Main extends Application 
{
	private Stage stage;
	private static Scene scn;
	private static Pane pWin;
	
	private static WinLoginConstructor winLogin = new WinLoginConstructor();
	private static WinHomePageConstructor winHomePage = new WinHomePageConstructor();
	private static WinStoreConstructor winStore = new WinStoreConstructor();
	private static WinRegProductConstructor winRegProduct = new WinRegProductConstructor();
	private static WinEditProductConstructor winEditProduct = new WinEditProductConstructor();
	
	@Override
	public void start(Stage PrimaryStage) throws Exception 
	{
		stage = PrimaryStage;
		
		pWin = new Pane();
		pWin.setPrefWidth(640);
		pWin.setPrefHeight(400);
		
		winLogin.addElements(pWin);
		
		scn = new Scene(pWin);
		
		stage.setScene(scn);
		stage.setResizable(false);
		stage.setTitle("Loja Online");
		stage.show();
	}
	
	void changeScene(String scnStr) //Fiz isso improvizado para ocorrer a mudança de tela
	{
		pWin.getChildren().clear();
		switch(scnStr)
		{
			case "login":
				winLogin.addElements(pWin);
				break;
			case "homePage":
				winHomePage.addElements(pWin);
				break;
			case "product":
				System.out.println("Product Client");
				break;
			case "cart":
				System.out.println("carrinho");
				break;
			case "store":
				winStore.addElements(pWin);
				break;
			case "regProduct":
				winRegProduct.addElements(pWin);
				break;
			case "productStore":
				System.out.println("Product Store");
				break;
			case "editProduct":
				winEditProduct.addElements(pWin);
				break;
			case "account":
				System.out.println("continha");
				break;
		}
		
	}
	
	public static void main(String[] args) 
	{
		launch(args);
	}
}
