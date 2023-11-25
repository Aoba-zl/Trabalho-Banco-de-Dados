package model;
/**
 * Classe Modelo que representa um endereço.
 */
public class Address
{
	private String cep;
	private String uf;
	private String localidade;
	private String bairro;
	private String logradouro;
    private int doorNumber;
    private String complement;

	/**
	 * Construtor para inicializar um objeto Address com os detalhes do endereço.
	 *
	 * @param cep        CEP do endereço.
	 * @param logradouro Logradouro.
	 * @param complement Complemento do endereço.
	 * @param bairro     Bairro do endereço.
	 * @param uf         Estado do endereço.
	 * @param localidade Cidade do endereço.
	 * @param doorNmbr   Número de porta.
	 */
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
	/**
	 * Construtor vazio para criar uma instância de Address.
	 */
	public Address()
	{
		super();
	}

	/**
	 * Obtém o CEP do endereço.
	 *
	 * @return O CEP do endereço.
	 */
	public String getCep() {
		return cep;
	}
	/**
	 * Define o CEP do endereço.
	 *
	 * @param cep O CEP do endereço.
	 */
	public void setCep(String cep) {
		this.cep = cep;
	}
	/**
	 * Obtém o estado do endereço.
	 *
	 * @return O estado do endereço.
	 */
	public String getEstate() {
		return uf;
	}
	/**
	 * Define o estado do endereço.
	 *
	 * @param estate O estado do endereço.
	 */
	public void setEstate(String estate) {
		this.uf = estate;
	}
	/**
	 * Obtém a cidade do endereço.
	 *
	 * @return A cidade do endereço.
	 */
	public String getCity() {
		return localidade;
	}
	/**
	 * Define a cidade do endereço.
	 *
	 * @param city A cidade do endereço.
	 */
	public void setCity(String city) {
		this.localidade = city;
	}
	/**
	 * Obtém o bairro do endereço.
	 *
	 * @return O bairro do endereço.
	 */
	public String getNeighborhood() {
		return bairro;
	}
	/**
	 * Define o bairro do endereço.
	 *
	 * @param bairro O bairro do endereço.
	 */
	public void setNeighborhood(String bairro) {
		this.bairro = bairro;
	}
	/**
	 * Obtém o logradouro do endereço.
	 *
	 * @return O logradouro do endereço.
	 */
	public String getStreet() {
		return logradouro;
	}
	/**
	 * Define o logradouro do endereço.
	 *
	 * @param logradouro O logradouro do endereço.
	 */
	public void setStreet(String logradouro) {
		this.logradouro = logradouro;
	}
	/**
	 * Obtém o complemento do endereço.
	 *
	 * @return O complemento do endereço.
	 */
	public String getComplement() {
		return complement;
	}
	/**
	 * Define o complemento do endereço.
	 *
	 * @param complement O complemento do endereço.
	 */
	public void setComplement(String complement) {
		this.complement = complement;
	}
	/**
	 * Obtém o número de porta do endereço.
	 *
	 * @return O número de porta do endereço.
	 */
	public int getDoorNumber() {
		return doorNumber;
	}
	/**
	 * Define o número de porta do endereço.
	 *
	 * @param doorNumber O número de porta do endereço.
	 */
	public void setDoorNumber(int doorNumber) {
		this.doorNumber = doorNumber;
	}
	@Override
	public String toString() {
		return "Address [cep=" + cep + ", uf=" + uf + ", localidade=" + localidade + ", bairro=" + bairro
				+ ", logradouro=" + logradouro + ", doorNumber=" + doorNumber + ", complement=" + complement + "]";
	}
	
}
