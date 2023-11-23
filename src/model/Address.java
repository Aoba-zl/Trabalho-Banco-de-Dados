package model;
public class Address
{
	private String cep;
	private String uf;
	private String localidade;
	private String bairro;
	private String logradouro;
    private int doorNumber;
    private String complement;
    
	public Address(String cep,String logradouro,String complement,String bairro, String uf, String localidade, String doorNmbr)
	{
		this.setCep(cep);	
		this.setStreet(logradouro);
		this.setComplement(complement);
		this.setNeighborhood(bairro);
		this.setEstate(uf);
		this.setCity(localidade);
		this.setDoorNumber(Integer.parseInt(doorNmbr));

	}
	public Address()
	{
		super();
	}

	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getEstate() {
		return uf;
	}
	public void setEstate(String estate) {
		this.uf = estate;
	}
	public String getCity() {
		return localidade;
	}
	public void setCity(String city) {
		this.localidade = city;
	}
	public String getNeighborhood() {
		return bairro;
	}
	public void setNeighborhood(String bairro) {
		this.bairro = bairro;
	}
	public String getStreet() {
		return logradouro;
	}
	public void setStreet(String logradouro) {
		this.logradouro = logradouro;
	}
	public String getComplement() {
		return complement;
	}
	public void setComplement(String complement) {
		this.complement = complement;
	}
	public int getDoorNumber() {
		return doorNumber;
	}
	public void setDoorNumber(int doorNumber) {
		this.doorNumber = doorNumber;
	}
	@Override
	public String toString() {
		return "Address [cep=" + cep + ", uf=" + uf + ", localidade=" + localidade + ", bairro=" + bairro
				+ ", logradouro=" + logradouro + ", doorNumber=" + doorNumber + ", complement=" + complement + "]";
	}
	
}
