package model;
public class Address
{
	private String cep;
	private String estate;
	private String city;
	private String neighborhood;
	private String Street;
    private int doorNumber;
    private String complement;

	public Address(String cep, String estate, String city, String street,
                          String neighborhood, String doorNmbr, String complement)
	{
		this.setCep(cep);
		this.setEstate(estate);
		this.setCity(city);
		this.setStreet(street);
		this.setNeighborhood(neighborhood);
		this.setDoorNumber(Integer.parseInt(doorNmbr));
		this.setComplement(complement);
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
		return estate;
	}
	public void setEstate(String estate) {
		this.estate = estate;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getNeighborhood() {
		return neighborhood;
	}
	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}
	public String getStreet() {
		return Street;
	}
	public void setStreet(String street) {
		Street = street;
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
}
