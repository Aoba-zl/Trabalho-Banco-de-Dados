package model;

import java.util.List;

/**
 * Classe Modelo que representa um Store, estende a classe User.
 */
public class Store extends User
{
    private List<Product> product;
    private Address address;
    private String nameStore;
    private String cnpj;

	/**
	 * Construtor que recebe o login como parâmetro.
	 *
	 * @param login O login da loja.
	 */
    public Store (String login)
    {
    	this.setLogin(login);
    }

	/**
	 * Construtor vazio para criar uma instância de Store.
	 */
    public Store()
    {
		super();
    }

	/**
	 * Obtém a lista de produtos da loja.
	 *
	 * @return A lista de produtos da loja.
	 */
	public List<Product> getProduct() {
		return product;
	}
	/**
	 * Define a lista de produtos da loja.
	 *
	 * @param product A lista de produtos da loja.
	 */
	public void setProduct(List<Product> product) {
		this.product = product;
	}
	/**
	 * Obtém o endereço da loja.
	 *
	 * @return O endereço da loja.
	 */
	public Address getAddress() {
		return address;
	}
	/**
	 * Define o endereço da loja.
	 *
	 * @param address O endereço da loja.
	 */
	public void setAddress(Address address) {
		this.address = address;
	}
	/**
	 * Obtém o nome da loja.
	 *
	 * @return O nome da loja.
	 */
	public String getNameStore() {
		return nameStore;
	}
	/**
	 * Define o nome da loja.
	 *
	 * @param nameStore O nome da loja.
	 */
	public void setNameStore(String nameStore) {
		this.nameStore = nameStore;
	}

	/**
	 * Obtém o CNPJ da loja.
	 *
	 * @return O CNPJ da loja.
	 */
	public String getCnpj() {
		return cnpj;
	}
	/**
	 * Define o CNPJ da loja.
	 *
	 * @param cnpj O CNPJ da loja.
	 */
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
}
