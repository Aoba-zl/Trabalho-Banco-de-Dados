package model;

import java.util.Currency;

public class Item
{
	private int quantidade;
    private Currency subTotal;

    public int getQuantidade()
	{
        return quantidade;
    }

    public void setQuantidade(int quantidade)
	{
        this.quantidade = quantidade;
    }

    public Currency getSubTotal()
	{
        return subTotal;
    }

    public void setSubTotal(Currency subTotal)
	{
        this.subTotal = subTotal;
    }
}
