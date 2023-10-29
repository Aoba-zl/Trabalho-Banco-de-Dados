package View;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;




public class WinPurchaseHistoryConstructor extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane pane= new Pane();
        Scene scene= new Scene(pane, 640, 400);

        Button btnReturn= new Button("<");
        Button btnQuit= new Button("Sair");
        Button btnAccount= new Button("Conta");
        Button btnSearch= new Button("Buscar");
        Button btnSeePurchase= new Button("Ver Compra");
        btnSeePurchase.setMinSize(100, 30);
        btnSearch.setMinSize(30, 30);
        btnQuit.relocate(530, 0);
        btnAccount.relocate(580, 0);
        btnSearch.relocate(520, 50);
        btnSeePurchase.relocate(480, 350);

        TextField tfSearch= new TextField();
        tfSearch.setMinSize(380, 30);
        tfSearch.relocate(115, 50);

        Label lblTitle= new Label("Histórico de Compra");
        lblTitle.setMinSize(150, 25);
        lblTitle.relocate(210, 10);
        lblTitle.setFont(Font.font(20));

        TableView tbPurchaseHistory= new TableView<>(); // colocar o objeto depois
        TableColumn columnId= new TableColumn<>("ID");
        TableColumn columnName= new TableColumn("Nome");
        TableColumn columnPurchaseDate= new TableColumn("Data da Compra");
        TableColumn columnStatus= new TableColumn("Status");
        columnId.setMinWidth(60);
        columnName.setMinWidth(200);
        columnPurchaseDate.setMinWidth(160);
        columnStatus.setMinWidth(140);
        tbPurchaseHistory.setMinWidth(550);
        tbPurchaseHistory.setMaxHeight(250);
        tbPurchaseHistory.relocate(30, 90);
        tbPurchaseHistory.getColumns().addAll(columnId,columnName,columnPurchaseDate,columnStatus);




        // ------------------Status Window----------------------
        Pane panePurchaseStatus= new Pane();
        GridPane gridPurchaseStatus = new GridPane();
        panePurchaseStatus.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 10px");
        panePurchaseStatus.setMaxHeight(320);
        panePurchaseStatus.setMaxWidth(315);
        gridPurchaseStatus.relocate(5, 20);

        Label lblTittle= new Label("Status Compra");
        Label lblProductName= new Label("Nome:");
        Label lblShop= new Label("Loja:");
        Label lblProductValue= new Label("Valor do Produto:");
        Label lblPortage= new Label("Frete:");
        Label lblTotalValue= new Label("Valor Total:");
        Label lblFormPayment= new Label("Forma de Pagamento:");
        Label lblStatus= new Label("Status:");
        lblTittle.setFont(Font.font(20));
        lblTittle.relocate(98, 5);


        TextField tfProductName= new TextField();
        TextField tfShop= new TextField();
        TextField tfProductValue= new TextField();
        TextField tfPortage= new TextField();
        TextField tfTotalValue= new TextField();
        TextField tfFormPayment= new TextField();
        TextField tfStatus= new TextField();

        Button btnCancel= new Button("Cancelar Compra");
        btnCancel.relocate(160, 285);

        gridPurchaseStatus.setVgap(10);
        gridPurchaseStatus.setHgap(10);

        gridPurchaseStatus.add(lblTittle, 1, 1);
        gridPurchaseStatus.add(lblProductName, 1, 2);
        gridPurchaseStatus.add(tfProductName, 2, 2);
        gridPurchaseStatus.add(lblShop, 1, 3);
        gridPurchaseStatus.add(tfShop, 2, 3);
        gridPurchaseStatus.add(lblProductValue, 1, 4);
        gridPurchaseStatus.add(tfProductValue, 2, 4);
        gridPurchaseStatus.add(lblPortage, 1, 5);
        gridPurchaseStatus.add(tfPortage, 2, 5);
        gridPurchaseStatus.add(lblTotalValue, 1, 6);
        gridPurchaseStatus.add(tfTotalValue, 2, 6);
        gridPurchaseStatus.add(lblFormPayment, 1, 7);
        gridPurchaseStatus.add(tfFormPayment, 2, 7);
        gridPurchaseStatus.add(lblStatus, 1, 8);
        gridPurchaseStatus.add(tfStatus, 2, 8);



        panePurchaseStatus.getChildren().addAll(lblTittle, gridPurchaseStatus, btnCancel);


        BorderPane paneTransp = new BorderPane();
        paneTransp.setPrefHeight(400);
        paneTransp.setPrefWidth(640);
        paneTransp.setStyle("-fx-background-color: rgba(0, 0, 0, 0.4)");
        paneTransp.setVisible(false);


        paneTransp.setCenter(panePurchaseStatus);


        btnSeePurchase.setOnMouseClicked(event -> {
            paneTransp.setVisible(true);
        });

        pane.setOnMouseClicked(event -> {
            paneTransp.setVisible(false);});


        // ----------------------------------------------------

        pane.getChildren().addAll(btnAccount, btnQuit, btnReturn,btnSeePurchase,btnSearch, tfSearch,lblTitle, tbPurchaseHistory, paneTransp);

        primaryStage.setScene(scene);
        primaryStage.setTitle("MarketPlace");
        primaryStage.show();
    }


    public static void main(String[] args) {
        Application.launch(args);
    }
}
