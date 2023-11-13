package model;

import javafx.beans.property.SimpleStringProperty;

import java.util.Currency;
import java.util.List;

public class Order {
    private List<Item> items;
    private Currency total;
    private Payment payment;
    private Integer id;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
