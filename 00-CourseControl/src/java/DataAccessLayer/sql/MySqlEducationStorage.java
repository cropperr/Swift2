
package DataAccessLayer.sql;

import DataAccessLayer.interfaces.EducationStorage;
import DataObjects.education.*;
import DataAccessLayer.exception.DALException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MySqlEducationStorage implements EducationStorage {

    private String dbmsConnString;
    private String userName;
    private String password;

    public MySqlEducationStorage(String dbmsConnString, String userName, String password) {
        this.dbmsConnString = dbmsConnString;
        this.userName = userName;
        this.password = password;
    }

    @Override
    public void insertEducation(List<Education> educations, int personId) throws DALException {
        try (Connection con = DriverManager.getConnection(dbmsConnString, userName, password);
                CallableStatement statement = con.prepareCall("{call insert_education(?,?,?,?,?,?,?)}")) {
            for (Education education : educations) {
                statement.setString("type", education.getDegree().toString());
                statement.setString("institution_name", education.getInstitutionName());
                statement.setDate("enrollment_date", (Date.valueOf(education.getEnrollmentDate())));
                statement.setDate("graduation_date", (Date.valueOf(education.getGraduationDate())));
                statement.setBoolean("graduated", education.isGraduated());

                if (education instanceof GradedEducation && education.getGraduationDate().isBefore(LocalDate.now())) {
                    statement.setDouble("final_grade", ((GradedEducation) education).getFinalGrade());
                } else {
                    statement.setDouble("final_grade", 0);
                }
                statement.setInt("person_id", personId);
                statement.execute();
            }
        } catch (SQLException ex) {
            throw new DALException("Error during educations import!", ex);
        }
    }

    @Override
    public List<Education> getEducationsByPersonID(int personId) throws DALException {
        List<Education> educations = new ArrayList<>();
        StringBuilder result = new StringBuilder();
        String sql = "select\n"
                + "distinct\n"
                + "ed.type,\n"
                + "ed.institution_name,\n"
                + "ed.enrollment_date,\n"
                + "ed.graduation_date,\n"
                + "ed.graduated,\n"
                + "ed.final_grade\n"
                + "from\n"
                + "people pe\n"
                + "join educations ed on ed.person_id = pe.id\n"
                + "where\n"
                + "pe.id = ?\n"
                + "order by\n"
                + "ed.graduation_date asc";
        try (Connection conn = DriverManager.getConnection(dbmsConnString, userName, password);
                PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, personId);

            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    String degree = rs.getString("type");
                    String institution = rs.getString("institution_name");
                    LocalDate enrollmentDate = rs.getDate("enrollment_date").toLocalDate();
                    LocalDate graduationDate = rs.getDate("graduation_date").toLocalDate();
                    Boolean graduated = rs.getBoolean("graduated");
                    Float finalGrade = rs.getFloat("final_grade");

                    switch (degree) {
                        case "Primary":
                            PrimaryEducation pEducation = new PrimaryEducation(institution, enrollmentDate, graduationDate);
                            educations.add(pEducation);
                            break;
                        case "Secondary":
                            SecondaryEducation sEducation = new SecondaryEducation(institution, enrollmentDate, graduationDate);
                            if (graduationDate.isBefore(LocalDate.now())) {
                                ((GradedEducation) sEducation).gotGraduated(finalGrade);
                            }
                            educations.add(sEducation);
                            break;
                        case "Bachelor":
                        case "Master":
                        case "Doctorate":
                            EducationDegree educationDegree = null;
                            if (degree.equals("Bachelor")) {
                                educationDegree = EducationDegree.Bachelor;
                            } else if (degree.equals("Master")) {
                                educationDegree = EducationDegree.Master;
                            } else {
                                educationDegree = EducationDegree.Doctorate;
                            }
                            HigherEducation hEducation = new HigherEducation(institution, enrollmentDate, graduationDate, educationDegree);
                            if (graduationDate.isBefore(LocalDate.now())) {
                                ((GradedEducation) hEducation).gotGraduated(finalGrade);
                            }
                            educations.add(hEducation);
                            break;
                    }
                }
            }
        } catch (SQLException ex) {
            throw new DALException("Error in education surch!", ex);
        }
        return educations;
    }

    @Override
    public void insertEducationWebPage(Education education, int personId) throws DALException {
        String sql = "INSERT INTO citizen_registrations.educations(`type`, `institution_name`, `enrollment_date`, `graduation_date`, `graduated`, `final_grade`, `person_id`)\n"
                + "VALUES (?,?,?,?,?,?,?);";

        try (Connection conn = DriverManager.getConnection(dbmsConnString, userName, password);
                PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setString(1, education.getDegree().toString());
            statement.setString(2, education.getInstitutionName());
            statement.setDate(3, (Date.valueOf(education.getEnrollmentDate())));
            statement.setDate(4, (Date.valueOf(education.getGraduationDate())));
            statement.setBoolean(5, education.isGraduated());

            if (education instanceof GradedEducation && education.getGraduationDate().isBefore(LocalDate.now())) {
                statement.setDouble(6, ((GradedEducation) education).getFinalGrade());
            } else {
                statement.setDouble(6, 0);
            }
            statement.setInt(7, personId);

            statement.execute();

        } catch (SQLException ex) {
            throw new DALException("Error during education insert!", ex);
        }
    }

    public List<Education> get(int personId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
