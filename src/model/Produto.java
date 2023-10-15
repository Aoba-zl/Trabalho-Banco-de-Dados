package model;

import java.util.Currency;
import java.util.List;

public class Produto
{
	private List<VariacaoProduto> variacoes;
    private List<Imagem> imagens;
    private String nome;
    private Currency preco;
    private String descricao;
    private int estoqueTotal;
    private String Categoria;

    public String getCategoria()
    {
        return Categoria;
    }

    public void setCategoria(String categoria)
    {
        Categoria = categoria;
    }

    public List<Imagem> getImagens()
	{
        return imagens;
    }

    public void setImagens(List<Imagem> imagens)
	{
        this.imagens = imagens;
    }

    public List<VariacaoProduto> getVariacoes()
	{
        return variacoes;
    }

    public void setVariacoes(List<VariacaoProduto> variacoes)
	{
        this.variacoes = variacoes;
    }

    public String getNome()
	{
        return nome;
    }

    public void setNome(String nome)
	{
        this.nome = nome;
    }

    public Currency getPreco()
	{
        return preco;
    }

    public void setPreco(Currency preco)
	{
        this.preco = preco;
    }

    public String getDescricao()
	{
        return descricao;
    }

    public void setDescricao(String descricao)
	{
        this.descricao = descricao;
    }

    public int getEstoqueTotal()
	{
        return estoqueTotal;
    }

    public void setEstoqueTotal(int estoqueTotal)
	{
        this.estoqueTotal = estoqueTotal;
    }
}