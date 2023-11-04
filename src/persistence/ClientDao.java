package persistence;

public class ClientDao
{
	private GenericDao gDao;
	
	public ClientDao(GenericDao gDao)
	{
		this.gDao = gDao;
	}
	
	public boolean signUpStore() //irá cadastrar, retornará false caso dê algum erro no cadastro
	{
		return true;
	}

}
