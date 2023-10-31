package persistence;

import java.sql.SQLException;
import java.util.List;

public interface ICrud<T> //optei por não utilizar por motivos de que eu não sei porque de fato iria utilizar isso @_@
{
	public void insert (T t) throws SQLException;
	public void update (T t) throws SQLException;
	public void delete (T t) throws SQLException;
	public T consult (T t) throws SQLException;
	public List<T> list() throws SQLException;
	
}
