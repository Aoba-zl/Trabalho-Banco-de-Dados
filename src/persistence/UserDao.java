package persistence;

import java.sql.SQLException;
import java.util.List;

import model.User;

public class UserDao
{
	private GenericDao gDao;
	
	public UserDao(GenericDao gDao)
	{
		this.gDao = gDao;
	}
	
	public void signInUser() throws SQLException
	{
		
	}
	
}
