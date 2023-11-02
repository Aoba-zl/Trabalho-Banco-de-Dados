package control;

import java.sql.SQLException;
import java.text.Format;

import javafx.scene.control.Label;
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
	TextField tfCod;
	Label lblMessage;
	
	public ProductController(TextField tfName, TextField tfPrice, TextField tfInStock, TextField tfShipping, TextField tfCategory, TextArea taDescription, TextField tfCod, Label lblMessage) 
	{
		this.tfName = tfName;
		this.tfPrice = tfPrice;
		this.tfInStock = tfInStock;
		this.tfShipping = tfShipping;
		this.tfCategory = tfCategory;
		this.taDescription = taDescription;
		this.lblMessage = lblMessage;
		this.tfCod = tfCod;
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
		
		int id = pDao.insert(p);
		
		if(id != -1)
		{
			tfCod.setText(String.valueOf(id));
			return true;
		}
		return false;
	}
	
	
	
}
