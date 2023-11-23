package persistence;

import model.Address;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Implementação da interface CrudDao para operações CRUD (Create, Read, Update, Delete) na tabela store_address
 * associados a uma loja no banco de dados.
 */
public class AddressDao implements CrudDao<Address>
{
    private GenericDao gDao;
    private User user;
    private String login;

    /**
     * Construtor da classe AddressDao.
     * @param gDao Objeto responsável pela comunicação com o banco de dados.
     * @param user Objeto que representa o usuário associado aos endereços.
     */
    public AddressDao(GenericDao gDao, User user)
    {
        this.gDao = gDao;
        this.user = user;
        this.login = this.user.getLogin();
    }

    @Override
    public void insert(Address address) throws SQLException
    {
        Connection connection = gDao.getConnection();
        String querySql = "INSERT INTO store_address VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = connection.prepareStatement(querySql);
        ps.setString(1, login);
        ps.setString(2, address.getCep());
        ps.setString(3, address.getEstate());
        ps.setString(4, address.getCity());
        ps.setString(5, address.getNeighborhood());
        ps.setString(6, address.getStreet());
        ps.setInt(7, address.getDoorNumber());
        ps.setString(8, address.getComplement());

        ps.execute();
        ps.close();
        connection.close();
    }

    @Override
    public void update(Address address) throws SQLException
    {
        Connection connection = gDao.getConnection();
        String querySql = "UPDATE store_address  SET state = ?, city = ?, neighborhood = ?, "
                + "street = ?, complement = ?"
                + "WHERE user_name = ? AND cep = ? AND number = ?";
        PreparedStatement ps = connection.prepareStatement(querySql);
        ps.setString(1, address.getEstate());
        ps.setString(2, address.getCity());
        ps.setString(3, address.getNeighborhood());
        ps.setString(4, address.getStreet());
        ps.setString(5, address.getComplement());
        ps.setString(6, login);
        ps.setString(7, address.getCep());
        ps.setInt   (8, address.getDoorNumber());

        ps.execute();
        ps.close();
        connection.close();
    }
    @Override
    public void delete(Address address) throws SQLException
    {
        Connection connection = gDao.getConnection();
        String querySql = "DELETE store_address WHERE user_name = ?";
        PreparedStatement ps = connection.prepareStatement(querySql);
        ps.setString(1, login);

        ps.execute();
        ps.close();
        connection.close();
    }
    @Override
    public Address consult(Address address) throws SQLException
    {
        Connection connection = gDao.getConnection();
        String querySql = "SELECT * FROM store_address WHERE user_name = ?";
        PreparedStatement ps = connection.prepareStatement(querySql);
        ps.setString(1, login);
        ResultSet result = ps.executeQuery();

        if (result.next())
        {
            address.setCep(result.getString("cep"));
            address.setComplement(result.getString("complement"));
            address.setDoorNumber(result.getInt("number"));
            address.setCity(result.getString("city"));
            address.setEstate(result.getString("state"));
            address.setNeighborhood(result.getString("neighborhood"));
            address.setStreet(result.getString("street"));
        }

        ps.execute();
        ps.close();
        connection.close();

        return address;
    }
    @Override
    public List<Address> list() throws SQLException
    {
        // Vai ficar sem list pq é nunca é usado
        return null;
    }
}
