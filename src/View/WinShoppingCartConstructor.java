package View;

import javafx.application.Application;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class WinShoppingCartConstructor {
    private Label lblTittle= new Label("Carrinho");
    private Label lblTotalPrice= new Label("Total:");
    private Label lblQuantity= new Label("Quantidade:");
    private Button btnRemove= new Button("Remover");
    private Button btnSelectAll= new Button("Selecionar Todos");
    private Button btnPlaceOrder= new Button("Realizar Pedido");
    private TextField tfQuantity= new TextField();

    public void addElements(Pane pane){
        Button btnReturn= new Button("<");
        Button btnAccount= new Button("Conta");
        Button btnQuit= new Button("Sair");
        btnQuit.relocate(530, 0);
        btnAccount.relocate(580, 0);
        btnRemove.setMinSize(100, 30);
        btnRemove.setStyle("-fx-background-color: #9f3f3f");
        btnRemove.relocate(30, 350);
        btnSelectAll.setMinSize(130, 30);
        btnSelectAll.relocate(160, 350);
        btnPlaceOrder.setMinSize(130, 30);
        btnPlaceOrder.relocate(460, 350);

        lblTittle.setFont(Font.font(20));
        lblTittle.relocate(280, 20);
        lblQuantity.setFont(Font.font(14));
        lblQuantity.relocate(30, 315);
        lblTotalPrice.setFont(Font.font(15));
        lblTotalPrice.relocate(345, 355);

        tfQuantity.setMaxSize(30, 30);
        tfQuantity.relocate(115, 315);

        TableColumn columnName= new TableColumn<>("Nome");
        TableColumn columnDesciption= new TableColumn<>("Descrição");
        TableColumn columnProductValue= new TableColumn<>("Preço");
        columnName.setMinWidth(190);
        columnDesciption.setMinWidth(215);
        columnProductValue.setMinWidth(170);

        TableView tbShoppingCart= new TableView<>();
        tbShoppingCart.setMinWidth(575);
        tbShoppingCart.setMaxHeight(250);
        tbShoppingCart.relocate(30, 60);
        tbShoppingCart.getColumns().addAll(columnName, columnDesciption, columnProductValue);


        pane.getChildren().addAll(tbShoppingCart, btnAccount,btnQuit,btnReturn,btnRemove, btnSelectAll, btnPlaceOrder,lblQuantity, lblTittle, lblTotalPrice, tfQuantity);

    }

}
