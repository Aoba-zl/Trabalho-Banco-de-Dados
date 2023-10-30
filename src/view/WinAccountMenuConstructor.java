package view;

import Utils.Constants;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class WinAccountMenuConstructor
{
    String user;
    private final String pageBack = "goBack";
    private final String pageOrderRecord = "orderRecord";

    private final WinAccountClientConstructor winAccountClient = new WinAccountClientConstructor();
    private final WinAccountStoreConstructor winAccountStore = new WinAccountStoreConstructor();
    private final WinAddressStoreConstructor winAddressStore = new WinAddressStoreConstructor();
    private final WinAddressClientConstructor winAddressClient = new WinAddressClientConstructor();
    private final WinAllAddressClientConstructor winAllAddressClient = new WinAllAddressClientConstructor();
    private VBox vbContent;
    private BooleanProperty isDisableButtons = new SimpleBooleanProperty(false);

    WinAccountMenuConstructor (String user)
    {
        Bindings.bindBidirectional(isDisableButtons, winAccountClient.getIsDisableMenuButtons());
        this.user = user;
        this.user = "client";
    }

    public void addElements(Pane pane)
    {
        double marginMenu = (Constants.WIDTH * 0.08);
        Button btnBack = new Button();
        setBtnBackStyle(btnBack);
        Label lblTitulo = new Label("Conta");
        lblTitulo.setStyle("-fx-font-weight: bold; -fx-font-size: 20;");

        BorderPane  bpTitle = new BorderPane();
        VBox vbMenuTop = new VBox(5);
        VBox vbMenu = new VBox(40);
        vbContent = new VBox(5);
        BorderPane allElements = new BorderPane();

        vbMenu
                .setStyle("-fx-border-color: black; -fx-border-width: 2px; -fx-border-radius: 8px;" +
                        "-fx-background-radius: 8px; -fx-padding: 20px; ");
        vbContent.setPrefWidth(Constants.WIDTH * 0.60);
        vbContent.setPrefHeight(Constants.HEIGHT * 0.845);
        vbContent
                .setStyle("-fx-border-color: black; -fx-border-width: 2px; -fx-border-radius: 8px;" +
                        "-fx-background-radius: 8px; -fx-padding: 5px 15px 20px 15px; ");

        Button  btnData = new Button("Dados");
        Button  btnAddress = new Button("Endereço");
        Button  btnOrderRecord = new Button("Historico");
        int defaultWidthBtn = (int) (Constants.WIDTH * 0.15);
        btnData.setPrefWidth(defaultWidthBtn);
        btnAddress.setPrefWidth(defaultWidthBtn);
        btnOrderRecord.setPrefWidth(defaultWidthBtn);
        btnData.setStyle("-fx-border-radius: 8px; -fx-background-radius: 8px");
        btnAddress.setStyle("-fx-border-radius: 8px; -fx-background-radius: 8px");
        btnOrderRecord.setStyle("-fx-background-color: rgba(0,0,0,0)");

        String dataPage;
        String addressPage;
        if (user.equalsIgnoreCase("store"))
        {
            dataPage = "storeAccountData";
            addressPage = "storeAddress";

        } else {
            dataPage = "clientAccountData";
            addressPage = "clientAddress";
        }

        winAccountClient.addElements(vbContent);
//TODO        winAllAddressClient.addElements(vbContent);

        allElements.setPrefWidth(Constants.WIDTH);
        bpTitle.setLeft(btnBack);
        bpTitle.setCenter(lblTitulo);
        vbMenuTop.getChildren().addAll(btnData, btnAddress);
        vbMenuTop.setAlignment(Pos.TOP_CENTER);
        vbMenu.getChildren().addAll(vbMenuTop, btnOrderRecord);
        vbMenu.setAlignment(Pos.TOP_CENTER);

        vbContent.setAlignment(Pos.TOP_CENTER);

        BorderPane.setMargin(vbMenu, new Insets(0, 0, 0, marginMenu));
        BorderPane.setMargin(vbContent, new Insets(0, marginMenu, 0, 0));
        allElements.setTop(bpTitle);
        allElements.setLeft(vbMenu);
        allElements.setRight(vbContent);

        pane.getChildren().add(allElements);

        btnAddress.setOnMouseClicked(
                e -> go2Page(addressPage));
        btnData.setOnMouseClicked(
                e -> go2Page(dataPage));
        btnOrderRecord.setOnMouseClicked(
                e -> go2Page(pageOrderRecord));
        btnBack.setOnMouseClicked(
                e -> go2Page(pageBack));
        isDisableButtons.addListener((observable, oldValue, newValue) ->
        {
            btnBack.setDisable(newValue);
            btnAddress.setDisable(newValue);
            btnData.setDisable(newValue);
            btnOrderRecord.setDisable(newValue);
        });

    }

    private void setBtnBackStyle(Button btnBack)
    {
        Image imgGoBackBtn = new Image(getClass().getResource("image/goBack.png").toString());
        ImageView ivGoBackBtn = new ImageView(imgGoBackBtn);
        int widthHeight = 25;
        ivGoBackBtn.setFitHeight(widthHeight);
        ivGoBackBtn.setFitWidth(widthHeight);

        btnBack.setStyle("-fx-border-color: rgba(255,255,255,0);" +
                "-fx-background-color: rgba(255,255,255,0);");

        btnBack.setGraphic(ivGoBackBtn);
    }

    private void go2Page(String page)
    {
        vbContent.getChildren().clear();
        switch (page)
        {
            case "clientAddress"     -> winAddressClient.addElements(vbContent);
            case "clientAccountData" -> winAccountClient.addElements(vbContent);
            case "storeAddress"      -> winAddressStore.addElements(vbContent);
            case "storeAccountData"  -> winAccountStore.addElements(vbContent);
            // TODO: implementar transicao entre paginas
            case "orderRecord" -> System.out.println("Vai pra pagina de histórioco");
            case "goBack" -> System.out.println("Volta pra outro pagina!");
        }
    }
}
