package control;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.*;

public class CartController
{
    private Client client;
    private Cart cart;

    private ObservableList<Item> listCart= FXCollections.observableArrayList();



    public CartController(Client client)
    {
        this.client = client;
        this.cart = client.getCart();
    }

    public ObservableList<Item> getListCart() {
        return listCart;
    }

    public void deleteOrder (Item order)
    {
        //TODO: CtrlCarrinho. Corpo da operacao
    }
    
    public double calculateTotal (List<Item> select)
    {
        double totalSelect = 0;
        //TODO: CtrlCarrinho. Corpo da operacao
        // loop de soma os valores dos pedidos selecionados no carrinho
        return totalSelect;
    }
    public void clearCart()
    {
        //TODO: CtrlCarrinho. Corpo da operacao
        List<Item> items = cart.getItens();
        items.clear();
    }
    
    public void alterQuantity ()
    {
        //TODO: CtrlCarrinho. Corpo da operacao
    }
    
    public void placeOrder ()
    {
        //TODO: CtrlCarrinho. Corpo da operacao
    }
    
    private Order createPedido ()
    {
        Order newOrder = new Order();
        return newOrder;
    }
}
