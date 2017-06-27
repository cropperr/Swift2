
package DataAccessLayer.sql;

import DataAccessLayer.exception.DALException;
import DataAccessLayer.interfaces.DeleteDatabaseStorage;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlDeleteDatabaseStorage implements DeleteDatabaseStorage {

    private String dbmsConnString;
    private String userName;
    private String password;

    public MySqlDeleteDatabaseStorage(String dbmsConnString, String userName, String password) {
        this.dbmsConnString = dbmsConnString;
        this.userName = userName;
        this.password = password;
    }

    @Override
    public void deleteDatabase() throws DALException {
        try (Connection con = DriverManager.getConnection(dbmsConnString, userName, password);
                CallableStatement statement = con.prepareCall("{call delete_all_citizen_registration()}")) {

            statement.execute();
        } catch (SQLException ex) {
            throw new DALException("Error during database truncate!", ex);
        }
    }

}