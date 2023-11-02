package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import model.Product;

public class ProductDao implements ICrud<Product>
{
	GenericDao gDao;
	
	public ProductDao(GenericDao gDao)
	{
		this.gDao = gDao;
	}

	@Override
	public boolean insert(Product p) throws SQLException
	{
		Connection c = gDao.getConnection();
		String sql = "INSERT INTO product VALUES (?, ?, ?, ?, ?, ?, ?);";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, "francisco");
		ps.setString(2, p.getName());
		ps.setDouble(3, p.getPrice());
		ps.setInt(4, p.getTotalStock());
		ps.setDouble(5, p.getShipping());
		ps.setString(6, p.getCategory());
		ps.setString(7, p.getDescription());
		
		int linha = ps.executeUpdate();
		
		ps.close();
		
		return linha > 0;
	}

	@Override
	public boolean update(Product p) throws SQLException 
	{
		
		return true;
	}

	@Override
	public boolean delete(Product p) throws SQLException 
	{
		return true;
	}

	@Override
	public Product consult(Product p) throws SQLException
	{
		
		return null;
	}

	@Override
	public List<Product> list() throws SQLException 
	{
		
		return null;
	}
	
	
	
	

}
