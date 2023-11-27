package view;

import control.AccountMenuController;
import control.ChangeSceneController;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import utils.SceneName;
import utils.UserSession;

import java.sql.SQLException;

/**
 * Classe responsável por construir e adicionar os elementos a interface gráfica de
 * gerenciamento de conta da Loja.
 * Implementa a interface GerericAccountMenuWinInterface.
 */
public class WinAccountStoreConstructor implements GerericAccountMenuWinInterface
{
    private Label lblErrorMsg;
    private TextField tfLogin, tfName, tfCnpj, tfEmail, tfPhone;
    private Button btnDeleteAccount, btnEditAccount, btnCancelEdit;
    private BorderPane bpButtons;
	
    private final StringProperty messageMenuPopUp = new SimpleStringProperty(null);
    private final BooleanProperty isMenuPopupActive = new SimpleBooleanProperty(false);
    private final BooleanProperty returnPopUp = new SimpleBooleanProperty(false);
    private VBox mainBox;
    private String action = null;
    private String userName;
    
    private final AccountMenuController control = new AccountMenuController();
    private final Pane pWin;
    
    ChangeSceneController changeSceneController = new ChangeSceneController();
    /**
     * Construtor da classe.
     *
     * @param mainPane O painel principal da interface gráfica.
     */
    public WinAccountStoreConstructor(Pane mainPane)
    {
        this.pWin = mainPane;
    }

    @Override
    public void addElements(VBox mainBox)
    {
        this.mainBox = mainBox;
        userName = UserSession.getUserName();
        setElements();
        setEvents();
        setPropertiesConnections();

        completeFields();
    }

    private void completeFields()
    {
        control.completeStoreFields(userName);

        lblErrorMsg.setVisible(false);
        setTextFieldNormalStyle(tfPhone);
        setTextFieldNormalStyle(tfName);
        setTextFieldNormalStyle(tfEmail);
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
            completeFields();
            action = null;
        });

        btnDeleteAccount.setOnMouseClicked(
                e -> btnDeleteClicked());
        
        returnPopUp.addListener(((observable, oldValue, newValue) ->
        {
            String msg = null;
            if (newValue && action == "edit")
            {
                msg = "Dados Alterados com sucesso!";
                if (fieldsAreValid())
                {
                    setDisableEditableFields(true);
                    changeCancelDeleteButtons(btnDeleteAccount);
                    control.editAccount(userName);
                }
                else
                    msg = "Dados Incorretos.\nPor favor, tente novamente.";
                openPopUp(msg);
            }
            else if (newValue && action == "delete")
            {
                msg = "Dados deletados com sucesso!";
                try
                {
                    control.deleteAccount(userName);
                    UserSession.clearSession();
                    changeSceneController.changeScene(SceneName.LOGIN, this.pWin);
                }
                catch (SQLException e)
                {
                    msg = "Houve um erro inesperado.\nPor favor, tente novamente\n";
                }
                finally
                {
                    openPopUp(msg);
                }
            }
            action = null;
        }));

        tfName.setOnKeyTyped(event ->
        {
            int len = tfName.getText().length();

            if (len > 0)
                setTextFieldNormalStyle(tfName);
            else
                setTextFieldErrorStyle(tfName);

        });

        tfEmail.setOnKeyTyped(event ->
        {
            int len = tfEmail.getText().length();
            String text = tfEmail.getText();

            if (len > 0 && text.contains("@") &&
                    text.substring(len-4).contains(".com"))
            {
                lblErrorMsg.setVisible(false);
                setTextFieldNormalStyle(tfEmail);
            }
            else
            {
                lblErrorMsg.setVisible(true);
                lblErrorMsg.setText("e-mail Invalido!");
                setTextFieldErrorStyle(tfEmail);
            }

        });

        tfPhone.setOnKeyTyped(event ->
        {
            int len = tfPhone.getText().length();

            if (len == 9 && tfPhone.getText().matches("\\d*"))
            {
                lblErrorMsg.setVisible(false);
                setTextFieldNormalStyle(tfPhone);
            }
            else
            {
                lblErrorMsg.setVisible(true);
                lblErrorMsg.setText("Telefone Invalido!");
                setTextFieldErrorStyle(tfPhone);
            }

        });
    }

    private void setTextFieldErrorStyle(TextField tf)
    {
        tf.setStyle("-fx-font-style: italic; -fx-text-fill: red");
    }
    private void setTextFieldNormalStyle(TextField tf)
    {
        tf.setStyle("");
    }

    private boolean fieldsAreValid()
    {
        int socialName = tfName.getStyle().length();
        int email = tfEmail.getStyle().length();
        int phone = tfPhone.getStyle().length();
        return (socialName == 0 && email == 0 && phone == 0);
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
        lblErrorMsg = new Label();
        lblErrorMsg.setStyle("-fx-font-weight: bold; -fx-font-style: italic; -fx-text-fill: red");
        lblErrorMsg.setVisible(false);
        
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
                bpBirthDate, bpButtons, lblErrorMsg);
    }

    private void openPopUp(String message)
    {
        messageMenuPopUp.setValue(message);
        isMenuPopupActive.setValue(true);
    }

    private void btnEditClicked()
    {
        returnPopUp.setValue(false);
        if (action == "edit")
            openPopUp("Tem certeza de que deseja alterar a conta?");
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
        openPopUp("Tem certeza de que deseja deletar a conta?");
    }
    
    private void setDisableEditableFields(boolean isDisable)
    {
        tfName.setDisable(isDisable);
        tfEmail.setDisable(isDisable);
        tfPhone.setDisable(isDisable);
    }

    private void changeCancelDeleteButtons(Button button)
    {
        bpButtons.setLeft(button);
    }

    /**
     * Obtém a propriedade que contém a mensagem exibida no menu pop-up.
     *
     * @return A propriedade que contém a mensagem exibida no menu pop-up.
     */
    StringProperty getMessageMenuPopUp() { return messageMenuPopUp; }
    /**
     * Obtém a propriedade que indica se o menu pop-up está ativo.
     *
     * @return A propriedade que indica se o menu pop-up está ativo.
     */
    BooleanProperty getIsMenuPopupActive() { return isMenuPopupActive; }
    /**
     * Obtém a propriedade que indica se houve um retorno no menu pop-up.
     *
     * @return A propriedade que indica se houve um retorno no menu pop-up.
     */
    BooleanProperty getReturnPopUp() { return returnPopUp; }
}
