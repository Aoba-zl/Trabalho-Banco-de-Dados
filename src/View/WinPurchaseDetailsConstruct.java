package View;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class WinPurchaseDetailsConstruct  {
    private Button btnBuy= new Button("Comprar");
    private Label lblTittle= new Label("Detalhes da Compra");
    private Label lblTotalPurchaseValue= new Label("Total:");
    private Label lblPortage= new Label("Frete:");
    private Label lblPaymentMethod= new Label("Método de Pagamento:");


    public void addElements(Pane pane) {
        Button btnReturn= new Button("<");
        btnBuy.relocate(500, 330);
        btnBuy.setPrefHeight(40);
        btnBuy.setPrefWidth(90);

        lblTittle.setFont(Font.font(24));
        lblTittle.relocate(210, 20);
        lblTotalPurchaseValue.setFont(Font.font(13));
        lblTotalPurchaseValue.relocate(380, 340);
        lblPortage.setFont(Font.font(13));
        lblPortage.relocate(270, 340);
        lblPaymentMethod.setFont(Font.font(13));
        lblPaymentMethod.relocate(30, 340);

        ComboBox cbPaymentMethod= new ComboBox<>();
        cbPaymentMethod.setPrefHeight(20);
        cbPaymentMethod.setPrefWidth(70);
        cbPaymentMethod.relocate(175, 338);

        TableView tbProducts= new TableView<>();
        TableColumn columnId= new TableColumn<>("ID");
        TableColumn columnName= new TableColumn<>("Nome");
        TableColumn columnStoreName= new TableColumn<>("Nome da Loja");
        TableColumn columnProductValue= new TableColumn<>("Preço");
        columnId.setMinWidth(60);
        columnName.setMinWidth(200);
        columnStoreName.setMinWidth(170);
        columnProductValue.setMinWidth(108);
        tbProducts.setPrefHeight(240);
        tbProducts.setPrefWidth(560);
        tbProducts.relocate(40, 70);
        tbProducts.getColumns().addAll(columnId, columnName, columnStoreName, columnProductValue);


        pane.getChildren().addAll(tbProducts, btnBuy, btnReturn, lblPaymentMethod,lblPortage,lblTotalPurchaseValue,lblTittle, cbPaymentMethod);

    }
}
