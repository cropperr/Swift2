
package DataAccessLayer.interfaces;

import DataObjects.address.Address;
import DataAccessLayer.exception.DALException;

public interface AddressStorage {

    public void insertAddress(Address address, int personId) throws DALException;

    public Address getAddressByPersonId(int person_id) throws DALException;

}