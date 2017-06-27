
package DataAccessLayer.interfaces;

import DataObjects.education.Education;
import DataAccessLayer.exception.DALException;
import java.util.List;

public interface EducationStorage {

    public void insertEducation(List<Education> educations, int personId) throws DALException;

    public List<Education> getEducationsByPersonID(int personId) throws DALException;

    public void insertEducationWebPage(Education education, int personId) throws DALException;
}
