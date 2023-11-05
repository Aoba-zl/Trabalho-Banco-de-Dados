package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Store;

public class StoreDao implements CrudDao<Store>
{
	private GenericDao gDao;
	
	public StoreDao(GenericDao gDao)
	{
		this.gDao = gDao;
	}

	@Override
	public void insert(Store store) throws SQLException
	{
		Connection connection = gDao.getConnection();
		String querySql = "INSERT INTO store VALUES (?, ?, ?)";
		PreparedStatement ps = connection.prepareStatement(querySql);
		ps.setString(1, store.getLogin());
		ps.setString(2, store.getNameStore());
		ps.setString(3, store.getCnpj());
		
		ps.execute();
		
		ps.close();
		connection.close();
	}

	@Override
	public void update(Store store) throws SQLException
	{
		Connection connection = gDao.getConnection();
		String querySql = "UPDATE store SET store_name = ?, cnpj = ? "
				+ "WHERE user_name = ?";
		PreparedStatement ps = connection.prepareStatement(querySql);
		ps.setString(1, store.getNameStore());
		ps.setString(2, store.getCnpj());
		ps.setString(3, store.getLogin());
		ps.execute();
		
		ps.close();
		connection.close();
	}

	@Override
	public void delete(Store store) throws SQLException
	{
		Connection connection = gDao.getConnection();
		String querySql = "DELETE store WHERE user_name = ?";
		PreparedStatement ps = connection.prepareStatement(querySql);
		ps.setString(1, store.getLogin());
		
		ps.execute();
		
		ps.close();
		connection.close();
	}

	@Override
	public Store consult(Store store) throws SQLException
	{
		Connection connection = gDao.getConnection();
		String querySql = "SELECT * FROM store WHERE user_name = ?";
		PreparedStatement ps = connection.prepareStatement(querySql);
		ps.setString(1, store.getLogin());
		
		ResultSet result = ps.executeQuery();
		
		if (result.next())
		{
			store.setNameStore(result.getString("store_name"));
			store.setCnpj(result.getString("cnpj"));
		}
		
		ps.close();
		connection.close();
		return store;
	}

	@Override
	public List<Store> list() throws SQLException
	{
		List<Store> stores = new ArrayList<>();
		Connection connection = gDao.getConnection();
		String querySql = "SELECT * FROM store WHERE user_name = ?";
		PreparedStatement ps = connection.prepareStatement(querySql);
		
		ResultSet result = ps.executeQuery();
		
		if (result.next())
		{
			Store store = new Store(result.getString("user_name"));
			store.setNameStore(result.getString("store_name"));
			store.setCnpj(result.getString("cnpj"));
			
			stores.add(store);
		}
		
		ps.close();
		connection.close();
		return stores;
	}
	


}
