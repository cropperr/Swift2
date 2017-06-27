
package DataAccessLayer.sql;

import DataAccessLayer.exception.DALException;
import DataAccessLayer.interfaces.SocialInsuranceRecordStorage;
import DataObjects.insurance.SocialInsuranceRecord;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public  class MySqlSocialInsuranceRecordStorage implements SocialInsuranceRecordStorage {

    private String dbmsConnString;
    private String userName;
    private String password;

    public MySqlSocialInsuranceRecordStorage(String dbmsConnString, String userName, String password) {
        this.dbmsConnString = dbmsConnString;
        this.userName = userName;
        this.password = password;
    }

    
    @Override
    public void insertSocialInsurance(List<SocialInsuranceRecord> socialInsurances, int personId) throws DALException {
        try (Connection con = DriverManager.getConnection(dbmsConnString, userName, password);
                CallableStatement statement = con.prepareCall("{call insert_social_insurance(?,?,?,?)}")) {
            for (SocialInsuranceRecord socialInsurance : socialInsurances) {

                statement.setInt("year", socialInsurance.getYear());
                statement.setInt("month", socialInsurance.getMonth());
                statement.setDouble("amount", socialInsurance.getAmount());
                statement.setInt("person_id", personId);

                statement.execute();
            }
        } catch (SQLException ex) {
            throw new DALException("Error during social insurance import!", ex);
        }
    }

    @Override
    public List<SocialInsuranceRecord> getSocialInsuranceByPersonId(int personId) throws DALException {
        List<SocialInsuranceRecord> socialInsurances = new ArrayList<>();
        StringBuilder result = new StringBuilder();
        String sql = "select\n"
                + "distinct\n"
                + "si.year,\n"
                + "si.month,\n"
                + "si.amount\n"
                + "from\n"
                + "people pe\n"
                + "join social_insurance si on si.person_id = pe.id\n"
                + "where\n"
                + "pe.id = ?\n"
                + "order by\n"
                + "si.year desc,\n"
                + "si.month desc";
        try (Connection conn = DriverManager.getConnection(dbmsConnString, userName, password);
                PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, personId);

            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    int year = rs.getInt("year");
                    int month = rs.getInt("month");
                    double amount = rs.getDouble("amount");

                    socialInsurances.add(new SocialInsuranceRecord(year, month, amount));
                }
            }
        } catch (SQLException ex) {
            throw new DALException("Error in social insurance surch!", ex);
        }
        return socialInsurances;
    }

    @Override
    public void insertSocialInsuranceFromWebPage(SocialInsuranceRecord socialInsurance, int person_id) throws DALException {

        String sql = "INSERT INTO citizen_registrations.social_insurance (`year`, `month`, `amount`, `person_id`)\n"
                + "VALUES (?,?,?,?);";
        try (Connection conn = DriverManager.getConnection(dbmsConnString, userName, password);
                PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setInt(1, socialInsurance.getYear());
            statement.setInt(2, socialInsurance.getMonth());
            statement.setDouble(3, socialInsurance.getAmount());
            statement.setInt(4, person_id);

            statement.execute();
        } catch (SQLException ex) {
            throw new DALException("Error during social insert!", ex);
        }

    }

    public List<SocialInsuranceRecord> get(int personId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

}