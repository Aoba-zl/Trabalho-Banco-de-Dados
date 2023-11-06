package model;

import java.util.Date;
import java.util.List;

public class Client extends User
{
    private List<Order> order;
    private List<Address> address;
    private Cart cart;
	private String socialName;
    private String cpf;
    private String sex;
//    private Date dateBirth;
    private String dateBirth;
    
    public Client(String login)
    {
    	this.setLogin(login);
    }

    public Client()
    {
    	super();
    }
    
	public List<Order> getOrder() 
	{
		return order;
	}
	
	public void setOrder(List<Order> order) 
	{
		this.order = order;
	}
	
	public List<Address> getAddress()
	{
		return address;
	}
	
	public void setAddress(List<Address> address) 
	{
		this.address = address;
	}
	
	public Cart getCart()
	{
		return cart;
	}
	
	public void setCart(Cart cart) 
	{
		this.cart = cart;
	}
	
	public String getSocialName() 
	{
		return socialName;
	}
	
	public void setSocialName(String socialName) 
	{
		this.socialName = socialName;
	}
	
	public String getCpf()
	{
		return cpf;
	}
	
	public void setCpf(String cpf) 
	{
		this.cpf = cpf;
	}
	
	public String getSex()
	{
		return sex;
	}
	
	public void setSex(String sex)
	{
		this.sex = sex;
	}

	public String getDateBirth() {
		return dateBirth;
	}

	public void setDateBirth(String dateBirth) {
		this.dateBirth = dateBirth;
	}
    
}
