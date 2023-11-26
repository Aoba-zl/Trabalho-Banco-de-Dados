package model;

/**
 * Classe Modelo que representa um Product.
 */
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
    private int status = 0;

	/**
	 * Obtém o nome do produto.
	 *
	 * @return O nome do produto.
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Define o nome do produto.
	 *
	 * @param name O nome do produto.
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * Obtém o preço do produto.
	 *
	 * @return O preço do produto.
	 */
	public double getPrice() 
	{
		return price;
	}

	/**
	 * Define o preço do produto.
	 *
	 * @param price O preço do produto.
	 */
	public void setPrice(double price) 
	{
		this.price = price;
	}

	/**
	 * Obtém o estoque total do produto.
	 *
	 * @return O estoque total do produto.
	 */
	public int getTotalStock()
	{
		return totalStock;
	}

	/**
	 * Define o estoque total do produto.
	 *
	 * @param totalStock O estoque total do produto.
	 */
	public void setTotalStock(int totalStock) 
	{
		this.totalStock = totalStock;
	}

	/**
	 * Obtém o custo de envio do produto.
	 *
	 * @return O custo de envio do produto.
	 */
	public double getShipping()
	{
		return shipping;
	}

	/**
	 * Define o custo de envio do produto.
	 *
	 * @param shipping O custo de envio do produto.
	 */
	public void setShipping(double shipping) 
	{
		this.shipping = shipping;
	}

	/**
	 * Obtém a categoria do produto.
	 *
	 * @return A categoria do produto.
	 */
	public String getCategory() 
	{
		return Category;
	}

	/**
	 * Define a categoria do produto.
	 *
	 * @param category A categoria do produto.
	 */
	public void setCategory(String category) 
	{
		Category = category;
	}

	/**
	 * Obtém a descrição do produto.
	 *
	 * @return A descrição do produto.
	 */
	public String getDescription() 
	{
		return description;
	}

	/**
	 * Define a descrição do produto.
	 *
	 * @param description A descrição do produto.
	 */
	public void setDescription(String description) 
	{
		this.description = description;
	}

	/**
	 * Obtém o código do produto.
	 *
	 * @return O código do produto.
	 */
	public int getCod()
{
		return cod;
	}

	/**
	 * Define o código do produto.
	 *
	 * @param cod O código do produto.
	 */
	public void setCod(int cod) 
	{
		this.cod = cod;
	}

	/**
	 * Obtém o login associado ao produto.
	 *
	 * @return O login associado ao produto.
	 */
	public String getLogin()
{
		return login;
	}

	/**
	 * Define o login associado ao produto.
	 *
	 * @param login O login associado ao produto.
	 */
	public void setLogin(String login) 
	{
		this.login = login;
	}
    
    public int getStatus()
    {
    	return status;
    }
    
    public void setStatus(int status)
    {
    	this.status = status;
    }
}