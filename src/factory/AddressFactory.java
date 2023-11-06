package factory;

import model.Address;

public class AddressFactory
{
    public Address creat(String cep, String estate, String city, String street,
                          String neighborhood, String doorNmbr, String complement)
    {
        Address address = new Address();
        address.setCep(cep);
        address.setEstate(estate);
        address.setCity(city);
        address.setStreet(street);
        address.setNeighborhood(neighborhood);
        address.setDoorNumber(Integer.parseInt(doorNmbr));
        address.setComplement(complement);

        return address;
    }
}
