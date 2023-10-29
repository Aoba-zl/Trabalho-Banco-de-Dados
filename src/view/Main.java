package view;


import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
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
		winHomePage.addElements(pWin);
		
		scnLogin = new Scene(pWin);
		
		stage.setScene(scnLogin);
		stage.setResizable(false);
		stage.setTitle("Loja Online");
		stage.show();
	}
	
	
	public void openNewScreen(String fxmlFileName)
	{
		FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFileName));
		Parent root = null;
		try {
		root = loader.load();
		stage.getScene().setRoot(root);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	
	
	public static void main(String[] args) 
	{
		launch(args);
	}
}
