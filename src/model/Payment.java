package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Currency;

/**
 * Classe Modelo que representa um .
 */
public class Payment
{
	private LocalDate date;
    private Currency totalPay;
    private String status;
    private String paymentMethod;

    DateTimeFormatter dtf= DateTimeFormatter.ofPattern("dd/MM/yyyy");

    /**
     * Obtém a data do pagamento.
     *
     * @return A data do pagamento.
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Define a data do pagamento.
     *
     * @param date A data do pagamento.
     */
    public void setDate(LocalDate date){
        this.date= date;
    }

    /**
     * Obtém o valor total pago.
     *
     * @return O valor total pago.
     */
    public Currency getTotalPay() {
        return totalPay;
    }

    /**
     * Define o valor total pago.
     *
     * @param totalPay O valor total pago.
     */
    public void setTotalPay(Currency totalPay) {
        this.totalPay = totalPay;
    }

    /**
     * Obtém o status do pagamento.
     *
     * @return O status do pagamento.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Define o status do pagamento.
     *
     * @param status O status do pagamento.
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Obtém o método de pagamento.
     *
     * @return O método de pagamento.
     */
    public String getPaymentMethod() {
        return paymentMethod;
    }

    /**
     * Define o método de pagamento.
     *
     * @param paymentMethod O método de pagamento.
     */
    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

}
