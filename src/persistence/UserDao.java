package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.User;
import utils.UserSession;

public class UserDao
{
	private GenericDao gDao;
	
	public UserDao(GenericDao gDao)
	{
		this.gDao = gDao;
	}
	
	public boolean signInUser(User u) throws SQLException
	{
		Connection c = gDao.getConnection();
		String sql = "SELECT user_name, permission FROM user_tbl WHERE user_name = ? AND user_password = ?;";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, u.getLogin());
		ps.setString(2, u.getPassword());
		
		ResultSet rs = ps.executeQuery();
		boolean result = rs.next();
		
		if(result)
		{
			UserSession.setUser(rs.getString(1), rs.getString(2));
		}
		
		rs.close();
		ps.close();
		c.close();
		return result;
	}
	
	
	
}
