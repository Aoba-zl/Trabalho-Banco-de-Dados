package control;

import model.Carrinho;
import model.Cliente;
import model.Item;
import model.Produto;

import java.util.ArrayList;
import java.util.List;

public class CtrlBuscarProduto
{
    Cliente cliente;
    Carrinho carrinho;

    public CtrlBuscarProduto(Cliente cliente)
    {
        this.cliente = cliente;
        this.carrinho = cliente.getCarrinho();
    }

    public List<Produto> buscarProduto(String nome)
    {
        List<Produto> produtosEncontrados = new ArrayList<Produto>();
        //TODO: CtrlBuscarProduto. Corpo da operacao
        return produtosEncontrados;
    }
    public void adicionarAoCarrinho(Produto produto, int quantidade)
    {
        //TODO: CtrlBuscarProduto. Corpo da operacao
        Item novoItem = gerarItemCarrinhoPedido(produto, quantidade);
        carrinho.addItem(novoItem);
    }
    public void adicionarAoCarrinhoComoPedido(Produto produto, int quantidade)
    {
        //TODO: CtrlBuscarProduto. Corpo da operacao. se não der para
        // envivar o produto já selecionado, esse método morre
        Item novoItem = gerarItemCarrinhoPedido(produto, quantidade);
        carrinho.addItem(novoItem);
    }
    private Item gerarItemCarrinhoPedido (Produto produto, int quantidade)
    {
        return new Item(produto, quantidade);
    }
    //TODO: há resalval em relação a essa opecacão ficar no controle
    public void exibirProduto(Produto produto)
    {
        //TODO: CtrlBuscarProduto. Corpo da operacao
    }
}
