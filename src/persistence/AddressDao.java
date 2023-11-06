package persistence;

import model.Address;
import model.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class AddressDao implements CrudDao<Address>
{
    private GenericDao gDao;
    private Client client;
    private String login;

    public AddressDao(GenericDao gDao, Client client)
    {
        this.gDao = gDao;
        this.client = client;
        this.login = this.client.getLogin();
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
        String querySql = "UPDATE address SET state = ?, city = ?, neighborhood = ?, "
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
        //TODO: Continuar a implementação
        Connection connection = gDao.getConnection();
        String querySql = "";
        PreparedStatement ps = connection.prepareStatement(querySql);
//        ps.setString(1, login);

        ps.execute();
        ps.close();
        connection.close();
    }
    @Override
    public Address consult(Address address) throws SQLException
    {
        Connection connection = gDao.getConnection();
        String querySql = "";
        PreparedStatement ps = connection.prepareStatement(querySql);
//        ps.setString(1, login);

        ps.execute();
        ps.close();
        connection.close();

        return null;
    }
    @Override
    public List<Address> list() throws SQLException
    {

        return null;
    }
}
