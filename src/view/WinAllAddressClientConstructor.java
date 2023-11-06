package view;

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

public class WinAllAddressClientConstructor
{
	private TableView<String[]> tvAddress;
	private Button btnDelete, btnEdit, btnNew;
    private String[] selectedAddress;
    private List<String[]> addressLst;
    private ObservableList<String[]> observableAddressList;
    
    private final StringProperty messageMenuPopUp = new SimpleStringProperty(null);
    private final BooleanProperty isMenuPopupActive = new SimpleBooleanProperty(false);
    private final BooleanProperty returnPopUp = new SimpleBooleanProperty(false);
    private final VBox mainBox;
    private String action = null;
    private String userName;
    
    private CtrlAddressMenu control = new CtrlAddressMenu();

    public WinAllAddressClientConstructor(VBox mainBox)
    {
        this.mainBox = mainBox;
        userName = UserSession.getUserName();
        
        addressLst = control.getAddressList(userName);
        observableAddressList = FXCollections.observableArrayList(addressLst);
        
        setElements();
        setEvents();
    }
    
    private void setEvents()
    {
    	// TODO: dar ação aos botoes
    	tvAddress.setOnMouseClicked(
                e -> {
                    String[] selectedData = tvAddress.getSelectionModel().getSelectedItem();
                    if (selectedData != null)
                    {
                        selectedAddress = selectedData;
                        setDisableButtons(false);
                    }
                    else
                        System.out.println("NULL");
                }
        );
        btnEdit.setOnMouseClicked(event -> openEditSelectedAddress(selectedAddress));
//        btnDelete.setOnMouseClicked(event -> deleteSelectedAddress());
//        btnNew.setOnMouseClicked(event -> addAddress());
    }
    
    private void setElements()
    {
        Label lblTitle = new Label("Endereço");
        lblTitle.setStyle("-fx-font-weight: bold; -fx-font-size: 16px;" +
                "-fx-label-padding: 0px 0px 15px 0px");

        BorderPane bpButtons = new BorderPane();

        tvAddress = new TableView<>();

        TableColumn<String[], String> colName = new TableColumn<>("Nome");
        TableColumn<String[], String> colCEP = new TableColumn<>("CEP");
        TableColumn<String[], String> colNumber = new TableColumn<>("Número");
        TableColumn<String[], String> colComplement = new TableColumn<>("Complemento");
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
        colName.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()[0]));
        colCEP.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()[1]));
        colNumber.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()[2]));
        colComplement.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()[3]));
        tvAddress.getItems().addAll(observableAddressList);

        mainBox.getChildren().addAll(lblTitle, tvAddress, bpButtons);
    }
    
    private void openEditSelectedAddress(String[] address)
    {
        mainBox.getChildren().clear();
        WinAddressClientConstructor win = new WinAddressClientConstructor(mainBox, address);
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
        btnNew.setDisable(isDisable);
    }
}
