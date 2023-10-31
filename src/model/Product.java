package model;

import java.util.List;

public class Product
{
    private String name;
    private double price;
    private String description;
    private int totalStock;
    private String Category;
    
	public String getName() 
	{
		return name;
	}
	
	public void setName(String name) 
	{
		this.name = name;
	}
	
	public double getPrice()
	{
		return price;
	}
	
	public void setPrice(double price)
	{

	}
	
	public String getDescription()
	{
		return description;
	}
	
	public void setDescription(String description)
	{
		this.description = description;
	}
	
	public int getTotalStock()
	{
		return totalStock;
	}
	
	public void setTotalStock(int totalStock) 
	{
		this.totalStock = totalStock;
	}
	
	public String getCategory() 
	{
		return Category;
	}
	
	public void setCategory(String category) 
	{
		Category = category;
	}

    
    
}