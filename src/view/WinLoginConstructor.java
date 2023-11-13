package view;

import java.sql.SQLException;

import control.ChangeSceneController;
import control.LoginController;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import utils.SceneName;
import utils.UserSession;

public class WinLoginConstructor implements GenericWindownInterface
{
	private Pane pWin;

	@Override
	public void addElements (Pane pane)
	{
		this.pWin = pane;
		
		Label lblLogin = new Label("Login");
		lblLogin.setPrefHeight(35);
		lblLogin.setPrefWidth(640);
		lblLogin.setStyle("-fx-font-size: 24px; -fx-alignment: center; -fx-font-weight: bold");
		lblLogin.setLayoutY(25);
		
		Label lblUser = new Label("Usuario:");
		Label lblPassword = new Label("Senha:");
		Label lblHasRegister = new Label("Não possui uma conta?");
		Label lblCreateRegister = new Label("Crie uma!");
		lblUser.setPrefWidth(75);
		lblPassword.setPrefWidth(75);
		lblHasRegister.setPrefWidth(140);
		lblCreateRegister.setPrefWidth(75);
		lblCreateRegister.setStyle("-fx-cursor: hand; -fx-text-fill: #2675ff;");
		
		TextField tfUserName = new TextField();
		TextField tfPassword = new TextField();
		tfUserName.setPrefWidth(120);
		tfPassword.setPrefWidth(120);
		
		Label lblMessage = new Label();
		lblMessage.setMinHeight(25);
		lblMessage.setMinWidth(130);
		lblMessage.setStyle("-fx-alignment: center-left; -fx-text-fill: red; -fx-font-size: 11px");
		
		Button btnEnter = new Button("Entrar");
		btnEnter.setPrefWidth(50);
		btnEnter.setStyle("-fx-background-color: #3498DB; -fx-text-fill: white");
		
		HBox hbUser = new HBox(2);
		HBox hbPassword = new HBox(2);
		HBox hbLblReg = new HBox(2);
		HBox hbBtnEnter = new HBox(2);
		hbBtnEnter.setStyle("-fx-alignment: bottom-right; -fx-spacing: 10px;");
		hbBtnEnter.setPrefHeight(50);
		hbUser.getChildren().addAll(lblUser, tfUserName);
		hbPassword.getChildren().addAll(lblPassword, tfPassword);
		hbLblReg.getChildren().addAll(lblHasRegister, lblCreateRegister);
		hbBtnEnter.getChildren().addAll(lblMessage, btnEnter);
		
		VBox vbUser = new VBox(4);
		vbUser.setStyle("-fx-border-width: 2; -fx-border-radius: 10; -fx-border-color: black;");
		vbUser.setPadding(new Insets(10));
		vbUser.setPrefHeight(140);
		vbUser.setPrefWidth(215);
		vbUser.getChildren().addAll(hbUser, hbPassword, hbLblReg, hbBtnEnter);
		vbUser.setLayoutX(195);
		vbUser.setLayoutY(91);
		
		Image imgStore1 = new Image(getClass().getResource("image/Store1.png").toString());
		Image imgStore2 = new Image(getClass().getResource("image/Store2.png").toString());
		ImageView imgViewStore1 = new ImageView(imgStore1);
		ImageView imgViewStore2 = new ImageView(imgStore2);
		imgViewStore1.setFitHeight(150);
		imgViewStore1.setFitWidth(163);
		imgViewStore1.setLayoutX(14);
		imgViewStore1.setLayoutY(91);
		imgViewStore2.setFitHeight(150);
		imgViewStore2.setFitWidth(163);
		imgViewStore2.setLayoutX(428);
		imgViewStore2.setLayoutY(91);
		
		Pane paneUser = new Pane();
		paneUser.setPrefHeight(321);
		paneUser.setPrefWidth(605);
		paneUser.setStyle("-fx-border-width: 2; -fx-border-radius: 10; -fx-border-color: black;");
		paneUser.setLayoutX(18);
		paneUser.setLayoutY(60);
		paneUser.getChildren().addAll(vbUser, imgViewStore1, imgViewStore2);
		
		
		//---------------------------------Register---------------------------------------
		
		Label lblTypeAc = new Label("Que tipo de conta deseja criar?");
		lblTypeAc.setStyle("-fx-font-size: 13px;");
		
		Button btnRegisterClient = new Button("Cliente");
		Button btnRegisterStore = new Button("Lojista");
		btnRegisterClient.setPrefWidth(75);
		btnRegisterClient.setPrefHeight(30);
		btnRegisterStore.setPrefWidth(75);
		btnRegisterStore.setPrefHeight(30);
		
		HBox hbTypeAc = new HBox();
		hbTypeAc.setStyle("-fx-alignment: center");
		hbTypeAc.getChildren().add(lblTypeAc);
		
		HBox hbRegister = new HBox();
		hbRegister.setStyle("-fx-alignment: center; -fx-spacing: 20px;");
		hbRegister.getChildren().addAll(btnRegisterClient, btnRegisterStore);
		
		VBox vbRegister = new VBox(20);
		vbRegister.setPrefHeight(130);
		vbRegister.setPrefWidth(200);
		vbRegister.setLayoutX(220.5);
		vbRegister.setLayoutY(156);
		vbRegister.setPadding(new Insets(25, 0, 0, 0));
		vbRegister.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 10px; -fx-border-radius: 10px; -fx-border-color: black;");
		vbRegister.getChildren().addAll(hbTypeAc, hbRegister);
		
		Pane pTransp = new Pane();
		pTransp.setPrefWidth(640);
		pTransp.setPrefHeight(400);
		pTransp.setStyle("-fx-background-color: rgba(0, 0, 0, 0.4)");
		pTransp.setVisible(false);
		pTransp.getChildren().add(vbRegister);
		
		lblCreateRegister.setOnMouseClicked(e -> 
										{
											pTransp.setVisible(true);
											lblCreateRegister.setStyle("-fx-cursor: hand; -fx-text-fill: red;");
										});
		pTransp.setOnMouseClicked(e -> pTransp.setVisible(false));
		
		btnRegisterClient.setOnAction(e -> toRegClient());
		
		btnRegisterStore.setOnAction(e -> toRegStore());
		
		//------------mudança de scene---------------
		btnEnter.setOnAction(e -> signUpUser(tfUserName, tfPassword, lblMessage));
		
		pane.getChildren().addAll(lblLogin, paneUser, pTransp);
		
	}
	
	private void signUpUser(TextField tfUserName, TextField tfPassword, Label lblMessage)
	{
		LoginController lc = new LoginController(tfUserName, tfPassword, lblMessage);
		
		if(!tfUserName.getText().isEmpty() && !tfPassword.getText().isEmpty())
		{
			try 
			{
				if(lc.login())
				{
					toHomePage();
				}
			} catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
		else
		{
			lblMessage.setText("Digite todos os valores!");
		}
	}
	
	private void toHomePage()
	{
		// TODO: Voltar pra Holme page apos os testes
//		ChangeSceneController.changeScene(SceneName.HOME_PAGE, this.pWin);
		ChangeSceneController.changeScene(SceneName.ACCOUNT_MENU, this.pWin);
	}
	
	private void toRegClient()
	{
		UserSession.setUser("register", null);;
		ChangeSceneController.changeScene(SceneName.REG_CLIENT_INFO, this.pWin);
	}
	
	private void toRegStore()
	{
		UserSession.setUser("register", null);
		ChangeSceneController.changeScene(SceneName.REG_STORE_INFO, this.pWin);
	}
	
}
