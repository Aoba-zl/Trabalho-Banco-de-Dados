package view;

import javafx.application.Application;
import javafx.stage.Stage;
import utils.UserSession;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;


public class Main extends Application 
{
	private Stage stage;
	private Scene scn;
	private static Pane pWin;
	
	private static WinLoginConstructor winLogin = new WinLoginConstructor();
	
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
	

	
	public static void main(String[] args) 
	{
		launch(args);
	}
}
