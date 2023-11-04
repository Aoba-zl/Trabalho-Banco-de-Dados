package persistence;

import java.sql.SQLException;
import java.util.List;

import model.Store;

public class StoreDao
{
	private GenericDao gDao;
	
	public StoreDao(GenericDao gDao)
	{
		this.gDao = gDao;
	}
	
	public boolean signUpStore() //irá cadastrar, retornará false caso dê algum erro no cadastro
	{
		
		return true;
	}

}
