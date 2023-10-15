package model;

import java.util.Objects;

public class Usuario
{
	private String login;
    private String senha;
    //TODO: Falta definir isso ainda, pq pode começar com 0 e ser str, ou não poder começar com 0 e ser int
    private String telefone;
    private String email;
    private String permissao;

    public boolean equals(Usuario user)
	{
        boolean validacaoLogin = Objects.equals(this.login, user.login);
        boolean validacaoSenha = Objects.equals(this.senha, user.senha);
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

    public String getSenha()
	{
        return senha;
    }

    public void setSenha(String senha)
	{
        this.senha = senha;
    }

    public String getTelefone()
	{
        return telefone;
    }

    public void setTelefone(String telefone)
	{
        this.telefone = telefone;
    }

    public String getEmail()
	{
        return email;
    }

    public void setEmail(String email)
	{
        this.email = email;
    }

    public String getPermissao()
	{
        return permissao;
    }

    public void setPermissao(String permissao)
	{
        this.permissao = permissao;
    }
}
