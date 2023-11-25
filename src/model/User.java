package model;

/**
 * Classe Modelo que representa um User.
 */
public class User
{
	private String login;
    private String password;
    private String telephone;
    private String email;
    private String permission;

	/**
	 * Construtor que recebe o login como parâmetro.
	 *
	 * @param login O login do usuário.
	 */
    public User(String login)
    {
    	this.setLogin(login);
    }

	/**
	 * Construtor vazio para criar uma instância de User.
	 */
	public User() { super(); }

	/**
	 * Obtém o login do usuário.
	 *
	 * @return O login do usuário.
	 */
	public String getLogin()
	{
		return login;
	}

	/**
	 * Define o login do usuário.
	 *
	 * @param login O login do usuário.
	 */
	public void setLogin(String login)
{
		this.login = login;
	}

	/**
	 * Obtém a senha do usuário.
	 *
	 * @return A senha do usuário.
	 */
	public String getPassword()
{
		return password;
	}

	/**
	 * Define a senha do usuário.
	 *
	 * @param password A senha do usuário.
	 */
	public void setPassword(String password) 
	{
		this.password = password;
	}

	/**
	 * Obtém o telefone do usuário.
	 *
	 * @return O telefone do usuário.
	 */
	public String getTelephone() 
	{
		return telephone;
	}

	/**
	 * Define o telefone do usuário.
	 *
	 * @param telephone O telefone do usuário.
	 */
	public void setTelephone(String telephone)
	{
		this.telephone = telephone;
	}

	/**
	 * Obtém o e-mail do usuário.
	 *
	 * @return O e-mail do usuário.
	 */
	public String getEmail()
	{
		return email;
	}

	/**
	 * Define o e-mail do usuário.
	 *
	 * @param email O e-mail do usuário.
	 */
	public void setEmail(String email)
	{
		this.email = email;
	}

	/**
	 * Obtém a permissão do usuário.
	 *
	 * @return A permissão do usuário.
	 */
	public String getPermission() 
	{
		return permission;
	}

	/**
	 * Define a permissão do usuário.
	 *
	 * @param permission A permissão do usuário.
	 */
	public void setPermission(String permission) 
	{
		this.permission = permission;
	}

}
