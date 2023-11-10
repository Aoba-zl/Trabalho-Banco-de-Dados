package view;

import javafx.beans.binding.Bindings;
import utils.AddressColumnDataModel;
import utils.Constants;
import utils.UserSession;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.util.List;

import control.CtrlAddressMenu;

public class WinAllAddressClientConstructor implements GerericAccountMenuWinInterface
{
	private TableView<AddressColumnDataModel> tvAddress;
	private Button btnDelete, btnEdit, btnNew;
    private Label lblTitle;
    private BorderPane bpButtons;
    private String[] selectedAddress;
    private AddressColumnDataModel selectedDataModel;
    private ObservableList<AddressColumnDataModel> observableAddressList = FXCollections.observableArrayList();
    
    private final StringProperty messageMenuPopUp = new SimpleStringProperty(null);
    private final BooleanProperty isMenuPopupActive = new SimpleBooleanProperty(false);
    private final BooleanProperty returnPopUp = new SimpleBooleanProperty(false);
    private final BooleanProperty editionMode = new SimpleBooleanProperty(false);
    private VBox mainBox;
    private String action = null;
    private String userName;
    
    private CtrlAddressMenu control = new CtrlAddressMenu();

    @Override
    public void addElements(VBox mainBox)
    {
        this.mainBox = mainBox;
        userName = UserSession.getUserName();
        
        setElements();
        mainBox.getChildren().addAll(lblTitle, tvAddress, bpButtons);
        setEvents();

        updateAddressLst();
    }
    
    private void setEvents()
    {
    	tvAddress.setOnMouseClicked(
                e -> {
                    selectedDataModel = tvAddress.getSelectionModel().getSelectedItem();
                    if (selectedDataModel != null)
                    {
                        String[] strSelectedData = selectedDataModel.toVetString();
                        selectedAddress = strSelectedData;
                        setDisableButtons(false);
                    }
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
                updateAddressLst();
            }
        });

        returnPopUp.addListener(((observable, oldValue, newValue) ->
        {
            System.out.println("B:" + newValue + ", " + action);
            if (newValue && action == "delete")
            {
                control.deleteAddress(selectedAddress, userName);
                openPopUp("Deletado com Sucesso");
                setDisableButtons(true);
                tvAddress.getSelectionModel().clearSelection();
                updateAddressLst();
                System.out.println("AAA");
            }
            action = "";
        }));

        btnEdit.setOnMouseClicked(event -> openEditSelectedAddress(selectedAddress));
        btnDelete.setOnMouseClicked(event -> deleteSelectedAddress());
        btnNew.setOnMouseClicked(event -> System.out.println("Vai pra pagina de criação de endereços"));
    }

    private void updateAddressLst()
    {
        List<String[]> dataBaseAddresTable = control.getAddressList(userName);
        tvAddress.getItems().clear();
        observableAddressList.clear();

        for (String[] row : dataBaseAddresTable)
        {
            String comp = row[3];
            if (comp == null || comp.isEmpty() || comp.equals("null"))
                comp = "  ";
            observableAddressList.add(new AddressColumnDataModel(row[0], row[1], row[2], comp));
        }
        tvAddress.getItems().addAll(observableAddressList);
    }

    private void deleteSelectedAddress()
    {
        returnPopUp.setValue(false);
        String msg = "Deve haver ao menos 1 endereço!";
        if (observableAddressList.size() > 1)
        {
            action = "delete";
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

        TableColumn<AddressColumnDataModel, String> colName = new TableColumn<>("Nome");
        TableColumn<AddressColumnDataModel, String> colCEP = new TableColumn<>("CEP");
        TableColumn<AddressColumnDataModel, String> colNumber = new TableColumn<>("Número");
        TableColumn<AddressColumnDataModel, String> colComplement = new TableColumn<>("Complemento");

        colName.setCellValueFactory(data -> data.getValue().column1Property());
        colCEP.setCellValueFactory(data -> data.getValue().column2Property());
        colNumber.setCellValueFactory(data -> data.getValue().column3Property());
        colComplement.setCellValueFactory(data -> data.getValue().column4Property());

        colName.setPrefWidth(Constants.WIDTH * 0.1);
        colCEP.setPrefWidth(Constants.WIDTH * 0.1);
        colNumber.setPrefWidth(Constants.WIDTH * 0.1);
        colComplement.setPrefWidth(Constants.WIDTH * 0.24);
        tvAddress.setPrefHeight(Constants.HEIGHT * 0.5);

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

        tvAddress.getColumns().addAll(colName, colCEP, colNumber, colComplement);
    }
    
    private void openEditSelectedAddress(String[] address)
    {
        editionMode.setValue(true);
        mainBox.getChildren().clear();
        WinAddressClientConstructor win = new WinAddressClientConstructor(mainBox, address);
        Bindings.bindBidirectional(editionMode, win.getEditionMode());
        Bindings.bindBidirectional(messageMenuPopUp, win.getMessageMenuPopUp());
        Bindings.bindBidirectional(isMenuPopupActive, win.getIsMenuPopupActive());
        Bindings.bindBidirectional(returnPopUp, win.getReturnPopUp());
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
