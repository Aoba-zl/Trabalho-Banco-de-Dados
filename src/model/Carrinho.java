package model;

import java.util.Currency;
import java.util.List;

public class Carrinho
{
    private List<Item> itens;
	private Currency total;

    public List<Item> getItens()
	{
        return itens;
    }

    public void setItens(List<Item> itens)
	{
        this.itens = itens;
    }

    public Currency getTotal()
	{
        return total;
    }

    public void setTotal(Currency total)
	{
        this.total = total;
    }
}
