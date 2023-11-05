package model;

import java.util.List;

public class Carrinho
{
    private List<Item> itens;
	private double total;

    public List<Item> getItens()
	{
        return itens;
    }

    public void setItens(List<Item> itens)
	{
        this.itens = itens;
    }
    public void addItem(Item novoProduto)
    {
        this.itens.add(novoProduto);
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
