package model;

import java.util.Currency;
import java.util.List;

public class Order {
    private List<Item> items;
    private Currency total;
    private Payment payment;

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Currency getTotal() {
        return total;
    }

    public void setTotal(Currency total) {
        this.total = total;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }
}
