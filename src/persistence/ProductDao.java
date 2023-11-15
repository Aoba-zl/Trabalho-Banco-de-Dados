package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Product;
import utils.UserSession;

public class ProductDao
{
	GenericDao gDao;
	
	public ProductDao(GenericDao gDao)
	{
		this.gDao = gDao;
	}

	public boolean insert(Product p) throws SQLException //
	{
		Connection c = gDao.getConnection();
		String sql = "INSERT INTO product VALUES (?, ?, ?, ?, ?, COALESCE(NULLIF(?,''), 'Sem categoria'), ?);";
		PreparedStatement ps = c.prepareStatement(sql); //Statement.RETURN_GENERATED_KEYS pode ser utilizado para caso queira pegar a PK que é gerado de forma automatica
		ps.setString(1, UserSession.getUserName());
		ps.setString(2, p.getName());
		ps.setDouble(3, p.getPrice());
		ps.setInt(4, p.getTotalStock());
		ps.setDouble(5, p.getShipping());
		ps.setString(6, p.getCategory());
		ps.setString(7, p.getDescription());
		
		int linha = ps.executeUpdate();
		
		
		
		ps.close();
		c.close();
		
		return linha > 0;
	}

	public boolean update(Product p) throws SQLException 
	{
		
		//
		//TODO Codigo para update
		//
		
		return true;
	}

	public boolean delete(Product p) throws SQLException 
	{
		
		//
		//TODO codigo para delete
		//
		
		return true;
	}

	public Product consult(Product p) throws SQLException
	{
		
		//
		//TODO codigo para conulta do produto
		//
		
		return null;
	}

	public List<Product> list() throws SQLException 
	{
		List<Product> products = new ArrayList<Product>();
		Connection c = gDao.getConnection();
		String sql = "SELECT id_product, name_product, unity_price, description FROM product;";
		PreparedStatement ps = c.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next())
		{
			Product p = new Product();
			p.setCod(rs.getInt(1));
			p.setName(rs.getString(2));
			p.setPrice(rs.getDouble(3));
			p.setDescription(rs.getString(4));
			
			products.add(p);
		}
		
		rs.close();
		ps.close();
		c.close();
		
		return products;
	}
	
	public List<Product> listStore() throws SQLException 
	{
		List<Product> products = new ArrayList<Product>();
		Connection c = gDao.getConnection();
		String sql = "SELECT id_product, name_product, unity_price, description FROM product WHERE user_name = ?;";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, UserSession.getUserName());
		ResultSet rs = ps.executeQuery();
		while(rs.next())
		{
			Product p = new Product();
			p.setCod(rs.getInt(1));
			p.setName(rs.getString(2));
			p.setPrice(rs.getDouble(3));
			p.setDescription(rs.getString(4));
			
			products.add(p);
		}
		
		rs.close();
		ps.close();
		c.close();
		
		return products;
	}
	
	
	

}
