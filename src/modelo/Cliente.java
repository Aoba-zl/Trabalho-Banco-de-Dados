package modelo;

import java.util.Date;
import java.util.List;

public class Cliente extends Usuario
{
    private List<Pedido> pedidos;
    private List<Endereco> enderecos;
    private Carrinho carrinho;
	private String nomeSocial;
    private String cpf;
    private String sexo;
    private Date dataNasc;

    public List<Pedido> getPedidos()
	{
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos)
	{
        this.pedidos = pedidos;
    }

    public List<Endereco> getEnderecos()
	{
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos)
	{
        this.enderecos = enderecos;
    }

    public Carrinho getCarrinho()
	{
        return carrinho;
    }

    public void setCarrinho(Carrinho carrinho)
	{
        this.carrinho = carrinho;
    }

    public String getNomeSocial()
	{
        return nomeSocial;
    }

    public void setNomeSocial(String nomeSocial)
	{
        this.nomeSocial = nomeSocial;
    }

    public String getCpf()
	{
        return cpf;
    }

    public void setCpf(String cpf)
	{
        this.cpf = cpf;
    }

    public String getSexo()
	{
        return sexo;
    }

    public void setSexo(String sexo)
	{
        this.sexo = sexo;
    }

    public Date getDataNasc()
	{
        return dataNasc;
    }

    public void setDataNasc(Date dataNasc)
	{
        this.dataNasc = dataNasc;
    }
}
