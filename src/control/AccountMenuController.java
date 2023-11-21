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


/**
 * Controlador responsável pelas operações da conta do usuário.
 */
public class AccountMenuController
{
	private final StringProperty login = new SimpleStringProperty("");
	private final StringProperty name = new SimpleStringProperty("");
	private final StringProperty cpfCnpj = new SimpleStringProperty("");
	private final StringProperty email = new SimpleStringProperty("");
	private final StringProperty phone = new SimpleStringProperty("");
    private final StringProperty dateBirth = new SimpleStringProperty("");
    private final StringProperty sex = new SimpleStringProperty("");

	/**
	 * Preenche os campos de dados de uma loja com base no login.
	 * @param login O login da loja.
	 */
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

	/**
	 * Preenche os campos de dados de um cliente com base no login.
	 * @param login O login do cliente.
	 */
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

	/**
	 * Edita a conta do usuário com base no tipo de usuário.
	 * @param login O login do usuário.
	 */
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

	/**
	 * Exclui a conta do usuário com base no tipo de usuário.
	 * @param login O login do usuário.
	 * @throws SQLException Se ocorrer um erro ao excluir a conta.
	 */
    public void deleteAccount(String login) throws SQLException
    {
    	String userType = UserSession.getUserType();
    	if (userType.equals("client"))
    		deleteClientAccount(login);
    	else
    		deleteStoreAccount(login);

    }
    
    private void deleteClientAccount(String login) throws SQLException
    {
    	// TODO: apagar carrinho + pedidos
		AddressMenuController controller = new AddressMenuController();
    	GenericDao genericDAO = new GenericDao();
    	UserDao userDao = new UserDao(genericDAO);
    	ClientDao clientDao = new ClientDao(genericDAO);

		ObservableList<ClientAddress> allAddress = controller.getAddressList(login);
		for (ClientAddress addr : allAddress)
			controller.deleteClientAddress(addr, login);
    	clientDao.delete(new Client(login));
		userDao.delete(new User(login));
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

	/**
	 * Obtém a propriedade de StringProperty login.
	 * @return A propriedade de login.
	 */
    public StringProperty getLoginProperty() {return login;}

	/**
	 * Obtém a propriedade de StringProperty name.
	 * @return A propriedade de name.
	 */
    public StringProperty getNameProperty(){return name;}

	/**
	 * Obtém a propriedade de StringProperty CpfCnpj.
	 * @return A propriedade de CpfCnpj.
	 */
	public StringProperty getCpfCnpjProperty(){return cpfCnpj;}

	/**
	 * Obtém a propriedade de StringProperty e-mail.
	 * @return A propriedade de e-mail.
	 */
	public StringProperty getEmailProperty(){return email;}

	/**
	 * Obtém a propriedade de StringProperty phone.
	 * @return A propriedade de phone.
	 */
	public StringProperty getPhoneProperty(){return phone;}

	/**
	 * Obtém a propriedade de StringProperty DateBirth.
	 * @return A propriedade de DateBirth.
	 */
	public StringProperty getDateBirthProperty(){return dateBirth;}

	/**
	 * Obtém a propriedade de StringProperty sex.
	 * @return A propriedade de sex.
	 */
	public StringProperty getSexProperty(){return sex;}

	/**
	 * Obtém o valor atual da propriedade name.
	 * @return O valor do name.
	 */
    public String getNameValue(){return name.getValue();}

	/**
	 * Obtém o valor atual da propriedade e-mail.
	 * @return O valor do e-mail.
	 */
	public String getEmailValue(){return email.getValue();}

	/**
	 * Obtém o valor atual da propriedade phone.
	 * @return O valor do phone.
	 */
	public String getPhoneValue(){return phone.getValue();}

	/**
	 * Obtém o valor atual da propriedade sex.
	 * @return O valor do sex.
	 */
	public String getSexValue(){return sex.getValue();}
}
