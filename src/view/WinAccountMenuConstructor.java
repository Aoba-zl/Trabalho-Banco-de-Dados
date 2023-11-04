package view;

import utils.Constants;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
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

    private VBox vbContent;
    private Label lblPopUpMessage = new Label();
    private StringProperty messagePopUp = new SimpleStringProperty(null);
    private BooleanProperty isPopupActive = new SimpleBooleanProperty(false);
    private BooleanProperty returnPopUp = new SimpleBooleanProperty(false);

    WinAccountMenuConstructor (String user)
    {
        this.user = user;
        this.user = "client";
    }

    public void addElements(Pane pane)
    {
        double marginMenu = (Constants.WIDTH * 0.08);
        Button btnBack = new Button();
        setBtnBackImage(btnBack);
        String styleEnter = "-fx-border-color: rgba(255,255,255,0); -fx-cursor: hand; " +
                "-fx-background-color: rgba(94,94,94,0.26); -fx-background-radius: 1000px";
        String styleExit = "-fx-border-color: rgba(255,255,255,0); -fx-cursor: hand; " +
                "-fx-background-color: rgba(255,255,255,0);";
        setOverButtonStyle(btnBack, styleEnter, styleExit);

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
        styleEnter = "-fx-background-color: rgba(0,0,0,0); -fx-text-fill: purple; -fx-cursor: hand;" +
                "-fx-font-weight: bold; -fx-underline: true";
        styleExit = "-fx-background-color: rgba(0,0,0,0); -fx-text-fill: blue; -fx-cursor: hand;" +
                "-fx-font-weight: bold";
        setOverButtonStyle(btnOrderRecord, styleEnter, styleExit);
        Pane panePopup = setPopUp();

        String dataPage;
        String addressPage;
        if (user.equalsIgnoreCase("store"))
        {
            dataPage = "storeAccountData";
            addressPage = "storeAddress";

        } else {
            dataPage = "clientAccountData";
            addressPage = "clientAllAddress";
        }

        openWinClientMenu();

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

        isPopupActive.addListener((observable, oldValue, newValue) ->
        {
            if (newValue)
            {
                lblPopUpMessage.setText(messagePopUp.getValue());
                pane.getChildren().add(panePopup);
            }
            else
                pane.getChildren().remove(panePopup);
        });

    }

    private void setBtnStyle(Button button, String style)
    {
        button.setStyle(style);
    }

    private void setBtnBackImage(Button btnBack)
    {
        Image imgGoBackBtn = new Image(getClass().getResource("image/goBack.png").toString());
        ImageView ivGoBackBtn = new ImageView(imgGoBackBtn);
        int widthHeight = 25;
        ivGoBackBtn.setFitHeight(widthHeight);
        ivGoBackBtn.setFitWidth(widthHeight);

        btnBack.setGraphic(ivGoBackBtn);
    }

    private void setOverButtonStyle(Button button, String styleEnter, String styleExit)
    {
        button.setOnMouseEntered(e -> setBtnStyle(button, styleEnter));
        button.setOnMouseExited(e -> setBtnStyle(button, styleExit));
        button.setStyle(styleExit);
    }

    private Pane setPopUp()
    {
        Pane transparentPane = new Pane();
        BorderPane popUp = new BorderPane();

        transparentPane.setPrefSize(640, 400);
        transparentPane.setStyle("-fx-background-color: rgba(0, 0, 0, 0.4)");

        popUp.setPrefSize(200, 130);
        popUp.setStyle("-fx-background-color: white; -fx-background-radius: 10px;" +
                "-fx-border-color: black; -fx-border-width: 2px; -fx-border-radius: 10px");
        popUp.setLayoutX(220);
        popUp.setLayoutY(135);

        lblPopUpMessage.setStyle("-fx-wrap-text: true;");

        BorderPane buttons = new BorderPane();
        Button btnCancel = new Button("Cancelar");
        btnCancel
                .setStyle( "-fx-background-color: #FF9999; -fx-text-fill: white;" +
                        "-fx-border-color: #ff6b6b; -fx-border-radius: 8px; -fx-background-radius: 8px;");
        Button btnConfirm = new Button("Confirmar");
        btnConfirm
                .setStyle( "-fx-background-color: #59cdff; -fx-text-fill: white;" +
                        "-fx-border-color: #00bbff; -fx-border-radius: 8px; -fx-background-radius: 8px;");
        buttons.setLeft(btnCancel);
        buttons.setRight(btnConfirm);

        popUp.setTop(lblPopUpMessage);
        popUp.setBottom(buttons);
        popUp.setPadding(new Insets(15));
        transparentPane.getChildren().add(popUp);

        transparentPane.setOnMouseClicked(e -> isPopupActive.setValue(false));
        btnCancel.setOnMouseClicked(e -> isPopupActive.setValue(false));
        btnConfirm.setOnMouseClicked(e ->
        {
            isPopupActive.setValue(false);
            returnPopUp.setValue(true);
        });

        return transparentPane;
    }

    private void go2Page(String page)
    {
        vbContent.getChildren().clear();
        switch (page)
        {
            case "clientAllAddress"  -> openWinAddressClientMenu();
            case "clientAccountData" -> openWinClientMenu();
            case "storeAddress"      -> openWinAddressStoreMenu();
            case "storeAccountData"  -> openWinStoreMenu();
            // TODO: implementar transicao entre paginas
            case "orderRecord" -> System.out.println("Vai pra pagina de histórioco");
            case "goBack" -> System.out.println("Volta pra outro pagina!");
        }
    }

    private void openWinClientMenu()
    {
        WinAccountClientConstructor win = new WinAccountClientConstructor(vbContent);

        Bindings.bindBidirectional(messagePopUp, win.getMessageMenuPopUp());
        Bindings.bindBidirectional(isPopupActive, win.getIsMenuPopupActive());
        Bindings.bindBidirectional(returnPopUp, win.getReturnPopUp());
    }

    private void openWinAddressClientMenu()
    {
        WinAllAddressClientConstructor win = new WinAllAddressClientConstructor(vbContent);

        Bindings.bindBidirectional(messagePopUp, win.getMessageMenuPopUp());
        Bindings.bindBidirectional(isPopupActive, win.getIsMenuPopupActive());
        Bindings.bindBidirectional(returnPopUp, win.getReturnPopUp());
    }
    private void openWinStoreMenu()
    {
        WinAccountStoreConstructor win = new WinAccountStoreConstructor(vbContent);

        Bindings.bindBidirectional(messagePopUp, win.getMessageMenuPopUp());
        Bindings.bindBidirectional(isPopupActive, win.getIsMenuPopupActive());
        Bindings.bindBidirectional(returnPopUp, win.getReturnPopUp());
    }

    private void openWinAddressStoreMenu()
    {
        WinAddressStoreConstructor win = new WinAddressStoreConstructor(vbContent);

        Bindings.bindBidirectional(messagePopUp, win.getMessageMenuPopUp());
        Bindings.bindBidirectional(isPopupActive, win.getIsMenuPopupActive());
        Bindings.bindBidirectional(returnPopUp, win.getReturnPopUp());
    }
}
