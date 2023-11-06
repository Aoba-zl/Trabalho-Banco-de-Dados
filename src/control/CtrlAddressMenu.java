package control;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import factory.UserFactory;
import model.Client;
import model.ClientAddress;
import persistence.ClientAddressDao;
import persistence.GenericDao;
import persistence.UserDao;

public class CtrlAddressMenu
{
	public List<String[]> getAddressList(String login)
	{
		List<String[]> allAddressStr;
		
		GenericDao genericDAO = new GenericDao();
    	UserDao userDao = new UserDao(genericDAO);
    	UserFactory userF = new UserFactory();
    	ClientAddressDao cAddressDao = new ClientAddressDao(genericDAO, 
    			new Client(login));
    	
    	// String[] => 'nome' 'cep' 'num' 'com'
    	
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
}
