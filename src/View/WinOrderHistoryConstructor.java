package View;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;



public class WinOrderHistoryConstructor {
    private Button btnSearch= new Button("Buscar");
    private TextField tfSearch= new TextField();
    private Label lblTitle= new Label("Histórico de Pedidos");


    public void addElements(Pane pane) throws Exception {
        Button btnReturn= new Button("<");
        Button btnQuit= new Button("Sair");
        Button btnAccount= new Button("Conta");
        btnSearch.setMinSize(30, 30);
        btnQuit.relocate(530, 0);
        btnAccount.relocate(580, 0);
        btnSearch.relocate(520, 50);

        tfSearch.setMinSize(380, 30);
        tfSearch.relocate(115, 50);

        lblTitle.setMinSize(150, 25);
        lblTitle.relocate(210, 10);
        lblTitle.setFont(Font.font(20));

        TableView tbOrderHistory = new TableView<>(); // colocar o objeto depois
        TableColumn columnId= new TableColumn<>("ID");
        TableColumn columnName= new TableColumn("Nome");
        TableColumn columnPurchaseDate= new TableColumn("Data da Compra");
        TableColumn columnStatus= new TableColumn("Status");
        columnId.setMinWidth(60);
        columnName.setMinWidth(200);
        columnPurchaseDate.setMinWidth(160);
        columnStatus.setMinWidth(140);
        tbOrderHistory.setMinWidth(550);
        tbOrderHistory.setMaxHeight(250);
        tbOrderHistory.relocate(30, 90);
        tbOrderHistory.getColumns().addAll(columnId,columnName,columnPurchaseDate,columnStatus);


        pane.getChildren().addAll(btnAccount, btnQuit, btnReturn,btnSearch, tfSearch,lblTitle, tbOrderHistory);

    }
}
