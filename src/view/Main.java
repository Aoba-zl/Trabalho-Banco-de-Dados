package view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import Utils.Constants;

public class Main extends Application
{
    @Override
    public void start(Stage stage) throws Exception
    {
        Pane mainPane = new Pane();
        mainPane.setPrefWidth(Constants.WIDTH);
        mainPane.setPrefHeight(Constants.HEIGHT);

        //TODO: Configurar tipo de conta
        WinAccountMenuConstructor accountMenu = new WinAccountMenuConstructor("PlaceHolder");
        accountMenu.addElements(mainPane);

        Scene scene = new Scene(mainPane, Constants.WIDTH, Constants.HEIGHT);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Loja Online");
        stage.show();
    }

    public static void main(String[] args)
    {
        Application.launch();
    }
}
