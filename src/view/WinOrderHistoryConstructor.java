package view;

import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;


public class WinOrderHistoryConstructor implements GenericWindownInterface
{
    private Button btnSearch= new Button("\uD83D\uDD0D");
    private TextField tfSearch= new TextField();
    private Label lblTitle= new Label("Histórico de Pedidos");


    public void addElements(Pane pane){
        Button btnReturn= new Button();
        Button btnQuit= new Button("Sair❌");
        Button btnAccount= new Button("Conta");
        btnSearch.setMinSize(30, 30);
        btnQuit.relocate(530, 0);
        btnAccount.relocate(580, 0);
        btnSearch.relocate(520, 50);
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

    private void setBtnBackImage(Button btnBack) {
        Image imgGoBackBtn = new Image(getClass().getResource("image/goBack.png").toString());
        ImageView ivGoBackBtn = new ImageView(imgGoBackBtn);
        int widthHeight = 25;
        ivGoBackBtn.setFitHeight(widthHeight);
        ivGoBackBtn.setFitWidth(widthHeight);

        btnBack.setGraphic(ivGoBackBtn);
    }

    private void setBtnStyle(Button button, String style) {
        button.setStyle(style);
    }


    private void setOverButtonStyle(Button button, String styleEnter, String styleExit) {
        button.setOnMouseEntered(e -> setBtnStyle(button, styleEnter));
        button.setOnMouseExited(e -> setBtnStyle(button, styleExit));
        button.setStyle(styleExit);
    }
}
