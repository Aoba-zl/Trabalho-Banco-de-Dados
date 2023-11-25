package model;

import java.util.List;

/**
 * Classe Modelo que representa um Client, estende a classe User.
 */
public class Client extends User
{
    private List<Order> order;
    private List<Address> address;
    private Cart cart;
	private String socialName;
    private String cpf;
    private String sex;
    private String dateBirth;

	/**
	 * Construtor que recebe o login do cliente.
	 *
	 * @param login O nome de usuário (login) do cliente.
	 */
    public Client(String login)
    {
    	this.setLogin(login);
    }

	/**
	 * Construtor vazio para criar uma instância de Client.
	 */
    public Client()
    {
    	super();
    }

	/**
	 * Obtém a lista de pedidos do cliente.
	 *
	 * @return A lista de pedidos do cliente.
	 */
	public List<Order> getOrder() 
	{
		return order;
	}

	/**
	 * Define a lista de pedidos do cliente.
	 *
	 * @param order A lista de pedidos do cliente.
	 */
	public void setOrder(List<Order> order) 
	{
		this.order = order;
	}

	/**
	 * Obtém a lista de endereços do cliente.
	 *
	 * @return A lista de endereços do cliente.
	 */
	public List<Address> getAddress()
	{
		return address;
	}

	/**
	 * Define a lista de endereços do cliente.
	 *
	 * @param address A lista de endereços do cliente.
	 */
	public void setAddress(List<Address> address) 
	{
		this.address = address;
	}

	/**
	 * Obtém o carrinho do cliente.
	 *
	 * @return O carrinho do cliente.
	 */
	public Cart getCart()
	{
		return cart;
	}

	/**
	 * Define o carrinho do cliente.
	 *
	 * @param cart O carrinho do cliente.
	 */
	public void setCart(Cart cart) 
	{
		this.cart = cart;
	}

	/**
	 * Obtém o nome social do cliente.
	 *
	 * @return O nome social do cliente.
	 */
	public String getSocialName() 
	{
		return socialName;
	}

	/**
	 * Define o nome social do cliente.
	 *
	 * @param socialName O nome social do cliente.
	 */
	public void setSocialName(String socialName) 
	{
		this.socialName = socialName;
	}

	/**
	 * Obtém o CPF do cliente.
	 *
	 * @return O CPF do cliente.
	 */
	public String getCpf()
	{
		return cpf;
	}

	/**
	 * Define o CPF do cliente.
	 *
	 * @param cpf O CPF do cliente.
	 */
	public void setCpf(String cpf) 
	{
		this.cpf = cpf;
	}

	/**
	 * Obtém o sexo do cliente.
	 *
	 * @return O sexo do cliente.
	 */
	public String getSex()
	{
		return sex;
	}

	/**
	 * Define o sexo do cliente.
	 *
	 * @param sex O sexo do cliente.
	 */
	public void setSex(String sex)
	{
		this.sex = sex;
	}

	/**
	 * Obtém a data de nascimento do cliente.
	 *
	 * @return A data de nascimento do cliente.
	 */
	public String getDateBirth() {
		return dateBirth;
	}

	/**
	 * Define a data de nascimento do cliente.
	 *
	 * @param dateBirth A data de nascimento do cliente.
	 */
	public void setDateBirth(String dateBirth) {
		this.dateBirth = dateBirth;
	}
    
}
