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
import utils.UserSession;

public class AddressMenuController
{
	private final StringProperty name = new SimpleStringProperty();
	private final StringProperty cep = new SimpleStringProperty();
	private final StringProperty cityEstate = new SimpleStringProperty();
	private final StringProperty neighborhood = new SimpleStringProperty();
	private final StringProperty street = new SimpleStringProperty();
	private final StringProperty number = new SimpleStringProperty();
	private final StringProperty complement = new SimpleStringProperty();


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

	public void editStoreAddress(String login) throws SQLException
	{
		GenericDao genericDAO = new GenericDao();
		AddressDao addrD = new AddressDao(genericDAO, new Store(login));

		Address currentAddress = addrD.consult(new Address());
		Address address = new Address(currentAddress.getCep(), currentAddress.getEstate(), currentAddress.getCity(),
				currentAddress.getStreet(), currentAddress.getNeighborhood(), getNumberText(), getComplementText());
		addrD.update(address);
	}

	public ClientAddress editClientAddress(ClientAddress currentAddress , String login)
	{
		Address address = new Address(currentAddress.getCep(), currentAddress.getEstate(), currentAddress.getCity(),
				currentAddress.getStreet(), currentAddress.getNeighborhood(), getNumberText(), getComplementText());
		ClientAddress newAddress = new ClientAddress(address, getNameText());

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

	public boolean deleteAddress(ClientAddress address, String login)
	{
		String userType = UserSession.getUserType();
		if (userType.equals("client"))
			return deleteClientAddress(address, login);
		else
			return deleteStoreAddress(address, login);
	}

	private boolean deleteClientAddress(ClientAddress address, String login)
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

	private boolean deleteStoreAddress(ClientAddress address, String login)
	{
		return false;
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
