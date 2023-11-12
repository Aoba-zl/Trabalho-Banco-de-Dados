package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Currency;
import java.util.Date;

public class Payment
{
	private LocalDate date;
    private Currency totalPay;
    private String status;

    DateTimeFormatter dtf= DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public LocalDate getDate() {
        return date;
    }

    public void setDate(String date) {
        LocalDate dt = LocalDate.parse(date, dtf);
        setDate(dt);
    }

    public void setDate(LocalDate date){
        this.date= date;
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
