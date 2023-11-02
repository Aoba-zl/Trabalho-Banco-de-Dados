package persistence;

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
	public void insert(Product t) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Product t) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Product t) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Product consult(Product t) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> list() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	

}
