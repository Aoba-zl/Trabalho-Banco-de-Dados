package factory;

import model.User;

/**
 * Classe responsável pela criação de instâncias da classe User.
 */
public class UserFactory
{
	/**
	 * Cria um novo objeto User com base nas informações fornecidas.
	 *
	 * @param login      O login associado ao usuário.
	 * @param password   A senha do usuário.
	 * @param permission A permissão atribuída ao usuário.
	 * @param email      O endereço de e-mail do usuário.
	 * @param phone      O número de telefone do usuário.
	 * @return Um novo objeto User criado com os parâmetros fornecidos.
	 */
	public User create(String login, String password, String permission,
			String email, String phone)
	{
		User newUser = new User();
		newUser.setLogin(login);
		newUser.setPassword(password);
		newUser.setPermission(permission);
		newUser.setEmail(email);
		newUser.setTelephone(phone);
		
		return newUser;
	}
}
