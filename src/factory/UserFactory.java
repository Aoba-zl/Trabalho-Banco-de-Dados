package factory;

import model.User;

public class UserFactory
{
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
