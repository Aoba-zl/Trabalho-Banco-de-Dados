package model;

import java.util.Currency;
import java.util.Date;

public class Pagamento
{
	private Date data;
    private Currency totalPagar;
    private String status;

    public Date getData()
	{
        return data;
    }

    public void setData(Date data)
	{
        this.data = data;
    }

    public Currency getTotalPagar()
	{
        return totalPagar;
    }

    public void setTotalPagar(Currency totalPagar)
	{
        this.totalPagar = totalPagar;
    }

    public String getStatus()
	{
        return status;
    }

    public void setStatus(String status)
	{
        this.status = status;
    }
}
