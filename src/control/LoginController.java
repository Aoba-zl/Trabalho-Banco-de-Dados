package control;

import java.sql.SQLException;

import javafx.scene.control.TextField;
import model.User;
import persistence.GenericDao;
import persistence.UserDao;

public class LoginController
{
	private TextField tfUserName;
	private TextField tfPassword;
	
	public LoginController(TextField tfUserName, TextField tfPassword)
	{
		this.tfUserName = tfUserName;
		this.tfPassword = tfPassword;
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
		
		return uDao.signInUser(u);
	}


	private boolean checkLogin() {
		
		if(tfUserName.getText().contains(" "))
		{
			return true;
		}
		
		if(tfPassword.getText().contains(" "))
		{
			return true;
		}
		
		return false;
	}
	
	
	
}
