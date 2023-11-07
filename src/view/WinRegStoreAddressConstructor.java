package view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;



public class WinRegStoreAddressConstructor {

	public void addElements(Pane pane) {
		Label lblHomePage = new Label("Cadastro de Loja");
		lblHomePage.setPrefHeight(35);
		lblHomePage.setPrefWidth(640);
		lblHomePage.setStyle("-fx-font-size: 24px; -fx-alignment: center; -fx-font-weight: bold");
		lblHomePage.setLayoutY(25);

		Button btnBack = new Button("Voltar");
		btnBack.setPrefWidth(90);
		btnBack.setPrefHeight(40);
		btnBack.relocate(20, 354);

		Button btnNext = new Button("Cadastrar");
		btnNext.setPrefWidth(90);
		btnNext.setPrefHeight(40);
		btnNext.relocate(530, 354);

		// ----- Criando Panel de Inserção ----- //

		Pane paneCli = new Pane();
		paneCli.setStyle("-fx-border-width: 2; -fx-border-radius: 10; -fx-border-color: black;");
		paneCli.setPrefHeight(280);
		paneCli.setPrefWidth(600);
		paneCli.relocate(20, 68);

		// ----- Criando Labels ----- //

		Label lblCEP = new Label("CEP:");
		setAlignment(lblCEP);
		Label lblNeighborhood = new Label("Bairro:");
		setAlignment(lblNeighborhood);
		Label lblNumber = new Label("Numero:");
		setAlignment(lblNumber);
		Label lblCity = new Label("Cidade:");
		setAlignment(lblCity);
		Label lblStreet = new Label("Logradouro:");
		setAlignment(lblStreet);
		Label lblComplement = new Label("Complemento:");
		setAlignment(lblComplement);
		Label lblWarning = new Label("");
		lblWarning.relocate(29, 245);
		Label lblDescription = new Label("Por favor, digite os seus dados");
		lblDescription.relocate(428, 255);

		// ----- Criando TextFields ----- //

		TextField tfCEP = new TextField();
		setAlignment(tfCEP);
		TextField tfNeighborhood = new TextField();
		TextField tfNumber = new TextField();
		TextField tfCity = new TextField();
		setAlignment(tfCity);
		TextField tfStreet = new TextField();
		TextField tfComplement = new TextField();


		// ----- Inserindo em Vbox/Hbox ----- //

		VBox vblb1 = new VBox(40, lblCEP, lblNeighborhood, lblNumber);
		VBox vbtf1 = new VBox(33, tfCEP, tfNeighborhood, tfNumber);
		VBox vblb2 = new VBox(40, lblCity,lblStreet, lblComplement);
		VBox vbtf2 = new VBox(33, tfCity,tfStreet, tfComplement);
		HBox hbClient = new HBox(3,vblb1, vbtf1, vblb2, vbtf2);
		hbClient.setPrefHeight(270);
		hbClient.setPrefWidth(600);
		hbClient.relocate(-30, 60);

		paneCli.getChildren().addAll(hbClient, lblDescription, lblWarning);

		pane.getChildren().addAll(lblHomePage, paneCli, btnBack, btnNext);

	}


	private void setAlignment(Label label) {
		label.setPrefWidth(120);
		label.setAlignment(Pos.CENTER_RIGHT);
		label.setStyle("-fx-font-size: 13px;");
	}
	private void setAlignment(TextField tf) {
		tf.setPrefWidth(170);
		tf.setStyle("-fx-font-size: 12px;");
	}
}