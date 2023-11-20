package view;

import javafx.beans.binding.Bindings;
import javafx.beans.property.*;
import model.ClientAddress;
import utils.Constants;
import utils.UserSession;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import control.AddressMenuController;

public class WinAllAddressClientConstructor implements GerericAccountMenuWinInterface
{
    private TableView<ClientAddress> tvAddress;
    private Button btnDelete, btnEdit, btnNew;
    private Label lblTitle;
    private BorderPane bpButtons;
    private static ClientAddress selectedAddress;
    private ObservableList<ClientAddress> observableAddressList = FXCollections.observableArrayList();

    private final StringProperty messageMenuPopUp = new SimpleStringProperty(null);
    private final BooleanProperty isMenuPopupActive = new SimpleBooleanProperty(false);
    private final BooleanProperty returnPopUp = new SimpleBooleanProperty(false);
    private final BooleanProperty editionMode = new SimpleBooleanProperty(false);
    private final StringProperty action = new SimpleStringProperty(null);
    private VBox mainBox;
    private String userName;

    private final AddressMenuController control = new AddressMenuController();

    private WinAddressClientConstructor winAddress;

    @Override
    public void addElements(VBox mainBox)
    {
        this.mainBox = mainBox;
        winAddress = new WinAddressClientConstructor(mainBox);
        userName = UserSession.getUserName();

        bindings();

        observableAddressList = control.getAddressList(userName);
        setElements();
        mainBox.getChildren().addAll(lblTitle, tvAddress, bpButtons);
        setEvents();
    }

    private void bindings()
    {
        Bindings.bindBidirectional(action, winAddress.actionProperty());
        Bindings.bindBidirectional(editionMode, winAddress.editionModeProperty());
        Bindings.bindBidirectional(messageMenuPopUp, winAddress.getMessageMenuPopUp());
        Bindings.bindBidirectional(isMenuPopupActive, winAddress.isMenuPopupActiveProperty());
        Bindings.bindBidirectional(returnPopUp, winAddress.returnPopUpProperty());
    }

    private void setEvents()
    {
        tvAddress.setOnMouseClicked(
                e -> {
                    selectedAddress = tvAddress.getSelectionModel().getSelectedItem();
                    setDisableButtons(false);
                }
        );

        editionMode.addListener((observable, oldValue, newValue) ->
        {
            if (!newValue)
            {
                mainBox.getChildren().clear();
                mainBox.getChildren().addAll(lblTitle, tvAddress, bpButtons);
                setDisableButtons(true);
                tvAddress.getSelectionModel().clearSelection();
            }
        });

        returnPopUp.addListener(((observable, oldValue, newValue) ->
        {
            if (newValue)
            {
                if (action.getValue() == "delete")
                {
                    boolean deleteStatus = control.deleteClientAddress(selectedAddress, userName);
                    if (!deleteStatus)
                        openPopUp("Houve um erro inesperado.\nPor favor, tente novamente");
                    else
                    {
                        openPopUp("Deletado com Sucesso");
                        observableAddressList.remove(selectedAddress);
                        setDisableButtons(true);
                        tvAddress.getSelectionModel().clearSelection();
                    }
                }
                if (action.getValue() == "edit")
                {
                    int i = observableAddressList.indexOf(selectedAddress);
                    observableAddressList.set(i, selectedAddress);
                }
                if (action.getValue() == "add")
                {
                    observableAddressList.add(selectedAddress);
                }
            }
            if (!editionMode.getValue())
                action.setValue(null);
        }));

        btnEdit.setOnMouseClicked(event ->
        {
            openEditSelectedAddress(selectedAddress, "edit");
        });
        btnDelete.setOnMouseClicked(event -> deleteSelectedAddress());
        btnNew.setOnMouseClicked(event ->
        {
            selectedAddress = new ClientAddress();
            openEditSelectedAddress(selectedAddress, "add");
        });
    }

    private void deleteSelectedAddress()
    {
        returnPopUp.setValue(false);
        String msg = "Deve haver ao menos 1 endereço!";
        if (observableAddressList.size() > 1)
        {
            action.setValue("delete");
            msg = "Tem certeza de que quer deleter o endereço?";
        }
        openPopUp(msg);
    }

    private void openPopUp(String message)
    {
        messageMenuPopUp.setValue(message);
        isMenuPopupActive.setValue(true);
    }

    private void setElements()
    {
        lblTitle = new Label("Endereço");
        lblTitle.setStyle("-fx-font-weight: bold; -fx-font-size: 16px;" +
                "-fx-label-padding: 0px 0px 15px 0px");

        bpButtons = new BorderPane();

        tvAddress = new TableView<>();

        TableColumn<ClientAddress, String> colName = new TableColumn<>("Nome");
        TableColumn<ClientAddress, String> colCEP = new TableColumn<>("CEP");
        TableColumn<ClientAddress, String> colNumber = new TableColumn<>("Número");
        TableColumn<ClientAddress, String> colComplement = new TableColumn<>("Complemento");

        colName.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getName()));
        colCEP.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getCep()));
        colNumber.setCellValueFactory(data -> new ReadOnlyStringWrapper(
                String.valueOf(data.getValue().getDoorNumber())
        ));
        colComplement.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getComplement()));
        tvAddress.getColumns().addAll(colName, colCEP, colNumber, colComplement);

        colName.setPrefWidth(Constants.WIDTH * 0.1);
        colCEP.setPrefWidth(Constants.WIDTH * 0.1);
        colNumber.setPrefWidth(Constants.WIDTH * 0.1);
        colComplement.setPrefWidth(Constants.WIDTH * 0.24);
        tvAddress.setPrefHeight(Constants.HEIGHT * 0.5);

        tvAddress.setItems(observableAddressList);

        btnDelete = new Button("Excluir");
        btnDelete
                .setStyle( "-fx-background-color: #ff5959; -fx-text-fill: white;" +
                        "-fx-border-color: #ff0000; -fx-border-radius: 8px; -fx-background-radius: 8px;");
        btnEdit   = new Button("Editar");
        btnEdit
                .setStyle("-fx-border-radius: 8px;-fx-background-radius: 8px");
        btnNew    = new Button("Adicionar Endereço");
        btnNew
                .setStyle("-fx-border-radius: 8px;-fx-background-radius: 8px");

        setDisableButtons(true);

        bpButtons.setLeft(btnDelete);
        bpButtons.setCenter(btnEdit);
        bpButtons.setRight(btnNew);

    }

    private void openEditSelectedAddress(ClientAddress address, String actionType)
    {
        editionMode.setValue(true);
        mainBox.getChildren().clear();

        action.setValue(actionType);
        winAddress.addElements(address);
    }

    StringProperty getMessageMenuPopUp()
    {
        return messageMenuPopUp;
    }
    BooleanProperty getIsMenuPopupActive()
    {
        return isMenuPopupActive;
    }
    BooleanProperty getReturnPopUp()
    {
        return returnPopUp;
    }

    private void setDisableButtons(boolean isDisable)
    {
        btnDelete.setDisable(isDisable);
        btnEdit.setDisable(isDisable);
    }
}
