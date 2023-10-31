package model;

public class Item
{
	private int quantity;
    private double subTotal;
    private Product product;
    
    
    
	public Item(Product product, int quantity) {
		this.quantity = quantity;
		this.product = product;
		this.updateSubTotal();
	}

	public int getQuantity()
	{
		return quantity;
	}
	
	public void setQuantity(int quantity) 
	{
		this.quantity = quantity;
	}
	
	public double getSubTotal() 
	{
		return subTotal;
	}
	
	public void setSubTotal(double subTotal)
	{
		this.subTotal = subTotal;
	}
	
	public Product getProduct()
	{
		return product;
	}
	
	public void setProduct(Product product)
	{
		this.product = product;
	}
	
	private void updateSubTotal() 
	{
		subTotal = product.getPrice() * quantity;
	}
	
}
