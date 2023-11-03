package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import model.Product;
import utils.UserSession;

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
		int id = -1; //Retorna -1 para caso tenha dado erro na inserção.
		Connection c = gDao.getConnection();
		String sql = "INSERT INTO product VALUES (?, ?, ?, ?, ?, ?, ?);";
		PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS); //Statement.RETURN_GENERATED_KEYS é necessário para pegar a PK
		ps.setString(1, UserSession.getUserName());
		ps.setString(2, p.getName());
		ps.setDouble(3, p.getPrice());
		ps.setInt(4, p.getTotalStock());
		ps.setDouble(5, p.getShipping());
		ps.setString(6, p.getCategory());
		ps.setString(7, p.getDescription());
		
		int linha = ps.executeUpdate();
		
		if(linha > 0)
		{
			ResultSet rs = ps.getGeneratedKeys(); //Obtém a PK do produto
			if(rs.next())
			{
				id = rs.getInt(1);						
			}
			
			rs.close();
		}
		
		ps.close();
		c.close();
		
		return id;
	}

	@Override
	public int update(Product p) throws SQLException 
	{
		int id = -1;
		
		//
		//TODO Codigo para update
		//
		
		
		return id;
	}

	@Override
	public boolean delete(Product p) throws SQLException 
	{
		
		//
		//TODO codigo para delete
		//
		
		return true;
	}

	@Override
	public Product consult(Product p) throws SQLException
	{
		
		//
		//TODO codigo para conulta do produto
		//
		
		return null;
	}

	@Override
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
	
	
	
	

}
