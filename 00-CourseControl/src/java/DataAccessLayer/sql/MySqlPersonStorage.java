
package DataAccessLayer.sql;

import DataObjects.education.Education;
import DataAccessLayer.exception.DALException;
import DataObjects.insurance.SocialInsuranceRecord;
import DataAccessLayer.interfaces.*;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import DataObjects.personaldetails.*;

public class MySqlPersonStorage implements PersonStorage {

    private String dbmsConnString;
    private String userName;
    private String password;

    public MySqlPersonStorage(String dbmsConnString, String userName, String password) {
        this.dbmsConnString = dbmsConnString;
        this.userName = userName;
        this.password = password;
    }

    @Override
    public void insertPerson(Citizen person) throws DALException {
        AddressStorage addAddress = new MySqlAddressStorage(dbmsConnString, userName, password);
        EducationStorage addEducation = new MySqlEducationStorage(dbmsConnString, userName, password);
        SocialInsuranceRecordStorage addSocialInsurance = new MySqlSocialInsuranceRecordStorage(dbmsConnString, userName, password);
        DeleteDatabaseStorage deleteDatabase = new MySqlDeleteDatabaseStorage(dbmsConnString, userName, password);

        try (Connection con = DriverManager.getConnection(dbmsConnString, userName, password);
                CallableStatement statement = con.prepareCall("{call insert_person(?,?,?,?,?,?,?)}")) {

            statement.setString("first_name", person.getFirstName());
            statement.setString("middle_name", person.getMiddleName());
            statement.setString("last_name", person.getLastName());
            statement.setString("gender", person.getGender().toString());
            statement.setInt("height", person.getHeight());
            statement.setDate("birth_date", (Date.valueOf(person.getDateOfBirth())));
            statement.executeQuery();
            int personId = statement.getInt("person_id");
            addAddress.insertAddress(person.getAddress(), personId);
            addEducation.insertEducation(person.getEducations(), personId);
            addSocialInsurance.insertSocialInsurance(person.getSocialInsuranceRecords(), personId);

        } catch (SQLException ex) {
            throw new DALException("Error during person import!", ex);
        }
    }

    @Override
    public Citizen getPresonById(int person_id) throws DALException {
        Citizen person = null;
        StringBuilder result = new StringBuilder();
        String sql = "select\n"
                + "distinct\n"
                + "pe.first_name,\n"
                + "pe.middle_name,\n"
                + "pe.last_name,\n"
                + "pe.gender,\n"
                + "pe.height,\n"
                + "pe.birth_date\n"
                + "from\n"
                + "people pe\n"
                + "where\n"
                + "pe.id = ?\n";
        try (Connection conn = DriverManager.getConnection(dbmsConnString, userName, password);
                PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, person_id);

            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    String firstName = rs.getString("pe.first_name");
                    String middleName = rs.getString("pe.middle_name");
                    String lastName = rs.getString("pe.last_name");
                    String gender = rs.getString("pe.gender");
                    int height = rs.getInt("pe.height");
                    LocalDate dateOfBirth = rs.getDate("pe.birth_date").toLocalDate();
                    if (gender.equals("Female")) {
                        person = new Citizen(firstName, middleName, lastName, Gender.Female, height, dateOfBirth);
                    } else {
                        person = new Citizen(firstName, middleName, lastName, Gender.Male, height, dateOfBirth);
                    }
                    AddressStorage address = new MySqlAddressStorage(dbmsConnString, userName, password);
                    EducationStorage educations = new MySqlEducationStorage(dbmsConnString, userName, password);
                    SocialInsuranceRecordStorage socialInsurances = new MySqlSocialInsuranceRecordStorage(dbmsConnString, userName, password);
                    person.setAddress(address.getAddressByPersonId(person_id));
                    for (Education education : educations.getEducationsByPersonID(person_id)) {
                        person.addEducation(education);
                    }
                    for (SocialInsuranceRecord socialInsurance : socialInsurances.getSocialInsuranceByPersonId(person_id)) {
                        person.addSocialInsuranceRecord(socialInsurance);
                    }
                }
            }
        } catch (SQLException ex) {
            throw new DALException("Error in person surch!", ex);
        }
        return person;
    }

    public Citizen get(int personId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
