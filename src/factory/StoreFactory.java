package factory;

import model.User;
import model.Store;

public class StoreFactory 
{
	public Store create (User user, String storeName, String cnpj)
	{
		Store newStore = new Store(user.getLogin());
		
		newStore.setNameStore(storeName);
		newStore.setCnpj(cnpj);
		
		return newStore;
	}
}
