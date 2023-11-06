package control;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import factory.AddressFactory;
import factory.ClientAddressFactory;
import factory.UserFactory;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Address;
import model.Client;
import model.ClientAddress;
import persistence.AddressDao;
import persistence.ClientAddressDao;
import persistence.GenericDao;
import persistence.UserDao;
import utils.UserSession;

public class CtrlAddressMenu
{
	private final StringProperty name = new SimpleStringProperty();
	private final StringProperty cep = new SimpleStringProperty();
	private final StringProperty cityEstate = new SimpleStringProperty();
	private final StringProperty neighborhood = new SimpleStringProperty();
	private final StringProperty street = new SimpleStringProperty();
	private final StringProperty number = new SimpleStringProperty();
	private final StringProperty complement = new SimpleStringProperty();


	public List<String[]> getAddressList(String login)
	{
		List<String[]> allAddressStr;
		
		GenericDao genericDAO = new GenericDao();
    	ClientAddressDao cAddressDao = new ClientAddressDao(genericDAO,
    			new Client(login));
    	
    	try
    	{
    		List<ClientAddress> allAddress = cAddressDao.list();
    		allAddressStr = convertAddress(allAddress);
    		return allAddressStr;
    	} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	private List<String[]> convertAddress(List<ClientAddress> allAddress)
	{
		List<String[]> allAddressStr = new ArrayList<>();
		
		for (ClientAddress address : allAddress)
		{
			String[] addressStr = address.toString().split(";");
			allAddressStr.add(addressStr);
		}
		return allAddressStr;
	}

	public void fillFields(String[] address, String login)
	{
		ClientAddress addressCli = getAddress(address, login);
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

	public void editAddress(String[] strAddress, String login)
	{
		String userType = UserSession.getUserType();
		if (userType.equals("client"))
			editClientAddress(strAddress, login);
    	else
			editStoreAddress(strAddress, login);
	}

	private void editStoreAddress(String[] strAddress, String login)
	{
		//TODO: Continuar a implementação
		GenericDao genericDAO = new GenericDao();
		AddressDao addressDao = new AddressDao(genericDAO, new Client(login));
		AddressFactory addressF = new AddressFactory();
	}

	private void editClientAddress(String[] strAddress, String login)
	{
		AddressFactory addressF = new AddressFactory();
		ClientAddressFactory clientAddressF = new ClientAddressFactory();

		ClientAddress currentAddress = getAddress(strAddress, login);
		Address address = addressF.creat(currentAddress.getCep(), currentAddress.getEstate(), currentAddress.getCity(),
				currentAddress.getStreet(), currentAddress.getNeighborhood(), getNumberText(), getComplementText());
		ClientAddress newAddress = clientAddressF.creat(address, getNameText());

		GenericDao genericDAO = new GenericDao();
		ClientAddressDao clientAddressDao = new ClientAddressDao(genericDAO, new Client(login));

		try
		{
			clientAddressDao.update(newAddress);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	public void deleteAddress(String[] strAddress, String login)
	{
		String userType = UserSession.getUserType();
		if (userType.equals("client"))
			deleteClientAddress(strAddress, login);
		else
			deleteStoreAddress(strAddress, login);
	}

	public void deleteClientAddress(String[] strAddress, String login)
	{
		GenericDao genericDAO = new GenericDao();
		ClientAddressDao clientAddressDao = new ClientAddressDao(genericDAO, new Client(login));

		try
		{
			ClientAddress currentAddress = getAddress(strAddress, login);
			clientAddressDao.delete(currentAddress);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	public void deleteStoreAddress(String[] strAddress, String login)
	{
	}

	private ClientAddress getAddress(String[] address, String login)
	{
		GenericDao genericDAO = new GenericDao();
		ClientAddressDao cAddressDao = new ClientAddressDao(genericDAO,
				new Client(login));
		ClientAddress addressCli = null;
		try
		{
			addressCli = new ClientAddress();
			addressCli.setCep(address[1]);
			addressCli.setDoorNumber(Integer.parseInt(address[2]));
			addressCli = cAddressDao.consult(addressCli);

		}catch (SQLException e)
		{
			e.printStackTrace();
		}

		return addressCli;
	}

	public StringProperty getNameProperty() { return name; }
	public StringProperty getCepProperty() { return cep; }
	public StringProperty getCityEstateProperty() { return cityEstate; }
	public StringProperty getNeighborhoodProperty() { return neighborhood; }
	public StringProperty getStreetProperty() { return street; }
	public StringProperty getNumberProperty() { return number; }
	public StringProperty getComplementProperty() { return complement; }

	public String getNameText() { return name.getValue(); }
	public String getCepText() { return cep.getValue(); }
	public String getCityEstateText() { return cityEstate.getValue(); }
	public String getNeighborhoodText() { return neighborhood.getValue(); }
	public String getStreetText() { return street.getValue(); }
	public String getNumberText() { return number.getValue(); }
	public String getComplementText()
	{
		String result = complement.getValue();
		if (result == null || result.isEmpty())
			return "";
		return result;
	}
}
