package view;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class WinAddressClientConstructor
{
    private String textBtnEditAccount = "Editar";
    private final VBox mainBox;
    public WinAddressClientConstructor(VBox mainBox, String[] selectedAddress)
    {
        // TODO: Carregar endereço para edição
        this.mainBox = mainBox;
        Label lblTitle = new Label("Endereço");
        lblTitle.setStyle("-fx-font-weight: bold; -fx-font-size: 16px;" +
                "-fx-label-padding: 0px 0px 15px 0px");

        BorderPane bpName         = new BorderPane();
        BorderPane bpCep          = new BorderPane();
        BorderPane bpEstateCity   = new BorderPane();
        BorderPane bpNeighborhood = new BorderPane();
        BorderPane bpStreet       = new BorderPane();
        BorderPane bpNumber       = new BorderPane();
        BorderPane bpComplemet    = new BorderPane();
        BorderPane bpButtons      = new BorderPane();

        Label lblName         = new Label("Nome:");
        Label lblCep          = new Label("CEP:");
        Label lblEstateCity   = new Label("Cidade (Estado):");
        Label lblNeighborhood = new Label("Bairro:");
        Label lblStreet       = new Label("Logradouro: ");
        Label lblNumber       = new Label("Numero da Porta:");
        Label lblComplement   = new Label("Complemento:");

        TextField tfName         = new TextField();
        TextField tfCep          = new TextField();
        TextField tfEstateCity   = new TextField();
        tfEstateCity.setDisable(true);
        TextField tfNeighborhood = new TextField();
        TextField tfStreet       = new TextField();
        TextField tfNumber       = new TextField();
        TextField tfComplement   = new TextField();

        Button btnEditAccount   = new Button(textBtnEditAccount);
        btnEditAccount
                .setStyle("-fx-border-radius: 8px;-fx-background-radius: 8px");
        Button btnCancel   = new Button("Cancelar");
        btnCancel
                .setStyle( "-fx-background-color: #fd7171; -fx-text-fill: white;" +
                        "-fx-border-radius: 8px; -fx-background-radius: 8px;");

        bpName.setLeft(lblName);
        bpName.setRight(tfName);
        bpCep.setLeft(lblCep);
        bpCep.setRight(tfCep);
        bpEstateCity.setLeft(lblEstateCity);
        bpEstateCity.setRight(tfEstateCity);
        bpNeighborhood.setLeft(lblNeighborhood);
        bpNeighborhood.setRight(tfNeighborhood);
        bpStreet.setLeft(lblStreet);
        bpStreet.setRight(tfStreet);
        bpNumber.setLeft(lblNumber);
        bpNumber.setRight(tfNumber);
        bpComplemet.setLeft(lblComplement);
        bpComplemet.setRight(tfComplement);

        bpButtons.setCenter(btnEditAccount);
        bpButtons.setRight(btnCancel);

        mainBox.getChildren().addAll(lblTitle, bpName, bpCep, bpEstateCity, bpNeighborhood,
                bpStreet, bpNumber, bpComplemet, bpButtons);
    }
}
