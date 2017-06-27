
package DataAccessLayer.sql;

import DataAccessLayer.interfaces.AddressStorage;
import DataObjects.address.Address;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import DataAccessLayer.exception.DALException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MySqlAddressStorage implements AddressStorage {
    
    private String dbmsConnString;
    private String userName;
    private String password;
    
    public MySqlAddressStorage(String dbmsConnString, String userName, String password) {
        this.dbmsConnString = dbmsConnString;
        this.userName = userName;
        this.password = password;
    }
    
    @Override
    public void insertAddress(Address address, int personId) throws DALException {
        try (Connection con = DriverManager.getConnection(dbmsConnString, userName, password);
                CallableStatement statement = con.prepareCall("{call insert_address(?,?,?,?,?,?,?,?,?)}")) {
            
            statement.setString("country", address.getCountry());
            statement.setString("city", address.getCity());
            statement.setString("municipality", address.getMunicipality());
            statement.setString("postal_code", address.getPostalCode());
            statement.setString("street", address.getStreet());
            statement.setString("number", address.getNumber());
            if (address.getFloor() != null) {
                statement.setInt("floor", address.getFloor());
                statement.setInt("apartmentNo", address.getApartmentNo());
            } else {
                statement.setInt("floor", 0);
                statement.setInt("apartmentNo", 0);
            }
            statement.setInt("person_id", personId);
            statement.executeQuery();
        } catch (SQLException ex) {
            throw new DALException("Error during address import!", ex);
        }
    }
    
    @Override
    public Address getAddressByPersonId(int person_id) throws DALException {
        Address address = null;
        StringBuilder result = new StringBuilder();
        String sql = "select\n"
                + "distinct\n"
                + "addr.country,\n"
                + "addr.city,\n"
                + "addr.municipality,\n"
                + "addr.postal_code,\n"
                + "addr.street,\n"
                + "addr.number,\n"
                + "addr.floor,\n"
                + "addr.apartmentNo\n"
                + "from\n"
                + "people pe\n"
                + "join addresses addr on addr.person_id = pe.id\n"
                + "where\n"
                + "pe.id = ?\n";
        try (Connection conn = DriverManager.getConnection(dbmsConnString, userName, password);
                PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, person_id);
            
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    String country = rs.getString("country");
                    String city = rs.getString("city");
                    String municipality = rs.getString("municipality");
                    String zip = rs.getString("postal_code");
                    String street = rs.getString("street");
                    String number = rs.getString("number");
                    int floor = rs.getInt("floor");
                    int apartmentNo = rs.getInt("apartmentNo");
                    if (floor != 0 && apartmentNo != 0) {
                        address = new Address(country, city, municipality, zip, street, number, floor, apartmentNo);
                    } else {
                        address = new Address(country, city, municipality, zip, street, number);
                    }
                }
            }
        } catch (SQLException ex) {
            throw new DALException("Error in address surch!", ex);
        }
        return address;
    }

    public Address get(int personId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
