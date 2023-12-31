package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Client;

/**
 * Implementação da interface CrudDao para operações CRUD (Create, Read, Update, Delete) na tabela client
 * no banco de dados.
 */
public class ClientDao implements CrudDao<Client>
{
	private final GenericDao gDao;

	/**
	 * Construtor da classe ClientDao.
	 * @param gDao Objeto responsável pela comunicação com o banco de dados.
	 */
	public ClientDao(GenericDao gDao)
	{
		this.gDao = gDao;
	}
	
	@Override
	public void insert(Client client) throws SQLException {
		Connection connection = gDao.getConnection();
		String querySql = "INSERT INTO client VALUES (?, ?, ?, ?, ?)";
		PreparedStatement preparedStatement = connection.prepareStatement(querySql);
		preparedStatement.setString(1, client.getLogin());
		preparedStatement.setString(2, client.getSocialName());
		preparedStatement.setString(3, client.getCpf());
		try
		{
			preparedStatement.setDate(4, convertDate(client.getDateBirth()));
		} catch (Exception e) {
			throw new SQLException("Invalid Data");
		}

		preparedStatement.setString(5, client.getSex());
		preparedStatement.execute();

		preparedStatement.close();
		connection.close();
	}

	private java.sql.Date convertDate(String strDate)throws ParseException
	{
		SimpleDateFormat df = new SimpleDateFormat("dd-mm-yyyy");
		Date date = (Date) df.parse(strDate);
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		return sqlDate;
	}

	@Override
	public void update(Client client) throws SQLException {
		Connection connection = gDao.getConnection();
		String querySql = "UPDATE client SET social_name = ?, cpf = ?, "
				+ "date_birth = ?, sex = ? WHERE user_name = ?";
		PreparedStatement preparedStatement = connection.prepareStatement(querySql);
		preparedStatement.setString(1, client.getSocialName());
		preparedStatement.setString(2, client.getCpf());
		try
		{
			preparedStatement.setDate(3, convertDate(client.getDateBirth()));
		} catch (Exception e) {
			throw new SQLException("Invalid Date");
		}
		preparedStatement.setString(4, client.getSex());
		preparedStatement.setString(5, client.getLogin());
		preparedStatement.execute();

		preparedStatement.close();
		connection.close();
	}

	@Override
	public void delete(Client client) throws SQLException
	{
		Connection connection = gDao.getConnection();
		String querySql = "DELETE client WHERE user_name = ?";
		PreparedStatement ps = connection.prepareStatement(querySql);
		ps.setString(1, client.getLogin());

		ps.execute();

		ps.close();
		connection.close();
	}

	@Override
	public Client consult(Client client) throws SQLException
	{
		Connection connection = gDao.getConnection();
		String querySql = """
				SELECT u.user_name AS login, u.email, u.telephone,
					   c.sex, c.social_name, c.cpf, CONVERT(CHAR(10), c.date_birth, 105) AS date_birth
				FROM user_tbl u, client c
				WHERE u.user_name = ? AND c.user_name = u.user_name
				""";
		PreparedStatement preparedStatement = connection.prepareStatement(querySql);
		preparedStatement.setString(1, client.getLogin());
		ResultSet result = preparedStatement.executeQuery();

		if (result.next())
		{
			client.setLogin(result.getString("login"));
			client.setEmail(result.getString("email"));
			client.setTelephone(result.getString("telephone"));
			client.setSocialName(result.getString("social_name"));
			client.setCpf(result.getString("cpf"));
			client.setDateBirth(result.getString("date_birth"));
			client.setSex(result.getString("sex"));
		}

		preparedStatement.close();
		connection.close();
		return client;
	}

	@Override
	public List<Client> list() throws SQLException
	{
		List<Client> clients = new ArrayList<>();
		Connection connection = gDao.getConnection();
		String querySql = """
				SELECT u.user_name AS login, u.email, u.telephone,
					   c.sex, c.social_name, c.cpf, CONVERT(CHAR(10), c.date_birth, 103) AS date_birth
				FROM user_tbl u, client c
				WHERE u.user_name = c.user_name AND c.user_name = u.user_name
				""";
		PreparedStatement preparedStatement = connection.prepareStatement(querySql);
		ResultSet result = preparedStatement.executeQuery();

		while (result.next())
		{
			Client client = new Client(null);
			client.setSocialName(result.getString("social_name"));
			client.setCpf(result.getString("cpf"));
			client.setDateBirth(result.getString("date_birth"));
			client.setSex(result.getString("sex"));

			clients.add(client);
		}

		preparedStatement.close();
		connection.close();
		return clients;
	}
}
