package controle;

import modelo.*;

import java.util.List;

public class CtrlCarrinho
{
    Cliente cliente;
    Carrinho carrinho;

    public CtrlCarrinho(Cliente cliente)
    {
        this.cliente = cliente;
        this.carrinho = cliente.getCarrinho();
    }

    public List<Item> carregarItensPedidos ()
    {
        return carrinho.getItens();
    }
    public void removerPedido (Item pedido)
    {
        //TODO: CtrlCarrinho. Corpo da operacao
    }
    public double calcularTotal (List<Item> selecao)
    {
        double totalSelecao = 0;
        //TODO: CtrlCarrinho. Corpo da operacao
        // loop de soma os valores dos pedidos selecionados no carrinho
        return totalSelecao;
    }
    public void esvaziarCarrinho ()
    {
        //TODO: CtrlCarrinho. Corpo da operacao
        List<Item> itens = carrinho.getItens();
        itens.clear();
    }
    public void alterarVariacao ()
    {
        //TODO: CtrlCarrinho. Corpo da operacao
    }
    public void alterarQuantidade ()
    {
        //TODO: CtrlCarrinho. Corpo da operacao
    }
    public void realizarPedido ()
    {
        //TODO: CtrlCarrinho. Corpo da operacao
    }
    private Pedido criarPedido ()
    {
        Pedido novoPedido = new Pedido();
        return novoPedido;
    }
}
