package control;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Client;
import model.User;
import model.Store;
import persistence.ClientDao;
import persistence.GenericDao;
import persistence.StoreDao;
import persistence.UserDao;
import utils.UserSession;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import factory.ClientFactory;
import factory.StoreFactory;
import factory.UserFactory;

public class CtrlAccountMenu
{
	private StringProperty login = new SimpleStringProperty("");
	private StringProperty name = new SimpleStringProperty("");
	private StringProperty cpfCnpj = new SimpleStringProperty("");
	private StringProperty email = new SimpleStringProperty("");
	private StringProperty phone = new SimpleStringProperty("");
    private StringProperty dateBirth = new SimpleStringProperty("");
    private StringProperty sex = new SimpleStringProperty("");

    public CtrlAccountMenu()
    {
    }

    public void completeStoreFields(String login)
    {
    	GenericDao genericDAO = new GenericDao();
    	StoreDao storeDao = new StoreDao(genericDAO);
    	UserDao userDao = new UserDao(genericDAO);
    	try
    	{
    		User user = new User();
    		user.setLogin(login);
    		user = userDao.consult(user);
    		Store store = storeDao.consult(new Store(login));
    		store.setEmail(user.getEmail());
    		store.setTelephone(user.getTelephone());
    		setFields(store);
    	}catch (Exception e) {
    		e.printStackTrace();
		}
    }
    
    public void completeClientFields(String login)
    {
    	GenericDao genericDAO = new GenericDao();
    	ClientDao clientDao = new ClientDao(genericDAO);
    	UserDao userDao = new UserDao(genericDAO);
    	try
    	{
    		User user = new User();
    		user.setLogin(login);
    		user = userDao.consult(user);
    		Client client = clientDao.consult(new Client(login));
    		client.setEmail(user.getEmail());
    		client.setTelephone(user.getTelephone());
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
    		String tel = getPhoneValue();
    		System.out.println(tel);
    		Store currentStore = storeDao.consult(new Store(login));
    		User newUserData = userF.create(login, currentStore.getPassword(), currentStore.getPermission(), 
    				getEmailValue(), tel);
    		Store newStoreData = storeF.create(newUserData, getNameValue(), currentStore.getCnpj());
    		userDao.update(newUserData);
    		storeDao.update(newStoreData);
    		setFields(newStoreData);
    	} catch (SQLException e) {
			e.printStackTrace();
		}
    }

    public void deleteAccount(String login)
    {
    	String userType = UserSession.getUserType();
    	System.out.printf("Editando um \"%s\"\n", userType);
    	// TODO: função interditada por ser mt complexa
//    	if (userType.equals("client"))
//    		deleteClientAccount(login);
//    	else
//    		deleteStoreAccount(login);
    	
    	if (userType.equals("client"))
    		System.out.println("Apagando Cliente");
    	else
    		System.out.println("Apagando Cliente");
    	
    }
    
    private void deleteClientAccount(String login)
    {
    	// TODO: apagar TODOS os endereços de cliente + carrinho + pedidos
    	GenericDao genericDAO = new GenericDao();
    	UserDao userDao = new UserDao(genericDAO);
    	ClientDao clientDao = new ClientDao(genericDAO);

    	try
    	{
    		userDao.delete(new User(login));
    		clientDao.delete(new Client(login));
    	} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
    private void deleteStoreAccount(String login)
    {
    	GenericDao genericDAO = new GenericDao();
    	UserDao userDao = new UserDao(genericDAO);
    	StoreDao storeDao = new StoreDao(genericDAO);
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
