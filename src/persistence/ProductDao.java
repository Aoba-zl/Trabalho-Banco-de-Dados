package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
	public int insert(Product p) throws SQLException //
	{
		int id = -1;
		Connection c = gDao.getConnection();
		String sql = "INSERT INTO product VALUES (?, ?, ?, ?, ?, ?, ?);";
		PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, "francisco"); //TODO necessário entender como será a entrada do usuário para o banco.
		ps.setString(2, p.getName());
		ps.setDouble(3, p.getPrice());
		ps.setInt(4, p.getTotalStock());
		ps.setDouble(5, p.getShipping());
		ps.setString(6, p.getCategory());
		ps.setString(7, p.getDescription());
		
		int linha = ps.executeUpdate();
		
		
		if(linha > 0)
		{
			ResultSet rs = ps.getGeneratedKeys();
			if(rs.next())
			{
				id = rs.getInt(1);						
			}
			
			rs.close();
		}
		
		ps.close();
		
		return id;
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
