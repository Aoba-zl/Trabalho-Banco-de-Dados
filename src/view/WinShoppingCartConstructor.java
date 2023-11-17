package view;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import model.Cart;
import model.Item;
import model.Product;

import java.util.List;

public class WinShoppingCartConstructor {
    private Label lblTittle= new Label("Carrinho");
    private Label lblTotalPrice= new Label("Total:");
    private Label lblPortage= new Label("Frete:");
    private Label lblQuantity= new Label("Quantidade:");
    private Button btnRemove= new Button("Remover");
    private Button btnSelectAll= new Button("Selecionar Todos");
    private Button btnPlaceOrder= new Button("Realizar Pedido");
    private Button btnMinus= new Button("-");
    private Button btnPlus= new Button("+");


    private TableView<Item> tableCart= new TableView<>();

    public void addElements(Pane pane){
        Button btnReturn= new Button();
        Button btnAccount= new Button("Conta");
        Button btnQuit= new Button("Sair❌");
        btnQuit.relocate(530, 0);
        btnAccount.relocate(580, 0);
        btnRemove.setMinSize(100, 30);
        btnRemove.setStyle("-fx-background-color: #9f3f3f");
        btnRemove.relocate(30, 350);
        btnSelectAll.setMinSize(130, 30);
        btnSelectAll.relocate(160, 350);
        btnPlaceOrder.setMinSize(130, 30);
        btnPlaceOrder.relocate(460, 350);
        btnMinus.relocate(120, 320);
        btnMinus.setFont(Font.font(12));
        btnPlus.relocate(145, 320);
        btnPlus.setFont(Font.font(12));
        setBtnBackImage(btnReturn);
        String styleEnter = "-fx-border-color: rgba(255,255,255,0); -fx-cursor: hand; " +
                "-fx-background-color: rgba(94,94,94,0.26); -fx-background-radius: 1000px";
        String styleExit = "-fx-border-color: rgba(255,255,255,0); -fx-cursor: hand; " +
                "-fx-background-color: rgba(255,255,255,0);";
        setOverButtonStyle(btnReturn, styleEnter, styleExit);
        setOverButtonStyle(btnQuit, styleEnter, styleExit);
        setOverButtonStyle(btnAccount, styleEnter, styleExit);

        lblTittle.setFont(Font.font(26));
        lblTittle.relocate(260, 15);
        lblTotalPrice.setFont(Font.font(15));
        lblTotalPrice.relocate(335, 355);
        lblPortage.setFont(Font.font(15));
        lblPortage.relocate(335, 325);
        lblQuantity.setFont(Font.font(15));
        lblQuantity.relocate(30, 320);


        tableCart.setMinWidth(575);
        tableCart.setMaxHeight(250);
        tableCart.relocate(30, 60);


        populateTable();
        pane.getChildren().addAll(tableCart, btnAccount,btnQuit,btnReturn,btnRemove, btnSelectAll, btnPlaceOrder, btnMinus, btnPlus, lblTittle, lblTotalPrice, lblPortage, lblQuantity);

    }

    private void populateTable(){
        TableColumn<Item, String> columnProductName= new TableColumn<>("Nome");
        columnProductName.setCellValueFactory(itemData -> {
            Product product = itemData.getValue().getProduct();
            String nameProduct = String.valueOf(product.getName());
            return new ReadOnlyStringWrapper(nameProduct);
        });

        TableColumn<Item, String> columnDescription= new TableColumn<>("Descrição");
        columnDescription.setCellValueFactory(itemData -> {
            Product product = itemData.getValue().getProduct();
            String productDescription = String.valueOf(product.getDescription());
            return new ReadOnlyStringWrapper(productDescription);
        });

        TableColumn<Item, String> columnQuantity= new TableColumn<>("Quantidade");
        columnQuantity.setCellValueFactory(itemData -> {
            String productQuantity= String.valueOf(itemData.getValue().getQuantity());
            return new ReadOnlyStringWrapper(productQuantity);
        });

        TableColumn<Item, String> columnPrice= new TableColumn<>("Preço");
        columnPrice.setCellValueFactory(itemData -> {
            String productPrice= String.valueOf(itemData.getValue().getSubTotal());
            return new ReadOnlyStringWrapper(productPrice);
        });

        columnProductName.setMinWidth(170);
        columnDescription.setMinWidth(205);
        columnQuantity.setMinWidth(40);
        columnPrice.setMinWidth(120);

        tableCart.getColumns().addAll(columnProductName, columnDescription, columnQuantity, columnPrice);

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
