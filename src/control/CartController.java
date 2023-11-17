package control;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.*;
import persistence.GenericDao;

public class CartController
{
    private Client client;
    private Cart cart;

    private ObservableList<Item> listCart= FXCollections.observableArrayList();



    public void populateWinCart(){
        if (listCart == null) {
            GenericDao genericDao= new GenericDao();
            // se a lista estiver vazia, consulta no banco de dados se a uma order que esta sem pagamento
            // se retornar null, o usuario não adicionou nenhum produto ao carrinho
        }
        else {
            //se tiver items na lista, consulta no banco de dados se a uma order sem pagamento
            //se retornar alguma order, aqueles items na lista são adicionados ao order usando o inserir do banco de dados
            //se não, é preciso criar uma order e os items na lista são inseridos no banco de dados
        }


    }

    public CartController(Client client) {
        this.client = client;
        this.cart = client.getCart();
    }

    public ObservableList<Item> getListCart() {
        return listCart;
    }

    public void setListCart(Item item) {
        listCart.add(0, item);
    }

    public void deleteOrder (Item order) {
        //TODO: CtrlCarrinho. Corpo da operacao
    }
    
    public double calculateTotal (List<Item> select) {
        double totalSelect = 0;
        //TODO: CtrlCarrinho. Corpo da operacao
        // loop de soma os valores dos pedidos selecionados no carrinho
        return totalSelect;
    }
    public void clearCart() {
        //TODO: CtrlCarrinho. Corpo da operacao
        List<Item> items = cart.getItens();
        items.clear();
    }
    
    public void alterQuantity () {
        //TODO: CtrlCarrinho. Corpo da operacao
    }
    
    public void placeOrder () {
        //TODO: CtrlCarrinho. Corpo da operacao
    }
    
    private Order createPedido () {
        Order newOrder = new Order();
        return newOrder;
    }
}
