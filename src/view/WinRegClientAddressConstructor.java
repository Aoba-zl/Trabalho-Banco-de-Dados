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
/**
 * Classe responsável por construir e adicionar os elementos a interface gráfica de
 * Cadastro de Endereço do cliente.
 * Implementa a interface GerericAccountMenuWinInterface.
 */
public class WinRegClientAddressConstructor implements GenericWindownInterface
{
	private Pane pWin;
	private RegisterUserController uCon;
	
	private ChangeSceneController changeSceneController = new ChangeSceneController();
	
    /**
     * Construtor da classe.
     *
     * @param uCon é o construtor onde se encontram as informaçoes do cliente.
     */
	public WinRegClientAddressConstructor (RegisterUserController uCon) {
		this.uCon = uCon;
    }

    /**
     * Construtor da classe.
     *
     */
    public WinRegClientAddressConstructor()
	{
		super();
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
		tfCEP.setOnKeyReleased(e -> uCon.completeAddress());
		TextField tfCity= new TextField();
		tfCity.setDisable(true);
		TextField tfStreet= new TextField();
		tfStreet.setDisable(true);
		TextField tfComplement= new TextField();
		
		Bindings.bindBidirectional(tfName.textProperty(), uCon.getAddressName());
		Bindings.bindBidirectional(tfState .textProperty(), uCon.getState());
		Bindings.bindBidirectional(tfNeighborhood.textProperty(), uCon.getNeighborhood());
		Bindings.bindBidirectional(tfNumber.textProperty(), uCon.getNumber());
		Bindings.bindBidirectional(tfCEP.textProperty(), uCon.getCep());
		Bindings.bindBidirectional(tfCity.textProperty(), uCon.getCity());
		Bindings.bindBidirectional(tfStreet.textProperty(), uCon.getStreet());
		Bindings.bindBidirectional(tfComplement.textProperty(), uCon.getComplement());
		Bindings.bindBidirectional(lblWarning.textProperty(), uCon.getWarning());

		
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
		btnBack.setOnAction(e -> {
			lblWarning.setText("");
			toClientInfo();
		});
		
		btnNext.setOnAction(e -> touCon());
		
		pane.getChildren().addAll(lblHomePage,paneCli,btnBack,btnNext);
		
	}
	private void setAlignment(Label label) {
		label.setPrefWidth(120);
		label.setAlignment(Pos.CENTER_RIGHT);
		label.setStyle("-fx-font-size: 13px;");
	}
	
	private void toClientInfo()
	{
		
		changeSceneController.changeScene(SceneName.REG_CLIENT_INFO, this.pWin);
	}
	
	private void touCon()
	{

		if(uCon.checkValuesAddress() && uCon.checkAddressName()) {
			try {
				uCon.generateClient();
				uCon.clean();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			UserSession.clearSession();
			changeSceneController.changeScene(SceneName.LOGIN, this.pWin);
		}

	}
	
}