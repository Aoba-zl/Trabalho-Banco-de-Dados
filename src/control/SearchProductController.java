package control;

import java.util.ArrayList;
import java.util.List;

import model.Cart;
import model.Client;
import model.Item;
import model.Product;

public class SearchProductController
{
    Client client;
    Cart cart;

    public SearchProductController(Client client)
    {
        this.client = client;
        this.cart = client.getCart();
    }

    public List<Product> searchProduct(String name)
    {
        List<Product> foundProduct = new ArrayList<Product>();
        //TODO: CtrlBuscarProduto. Corpo da operacao
        return foundProduct;
    }
    public void addToCart(Product product, int quantity)
    {
        //TODO: CtrlBuscarProduto. Corpo da operacao
        Item newItem = generateItemCartOrder(product, quantity);
        cart.addItems(newItem);
    }
    public void addToCartAsOrder(Product product, int quantity)
    {
        //TODO: CtrlBuscarProduto. Corpo da operacao. se não der para
        // envivar o produto já selecionado, esse método morre
        Item newItem = generateItemCartOrder(product, quantity);
        cart.addItems(newItem);
    }
    private Item generateItemCartOrder (Product product, int quantity)
    {
        return new Item(product, quantity);
    }
    //TODO: há resalval em relação a essa opecacão ficar no controle
    public void displayProduct(Product product)
    {
        //TODO: CtrlBuscarProduto. Corpo da operacao
    }
}
