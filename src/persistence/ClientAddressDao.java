package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Client;
import model.ClientAddress;

public class ClientAddressDao implements CrudDao<ClientAddress>
{
	private GenericDao gDao;
	private Client client;
	private String login;

	public ClientAddressDao(GenericDao gDao, Client client)
	{
		this.gDao = gDao;
		this.client = client;
		this.login = this.client.getLogin();
	}

	@Override
	public void insert(ClientAddress address) throws SQLException
	{
		Connection connection = gDao.getConnection();
		String querySql = "INSERT INTO address VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement ps = connection.prepareStatement(querySql);
		ps.setString(1, login);
		ps.setString(2, address.getName());
		ps.setString(3, address.getCep());
		ps.setString(4, address.getEstate());
		ps.setString(5, address.getCity());
		ps.setString(6, address.getNeighborhood());
		ps.setString(7, address.getStreet());
		ps.setInt(8, address.getDoorNumber());
		ps.setString(9, address.getComplement());
		
		ps.execute();
		
		ps.close();
		connection.close();
	}

	@Override
	public void update(ClientAddress address) throws SQLException
	{
		Connection connection = gDao.getConnection();
		String querySql = "UPDATE address SET address_name = ?, state = ?, "
				+ "city = ?, neighborhood = ?, street = ?, complement = ?"
				+ "WHERE user_name = ? AND cep = ? AND number = ?";
		PreparedStatement ps = connection.prepareStatement(querySql);
		ps.setString(1, address.getName());
		ps.setString(2, address.getEstate());
		ps.setString(3, address.getCity());
		ps.setString(4, address.getNeighborhood());
		ps.setString(5, address.getStreet());
		ps.setString(6, address.getComplement());
		ps.setString(7, login);
		ps.setString(8, address.getCep());
		ps.setInt   (9, address.getDoorNumber());
		
		ps.execute();
		
		ps.close();
		connection.close();
	}

	@Override
	public void delete(ClientAddress address) throws SQLException
	{
		Connection connection = gDao.getConnection();
		String querySql = "DELETE address WHERE user_name = ? AND cep = ? AND number = ?";
		PreparedStatement ps = connection.prepareStatement(querySql);
		ps.setString(1, login);
		ps.setString(2, address.getCep());
		ps.setInt   (3, address.getDoorNumber());

		ps.execute();
		
		ps.close();
		connection.close();
	}

	@Override
	public ClientAddress consult(ClientAddress address) throws SQLException
	{
		Connection connection = gDao.getConnection();
		String querySql = "SELECT address_name, cep, state, city, neighborhood, "
				+ "street, number, complement FROM address "
				+ "WHERE user_name = ? AND cep = ? AND number = ?";
		PreparedStatement ps = connection.prepareStatement(querySql);
		ps.setString(1, login);
		ps.setString(2, address.getCep());
		ps.setInt(3, address.getDoorNumber());
		
		ResultSet result = ps.executeQuery();
		
		if (result.next())
		{
			address.setName(result.getString("address_name"));
			address.setCep(result.getString("cep"));
			address.setEstate(result.getString("state"));
			address.setCity(result.getString("city"));
			address.setNeighborhood(result.getString("neighborhood"));
			address.setStreet(result.getString("street"));
			address.setDoorNumber(result.getInt("number"));
			address.setComplement(result.getString("complement"));
		}
		ps.close();
		connection.close();
		return address;
	}

	@Override
	public List<ClientAddress> list() throws SQLException {
		List<ClientAddress> allAddress = new ArrayList<>();
		Connection connection = gDao.getConnection();
		String querySql = "SELECT address_name, cep, state, city, neighborhood, "
				+ "street, number, complement FROM address "
				+ "WHERE user_name = ?";
		PreparedStatement ps = connection.prepareStatement(querySql);
		ps.setString(1, login);
		
		ResultSet result = ps.executeQuery();
		
		while (result.next())
		{
			ClientAddress address = new ClientAddress();

			address.setName(result.getString("address_name"));
			address.setCep(result.getString("cep"));
			address.setEstate(result.getString("state"));
			address.setCity(result.getString("city"));
			address.setNeighborhood(result.getString("neighborhood"));
			address.setStreet(result.getString("street"));
			address.setDoorNumber(result.getInt("number"));
			address.setComplement(result.getString("complement"));
			
			allAddress.add(address);
		}
		ps.close();
		connection.close();
		return allAddress;
	}
}
