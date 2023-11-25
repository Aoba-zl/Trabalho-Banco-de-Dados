package view;

import control.AddressMenuController;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.util.converter.IntegerStringConverter;
import model.ClientAddress;
import utils.UserSession;

/**
 * Classe responsável por construir e adicionar os elementos a interface gráfica de
 * gerenciamento de um endereço espacífico do cliente.
 */
@SuppressWarnings("ALL")
public class WinAddressClientConstructor
{
    private String textBtnEditAccount;
    private Label lblErrorMsg;
    private TextField tfName, tfCep, tfCityEstate, tfNeighborhood,
            tfStreet, tfNumber, tfComplement;
    private Button btnEditAccount, btnCancel;

    private final StringProperty messageMenuPopUp = new SimpleStringProperty(null);
    private final BooleanProperty isMenuPopupActive = new SimpleBooleanProperty(false);
    private final BooleanProperty returnPopUp = new SimpleBooleanProperty(false);
    private final BooleanProperty editionMode = new SimpleBooleanProperty(true);
    private final StringProperty action = new SimpleStringProperty(null);
    private final VBox mainBox;
    private final String userName;
    private final AddressMenuController control = new AddressMenuController();
    private ClientAddress selectedAddress;
    private String errorMsg = "";

    /**
     * Construtor da classe.
     *
     * @param mainBox          O VBox principal da interface gráfica.
     */
    public WinAddressClientConstructor(VBox mainBox)
    {
        this.mainBox = mainBox;
        userName = UserSession.getUserName();
    }

    /**
     * Adiciona elementos ao contêiner especificado no construtor.
     */
    public void addElements(ClientAddress selectedAddress)
    {
        this.selectedAddress = selectedAddress;

        System.out.println(actionValue());

        if (actionValue() == ("edit"))
            textBtnEditAccount = "Editar";
        else if (actionValue() == ("add"))
            textBtnEditAccount = "Adicionar";

        setElements();
        setEvents();
        setPropertiesConnections();
        if (action.getValue() == ("edit"))
        {
            control.fillClientAddressFields(this.selectedAddress);
            setTextFieldNormalStyle(tfNumber);
            setTextFieldNormalStyle(tfCep);
            setTextFieldNormalStyle(tfName);
        }
        else if (actionValue() == ("add"))
        {
            setTextFieldErrorStyle(tfCep);
            setTextFieldErrorStyle(tfNumber);
            setTextFieldErrorStyle(tfName);
            clearFields();
        }
    }

    private void clearFields()
    {
        tfName.setText("");
        tfCep.setText("");
        tfCityEstate.setText("");
        tfNeighborhood.setText("");
        tfStreet.setText("");
        tfNumber.setText("");
        tfComplement.setText("");
    }

    private void setPropertiesConnections()
    {
        Bindings.bindBidirectional(tfName.textProperty()           ,  control.getNameProperty());
        Bindings.bindBidirectional(tfCep.textProperty()            ,  control.getCepProperty());
        Bindings.bindBidirectional(tfCityEstate.textProperty()     ,  control.getCityEstateProperty());
        Bindings.bindBidirectional(tfNeighborhood.textProperty()   ,  control.getNeighborhoodProperty());
        Bindings.bindBidirectional(tfStreet.textProperty()         ,  control.getStreetProperty());
        Bindings.bindBidirectional(tfNumber.textProperty()         ,  control.getNumberProperty());
        Bindings.bindBidirectional(tfComplement.textProperty()     ,  control.getComplementProperty());
    }

    private void setEvents()
    {
        btnCancel.setOnMouseClicked(
                e -> editionMode.setValue(false));
        btnEditAccount.setOnMouseClicked(e ->
        {
            returnPopUp.setValue(false);
            openPopUp("Tem certeza?");
        });

        tfNumber.setOnKeyTyped(event ->
        {
            if (tfNumber.getText().length() > 0 && tfNumber.getText().length() <= 7)
                setTextFieldNormalStyle(tfNumber);
            else
                setTextFieldErrorStyle(tfNumber);
            if (tfNumber.getText().length() > 7)
            {
                lblErrorMsg.setVisible(true);
                lblErrorMsg.setText("Número de Porta inválido!");
            }
            else
                lblErrorMsg.setVisible(false);
        });

        tfName.setOnKeyTyped(event ->
        {
            if (tfName.getText().length() > 0 && tfName.getText().length() <= 100)
                setTextFieldNormalStyle(tfName);
            else
                setTextFieldErrorStyle(tfName);
            if (tfName.getText().length() > 100)
            {
                lblErrorMsg.setVisible(true);
                lblErrorMsg.setText("Nome de endereço INVÁLIDO!");
            }
            else
                lblErrorMsg.setVisible(false);
        });

        tfCep.setOnKeyTyped(event ->
        {
            if (!cepLengthIsValid())
            {
                lblErrorMsg.setVisible(false);
                tfCityEstate.setText("");
                tfNeighborhood.setText("");
                tfStreet.setText("");
                setTextFieldErrorStyle(tfCep);
            }
            if (actionValue() == ("add") && cepLengthIsValid())
            {
                try
                {
                    lblErrorMsg.setVisible(false);
                    control.completeAddressByCep();
                    setTextFieldNormalStyle(tfCep);
                }
                catch (Exception e)
                {
                    lblErrorMsg.setVisible(true);
                    lblErrorMsg.setText("Erro com endereço!");
                    if (e.getMessage().contains("Invalid Cep"))
                        lblErrorMsg.setText("CEP Invalido!!!");
                    setTextFieldErrorStyle(tfCep);
                }
            }
        });

        returnPopUp.addListener(((observable, oldValue, newValue) ->
        {
            if (newValue)
            {
                if (actionValue() == ("edit"))
                {
                    ClientAddress newaddr = control.editClientAddress(selectedAddress, userName);
                    if (newaddr == null)
                        openPopUp("Houve um erro inesperado.\nPor favor, tente novamente");
                    else
                    {
                        selectedAddress.setName(newaddr.getName());
                        selectedAddress.setNeighborhood(newaddr.getNeighborhood());
                        selectedAddress.setStreet(newaddr.getStreet());
                        selectedAddress.setEstate(newaddr.getEstate());
                        selectedAddress.setCity(newaddr.getCity());
                        selectedAddress.setCep(newaddr.getCep());
                        selectedAddress.setComplement(newaddr.getComplement());
                        selectedAddress.setDoorNumber(newaddr.getDoorNumber());

                        openPopUp("Editado com Sucesso");
                        editionMode.setValue(false);
                    }
                }
                else if (actionValue() == ("add"))
                {
                    String msg = "Endereço adicionado com Sucesso!";
                    if (addressIsValid())
                    {
                        try
                        {
                            ClientAddress newaddr = control.newClientAddress(userName);
                            selectedAddress.setName(newaddr.getName());
                            selectedAddress.setNeighborhood(newaddr.getNeighborhood());
                            selectedAddress.setStreet(newaddr.getStreet());
                            selectedAddress.setEstate(newaddr.getEstate());
                            selectedAddress.setCity(newaddr.getCity());
                            selectedAddress.setCep(newaddr.getCep());
                            selectedAddress.setComplement(newaddr.getComplement());
                            selectedAddress.setDoorNumber(newaddr.getDoorNumber());
                            editionMode.setValue(false);
                        }
                        catch (Exception e)
                        {
                            msg = "Houve um erro inesperado.\nPor favor, tente novamente";
                            if (e.getMessage().contains("Violação da restrição PRIMARY KEY"))
                                msg = "Endereço Duplicado!\nPor favor, tente novamente";
                        }
                    }
                    else
                        msg = "Dados Incorretos.\nPor favor, tente novamente";
                    openPopUp(msg);
                }
            }
        }));
    }

    private void setElements()
    {
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

        lblErrorMsg     = new Label("ERRO");
        lblErrorMsg.setStyle("-fx-font-weight: bold; -fx-font-style: italic; -fx-text-fill: red");
        lblErrorMsg.setVisible(false);

        tfName         = new TextField();
        tfCep          = new TextField();
        tfCityEstate = new TextField();
        tfNeighborhood = new TextField();
        tfStreet       = new TextField();
        tfNumber       = new TextField();
        tfComplement   = new TextField();

        TextFormatter<Integer> textFormatterNumericCEP = new TextFormatter<>(new IntegerStringConverter(), 0,
                change -> {
                    String newText = change.getControlNewText();
                    if (newText.matches("\\d*")) {
                        return change;
                    }
                    return null;
                });
        TextFormatter<Integer> textFormatterNumericDoorNmbr = new TextFormatter<>(new IntegerStringConverter(), 0,
                change -> {
                    String newText = change.getControlNewText();
                    if (newText.matches("\\d*")) {
                        return change;
                    }
                    return null;
                });

        tfCep.setTextFormatter(textFormatterNumericCEP);
        tfNumber.setTextFormatter(textFormatterNumericDoorNmbr);

        tfCityEstate.setDisable(true);
        tfNeighborhood.setDisable(true);
        tfStreet.setDisable(true);
        if (actionValue() == ("edit"))
        {
            tfCep.setDisable(true);
            tfNumber.setDisable(true);
        }

        btnEditAccount   = new Button(textBtnEditAccount);
        btnEditAccount
                .setStyle("-fx-border-radius: 8px;-fx-background-radius: 8px");
        btnCancel   = new Button("Cancelar");
        btnCancel
                .setStyle( "-fx-background-color: #fd7171; -fx-text-fill: white;" +
                        "-fx-border-radius: 8px; -fx-background-radius: 8px;");

        bpName.setLeft(lblName);
        bpName.setRight(tfName);
        bpCep.setLeft(lblCep);
        bpCep.setRight(tfCep);
        bpEstateCity.setLeft(lblEstateCity);
        bpEstateCity.setRight(tfCityEstate);
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
                bpStreet, bpNumber, bpComplemet, bpButtons, lblErrorMsg);
    }

    private void openPopUp(String message)
    {
        isMenuPopupActive.setValue(false);
        messageMenuPopUp.setValue(message);
        isMenuPopupActive.setValue(true);
    }

    private boolean cepLengthIsValid()
    {
        String cep = tfCep.getText();
        int len = cep.length();
        return (len == 8);
    }

    private void setTextFieldErrorStyle(TextField tf)
    {
        tf.setStyle("-fx-font-style: italic; -fx-text-fill: red");
    }
    private void setTextFieldNormalStyle(TextField tf)
    {
        tf.setStyle("");
    }

    private boolean addressIsValid()
    {
        int cep = tfCep.getStyle().length();
        int name = tfName.getStyle().length();
        int doorNumber = tfNumber.getStyle().length();
        return (cep == 0 && name == 0 && doorNumber == 0);
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
    BooleanProperty isMenuPopupActiveProperty() { return isMenuPopupActive; }
    /**
     * Obtém a propriedade que indica se houve um retorno no menu pop-up.
     *
     * @return A propriedade que indica se houve um retorno no menu pop-up.
     */
    BooleanProperty returnPopUpProperty() { return returnPopUp; }
    /**
     * Obtém a propriedade que indica se o modo de edição está ativo.
     *
     * @return A propriedade que indica se o modo de edição está ativo.
     */
    BooleanProperty editionModeProperty() { return editionMode; }
    /**
     * Obtém a propriedade que indica a ação em andamento.
     *
     * @return A propriedade que indica a ação em andamento.
     */
    StringProperty actionProperty() { return action; }

    private String actionValue() { return action.getValue(); }
}
