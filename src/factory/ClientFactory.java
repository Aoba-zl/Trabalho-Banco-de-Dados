package factory;

import model.Client;
import model.User;

public class ClientFactory 
{
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
