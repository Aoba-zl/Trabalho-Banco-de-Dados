package model;

import java.util.Objects;

public class User
{
	private String login;
    private String password;
    //TODO: Falta definir isso ainda, pq pode começar com 0 e ser str, ou não poder começar com 0 e ser int
    private String telephone;
    private String email;
    private String permission;

    public User(String login)
    {
    	this.setLogin(login);
    }
    
    public User()
    {
    }
    
    public boolean equals(User user)
	{
        boolean validacaoLogin = Objects.equals(this.login, user.login);
        boolean validacaoSenha = Objects.equals(this.password, user.password);
        return (validacaoLogin && validacaoSenha);
    }

	public String getLogin()
	{
		return login;
	}

	public void setLogin(String login)
{
		this.login = login;
	}

	public String getPassword()
{
		return password;
	}

	public void setPassword(String password) 
	{
		this.password = password;
	}

	public String getTelephone() 
	{
		return telephone;
	}

	public void setTelephone(String telephone)
	{
		this.telephone = telephone;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getPermission() 
	{
		return permission;
	}

	public void setPermission(String permission) 
	{
		this.permission = permission;
	}
    
    
}
