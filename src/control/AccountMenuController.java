package control;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import model.Client;
import model.ClientAddress;
import model.User;
import model.Store;
import persistence.ClientDao;
import persistence.GenericDao;
import persistence.StoreDao;
import persistence.UserDao;
import utils.UserSession;

import java.sql.SQLException;

import factory.ClientFactory;
import factory.StoreFactory;
import factory.UserFactory;

public class AccountMenuController
{
	private final StringProperty login = new SimpleStringProperty("");
	private final StringProperty name = new SimpleStringProperty("");
	private final StringProperty cpfCnpj = new SimpleStringProperty("");
	private final StringProperty email = new SimpleStringProperty("");
	private final StringProperty phone = new SimpleStringProperty("");
    private final StringProperty dateBirth = new SimpleStringProperty("");
    private final StringProperty sex = new SimpleStringProperty("");

    public void completeStoreFields(String login)
    {
    	GenericDao genericDAO = new GenericDao();
    	StoreDao storeDao = new StoreDao(genericDAO);
    	try
    	{
    		Store store = storeDao.consult(new Store(login));
    		setFields(store);
    	}catch (Exception e) {
    		e.printStackTrace();
		}
    }
    
    public void completeClientFields(String login)
    {
    	GenericDao genericDAO = new GenericDao();
    	ClientDao clientDao = new ClientDao(genericDAO);
    	try
    	{
    		Client client = clientDao.consult(new Client(login));
    		setFields(client);
    	}catch (Exception e) {
    		e.printStackTrace();
		}
    }

    public void editAccount(String login)
    {
    	String userType = UserSession.getUserType();
    	if (userType.equals("client"))
    		editClientAccount(login);
    	else
    		editStoreAccount(login);
    }
    
    private void editClientAccount(String login)
    {
    	GenericDao genericDAO = new GenericDao();
    	UserDao userDao = new UserDao(genericDAO);
    	ClientDao clientDao = new ClientDao(genericDAO);
    	ClientFactory clientF = new ClientFactory();
    	UserFactory userF = new UserFactory();
    	
    	try
    	{
    		Client currentClient = clientDao.consult(new Client(login));
    		User newUserData = userF.create(login, currentClient.getPassword(), currentClient.getPermission(), 
    				getEmailValue(), getPhoneValue());
    		Client newClientData = clientF.create(newUserData, getNameValue(), currentClient.getCpf(), 
    				currentClient.getDateBirth(), getSexValue());
    		userDao.update(newUserData);
    		clientDao.update(newClientData);
    		setFields(newClientData);
    	} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
    private void editStoreAccount(String login)
    {
    	GenericDao genericDAO = new GenericDao();
    	UserDao userDao = new UserDao(genericDAO);
    	StoreDao storeDao = new StoreDao(genericDAO);
    	UserFactory userF = new UserFactory();
    	StoreFactory storeF = new StoreFactory();
    	
    	try
    	{
    		Store currentStore = storeDao.consult(new Store(login));
    		User newUserData = userF.create(login, currentStore.getPassword(), currentStore.getPermission(), 
    				getEmailValue(), getPhoneValue());
    		Store newStoreData = storeF.create(newUserData, getNameValue(), currentStore.getCnpj());
    		userDao.update(newUserData);
    		storeDao.update(newStoreData);
    		setFields(newStoreData);
    	} catch (SQLException e) {
			e.printStackTrace();
		}
    }

    public void deleteAccount(String login) throws SQLException
    {
    	String userType = UserSession.getUserType();
    	if (userType.equals("client"))
    		deleteClientAccount(login);
    	else
    		deleteStoreAccount(login);

    }
    
    private void deleteClientAccount(String login)
    {
    	// TODO: apagar carrinho + pedidos
		AddressMenuController controller = new AddressMenuController();
    	GenericDao genericDAO = new GenericDao();
    	UserDao userDao = new UserDao(genericDAO);
    	ClientDao clientDao = new ClientDao(genericDAO);

    	try
    	{
			ObservableList<ClientAddress> allAddress = controller.getAddressList(login);
			for (ClientAddress addr : allAddress)
				controller.deleteClientAddress(addr, login);
    		clientDao.delete(new Client(login));
			userDao.delete(new User(login));
    	} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
    private void deleteStoreAccount(String login) throws SQLException
    {
		AddressMenuController addressMenuController = new AddressMenuController();
    	GenericDao genericDAO = new GenericDao();
    	UserDao userDao = new UserDao(genericDAO);
    	StoreDao storeDao = new StoreDao(genericDAO);

		addressMenuController.deleteStoreAddress(login);
		storeDao.delete(new Store(login));
		userDao.delete(new User(login));

    }
    
    private void setFields(User user)
    {
    	String userType = UserSession.getUserType();
    	
    	login.setValue(user.getLogin());
    	email.setValue(user.getEmail());
    	phone.setValue(user.getTelephone());
    	
    	if (userType.equals("client"))
    		setClientFields((Client) user);
    	else
    		setStoreFields((Store) user);
    		
    }
    private void setStoreFields(Store store)
    {
    	name.setValue(store.getNameStore());
    	cpfCnpj.setValue(store.getCnpj());
    }
    
    private void setClientFields(Client client)
    {
    	dateBirth.setValue(client.getDateBirth());
    	name.setValue(client.getSocialName());
    	cpfCnpj.setValue(client.getCpf());
    	sex.setValue(client.getSex());
    }
    
    public StringProperty getLoginProperty()
    {
    	return login;
    }
    public StringProperty getNameProperty()
    {
    	return name;
    }
    public StringProperty getCpfCnpjProperty()
    {
    	return cpfCnpj;
    }
    public StringProperty getEmailProperty()
    {
    	return email;
    }
    public StringProperty getPhoneProperty()
    {
    	return phone;
    }
    public StringProperty getDateBirthProperty()
    {
    	return dateBirth;
    }
    public StringProperty getSexProperty()
    {
    	return sex;
    }

    public String getLoginValue()
    {
    	return login.getValue();
    }
    
    public String getNameValue()
    {
    	return name.getValue();
    }
    
    public String getCpfCnpjValue()
    {
    	return cpfCnpj.getValue();
    }
    public String getEmailValue()
    {
    	return email.getValue();
    }
    public String getPhoneValue()
    {
    	return phone.getValue();
    }
    public String getDateBirthValue()
    {
    	return dateBirth.getValue();
    }
    public String getSexValue()
    {
    	return sex.getValue();
    }
}
