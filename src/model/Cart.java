package model;

import java.util.List;

public class Cart
{
    private List<Item> items;
	private double total;

    public List<Item> getItens()
	{
        return items;
    }

    public void setItems(List<Item> items)
	{
        this.items = items;
    }
    
    public void addItems(Item newProduct)
    {
        this.items.add(newProduct);
    }

    public double getTotal()
	{
        return total;
    }

    public void setTotal(double total)
	{
        this.total = total;
    }
}
