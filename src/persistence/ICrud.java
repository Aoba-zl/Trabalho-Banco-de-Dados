package persistence;

import java.sql.SQLException;
import java.util.List;

public interface ICrud<T> //optei por não utilizar por motivos de que eu não sei porque de fato iria utilizar isso @_@
{
	public int insert (T t) throws SQLException;
	public int update (T t) throws SQLException;
	public boolean delete (T t) throws SQLException;
	public T consult (T t) throws SQLException;
	public List<T> list() throws SQLException;
	
}
