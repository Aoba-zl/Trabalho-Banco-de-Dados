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

    public boolean equals(User user)
	{
        boolean validacaoLogin = Objects.equals(this.login, user.login);
        boolean validacaoSenha = Objects.equals(this.password, user.password);
        return (validacaoLogin && validacaoSenha);
    }
}
