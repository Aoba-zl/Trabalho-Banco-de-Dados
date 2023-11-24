package model;

public class Product
{
	private int cod;
    private String name;
    private double price;
    private int totalStock;
    private double shipping;
    private String Category;
    private String description;
    private String login;
    
    
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
		this.price = price;
	}
	
	public int getTotalStock()
	{
		return totalStock;
	}
	
	public void setTotalStock(int totalStock) 
	{
		this.totalStock = totalStock;
	}
	
	public double getShipping()
	{
		return shipping;
	}
	
	public void setShipping(double shipping) 
	{
		this.shipping = shipping;
	}
	
	public String getCategory() 
	{
		return Category;
	}
	
	public void setCategory(String category) 
	{
		Category = category;
	}
	
	public String getDescription() 
	{
		return description;
	}
	
	public void setDescription(String description) 
	{
		this.description = description;
	}

	public int getCod()
{
		return cod;
	}

	public void setCod(int cod) 
	{
		this.cod = cod;
	}
	
	public String getLogin()
{
		return login;
	}

	public void setLogin(String login) 
	{
		this.login = login;
	}
    
    
    
}