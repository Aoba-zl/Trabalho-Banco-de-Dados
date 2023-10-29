package view;

import Utils.Constants;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class WinAccountMenuConstructor
{
    private final String pageBack = "PlaceHolder";
    private final String pageOrderRecord = "PlaceHolder";

    private final WindowAccountClientConstructor windowAccountClient = new WindowAccountClientConstructor();
    private final WinAccountStoreConstructor winAccountStore = new WinAccountStoreConstructor();
    private final WinAddressStoreConstructor winAddressStore = new WinAddressStoreConstructor();
    private final WinAddressClientConstructor winAddressClient = new WinAddressClientConstructor();
    private final WinAllAddressClientConstructor winAllAddressClient = new WinAllAddressClientConstructor();

    public void addElements(Pane pane)
    {
        double marginMenu = (Constants.WIDTH * 0.08);
        Button btnVoltar = new Button("Voltar");
        Label lblTitulo = new Label("Conta");
        lblTitulo.setStyle("-fx-font-weight: bold; -fx-font-size: 20;");

        BorderPane  bpTitle = new BorderPane();
        VBox vbMenuTop = new VBox(5);
        VBox vbMenu = new VBox(40);
        VBox vbContent = new VBox(5);
        BorderPane allElements = new BorderPane();

        vbMenu
                .setStyle("-fx-border-color: black; -fx-border-width: 2px; -fx-border-radius: 8px;" +
                        "-fx-background-radius: 8px; -fx-padding: 20px; ");
        vbContent
                .setStyle("-fx-border-color: black; -fx-border-width: 2px; -fx-border-radius: 8px;" +
                        "-fx-background-radius: 8px; -fx-padding: 20px; ");

        //TODO: colocar elementos
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

        //TODO: Definir eventos

        //TODO: Add os elementos aos layouts
        vbContent.setPrefWidth(Constants.WIDTH * 0.60);
        windowAccountClient.addElements(vbContent);
//        winAddressClient.addElements(vbContent);
//        winAllAddressClient.addElements(vbContent);

//        winAccountStore.addElements(vbContent);
//        winAddressStore.addElements(vbContent);



        allElements.setPrefWidth(Constants.WIDTH);
        bpTitle.setLeft(btnVoltar);
        bpTitle.setCenter(lblTitulo);
        vbMenuTop.getChildren().addAll(btnData, btnAddress);
        vbMenuTop.setAlignment(Pos.TOP_CENTER);
        vbMenu.getChildren().addAll(vbMenuTop, btnOrderRecord);
        vbMenu.setAlignment(Pos.TOP_CENTER);

        vbContent.setAlignment(Pos.TOP_CENTER);

        BorderPane.setMargin(bpTitle, new Insets(0, 0, 10, 0));
        BorderPane.setMargin(vbMenu, new Insets(0, 0, 0, marginMenu));
        BorderPane.setMargin(vbContent, new Insets(0, marginMenu, 0, 0));
        allElements.setTop(bpTitle);
        allElements.setLeft(vbMenu);
        allElements.setRight(vbContent);

        pane.getChildren().add(allElements);
    }

    private void go2Page(String page)
    {
        // TODO: Implementar go2Page
    }
}
