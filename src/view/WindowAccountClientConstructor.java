package view;

import Utils.Constants;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class WindowAccountClientConstructor
{
    public void addElements(VBox mainBox)
    {
        Label lblTitle = new Label("Dados");
        lblTitle.setStyle("-fx-font-weight: bold; -fx-font-size: 16px;" +
                "-fx-label-padding: 0px 0px 15px 0px");

        BorderPane bpLogin     = new BorderPane();
        BorderPane bpName      = new BorderPane();
        BorderPane bpCpf       = new BorderPane();
        BorderPane bpEmail     = new BorderPane();
        BorderPane bpPhone     = new BorderPane();
        BorderPane bpBirthDate = new BorderPane();
        BorderPane bpSex       = new BorderPane();
        BorderPane bpButtons   = new BorderPane();

        Label lblLogin     = new Label("Nome de Usuário: ");
        Label lblName      = new Label("Nome Social: ");
        Label lblCpf       = new Label("CPF: ");
        Label lblEmail     = new Label("eMail: ");
        Label lblPhone     = new Label("Telefone: ");
        Label lblBirthDate = new Label("Data de Nascimento: ");
        Label lblSex       = new Label("Sexo: ");

        TextField tfLogin     = new TextField();
        tfLogin.setDisable(true);
        TextField tfName      = new TextField();
        TextField tfCpf       = new TextField();
        tfCpf.setDisable(true);
        TextField tfEmail     = new TextField();
        TextField tfPhone     = new TextField();
        TextField tfBirthDate = new TextField();
        tfBirthDate.setDisable(true);

        TextField tfSex       = new TextField();
        tfSex.setVisible(false);
        RadioButton rbMale    = new RadioButton("Masculino");
        RadioButton rbFemale  = new RadioButton("Feminino");
        RadioButton rbOther   = new RadioButton("Outro");
        ToggleGroup group = new ToggleGroup();
        rbFemale.setToggleGroup(group);
        rbMale.setToggleGroup(group);
        rbOther.setToggleGroup(group);

        HBox sexs = new HBox(rbMale, rbFemale, rbOther);
        VBox rbSex = new VBox(10, sexs, tfSex);

        Button btnDeleteAccount = new Button("Excluir Conta");
        btnDeleteAccount
                .setStyle( "-fx-background-color: #ff5959; -fx-text-fill: white;" +
                        "-fx-border-color: #ff0000; -fx-border-radius: 8px; -fx-background-radius: 8px;");
        Button btnEditAccount   = new Button("Editar");
        btnEditAccount
                .setStyle("-fx-border-radius: 8px;-fx-background-radius: 8px");

        bpLogin.setLeft(lblLogin);
        bpLogin.setRight(tfLogin);
        bpName.setLeft(lblName);
        bpName.setRight(tfName);
        bpCpf.setLeft(lblCpf);
        bpCpf.setRight(tfCpf);
        bpEmail.setLeft(lblEmail);
        bpEmail.setRight(tfEmail);
        bpPhone.setLeft(lblPhone);
        bpPhone.setRight(tfPhone);
        bpBirthDate.setLeft(lblBirthDate);
        bpBirthDate.setRight(tfBirthDate);
        bpSex.setLeft(lblSex);
        bpSex.setRight(rbSex);

        bpButtons.setLeft(btnDeleteAccount);
        bpButtons.setRight(btnEditAccount);

        mainBox.getChildren().addAll(lblTitle, bpLogin, bpName, bpCpf, bpEmail, bpPhone,
                bpBirthDate, bpSex, bpButtons);
    }
}
