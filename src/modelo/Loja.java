package modelo;

import java.util.List;

public class Loja extends Usuario
{
    private List<Produto> produtos;
    private Endereco endereco;
    private String nomeLoja;
    private String nomeGerente;
    private String cnpj;

    public List<Produto> getProdutos()
	{
        return produtos;
    }

    public void setProdutos(List<Produto> produtos)
	{
        this.produtos = produtos;
    }

    public Endereco getEndereco()
	{
        return endereco;
    }

    public void setEndereco(Endereco endereco)
	{
        this.endereco = endereco;
    }

    public String getNomeLoja()
	{
        return nomeLoja;
    }

    public void setNomeLoja(String nomeLoja)
	{
        this.nomeLoja = nomeLoja;
    }

    public String getNomeGerente()
	{
        return nomeGerente;
    }

    public void setNomeGerente(String nomeGerente)
	{
        this.nomeGerente = nomeGerente;
    }

    public String getCnpj()
	{
        return cnpj;
    }

    public void setCnpj(String cnpj)
	{
        this.cnpj = cnpj;
    }
}
