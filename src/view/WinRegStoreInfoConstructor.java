package view;

import control.ChangeSceneController;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import utils.SceneName;
import utils.UserSession;

public class WinRegStoreInfoConstructor implements GenericWindownInterface
{
	Pane pWin;
	
	public void addElements(Pane pane) 
	{
		this.pWin = pane;
		
		Label lblHomePage = new Label("Cadastro de Loja");
		lblHomePage.setPrefHeight(35);
		lblHomePage.setPrefWidth(640);
		lblHomePage.setStyle("-fx-font-size: 24px; -fx-alignment: center; -fx-font-weight: bold");
		lblHomePage.setLayoutY(25);

		Button btnBack = new Button("Voltar");
		btnBack.setPrefWidth(90);
		btnBack.setPrefHeight(40);
		btnBack.relocate(20, 354);

		Button btnNext = new Button("Proximo");
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

		Label lblName = new Label("Nome do Usuario:");
		setAlignment(lblName);
		Label lblStore = new Label("Nome da Loja:");
		setAlignment(lblStore);
		Label lblPhone = new Label("Telefone:");
		setAlignment(lblPhone);
		Label lblCNPJ = new Label("CNPJ:");
		setAlignment(lblCNPJ);
		Label lblEmail = new Label("Email:");
		setAlignment(lblEmail);
		Label lblSenha = new Label("Senha:");
		setAlignment(lblSenha);
		Label lblWarning = new Label("");
		lblWarning.relocate(29, 245);
		Label lblDescription = new Label("Por favor, digite os seus dados");
		lblDescription.relocate(428, 255);

		// ----- Criando TextFields ----- //

		TextField tfName = new TextField();
		setAlignment(tfName);
		TextField tfStore = new TextField();
		setAlignment(tfStore);
		TextField tfPhone = new TextField();
		setAlignment(tfPhone);
		TextField tfCNPJ = new TextField();
		setAlignment(tfCNPJ);
		TextField tfEmail = new TextField();
		setAlignment(tfEmail);
		TextField tfSenha = new TextField();
		setAlignment(tfSenha);


		// ----- Inserindo em Vbox/Hbox ----- //

		VBox vblb1 = new VBox(40, lblName, lblStore, lblPhone);
		VBox vbtf1 = new VBox(33, tfName, tfStore, tfPhone);
		VBox vblb2 = new VBox(40, lblCNPJ,lblEmail, lblSenha);
		VBox vbtf2 = new VBox(33, tfCNPJ,tfEmail, tfSenha);
		HBox hbClient = new HBox(3,vblb1, vbtf1, vblb2, vbtf2);
		hbClient.setPrefHeight(270);
		hbClient.setPrefWidth(600);
		hbClient.relocate(0, 60);

		paneCli.getChildren().addAll(hbClient, lblDescription, lblWarning);

		//ChangeScene
		btnBack.setOnAction(e -> toLogin());
		btnNext.setOnAction(e -> toStoreAddress());
		
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
	
	private void toLogin()
	{
		UserSession.clearSession();
		ChangeSceneController.changeScene(SceneName.LOGIN, this.pWin);
	}
	
	private void toStoreAddress()
	{
		ChangeSceneController.changeScene(SceneName.REG_STORE_ADDRESS, this.pWin);
	}
}