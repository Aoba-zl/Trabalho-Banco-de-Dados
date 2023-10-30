package view;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;


public class Main extends Application 
{
	private static Stage stage;
	
	
	@Override
	public void start(Stage PrimaryStage) throws Exception 
	{
		stage = PrimaryStage;
		Scene scnLogin;
		
		Pane pWin = new Pane();
		pWin.setPrefWidth(640);
		pWin.setPrefHeight(400);
		
		WinLoginConstructor winLogin = new WinLoginConstructor();
//		winLogin.addElements(pWin);
		
		WinHomePageConstructor winHomePage = new WinHomePageConstructor();
//		winHomePage.addElements(pWin);
		
		WinStoreConstructor winStore = new WinStoreConstructor();
		winStore.addElements(pWin);
		
		scnLogin = new Scene(pWin);
		
		stage.setScene(scnLogin);
		stage.setResizable(false);
		stage.setTitle("Loja Online");
		stage.show();
	}
	
	
	public static void main(String[] args) 
	{
		launch(args);
	}
}
