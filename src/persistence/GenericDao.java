package persistence;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe responsável por fornecer uma conexão com o banco de dados para operações genéricas.
 */
public class GenericDao {
    private Connection c;

    /**
     * Obtém uma conexão com o banco de dados.
     *
     * @return Objeto Connection representando a conexão com o banco de dados.
     * @throws SQLException Exceção lançada em caso de falha na obtenção da conexão.
     */
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