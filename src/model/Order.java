package model;

import java.util.List;

/**
 * Classe Modelo que representa um Order.
 */
public class Order {
    private List<Item> items;
    private double total;

    private double totalPortage;
    private Payment payment;
    private Integer id;

    /**
     * Obtém a lista de itens do pedido.
     *
     * @return A lista de itens do pedido.
     */
    public List<Item> getItems() {
        return items;
    }

    /**
     * Define a lista de itens do pedido.
     *
     * @param items A lista de itens do pedido.
     */
    public void setItems(List<Item> items) {
        this.items = items;
    }

    /**
     * Obtém o valor total do pedido.
     *
     * @return O valor total do pedido.
     */
    public Double getTotal() {
        return total;
    }

    /**
     * Define o valor total do pedido.
     *
     * @param total O valor total do pedido.
     */
    public void setTotal(Double total) {
        this.total = total;
    }

    /**
     * Obtém o método de pagamento do pedido.
     *
     * @return O método de pagamento do pedido.
     */
    public Payment getPayment() {
        return payment;
    }

    /**
     * Define o método de pagamento do pedido.
     *
     * @param payment O método de pagamento do pedido.
     */
    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    /**
     * Obtém o identificador do pedido.
     *
     * @return O identificador do pedido.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Define o identificador do pedido.
     *
     * @param id O identificador do pedido.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Obtém o valor total do frete do pedido.
     *
     * @return O valor total do frete do pedido.
     */
    public double getTotalPortage() {
        return totalPortage;
    }

    /**
     * Define o valor total do frete do pedido.
     *
     * @param totalPortage O valor total do frete do pedido.
     */
    public void setTotalPortage(double totalPortage) {
        this.totalPortage = totalPortage;
    }
}
