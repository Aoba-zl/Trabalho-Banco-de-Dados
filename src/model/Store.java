package model;

import java.util.List;

public class Store extends User
{
    private List<Product> product;
    private Address address;
    private String nameStore;
    private String cnpj;
    
    public Store (String login)
    {
    	this.setLogin(login);
    }
    
    public Store()
    {
    }
    
	public List<Product> getProduct() {
		return product;
	}
	public void setProduct(List<Product> product) {
		this.product = product;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public String getNameStore() {
		return nameStore;
	}
	public void setNameStore(String nameStore) {
		this.nameStore = nameStore;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
}
