
package DataAccessLayer.interfaces;
import DataAccessLayer.exception.DALException;


public interface DeleteDatabaseStorage {

    public void deleteDatabase() throws DALException;

}