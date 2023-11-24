package control;

import java.sql.SQLException;
import java.util.List;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Product;
import persistence.GenericDao;
import persistence.ProductDao;

public class ProductController
{
	private StringProperty spName = new SimpleStringProperty("");
	private StringProperty spPrice = new SimpleStringProperty("");
	private StringProperty spInStock = new SimpleStringProperty("");
	private StringProperty spShipping = new SimpleStringProperty("");
	private StringProperty spCategory = new SimpleStringProperty("");
	private StringProperty spDescription = new SimpleStringProperty("");
	private StringProperty lblMessage = new SimpleStringProperty("");
	
	/**
	 * Faz a inserção do produto no banco de dados.
	 * @return True para caso tenha inserido o produto, false caso o contrário.
	 * @throws SQLException Caso ocorra um erro de conexão no banco de dados.
	 */
	public boolean insert() throws SQLException
	{
		if(checkValues())
		{
			return false;
		}
		
		GenericDao gDao = new GenericDao();
		ProductDao pDao = new ProductDao(gDao);
		
		Product p = new Product();
		p.setName(spName.get());
		p.setPrice(Double.parseDouble(spPrice.get().replace(",", ".")));
		p.setTotalStock(Integer.parseInt(spInStock.get()));
		p.setShipping(Double.parseDouble(spShipping.get().replace(",", ".")));
		p.setCategory(spCategory.get());
		p.setDescription(spDescription.get());
		
		return pDao.insert(p);
	}
	
	/**
	 * Faz a alteração do produto.
	 * @return True para tenha alterado o produto, false caso o contrário.
	 * @throws SQLException Caso ocorra um erro de conexão no banco de dados.
	 */
	public boolean update(IntegerProperty ipCod) throws SQLException
	{
		if(checkValues())
		{
			return false;
		}
		
		GenericDao gDao = new GenericDao();
		ProductDao pDao = new ProductDao(gDao);
		
		Product p = new Product();
		p.setCod(ipCod.get());
		p.setName(spName.get());
		p.setPrice(Double.parseDouble(spPrice.get().replace(",", ".")));
		p.setTotalStock(Integer.parseInt(spInStock.get()));
		p.setShipping(Double.parseDouble(spShipping.get().replace(",", ".")));
		p.setCategory(spCategory.get());
		p.setDescription(spDescription.get());
		
		return pDao.update(p);
	}
	
	public void setValueEdit(IntegerProperty ipCod)
	{
		Product p = new Product();
		
		p.setCod(ipCod.get());
		p = consulta(p);
		
		spName.set(p.getName());
		spPrice.set(String.valueOf(p.getPrice()));
		spInStock.set(String.valueOf(p.getTotalStock()));
		spShipping.set(String.valueOf(p.getShipping()));
		spCategory.set(p.getCategory());
		spDescription.set(p.getDescription());
	}
	
	/**
	 * Busca a lista de produto para o usuário na pagina principal.
	 * @return Lista de produto.
	 * @throws SQLException Caso ocorra um erro de conexão no banco de dados.
	 */
	public List<Product> listProduct() throws SQLException
	{
		GenericDao gDao = new GenericDao();
		ProductDao pDao = new ProductDao(gDao);
		
		List<Product> products = pDao.list();
		
		return products;
	}
	
	/**
	 * Busca a lista de produto para o usuário lojista na pagina da loja.
	 * @return Lista de produto do lojista.
	 * @throws SQLException Caso ocorra um erro de conexão no banco de dados.
	 */
	public List<Product> listProductStore() throws SQLException
	{
		GenericDao gDao = new GenericDao();
		ProductDao pDao = new ProductDao(gDao);
		
		List<Product> products = pDao.listStore();
		
		return products;
	}

	public Product consulta (Product product) {
		GenericDao gDao = new GenericDao();
		ProductDao pDao = new ProductDao(gDao);
		
		try {
			product = pDao.consult(product);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return product;
	}
	
	public boolean delete (Product product) {
		
		GenericDao gDao = new GenericDao();
		ProductDao pDao = new ProductDao(gDao);
		try {
			return  pDao.delete(product);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public int quant (Product product) {
		GenericDao gDao = new GenericDao();
		ProductDao pDao = new ProductDao(gDao);
		try {
			return pDao.quantProduct(product);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	// \\d+ = apenas numeros
	// \\.\\d+ = verificação depois da virgula
	// \\d+(\\.\\d+)? = apenas numeros e casas depois da virgula, após a virgula é opcional "?"
	private boolean checkValues() //checagem para não enviar valores errados para a DB
	{
		if(spName.get().trim().isBlank())
		{
			lblMessage.set("Nome Inválido");
			return true;
		}
		
		if(spPrice.get().trim().isBlank() || !spPrice.get().replace(",", ".").matches("\\d+(\\.\\d+)?"))
		{
			lblMessage.set("Preço Inválido");
			return true;
		}
		
		if(spInStock.get().trim().isBlank() || !spInStock.get().matches("\\d+"))
		{
			lblMessage.set("Estoque Inválido");
			return true;
		}
		
		if(spShipping.get().trim().isBlank() || !spShipping.get().replace(",", ".").matches("\\d+(\\.\\d+)?"))
		{
			lblMessage.set("Frete Inválido");
			return true;
		}
		
		if(spDescription.get().trim().isBlank())
		{
			lblMessage.set("Descrição Inválida");
			return true;
		}
		
		return false;
	}

	public StringProperty getNameProperty() { return spName; }
	
	public StringProperty getPriceProperty() { return spPrice; }
	
	public StringProperty getInStockProperty() { return spInStock; }
	
	public StringProperty getShippingProperty() { return spShipping; }
	
	public StringProperty getCategoryProperty() { return spCategory; }
	
	public StringProperty getDescriptionProperty() { return spDescription; }

	public StringProperty getMessageProperty() { return lblMessage; }
	
}
