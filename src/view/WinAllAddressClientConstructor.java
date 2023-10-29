package view;

import Utils.Constants;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class WinAllAddressClientConstructor
{
    List<String> addressLst = new ArrayList<String>();

    public void addElements(VBox mainBox)
    {
        Label lblTitle = new Label("Endereço");
        lblTitle.setStyle("-fx-font-weight: bold; -fx-font-size: 16px;" +
                "-fx-label-padding: 0px 0px 15px 0px");

        BorderPane bpButtons = new BorderPane();

        TableView <String> tvAddress = new TableView<>();

        TableColumn<String, String> colName = new TableColumn<>("Nome");
        TableColumn<String, String> colCEP = new TableColumn<>("CEP");
        TableColumn<String, String> colNumber = new TableColumn<>("Número");
        TableColumn<String, String> colComplement = new TableColumn<>("Complemento");
        tvAddress.getColumns().addAll(colName, colCEP, colNumber, colComplement);
        tvAddress.setPrefHeight(Constants.HEIGHT * 0.5);

        Button btnDelete = new Button("Excluir");
        btnDelete
                .setStyle( "-fx-background-color: #ff5959; -fx-text-fill: white;" +
                        "-fx-border-color: #ff0000; -fx-border-radius: 8px; -fx-background-radius: 8px;");
        Button btnEdit   = new Button("Editar");
        btnEdit
                .setStyle("-fx-border-radius: 8px;-fx-background-radius: 8px");
        Button btnNew    = new Button("Adicionar Endereço");
        btnNew
                .setStyle("-fx-border-radius: 8px;-fx-background-radius: 8px");

        bpButtons.setLeft(btnDelete);
        bpButtons.setCenter(btnEdit);
        bpButtons.setRight(btnNew);

        mainBox.getChildren().addAll(lblTitle, tvAddress,
                bpButtons);
    }
}
