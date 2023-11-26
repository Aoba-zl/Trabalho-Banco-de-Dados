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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import utils.UserSession;

import java.sql.SQLException;

/**
 * Classe responsável por construir e adicionar os elementos a interface gráfica de
 * gerenciamento do endereço da Loja.
 * Implementa a interface GerericAccountMenuWinInterface.
 */
public class WinAddressStoreConstructor implements GerericAccountMenuWinInterface
{
    private TextField tfCep, tfEstateCity, tfNeighborhood, tfStreet, tfNumber, tfComplement;
    private Button btnEditAddress, btnCancel;

    private final StringProperty messageMenuPopUp = new SimpleStringProperty(null);
    private final BooleanProperty isMenuPopupActive = new SimpleBooleanProperty(false);
    private final BooleanProperty returnPopUp = new SimpleBooleanProperty(false);
    private final AddressMenuController control = new AddressMenuController();

    private VBox mainBox;
    private boolean editMode = false;
    private String userLogin;

    @Override
    public void addElements(VBox mainBox)
    {
        this.mainBox = mainBox;
        userLogin = UserSession.getUserName();

        setElements();
        setEvents();
        setPropertiesConnections();
        control.fillStoreAddressFields(userLogin);
    }

    private void setEvents()
    {
        btnCancel.setOnMouseClicked(event ->
        {
            editMode = false;
            setEnableEditableElements(false);
            control.fillStoreAddressFields(userLogin);
        });
        btnEditAddress.setOnMouseClicked(event -> btnEditClicked());

        returnPopUp.addListener(((observable, oldValue, newValue) ->
        {
            if (newValue)
            {
                String msg = "Endereço editado com sucesso!";
                try {
                    control.editStoreAddress(userLogin);
                    editMode = false;
                    setEnableEditableElements(false);
                }
                catch (SQLException sqlException)
                {
                    msg = "Houve um erro inesperado.\nPor favor, tente novamente";
                    sqlException.printStackTrace();
                }
                finally
                {
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

        BorderPane bpCep          = new BorderPane();
        BorderPane bpEstateCity   = new BorderPane();
        BorderPane bpNeighborhood = new BorderPane();
        BorderPane bpStreet       = new BorderPane();
        BorderPane bpNumber       = new BorderPane();
        BorderPane bpComplemet    = new BorderPane();
        BorderPane bpButtons      = new BorderPane();

        Label lblCep          = new Label("CEP:");
        Label lblEstateCity   = new Label("Cidade (Estado):");
        Label lblNeighborhood = new Label("Bairro:");
        Label lblStreet       = new Label("Logradouro: ");
        Label lblNumber       = new Label("Numero da Porta:");
        Label lblComplement   = new Label("Complemento:");

        tfCep          = new TextField();
        tfEstateCity   = new TextField();
        tfNeighborhood = new TextField();
        tfStreet       = new TextField();
        tfNumber       = new TextField();
        tfComplement   = new TextField();

        tfComplement.setDisable(true);
        tfEstateCity.setDisable(true);
        tfCep.setDisable(true);
        tfNeighborhood.setDisable(true);
        tfStreet.setDisable(true);
        tfNumber.setDisable(true);

        btnEditAddress = new Button("Editar");
        btnEditAddress
                .setStyle("-fx-border-radius: 8px;-fx-background-radius: 8px");
        btnCancel   = new Button("Cancelar");
        btnCancel
                .setStyle( "-fx-background-color: #fd7171; -fx-text-fill: white;" +
                        "-fx-border-radius: 8px; -fx-background-radius: 8px;");
        btnCancel.setVisible(false);

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

        bpButtons.setRight(btnCancel);
        bpButtons.setCenter(btnEditAddress);

        mainBox.getChildren().addAll(lblTitle, bpCep, bpEstateCity, bpNeighborhood,
                bpStreet, bpNumber, bpComplemet, bpButtons);
    }

    private void setPropertiesConnections()
    {
        Bindings.bindBidirectional(tfComplement.textProperty(), control.getComplementProperty());
        Bindings.bindBidirectional(tfCep.textProperty(), control.getCepProperty());
        Bindings.bindBidirectional(tfNumber.textProperty(), control.getNumberProperty());
        Bindings.bindBidirectional(tfStreet.textProperty(), control.getStreetProperty());
        Bindings.bindBidirectional(tfNeighborhood.textProperty(), control.getNeighborhoodProperty());
        Bindings.bindBidirectional(tfEstateCity.textProperty(), control.getCityEstateProperty());
    }

    private void btnEditClicked()
    {
        returnPopUp.setValue(false);
        if (editMode)
            openPopUp("Tem certeza de que deseja editar?");
        editMode = true;
        setEnableEditableElements(true);
    }

    private void openPopUp(String message)
    {
        messageMenuPopUp.setValue(message);
        isMenuPopupActive.setValue(true);
    }

    private void setEnableEditableElements(boolean isEnable)
    {
        tfComplement.setDisable(!isEnable);
        btnCancel.setVisible(isEnable);
    }

    /**
     * Obtém a propriedade que contém a mensagem exibida no menu pop-up.
     *
     * @return A propriedade que contém a mensagem exibida no menu pop-up.
     */
    StringProperty getMessageMenuPopUp()
    {
        return messageMenuPopUp;
    }
    /**
     * Obtém a propriedade que indica se o menu pop-up está ativo.
     *
     * @return A propriedade que indica se o menu pop-up está ativo.
     */
    BooleanProperty getIsMenuPopupActive()
    {
        return isMenuPopupActive;
    }
    /**
     * Obtém a propriedade que indica se houve um retorno no menu pop-up.
     *
     * @return A propriedade que indica se houve um retorno no menu pop-up.
     */
    BooleanProperty getReturnPopUp()
    {
        return returnPopUp;
    }
}
