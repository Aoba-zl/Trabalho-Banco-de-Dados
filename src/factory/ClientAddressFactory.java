package factory;

import model.Address;
import model.ClientAddress;

public class ClientAddressFactory
{
    public ClientAddress creat(Address address, String name)
    {
        ClientAddress clientAddress = new ClientAddress();

        clientAddress.setCep(address.getCep());
        clientAddress.setEstate(address.getEstate());
        clientAddress.setCity(address.getCity());
        clientAddress.setStreet(address.getStreet());
        clientAddress.setNeighborhood(address.getNeighborhood());
        clientAddress.setDoorNumber(address.getDoorNumber());
        clientAddress.setComplement(address.getComplement());
        clientAddress.setName(name);

        return clientAddress;
    }
}
