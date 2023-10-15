package modelo;
public class Boleto extends Pagamento
{
    private String linhaDigitavel;

    public String getLinhaDigitavel()
    {
        return linhaDigitavel;
    }

    public void setLinhaDigitavel(String linhaDigitavel)
    {
        this.linhaDigitavel = linhaDigitavel;
    }
}
