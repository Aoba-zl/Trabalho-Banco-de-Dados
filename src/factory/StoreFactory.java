package factory;

import model.User;
import model.Store;

/**
 * Classe responsável pela criação de instâncias da classe Store.
 */
public class StoreFactory 
{
	/**
	 * Cria um novo objeto Store com base nas informações fornecidas.
	 *
	 * @param user      O objeto User associado ao cliente.
	 * @param storeName O nome da Loja
	 * @param cnpj      O CNPJ da loja
	 * @return Uma nova instância da classe Store  criada com os parâmetros fornecidos.
	 */
	public Store create (User user, String storeName, String cnpj)
	{
		Store newStore = new Store(user.getLogin());
		
		newStore.setLogin(user.getLogin());
		newStore.setEmail(user.getEmail());
		newStore.setTelephone(user.getTelephone());
		newStore.setNameStore(storeName);
		newStore.setCnpj(cnpj);
		
		return newStore;
	}
}
