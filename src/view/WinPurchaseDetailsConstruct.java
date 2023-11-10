package view;

import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

public class WinPurchaseDetailsConstruct  {
    private Button btnBuy= new Button("Comprar");
    private Label lblTittle= new Label("Detalhes da Compra");
    private Label lblTotalPurchaseValue= new Label("Total:");
    private Label lblPortage= new Label("Frete:");
    private Label lblPaymentMethod= new Label("Método de Pagamento:");


    public void addElements(Pane pane) {
        Button btnReturn= new Button();
        btnBuy.relocate(500, 330);
        btnBuy.setPrefHeight(40);
        btnBuy.setPrefWidth(90);
        setBtnBackImage(btnReturn);
        String styleEnter = "-fx-border-color: rgba(255,255,255,0); -fx-cursor: hand; " +
                "-fx-background-color: rgba(94,94,94,0.26); -fx-background-radius: 1000px";
        String styleExit = "-fx-border-color: rgba(255,255,255,0); -fx-cursor: hand; " +
                "-fx-background-color: rgba(255,255,255,0);";
        setOverButtonStyle(btnReturn, styleEnter, styleExit);


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
