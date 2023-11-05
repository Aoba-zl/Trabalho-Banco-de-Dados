package model;

import java.util.Currency;
import java.util.List;

public class Pedido
{
    private List<Item> itens;
    private Currency total;
    private Pagamento pagamento;

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

    public Pagamento getPagamento()
	{
        return pagamento;
    }

    public void setPagamento(Pagamento pagamento)
	{
        this.pagamento = pagamento;
    }
}
