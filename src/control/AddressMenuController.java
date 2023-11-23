package control;

import java.sql.SQLException;
import java.util.List;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Address;
import model.Client;
import model.ClientAddress;
import model.Store;
import persistence.AddressDao;
import persistence.ClientAddressDao;
import persistence.GenericDao;

public class AddressMenuController
{
	private final StringProperty name = new SimpleStringProperty();
	private final StringProperty cep = new SimpleStringProperty();
	private final StringProperty cityEstate = new SimpleStringProperty();
	private final StringProperty neighborhood = new SimpleStringProperty();
	private final StringProperty street = new SimpleStringProperty();
	private final StringProperty number = new SimpleStringProperty();
	private final StringProperty complement = new SimpleStringProperty();

	/**
	 * Obtém uma lista observável de endereços do cliente.
	 * @param login O login do cliente.
	 * @return Uma lista observável de endereços do cliente.
	 */
	public ObservableList<ClientAddress> getAddressList(String login)
	{
		ObservableList<ClientAddress> allAddressStr = FXCollections.observableArrayList();

		GenericDao genericDAO = new GenericDao();
    	ClientAddressDao cAddressDao = new ClientAddressDao(genericDAO,
    			new Client(login));
    	
    	try
    	{
			List<ClientAddress> list = cAddressDao.list();
			for (ClientAddress address: list)
				allAddressStr.add(address);
    		return allAddressStr;
    	} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}


	/**
	 * Preenche os campos de endereço do cliente.
	 * @param addressCli O endereço do cliente (vem da lista de endereços).
	 */
	public void fillClientAddressFields(ClientAddress addressCli)
	{
		name.setValue(addressCli.getName());
		cep.setValue(addressCli.getCep());
		neighborhood.setValue(addressCli.getNeighborhood());
		street.setValue(addressCli.getStreet());
		number.setValue(Integer.toString(addressCli.getDoorNumber()));
		complement.setValue(addressCli.getComplement());
		String city = addressCli.getCity();
		String estate = addressCli.getEstate();
		cityEstate.setValue(city + " ("+ estate +")");
	}

	/**
	 * Preenche os campos de endereço da loja.
	 * @param login O login da loja.
	 */
	public void fillStoreAddressFields(String login)
	{
		GenericDao genericDAO = new GenericDao();
		AddressDao addrD = new AddressDao(genericDAO, new Store(login));

		try
		{
			Address newaddr = addrD.consult(new Address());
			cep.setValue(newaddr.getCep());
			street.setValue(newaddr.getStreet());
			neighborhood.setValue(newaddr.getNeighborhood());
			number.setValue(String.valueOf(newaddr.getDoorNumber()));

			String strComp = newaddr.getComplement();
			String strCity = newaddr.getCity();
			String strEstate = newaddr.getEstate();
			if (strComp == null)
				strComp = " ";
			complement.setValue(strComp);
			cityEstate.setValue(strCity + " (" + strEstate + ")");
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Edita o endereço da loja.
	 * @param login O login da loja.
	 * @throws SQLException Se ocorrer um erro ao editar o endereço.
	 */
	public void editStoreAddress(String login) throws SQLException
	{
		GenericDao genericDAO = new GenericDao();
		AddressDao addrD = new AddressDao(genericDAO, new Store(login));

		Address currentAddress = addrD.consult(new Address());
		Address address = new Address(currentAddress.getCep(), currentAddress.getStreet(), getComplementValue(),
				currentAddress.getNeighborhood(), currentAddress.getEstate(),
				currentAddress.getCity(), getNumberValue());
		addrD.update(address);
	}

	/**
	 * Edita o endereço do cliente.
	 * @param currentAddress O endereço atual do cliente.
	 * @param login O login do cliente.
	 * @return O novo endereço do cliente.
	 */
	public ClientAddress editClientAddress(ClientAddress currentAddress , String login)
	{
		/*
		Address address = new Address(currentAddress.getCep(), currentAddress.getStreet(), getComplementValue(),
				currentAddress.getNeighborhood(), currentAddress.getEstate(),
				currentAddress.getCity(), getNumberValue());
		 */

//		Address address = new Address(currentAddress.getCep(), currentAddress.getEstate(), currentAddress.getCity(),
//				currentAddress.getStreet(), currentAddress.getNeighborhood(), getNumberValue(), getComplementValue());

		Address address = new Address(currentAddress.getCep(), currentAddress.getStreet(), getComplementValue(),
				currentAddress.getNeighborhood(), currentAddress.getEstate(),
				currentAddress.getCity(), getNumberValue());

		ClientAddress newAddress = new ClientAddress(address, getNameValue());

		GenericDao genericDAO = new GenericDao();
		ClientAddressDao clientAddressDao = new ClientAddressDao(genericDAO, new Client(login));

		try
		{
			clientAddressDao.update(newAddress);
			return newAddress;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Exclui o endereço do cliente.
	 * @param address O endereço do cliente.
	 * @param login O login do cliente.
	 * @return true se o endereço foi excluído com sucesso, false caso contrário.
	 */
	public boolean deleteClientAddress(ClientAddress address, String login)
	{
		GenericDao genericDAO = new GenericDao();
		ClientAddressDao clientAddressDao = new ClientAddressDao(genericDAO, new Client(login));

		try
		{
			ClientAddress currentAddress = clientAddressDao.consult(address);
			clientAddressDao.delete(currentAddress);
			return true;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Exclui o endereço da loja.
	 * @param login O login da loja.
	 * @throws SQLException Se ocorrer um erro ao excluir o endereço.
	 */
	public void deleteStoreAddress(String login) throws SQLException
	{
		GenericDao genericDAO = new GenericDao();
		AddressDao addrD = new AddressDao(genericDAO, new Store(login));

		addrD.delete(new Address());
	}

	/**
	 * Cria e insere um novo endereço de cliente no banco de dados.
	 *
	 * @param login O nome de usuário associado ao cliente para o qual o endereço está sendo criado.
	 * @return O novo objeto ClientAddress criado e inserido no banco de dados.
	 * @throws SQLException Se ocorrer um erro ao acessar o banco de dados durante a inserção do novo endereço.
	 */
	public ClientAddress newClientAddress(String login) throws SQLException
	{
		GenericDao genericDAO = new GenericDao();
		ClientAddressDao addrD = new ClientAddressDao(genericDAO, new Client(login));

		String city = getCityEstateValue().substring(0, getCityEstateValue().indexOf('('));
		String estate = getCityEstateValue().substring(getCityEstateValue().indexOf('(')+1,
				getCityEstateValue().indexOf(')'));

		Address address = new Address(getCepText(), getStreetText(), getComplementValue(), getNeighborhoodText()
				, estate, city, getNumberValue());
		ClientAddress clientAddress = new ClientAddress(address, getNameValue());

		addrD.insert(clientAddress);
		return clientAddress;
	}

	/**
	 * Obtém a propriedade do StringProperty name.
	 * @return A propriedade do name.
	 */
	public StringProperty getNameProperty() { return name; }

	/**
	 * Obtém a propriedade do StringProperty CEP.
	 * @return A propriedade do CEP.
	 */
	public StringProperty getCepProperty() { return cep; }

	/**
	 * Obtém a propriedade do StringProperty CityEstate.
	 * @return A propriedade do CityEstate.
	 */
	public StringProperty getCityEstateProperty() { return cityEstate; }

	/**
	 * Obtém a propriedade do StringProperty Neighborhood.
	 * @return A propriedade do Neighborhood.
	 */
	public StringProperty getNeighborhoodProperty() { return neighborhood; }

	/**
	 * Obtém a propriedade do StringProperty street.
	 * @return A propriedade do street.
	 */
	public StringProperty getStreetProperty() { return street; }

	/**
	 * Obtém a propriedade do StringProperty number.
	 * @return A propriedade do number.
	 */
	public StringProperty getNumberProperty() { return number; }

	/**
	 * Obtém a propriedade do StringProperty complement.
	 * @return A propriedade do complement.
	 */
	public StringProperty getComplementProperty() { return complement; }

	// -----------------------------------------------

	/**
	 * Obtém o valor atual de propriedade do name.
	 * @return O valor do nome.
	 */
	public String getNameValue() { return name.getValue(); }
    public String getCepText() { return cep.getValue(); }
    public String getCityEstateValue() { return cityEstate.getValue(); }
    public String getNeighborhoodText() { return neighborhood.getValue(); }
    public String getStreetText() { return street.getValue(); }

	/**
	 * Obtém o valor atual de propriedade do number.
	 * @return O valor do número.
	 */
	public String getNumberValue() { return number.getValue(); }

	/**
	 * Obtém o valor atual da propriedade de complement.
	 * @return O valor do complemento.
	 */
	public String getComplementValue()
	{
		String result = complement.getValue();
		if (result == null || result.isEmpty())
			return "  ";
		return result;
	}
}
