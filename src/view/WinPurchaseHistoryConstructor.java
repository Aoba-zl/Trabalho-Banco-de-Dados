package view;

import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;


public class WinPurchaseHistoryConstructor {
    private Button btnSearch= new Button("Buscar");
    private Button btnSeePurchase= new Button("Ver Compra");
    private TextField tfSearch= new TextField();
    Label lblTitle= new Label("Histórico de Compra");


    public void addElements(Pane pane) {
        Button btnReturn= new Button();
        Button btnQuit= new Button("Sair❌");
        Button btnAccount= new Button("Conta");
        btnSeePurchase.setMinSize(100, 30);
        btnSearch.setMinSize(30, 30);
        btnQuit.relocate(530, 0);
        btnAccount.relocate(580, 0);
        btnSearch.relocate(520, 50);
        btnSeePurchase.relocate(480, 350);
        setBtnBackImage(btnReturn);
        String styleEnter = "-fx-border-color: rgba(255,255,255,0); -fx-cursor: hand; " +
                "-fx-background-color: rgba(94,94,94,0.26); -fx-background-radius: 1000px";
        String styleExit = "-fx-border-color: rgba(255,255,255,0); -fx-cursor: hand; " +
                "-fx-background-color: rgba(255,255,255,0);";
        setOverButtonStyle(btnReturn, styleEnter, styleExit);
        setOverButtonStyle(btnQuit, styleEnter, styleExit);
        setOverButtonStyle(btnAccount, styleEnter, styleExit);

        tfSearch.setMinSize(380, 30);
        tfSearch.relocate(115, 50);

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



        // ---------------------Events---------------------------

        btnSeePurchase.setOnMouseClicked(event -> {
            pane.getChildren().add(subWindow());
        });


        // ----------------------------------------------------

        pane.getChildren().addAll(btnAccount, btnQuit, btnReturn,btnSeePurchase,btnSearch, tfSearch,lblTitle, tbPurchaseHistory);

    }

    public BorderPane subWindow(){
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
        lblTittle.relocate(90, 5);


        TextField tfProductName= new TextField();
        TextField tfShop= new TextField();
        TextField tfProductValue= new TextField();
        TextField tfPortage= new TextField();
        TextField tfTotalValue= new TextField();
        TextField tfFormPayment= new TextField();
        TextField tfStatus= new TextField();

        Button btnCancel= new Button("Cancelar Compra");
        Button btnReturnPurchaseStatus= new Button("Voltar");
        btnCancel.relocate(160, 285);
        btnReturnPurchaseStatus.relocate(60, 285);

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


        panePurchaseStatus.getChildren().addAll(lblTittle, gridPurchaseStatus, btnCancel, btnReturnPurchaseStatus);


        BorderPane paneTransp = new BorderPane();
        paneTransp.setPrefHeight(400);
        paneTransp.setPrefWidth(640);
        paneTransp.setStyle("-fx-background-color: rgba(0, 0, 0, 0.4)");


        paneTransp.setCenter(panePurchaseStatus);

        btnReturnPurchaseStatus.setOnMouseClicked(event -> {paneTransp.setVisible(false);});


        return paneTransp;
    }

    private void setBtnBackImage(Button btnBack) {
        Image imgGoBackBtn = new Image(getClass().getResource("image/goBack.png").toString());
        ImageView ivGoBackBtn = new ImageView(imgGoBackBtn);
        int widthHeight = 25;
        ivGoBackBtn.setFitHeight(widthHeight);
        ivGoBackBtn.setFitWidth(widthHeight);

        btnBack.setGraphic(ivGoBackBtn);
    }

    private void setBtnStyle(Button button, String style)
    {
        button.setStyle(style);
    }


    private void setOverButtonStyle(Button button, String styleEnter, String styleExit) {
        button.setOnMouseEntered(e -> setBtnStyle(button, styleEnter));
        button.setOnMouseExited(e -> setBtnStyle(button, styleExit));
        button.setStyle(styleExit);
    }

}
