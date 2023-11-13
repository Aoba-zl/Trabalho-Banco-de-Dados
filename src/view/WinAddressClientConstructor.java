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
import model.ClientAddress;
import utils.UserSession;

public class WinAddressClientConstructor
{
    private String textBtnEditAccount;
    private TextField tfName, tfCep, tfCityEstate, tfNeighborhood,
            tfStreet, tfNumber, tfComplement;
    private Button btnEditAccount, btnCancel;

    private final StringProperty messageMenuPopUp = new SimpleStringProperty(null);
    private final BooleanProperty isMenuPopupActive = new SimpleBooleanProperty(false);
    private final BooleanProperty returnPopUp = new SimpleBooleanProperty(false);
    private final BooleanProperty editionMode = new SimpleBooleanProperty(true);
    private StringProperty action = new SimpleStringProperty(null);
    private final VBox mainBox;
    private String userName;
    private AddressMenuController control = new AddressMenuController();
    private ClientAddress selectedAddress;

    public WinAddressClientConstructor(VBox mainBox, ClientAddress selectedAddress)
    {
        this.mainBox = mainBox;
        this.selectedAddress = selectedAddress;
        userName = UserSession.getUserName();
        textBtnEditAccount = "Editar";

        setElements();
        setEvents();
        setPropertiesConnections();

        control.fillClientAddressFields(this.selectedAddress);
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
            openPopUp("Tem certeza de que deseja alterar o endereço?");
        });

        returnPopUp.addListener(((observable, oldValue, newValue) ->
        {
            if (newValue)
            {
                if (action.getValue() == "edit")
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

        tfName         = new TextField();
        tfCep          = new TextField();
        tfCep.setDisable(true);
        tfCityEstate = new TextField();
        tfCityEstate.setDisable(true);
        tfNeighborhood = new TextField();
        tfNeighborhood.setDisable(true);
        tfStreet       = new TextField();
        tfStreet.setDisable(true);
        tfNumber       = new TextField();
        tfNumber.setDisable(true);
        tfComplement   = new TextField();


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
                bpStreet, bpNumber, bpComplemet, bpButtons);
    }

    private void openPopUp(String message)
    {
        isMenuPopupActive.setValue(false);
        messageMenuPopUp.setValue(message);
        isMenuPopupActive.setValue(true);
    }

    StringProperty getMessageMenuPopUp() { return messageMenuPopUp; }
    BooleanProperty isMenuPopupActiveProperty() { return isMenuPopupActive; }
    BooleanProperty returnPopUpProperty() { return returnPopUp; }
    BooleanProperty editionModeProperty() { return editionMode; }
    StringProperty actionProperty() { return action; }
}
