package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Product;
import utils.UserSession;

public class ProductDao
{
	GenericDao gDao;
	
	/**
	 * Construtor da classe ProductDao.
	 * @param gDao Objeto responsável pela comunicação com o banco de dados.
	 */
	public ProductDao(GenericDao gDao)
	{
		this.gDao = gDao;
	}
	
	/**
	 * Faz a inserção do produto no banco de dados.
	 * @param p Armazena o objeto Product.
	 * @return True para caso tenha inserido o produto no banco de dados, false caso o contrário.
	 * @throws SQLException Caso ocorra um erro de conexão no banco de dados.
	 */
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
	
	/**
	 * Faz a alteração do produto no banco de dados.
	 * @param p p Armazena o objeto Product.
	 * @return True para caso tenha alterado o produto no banco de dados, false caso o contrário.
	 * @throws SQLException Caso ocorra um erro de conexão no banco de dados.
	 */
	public boolean update(Product p) throws SQLException 
	{
		
		Connection c = gDao.getConnection();
		String sql = "UPDATE product "
				   + "SET "
				   + "name_product = ?, "
				   + "unity_price = ?, "
				   + "total_stock = ?, "
				   + "shipping = ?, "
				   + "category = COALESCE(NULLIF(?,''), 'Sem categoria'), "
				   + "description = ? "
				   + "WHERE id_product = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, p.getName());
		ps.setDouble(2, p.getPrice());
		ps.setInt(3, p.getTotalStock());
		ps.setDouble(4, p.getShipping());
		ps.setString(5, p.getCategory());
		ps.setString(6, p.getDescription());
		ps.setInt(7, p.getCod());
		
		int linha = ps.executeUpdate();
		
		ps.close();
		c.close();
		
		return linha > 0;
		
	}

	public boolean delete(Product p) throws SQLException 
	{
		Connection connection = gDao.getConnection();
		String querySql = "DELETE product WHERE id_product = ?";
		PreparedStatement ps = connection.prepareStatement(querySql);
		ps.setInt(1, p.getCod());

		ps.execute();

		ps.close();
		connection.close();
		
		return true;
	}

	public Product consult(Product product) throws SQLException
	{
		Connection c = gDao.getConnection();
		String sql = """ 
				SELECT id_product, user_name, name_product, unity_price, total_stock, shipping, category, description 
				FROM product
				WHERE id_product = ?
				""";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, product.getCod());
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			product.setLogin(rs.getString(2));
			product.setName(rs.getString(3));
			product.setPrice(rs.getDouble(4));
			product.setTotalStock(rs.getInt(5));
			product.setShipping(rs.getDouble(6));
			product.setCategory(rs.getString(7));
			product.setDescription(rs.getString(8));
		}
		rs.close();
		ps.close();
		c.close();
		
		return product;
	}

	/**
	 * Busca a lista de produto no banco de dados.
	 * @return A lista de produto.
	 * @throws SQLException Caso ocorra um erro de conexão no banco de dados.
	 */
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
	
	/**
	 * Busca a lista de produto para o lojista no banco de dados.
	 * @return A lista de produto do lojista.
	 * @throws SQLException Caso ocorra um erro de conexão no banco de dados.
	 */
	public List<Product> listStore() throws SQLException 
	{
		List<Product> products = new ArrayList<Product>();
		Connection c = gDao.getConnection();
		String sql = "SELECT id_product, name_product, unity_price, total_stock, description FROM product WHERE user_name = ?;";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, UserSession.getUserName());
		ResultSet rs = ps.executeQuery();
		while(rs.next())
		{
			Product p = new Product();
			p.setCod(rs.getInt(1));
			p.setName(rs.getString(2));
			p.setPrice(rs.getDouble(3));
			p.setTotalStock(rs.getInt(4));
			p.setDescription(rs.getString(5));
			
			products.add(p);
		}
		
		rs.close();
		ps.close();
		c.close();
		
		return products;
	}
}
