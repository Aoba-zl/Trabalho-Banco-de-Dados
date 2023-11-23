package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.User;
import utils.UserSession;

/**
 * Implementação da interface CrudDao para operações CRUD (Create, Read, Update, Delete) na tabela user_tbl
 * no banco de dados.
 */
public class UserDao implements CrudDao<User>
{
	private GenericDao gDao;

	/**
	 * Construtor da classe UserDao.
	 * @param gDao Objeto responsável pela comunicação com o banco de dados.
	 */
	public UserDao(GenericDao gDao)
	{
		this.gDao = gDao;
	}
	
	/**
	 * Faz a busca de usuario no banco de dados.
	 * @param u Armazena o objeto User.
	 * @return True para caso tenha encontrado o usuário no banco de dados, false caso o contrário.
	 * @throws SQLException Caso ocorra um erro de conexão no banco de dados.
	 */
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

	@Override
	public void insert(User u) throws SQLException {
		Connection connection = gDao.getConnection();
		String querySql = "INSERT INTO user_tbl VALUES (?, ?, ?, ?, ?)";
		PreparedStatement ps = connection.prepareStatement(querySql);
		ps.setString(1, u.getLogin());
		ps.setString(2, u.getPassword());
		ps.setString(3, u.getTelephone());
		ps.setString(4, u.getEmail());
		ps.setString(5, u.getPermission());
		
		ps.execute();
		
		ps.close();
		connection.close();
	}

	@Override
	public void update(User u) throws SQLException {
		Connection connection = gDao.getConnection();
		String querySql = "UPDATE user_tbl SET telephone = ?, email = ? "
				+ "WHERE user_name = ?";
		PreparedStatement ps = connection.prepareStatement(querySql);
		ps.setString(1, u.getTelephone());
		ps.setString(2, u.getEmail());
		ps.setString(3, u.getLogin());
		
		ps.execute();
		
		ps.close();
		connection.close();
	}

	@Override
	public void delete(User u) throws SQLException {
		Connection connection = gDao.getConnection();
		String querySql = "DELETE user_tbl WHERE user_name = ?";
		PreparedStatement ps = connection.prepareStatement(querySql);
		ps.setString(1, u.getLogin());		

		ps.execute();
		ps.close();
		connection.close();
	}

	@Override
	public User consult(User u) throws SQLException {
		Connection connection = gDao.getConnection();
		String querySql = "SELECT * FROM user_tbl WHERE user_name = ?";
		PreparedStatement ps = connection.prepareStatement(querySql);
		ps.setString(1, u.getLogin());
		
		ResultSet result = ps.executeQuery();
		
		if (result.next())
		{
			u.setLogin(result.getString("user_name"));
			u.setEmail(result.getString("email"));
			u.setTelephone(result.getString("telephone"));
		}
		
		ps.close();
		connection.close();
		
		return u;
	}

	@Override
	public List<User> list() throws SQLException {
		List<User> users = new ArrayList<>();
		Connection connection = gDao.getConnection();
		String querySql = "SELECT * FROM user_tbl";
		PreparedStatement ps = connection.prepareStatement(querySql);
		
		ResultSet result = ps.executeQuery();
		
		if (result.next())
		{
			User u = new User();
			u.setLogin(result.getString("user_name"));
			u.setEmail(result.getString("email"));
			u.setTelephone(result.getString("telephone"));
			users.add(u);
		}
		
		ps.close();
		connection.close();
		
		return users;
	}
}
