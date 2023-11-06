package persistence;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GenericDao {
    private Connection c;

    public Connection getConnection() throws SQLException
    {
        String hostName = "localhost";
        String dbName = "marketplace";
        String user = "usuarioDB";
        String password = "123456";


        c = DriverManager.getConnection(String.format("jdbc:jtds:sqlserver://%s:1433;databaseName=%s;user=%s;password=%s",
                    hostName, dbName, user, password));


        return c;
    }


}