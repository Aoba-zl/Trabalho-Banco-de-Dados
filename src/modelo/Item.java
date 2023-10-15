package modelo;

public class Item
{
	private int quantidade;
    private double subTotal;
    private Produto produto;

    public Item(Produto produto, int quantidade)
    {
        this.quantidade = quantidade;
        this.produto = produto;
        this.atualizarSubTotal();
    }

    public int getQuantidade()
	{
        return quantidade;
    }

    public void setQuantidade(int quantidade)
	{
        this.quantidade = quantidade;
        this.atualizarSubTotal();
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public double getSubTotal()
	{
        return subTotal;
    }
    private void atualizarSubTotal ()
    {
        subTotal = produto.getPreco() * quantidade;
    }
}
