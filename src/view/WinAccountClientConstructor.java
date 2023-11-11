package view;

import control.AccountMenuController;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import utils.UserSession;

public class WinAccountClientConstructor implements GerericAccountMenuWinInterface
{
    private TextField tfLogin, tfCpf, tfBirthDate, tfOtherSex, tfName, tfEmail, tfPhone;
    private StringProperty sexText = new SimpleStringProperty("");
    private ToggleGroup group;
    private BorderPane bpButtons;
    private RadioButton rbMale, rbFemale, rbOther;
    private Button btnDeleteAccount, btnCancelEdit, btnEditAccount;
    
    private final StringProperty messageMenuPopUp = new SimpleStringProperty(null);
    private final BooleanProperty isMenuPopupActive = new SimpleBooleanProperty(false);
    private final BooleanProperty returnPopUp = new SimpleBooleanProperty(false);
    private VBox mainBox;
    private String action = null;
    private String userName;
    
    private AccountMenuController control = new AccountMenuController();

    @Override
    public void addElements(VBox mainBox)
    {
    	this.mainBox = mainBox;
    	userName = UserSession.getUserName();
        setElements();
        setEvents();
        setPropertiesConnections();
        control.completeClientFields(userName);
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
        
        
        rbMale.selectedProperty().addListener(
                (observable, oldValue, newValue) -> sexText.setValue("masculino"));
        rbFemale.selectedProperty().addListener(
                (observable, oldValue, newValue) -> sexText.setValue("feminino"));
        rbOther.selectedProperty().addListener((observable, oldValue, newValue) -> 
        {
        	tfOtherSex.setVisible(newValue);
        });
        
        sexText.addListener(((observable, oldValue, newValue) ->
        {
        	if (newValue != null && newValue.length() > 1)
        	{
        		if (newValue.toUpperCase().charAt(0) == 'M')
        			rbMale.setSelected(true);
        		else if (newValue.toUpperCase().charAt(0) == 'F')
        			rbFemale.setSelected(true);
        		else
        		{
        			rbOther.setSelected(true);
        			tfOtherSex.setText(sexText.getValue());
        		}
        	}
        }
        ));
    }
    
    private void setElements()
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
        bpButtons   = new BorderPane();

        Label lblLogin     = new Label("Nome de Usuário: ");
        Label lblName      = new Label("Nome Social: ");
        Label lblCpf       = new Label("CPF: ");
        Label lblEmail     = new Label("eMail: ");
        Label lblPhone     = new Label("Telefone: ");
        Label lblBirthDate = new Label("Data de Nascimento: ");
        Label lblSex       = new Label("Sexo: ");

        tfLogin     = new TextField();
        tfLogin.setDisable(true);
        tfCpf       = new TextField();
        tfCpf.setDisable(true);
        tfBirthDate = new TextField();
        tfBirthDate.setDisable(true);
        tfName      = new TextField();
        tfEmail     = new TextField();
        tfPhone     = new TextField();

        tfOtherSex       = new TextField();
        tfOtherSex.setVisible(false);
                
        rbMale    = new RadioButton("Masculino");
        rbFemale  = new RadioButton("Feminino");
        rbOther   = new RadioButton("Outro");
        group = new ToggleGroup();
        rbFemale.setToggleGroup(group);
        rbMale.setToggleGroup(group);
        rbOther.setToggleGroup(group);

        HBox sexs = new HBox(rbMale, rbFemale, rbOther);
        VBox rbSex = new VBox(10, sexs, tfOtherSex);

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

        setDisableEditableFields(true);
        
        mainBox.getChildren().addAll(lblTitle, bpLogin, bpName, bpCpf, bpEmail, bpPhone,
                bpBirthDate, bpSex, bpButtons);

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
        tfOtherSex.setDisable(isDisable);
        group.getToggles()
                .forEach(toggle -> ((RadioButton)toggle).setDisable(isDisable));
    }

    void changeCancelDeleteButtons(Button button)
    {
        bpButtons.setLeft(button);
    }

    private void setPropertiesConnections()
    {
    	Bindings.bindBidirectional(tfLogin.textProperty(),		control.getLoginProperty());
    	Bindings.bindBidirectional(tfCpf.textProperty(),		control.getCpfCnpjProperty());
    	Bindings.bindBidirectional(tfBirthDate.textProperty(),	control.getDateBirthProperty());
    	Bindings.bindBidirectional(sexText,						control.getSexProperty());
    	Bindings.bindBidirectional(tfName.textProperty(),		control.getNameProperty());
    	Bindings.bindBidirectional(tfEmail.textProperty(),		control.getEmailProperty());
    	Bindings.bindBidirectional(tfPhone.textProperty(),		control.getPhoneProperty());
    	
    	Bindings.bindBidirectional(tfOtherSex.textProperty(), sexText);
    }
    
    BooleanProperty getIsMenuPopupActive()
    {
        return isMenuPopupActive;
    }

    StringProperty getMessageMenuPopUp()
    {
        return messageMenuPopUp;
    }

    BooleanProperty getReturnPopUp()
    {
        return returnPopUp;
    }
}
