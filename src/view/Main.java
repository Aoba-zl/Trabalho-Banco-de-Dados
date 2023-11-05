package view;

import persistence.GenericDao;
import utils.Constants;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane pane= new Pane();
        pane.setPrefWidth(Constants.WIDTH);
        pane.setPrefHeight(Constants.HEIGHT);
        Scene scene= new Scene(pane, Constants.WIDTH, Constants.HEIGHT);
        WinShoppingCartConstructor winShoppingCartConstructor= new WinShoppingCartConstructor();
        WinPurchaseHistoryConstructor winPurchaseHistoryConstructor= new WinPurchaseHistoryConstructor();
        WinPurchaseDetailsConstruct winPurchaseDetailsConstruct= new WinPurchaseDetailsConstruct();
        WinOrderHistoryConstructor winOrderHistoryConstructor= new WinOrderHistoryConstructor();
        GenericDao genericDao= new GenericDao();

//        winPurchaseHistoryConstructor.addElements(pane);
        genericDao.getConnection();

        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Loja Online");
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
