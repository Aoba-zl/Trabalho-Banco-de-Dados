package persistence;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GenericDao {
    private static final String URL= "jdbc:sqlserver://localhost\\DESKTOP-GFU36L4:1433;databaseName=marketplace;instance=MSSQLSERVER;encrypt=false;trustServerCertificate=true;";
    private static final String user= "Luan";
    private static final String password= "123456";

    Connection connection= null;
    public Connection getConnection() throws ClassNotFoundException {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection= DriverManager.getConnection(URL, user, password);
            System.out.println("Banco de Dados conectado com sucesso!");
            connection.close();
        } catch (SQLException e){
            System.out.println("Falha ao conectar com o Banco de Dados \n" + e.getMessage());
        }
        return connection;
    }
}
