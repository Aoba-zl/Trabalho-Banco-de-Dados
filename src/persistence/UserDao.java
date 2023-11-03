package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.User;

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
		String sql = "SELECT user_name FROM user_tbl WHERE user_name = ? AND user_password = ?;";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, u.getLogin());
		ps.setString(2, u.getPassword());
		
		ResultSet rs = ps.executeQuery();
		boolean result = rs.next();
		
		rs.close();
		ps.close();
		c.close();
		return result;
	}
	
	
	
}
