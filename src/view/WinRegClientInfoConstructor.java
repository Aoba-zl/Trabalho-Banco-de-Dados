package view;

import java.sql.SQLException;

import javax.naming.Binding;

import control.ChangeSceneController;
import control.RegisterUserController;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import utils.SceneName;
import utils.UserSession;
/**
 * Classe responsável por construir e adicionar os elementos a interface gráfica de
 * Cadastro de Informaçoes do cliente.
 * Implementa a interface GerericAccountMenuWinInterface.
 */
public class WinRegClientInfoConstructor implements GenericWindownInterface
{
	Pane pWin;
	private RegisterUserController uCon = new RegisterUserController();
	private WinRegClientAddressConstructor ClienA = new WinRegClientAddressConstructor(uCon);
    private ToggleGroup group;
    
    private ChangeSceneController changeSceneController = new ChangeSceneController();
    /**
     * Construtor da classe.
     *
     * @param pane O painel principal da interface gráfica.
     */
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
		
		Label lblName = new Label("Nome do Usuário:");
		setAlignment(lblName);
		Label lblCPF = new Label("CPF:");
		setAlignment(lblCPF);
		Label lblEmail = new Label("Email:");
		setAlignment(lblEmail);
		Label lblPasswd = new Label("Senha:");
		setAlignment(lblPasswd);
		Label lblSocialName = new Label("Nome Social:");
		setAlignment(lblSocialName);
		Label lblPhone = new Label("Telefone:");
		setAlignment(lblPhone);
		Label lblBirthDate = new Label("Data de Nascimento:");
		setAlignment(lblBirthDate);
		Label lblSex = new Label("Sexo:");
		lblSex.relocate(270,202);
		setAlignment(lblSex);
		Label lblWarning = new Label("");
		lblWarning.relocate(29, 245);
		Label lblDescription = new Label("Por favor, digite os seus dados");
		lblDescription.relocate(428, 255);
		lblDescription.setVisible(false);
		
		// ----- Criando TextFields ----- //
		
		TextField tfName = new TextField();
		TextField tfCPF = new TextField();
		TextField tfEmail = new TextField();
		TextField tfPasswd = new TextField();
		TextField tfSocialName = new TextField();
		TextField tfPhone= new TextField();
		TextField tfBirthDate= new TextField();
		TextField tfSex= new TextField();
		tfSex.relocate(435, 225);
		tfSex.setVisible(false);
		
		// ----- Criando property com o controller ----- //
		
		Bindings.bindBidirectional(tfName.textProperty(), uCon.getName());
		Bindings.bindBidirectional(tfCPF.textProperty(), uCon.getCpf());
		Bindings.bindBidirectional(tfEmail.textProperty(), uCon.getEmail());
		Bindings.bindBidirectional(tfPasswd.textProperty(), uCon.getPasswd());
		Bindings.bindBidirectional(tfSocialName.textProperty(), uCon.getSocialName());
		Bindings.bindBidirectional(tfPhone.textProperty(), uCon.getPhone());
		Bindings.bindBidirectional(tfBirthDate.textProperty(), uCon.getBirthDate());
		Bindings.bindBidirectional(tfSex.textProperty(), uCon.getSex());
		Bindings.bindBidirectional(lblWarning.textProperty(), uCon.getWarning());
		
		
		// ----- Criando optionPanes ----- //
		
		RadioButton rbMale = new RadioButton("Masculino");
		RadioButton rbFem = new RadioButton("Feminino");
		RadioButton rbOther = new RadioButton("Outro");
		
		Bindings.bindBidirectional(rbMale.selectedProperty(), uCon.getMale());
		Bindings.bindBidirectional(rbFem.selectedProperty(), uCon.getFem());
		Bindings.bindBidirectional(rbOther.selectedProperty(), uCon.getOther());
		group = new ToggleGroup();
		rbMale.setToggleGroup(group);
		rbFem.setToggleGroup(group);
		rbOther.setToggleGroup(group);
		HBox hbSex = new HBox(rbMale, rbFem, rbOther);
		hbSex.relocate(396, 203);
		
		group.selectedToggleProperty().addListener(new ChangeListener<Toggle>()  {
			public void changed(ObservableValue<? extends Toggle> ob, Toggle o, Toggle n) {
				setVisible(rbOther,lblDescription,tfSex);
			}
		});
		// ----- Inserindo em Vbox/Hbox ----- //
		
		VBox vblb1 = new VBox(41,lblName,lblCPF,lblEmail,lblPasswd);	
		VBox vbtf1 = new VBox(34,tfName,tfCPF,tfEmail,tfPasswd);
		VBox vblb2 = new VBox(41,lblSocialName,lblPhone,lblBirthDate);	
		VBox vbtf2 = new VBox(34,tfSocialName,tfPhone,tfBirthDate);
		HBox hbClient = new HBox(15,vblb1,vbtf1,vblb2,vbtf2);
		hbClient.setPrefHeight(270);
		hbClient.setPrefWidth(600);
		hbClient.relocate(0, 20);
		
		paneCli.getChildren().addAll(hbClient,tfSex,hbSex,lblSex,lblDescription,lblWarning);
		
		//ChangeScene
		btnBack.setOnAction(e -> toLogin());
		btnNext.setOnAction(e -> toClientAddress());
		
		pane.getChildren().addAll(lblHomePage,paneCli,btnBack,btnNext);
		
	}
	private void setAlignment(Label label) {
		label.setPrefWidth(120);
		label.setAlignment(Pos.CENTER_RIGHT);
		label.setStyle("-fx-font-size: 13px;");
	}
	private void setVisible(RadioButton rb, Label lb,TextField tf) {
		if (rb.isSelected()) {
			lb.setVisible(true);
			tf.setVisible(true);
		}else {	
			lb.setVisible(false);
			tf.setVisible(false);
		}
	}
	
	// Voltando para tela de Login
	private void toLogin() {
		uCon.clean();
		UserSession.clearSession();
		changeSceneController.changeScene(SceneName.LOGIN, this.pWin);
	}
	// avançando para tela de endereco e validando inserçoes
	private void toClientAddress() {
			try {
				if (uCon.checkValuesClient()) {
					pWin.getChildren().clear();
					ClienA.addElements(pWin);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
}