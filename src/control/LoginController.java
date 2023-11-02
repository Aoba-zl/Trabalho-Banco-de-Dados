package control;

import java.sql.SQLException;

import javafx.scene.control.TextField;
import model.User;
import persistence.GenericDao;
import persistence.UserDao;

public class LoginController
{
	private TextField tfUserName;
	private TextField tfpassword;
	
	public LoginController(TextField tfUserName, TextField tfPassword)
	{
		this.tfUserName = tfUserName;
		this.tfpassword = tfPassword;
	}

	
	public boolean login() throws SQLException
	{
		GenericDao gDao = new GenericDao();
		UserDao uDao = new UserDao(gDao);
		User u = new User();
		u.setLogin(this.tfUserName.getText());
		u.setPassword(this.tfpassword.getText());
		
//		return uDao.signInUser(u);
		
		return true;
	}
	
	
	
}
