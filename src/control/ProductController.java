package control;

import java.sql.SQLException;
import java.util.List;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.Product;
import persistence.GenericDao;
import persistence.ProductDao;

public class ProductController
{
	private TextField tfName;
	private TextField tfPrice;
	private TextField tfInStock;
	private TextField tfShipping;
	private TextField tfCategory;
	private TextArea taDescription;
	private Label lblMessage;
	
	/**
	 * Construtor da classe ProductController para caso não precise de parâmetros.
	 */
	public ProductController()
	{
		
	}
	
	/**
	 * Construtor da classe ProductController para caso precise de parâmetros.
	 * @param tfName TextField que contém o nome do produto.
	 * @param tfPrice TextField que contém o preço do produto.
	 * @param tfInStock TextField que contém o estoque do produto.
	 * @param tfShipping TextField que contém o frete do produto.
	 * @param tfCategory TextField que contém a categoria do produto.
	 * @param taDescription TextField que contém a descrição do produto.
	 * @param lblMessage Mensagem de aviso da tela.
	 */
	public ProductController(TextField tfName, TextField tfPrice, TextField tfInStock, TextField tfShipping, TextField tfCategory, TextArea taDescription, Label lblMessage) 
	{
		this.tfName = tfName;
		this.tfPrice = tfPrice;
		this.tfInStock = tfInStock;
		this.tfShipping = tfShipping;
		this.tfCategory = tfCategory;
		this.taDescription = taDescription;
		this.lblMessage = lblMessage;
	}
	
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
		p.setName(tfName.getText());
		p.setPrice(Double.parseDouble(tfPrice.getText().replace(",", ".")));
		p.setTotalStock(Integer.parseInt(tfInStock.getText()));
		p.setShipping(Double.parseDouble(tfShipping.getText().replace(",", ".")));
		p.setCategory(tfCategory.getText());
		p.setDescription(taDescription.getText());
		
		return pDao.insert(p);
	}
	
	/**
	 * Faz a alteração do produto.
	 * @return True para tenha alterado o produto, false caso o contrário.
	 * @throws SQLException Caso ocorra um erro de conexão no banco de dados.
	 */
	public boolean update() throws SQLException
	{
		if(checkValues())
		{
			return false;
		}
		
		GenericDao gDao = new GenericDao();
		ProductDao pDao = new ProductDao(gDao);
		
		Product p = new Product();
		p.setName(tfName.getText());
		p.setPrice(Double.parseDouble(tfPrice.getText().replace(",", ".")));
		p.setTotalStock(Integer.parseInt(tfInStock.getText()));
		p.setShipping(Double.parseDouble(tfShipping.getText().replace(",", ".")));
		p.setCategory(tfCategory.getText());
		p.setDescription(taDescription.getText());
		//TODO falta setCod ainda
		
		
		return pDao.update(p);
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
	
	// \\d+ = apenas numeros
	// \\.\\d+ = verificação depois da virgula
	// \\d+(\\.\\d+)? = apenas numeros e casas depois da virgula, após a virgula é opcional "?"
	private boolean checkValues() //checagem para não enviar valores errados para a DB
	{
		if(tfName.getText().trim().isBlank())
		{
			lblMessage.setText("Nome Inválido");
			return true;
		}
		
		if(tfPrice.getText().trim().isBlank() || !tfPrice.getText().replace(",", ".").matches("\\d+(\\.\\d+)?"))
		{
			lblMessage.setText("Preço Inválido");
			return true;
		}
		
		if(tfInStock.getText().trim().isBlank() || !tfInStock.getText().matches("\\d+"))
		{
			lblMessage.setText("Estoque Inválido");
			return true;
		}
		
		if(tfShipping.getText().trim().isBlank() || !tfShipping.getText().replace(",", ".").matches("\\d+(\\.\\d+)?"))
		{
			lblMessage.setText("Frete Inválido");
			return true;
		}
		
		if(taDescription.getText().trim().isBlank())
		{
			lblMessage.setText("Descrição Inválida");
			return true;
		}
		
		return false;
	}
}
