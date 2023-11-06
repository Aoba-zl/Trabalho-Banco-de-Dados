package control;

import java.sql.SQLException;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.User;
import persistence.GenericDao;
import persistence.UserDao;

public class LoginController
{
	private TextField tfUserName;
	private TextField tfPassword;
	private Label lblMessage;
	
	public LoginController(TextField tfUserName, TextField tfPassword, Label lblMessage)
	{
		this.tfUserName = tfUserName;
		this.tfPassword = tfPassword;
		this.lblMessage = lblMessage;
	}

	
	public boolean login() throws SQLException
	{
		GenericDao gDao = new GenericDao();
		UserDao uDao = new UserDao(gDao);
		User u = new User();
		
		if(checkLogin())
		{
			return false;
		}
		
		u.setLogin(this.tfUserName.getText());
		u.setPassword(this.tfPassword.getText());
		
		boolean test = uDao.signInUser(u);
		
		if(!test)
		{
			lblMessage.setText("Usuário ou Senha inválido.");
		}
		
		return test;
	}


	private boolean checkLogin() {
		
		if(tfUserName.getText().contains(" ") || tfPassword.getText().contains(" "))
		{
			lblMessage.setText("Digite sem espaço!");
			return true;
		}
		
		
		return false;
	}
	
	
	
}
