package factory;

import model.Client;
import model.User;

/**
 * Classe responsável pela criação de instâncias da classe Client.
 */
public class ClientFactory 
{
	/**
	 * Cria um novo objeto Client com base nas informações fornecidas.
	 *
	 * @param user     O objeto User associado ao cliente.
	 * @param name     O nome social do cliente.
	 * @param cpf      O CPF do cliente.
	 * @param dateBirth A data de nascimento do cliente.
	 * @param sex      O sexo do cliente.
	 * @return Uma nova instância da classe Client criada com os parâmetros fornecidos.
	 */
	public Client create(User user, String name, 
			String cpf, String dateBirth, String sex)
	{
		Client newClient =  new Client(user.getLogin());
		newClient.setSocialName(name);
		newClient.setCpf(cpf);
		newClient.setEmail(user.getEmail());
		newClient.setTelephone(user.getTelephone());
		newClient.setDateBirth(dateBirth);
		newClient.setSex(sex);
		return newClient;
	}
}
