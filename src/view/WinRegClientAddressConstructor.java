package view;

import java.sql.SQLException;

import control.ChangeSceneController;
import control.RegisterUserController;
import javafx.beans.binding.Bindings;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import utils.SceneName;
import utils.UserSession;

public class WinRegClientAddressConstructor implements GenericWindownInterface
{
	private Pane pWin;
	private RegisterUserController login;
	
	public WinRegClientAddressConstructor (RegisterUserController login) {
		this.login = login;
    }

	
    public WinRegClientAddressConstructor() {
		// TODO Auto-generated constructor stub
	}


	public void addElements(Pane pane)
	{
    	this.pWin = pane;
    	
		Label lblHomePage = new Label("Cadastro de Cliente");
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
		
		Label lblName = new Label("Nome:");
		setAlignment(lblName);
		Label lblState = new Label("Estado:");
		setAlignment(lblState);
		Label lblNeighborhood = new Label("Bairro:");
		setAlignment(lblNeighborhood);
		Label lblNumber = new Label("Numero:");
		setAlignment(lblNumber);
		Label lblCEP = new Label("CEP:");
		setAlignment(lblCEP);
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
		
		TextField tfName = new TextField();
		TextField tfState = new TextField();
		tfState.setDisable(true);
		TextField tfNeighborhood = new TextField();
		tfNeighborhood.setDisable(true);
		TextField tfNumber = new TextField();
		TextField tfCEP = new TextField();
		tfCEP.setOnKeyReleased(e -> login.completeAddress());
		TextField tfCity= new TextField();
		tfCity.setDisable(true);
		TextField tfStreet= new TextField();
		tfStreet.setDisable(true);
		TextField tfComplement= new TextField();
		
		Bindings.bindBidirectional(tfName.textProperty(), login.getAddressName());
		Bindings.bindBidirectional(tfState .textProperty(), login.getState());
		Bindings.bindBidirectional(tfNeighborhood.textProperty(), login.getNeighborhood());
		Bindings.bindBidirectional(tfNumber.textProperty(), login.getNumber());
		Bindings.bindBidirectional(tfCEP.textProperty(), login.getCep());
		Bindings.bindBidirectional(tfCity.textProperty(), login.getCity());
		Bindings.bindBidirectional(tfStreet.textProperty(), login.getStreet());
		Bindings.bindBidirectional(tfComplement.textProperty(), login.getComplement());
		Bindings.bindBidirectional(lblWarning.textProperty(), login.getWarning());

		
		// ----- Inserindo em Vbox/Hbox ----- //
		
		VBox vblb1 = new VBox(41,lblName,lblState,lblNeighborhood,lblNumber);	
		VBox vbtf1 = new VBox(34,tfName,tfState,tfNeighborhood,tfNumber);
		VBox vblb2 = new VBox(41,lblCEP,lblCity,lblStreet,lblComplement);	
		VBox vbtf2 = new VBox(34,tfCEP,tfCity,tfStreet,tfComplement);
		HBox hbClient = new HBox(vblb1,vbtf1,vblb2,vbtf2);
		hbClient.setPrefHeight(270);
		hbClient.setPrefWidth(600);
		hbClient.relocate(0, 20);
		
		paneCli.getChildren().addAll(hbClient,lblDescription,lblWarning);
		
		//ChangeScene
		btnBack.setOnAction(e -> toClientInfo());
		
		btnNext.setOnAction(e -> toLogin());
		
		pane.getChildren().addAll(lblHomePage,paneCli,btnBack,btnNext);
		
	}
	private void setAlignment(Label label) {
		label.setPrefWidth(120);
		label.setAlignment(Pos.CENTER_RIGHT);
		label.setStyle("-fx-font-size: 13px;");
	}
	
	private void toClientInfo()
	{
		ChangeSceneController.changeScene(SceneName.REG_CLIENT_INFO, this.pWin);
	}
	
	private void toLogin()
	{
		if(login.checkValuesAddress()) {
			try {
				login.generateClient();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			UserSession.clearSession();
			ChangeSceneController.changeScene(SceneName.LOGIN, this.pWin);
		}

	}
	
}