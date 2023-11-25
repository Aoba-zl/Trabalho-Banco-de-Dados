package model;

/**
 * Classe Modelo que representa um ClientAddress extende a classe Address.
 */
public class ClientAddress extends Address
{
	private String name;

	/**
	 * Construtor que cria um objeto ClientAddress com base em um objeto Address e um nome de identificação.
	 *
	 * @param address O objeto Address contendo informações de endereço.
	 * @param name    O nome associado de identificação do endereço.
	 */
	public ClientAddress(Address address, String name)
	{
		this.setCep(address.getCep());
		this.setEstate(address.getEstate());
		this.setCity(address.getCity());
		this.setStreet(address.getStreet());
		this.setNeighborhood(address.getNeighborhood());
		this.setDoorNumber(address.getDoorNumber());
		this.setComplement(address.getComplement());
		this.setName(name);
	}

	/**
	 * Construtor vazio para criar uma instância de ClientAddress.
	 */
	public ClientAddress()
	{
		super();
	}

	/**
	 * Obtém o nome de identificação do endereço.
	 *
	 * @return o nome de identificação do endereço.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Define o nome de identificação do endereço.
	 *
	 * @param name o nome de identificação do endereço.
	 */
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString()
	{
		String name = getName();
		String cep = getCep();
		String number = Integer.toString(getDoorNumber());
		String comp = getComplement();
		
		String msg = String.format("%s;%s;%s;%s", name, cep, number, comp);
		
		return msg;
	}
}
