
package DataAccessLayer.interfaces;

import DataAccessLayer.exception.DALException;
import DataObjects.insurance.SocialInsuranceRecord;
import java.util.List;

public interface SocialInsuranceRecordStorage {

    public void insertSocialInsurance(List<SocialInsuranceRecord> socialInsurances, int personId) throws DALException;

    public List<SocialInsuranceRecord> getSocialInsuranceByPersonId(int personId) throws DALException;

    public void insertSocialInsuranceFromWebPage(SocialInsuranceRecord socialInsurance, int person_id) throws DALException;
}