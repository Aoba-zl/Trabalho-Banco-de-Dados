package model;

import java.util.List;

/**
 * Classe Modelo que representa um carrinho de compras.
 */
public class Cart
{
    private List<Item> items;
	private double total;

    /**
     * Obtém a lista de itens no carrinho.
     *
     * @return A lista de itens no carrinho.
     */
    public List<Item> getItens()
	{
        return items;
    }

    /**
     * Define a lista de itens no carrinho.
     *
     * @param items A lista de itens no carrinho.
     */
    public void setItems(List<Item> items)
	{
        this.items = items;
    }

    /**
     * Adiciona um novo item ao carrinho.
     *
     * @param newProduct O novo item a ser adicionado ao carrinho.
     */
    public void addItems(Item newProduct)
    {
        this.items.add(newProduct);
    }

    /**
     * Obtém o total do carrinho.
     *
     * @return O total do carrinho.
     */
    public double getTotal()
	{
        return total;
    }

    /**
     * Define o total do carrinho.
     *
     * @param total O total do carrinho.
     */
    public void setTotal(double total)
	{
        this.total = total;
    }
}
