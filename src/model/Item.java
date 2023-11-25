package model;

/**
 * Classe Modelo que representa um Item.
 */
public class Item
{
	private int quantity;
    private double subTotal;
    private Product product;

	/**
	 * Construtor que cria um objeto Item com base em um produto e uma quantidade.
	 *
	 * @param product  O produto associado ao item.
	 * @param quantity A quantidade do produto.
	 */
	public Item(Product product, int quantity) {
		this.quantity = quantity;
		this.product = product;
		this.updateSubTotal();
	}

	/**
	 * Construtor vazio para criar uma instância de Item.
	 */
	public Item()
	{
		super();
	}

	/**
	 * Obtém a quantidade do item.
	 *
	 * @return A quantidade do item.
	 */
	public int getQuantity()
	{
		return quantity;
	}

	/**
	 * Define a quantidade do item.
	 *
	 * @param quantity A quantidade do item.
	 */
	public void setQuantity(int quantity) 
	{
		this.quantity = quantity;
	}

	/**
	 * Obtém o subtotal do item.
	 *
	 * @return O subtotal do item.
	 */
	public double getSubTotal() 
	{
		return subTotal;
	}

	/**
	 * Define o subtotal do item.
	 *
	 * @param subTotal O subtotal do item.
	 */
	public void setSubTotal(double subTotal)
	{
		this.subTotal = subTotal;
	}

	/**
	 * Obtém o produto associado ao item.
	 *
	 * @return O produto associado ao item.
	 */
	public Product getProduct()
	{
		return product;
	}

	/**
	 * Define o produto associado ao item.
	 *
	 * @param product O produto associado ao item.
	 */
	public void setProduct(Product product)
	{
		this.product = product;
	}

	/**
	 * Atualiza o subtotal com base na quantidade e no preço do produto.
	 */
	private void updateSubTotal() 
	{
		subTotal = product.getPrice() * quantity;
	}
	
}
