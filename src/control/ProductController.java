package control;

import java.sql.SQLException;

import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.Product;
import persistence.GenericDao;
import persistence.ProductDao;

public class ProductController
{
	TextField tfName;
	TextField tfPrice;
	TextField tfInStock;
	TextField tfShipping;
	TextField tfCategory;
	TextArea taDescription;
	
	public ProductController(TextField tfName, TextField tfPrice, TextField tfInStock, TextField tfShipping, TextField tfCategory, TextArea taDescription) 
	{
		this.tfName = tfName;
		this.tfPrice = tfPrice;
		this.tfInStock = tfInStock;
		this.tfShipping = tfShipping;
		this.tfCategory = tfCategory;
		this.taDescription = taDescription;
	}
	
	public boolean insert() throws SQLException
	{
		
		GenericDao gDao = new GenericDao();
		ProductDao pDao = new ProductDao(gDao);
		
		Product p = new Product();
		p.setName(tfName.getText());
		p.setPrice(Double.parseDouble(tfPrice.getText().replace(",", ".")));
		p.setTotalStock(Integer.parseInt(tfInStock.getText()));
		p.setShipping(Double.parseDouble(tfInStock.getText().replace(",", ".")));
		p.setCategory(tfCategory.getText());
		p.setDescription(taDescription.getText());
		
		return pDao.insert(p);
	}
	
	
	
}
