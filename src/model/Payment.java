package model;

import java.util.Currency;
import java.util.Date;

public class Payment
{
	private Date date;
    private Currency totalPay;
    private String status;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Currency getTotalPay() {
        return totalPay;
    }

    public void setTotalPay(Currency totalPay) {
        this.totalPay = totalPay;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
