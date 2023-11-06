package view;

import control.CtrlAccountMenu;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import utils.UserSession;

public class WinAccountStoreConstructor
{
    private TextField tfLogin, tfName, tfCnpj, tfEmail, tfPhone;
    private Button btnDeleteAccount, btnEditAccount, btnCancelEdit;
    private BorderPane bpButtons;
	
    private final StringProperty messageMenuPopUp = new SimpleStringProperty(null);
    private final BooleanProperty isMenuPopupActive = new SimpleBooleanProperty(false);
    private final BooleanProperty returnPopUp = new SimpleBooleanProperty(false);
    private VBox mainBox;
    private String action = null;
    private String userName;
    
    private CtrlAccountMenu control = new CtrlAccountMenu();

    public WinAccountStoreConstructor(VBox mainBox)
    {
        this.mainBox = mainBox;
        userName = UserSession.getUserName();
        setElements();
        setEvents();
        setPropertiesConnections();
        
        control.completeStoreFields(userName);
    }

    private void setPropertiesConnections()
    {
    	Bindings.bindBidirectional(tfLogin.textProperty(),		control.getLoginProperty());
    	Bindings.bindBidirectional(tfName.textProperty(),		control.getNameProperty());
    	Bindings.bindBidirectional(tfCnpj.textProperty(),		control.getCpfCnpjProperty());
    	Bindings.bindBidirectional(tfEmail.textProperty(),		control.getEmailProperty());
    	Bindings.bindBidirectional(tfPhone.textProperty(),		control.getPhoneProperty());
    }
    
    private void setEvents()
    {
        btnEditAccount.setOnMouseClicked(e -> btnEditClicked());

        btnCancelEdit.setOnMouseClicked(e ->
        {
            changeCancelDeleteButtons(btnDeleteAccount);
            setDisableEditableFields(true);
            action = null;
        });

        btnDeleteAccount.setOnMouseClicked(
                e -> btnDeleteClicked());
        
        returnPopUp.addListener(((observable, oldValue, newValue) ->
        {
            if (newValue && action == "edit")
            {
                setDisableEditableFields(true);
                changeCancelDeleteButtons(btnDeleteAccount);
                control.editAccount(userName);
            }
            else if (newValue && action == "delete")
            {
            	control.deleteAccount(userName);
            }
            action = null;
        }));
    }
    
    private void setElements()
    {
    	Label lblTitle = new Label("Dados");
        lblTitle.setStyle("-fx-font-weight: bold; -fx-font-size: 16px;" +
                "-fx-label-padding: 0px 0px 15px 0px");

        BorderPane bpLogin     = new BorderPane();
        BorderPane bpName      = new BorderPane();
        BorderPane bpCnpj      = new BorderPane();
        BorderPane bpEmail     = new BorderPane();
        BorderPane bpPhone     = new BorderPane();
        BorderPane bpBirthDate = new BorderPane();
        bpButtons   = new BorderPane();

        Label lblLogin     = new Label("Nome de Usuário: ");
        Label lblName      = new Label("Nome da Loja: ");
        Label lblCnpj       = new Label("CNPJ: ");
        Label lblEmail     = new Label("eMail: ");
        Label lblPhone     = new Label("Telefone: ");
        
        tfLogin = new TextField();
        tfLogin.setDisable(true);
        tfName = new TextField();
        tfCnpj = new TextField();
        tfCnpj.setDisable(true);
        tfEmail = new TextField();
        tfPhone = new TextField();

        btnDeleteAccount = new Button("Excluir Conta");
        btnDeleteAccount
                .setStyle( "-fx-background-color: #ff5959; -fx-text-fill: white;" +
                        "-fx-border-color: #ff0000; -fx-border-radius: 8px; -fx-background-radius: 8px;");
        btnEditAccount   = new Button("Editar");
        btnEditAccount
                .setStyle("-fx-border-radius: 8px;-fx-background-radius: 8px");
        btnCancelEdit= new Button("Cancelar");
        btnCancelEdit
                .setStyle("-fx-border-radius: 8px;-fx-background-radius: 8px");

        bpLogin.setLeft(lblLogin);
        bpLogin.setRight(tfLogin);
        bpName.setLeft(lblName);
        bpName.setRight(tfName);
        bpCnpj.setLeft(lblCnpj);
        bpCnpj.setRight(tfCnpj);
        bpEmail.setLeft(lblEmail);
        bpEmail.setRight(tfEmail);
        bpPhone.setLeft(lblPhone);
        bpPhone.setRight(tfPhone);

        bpButtons.setLeft(btnDeleteAccount);
        bpButtons.setRight(btnEditAccount);

        setDisableEditableFields(true);
        
        mainBox.getChildren().addAll(lblTitle, bpLogin, bpName, bpCnpj, bpEmail, bpPhone,
                bpBirthDate, bpButtons);
    }
    
    private void btnEditClicked()
    {
        returnPopUp.setValue(false);
        if (action == "edit")
        {
            messageMenuPopUp.setValue("Tem certeza de que deseja alterar a conta?");
            isMenuPopupActive.setValue(true);
        }
        else
        {
            action = "edit";
            setDisableEditableFields(false);
            changeCancelDeleteButtons(btnCancelEdit);
        }
    }
    
    private void btnDeleteClicked()
    {
        returnPopUp.setValue(false);
        action = "delete";
        messageMenuPopUp.setValue("Tem certeza de que deseja deletar a conta?");
        isMenuPopupActive.setValue(true);
    }
    
    private void setDisableEditableFields(boolean isDisable)
    {
        tfName.setDisable(isDisable);
        tfEmail.setDisable(isDisable);
        tfPhone.setDisable(isDisable);
    }

    void changeCancelDeleteButtons(Button button)
    {
        bpButtons.setLeft(button);
    }
    
    StringProperty getMessageMenuPopUp() { return messageMenuPopUp; }
    BooleanProperty getIsMenuPopupActive() { return isMenuPopupActive; }
    BooleanProperty getReturnPopUp() { return returnPopUp; }
}
