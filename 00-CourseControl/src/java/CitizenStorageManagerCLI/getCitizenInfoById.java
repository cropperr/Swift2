/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CitizenStorageManagerCLI;

import DataAccessLayer.exception.DALException;
import DataAccessLayer.sql.MySqlAddressStorage;
import DataAccessLayer.sql.MySqlPersonStorage;
import DataAccessLayer.sql.MySqlSocialInsuranceRecordStorage;
import DataAccessLayer.sql.MySqlEducationStorage;
import DataObjects.address.Address;
import DataObjects.education.Education;
import DataObjects.education.GradedEducation;
import DataObjects.insurance.SocialInsuranceRecord;
import java.util.List;
import DataObjects.personaldetails.Citizen;

public class getCitizenInfoById {

    public int personId;

    public getCitizenInfoById(int personId) {
        this.personId = personId;
    }

    public void printEducation(MySqlEducationStorage SqlEdu) throws DALException {
        List<Education> educations = SqlEdu.get(personId);
        for (Education education : educations) {

            // леко чийт с try catch, но иначе хваща exception-a за от 2 до 6 оценка при getFinalGrade
            try {
                ((GradedEducation) education).getFinalGrade();
                System.out.printf("%s, %s, %s, %s, %.2f%n", education.getInstitutionName(), education.getEnrollmentDate(),
                        education.getGraduationDate(), education.getDegree(), ((GradedEducation) education).getFinalGrade());
            } catch (Exception e) {
                System.out.printf("%s, %s, %s, %s%n", education.getInstitutionName(), education.getEnrollmentDate(),
                        education.getGraduationDate(), education.getDegree());
            }
        }
    }

    public List<Education> getEducationList(MySqlEducationStorage SqlEdu) throws DALException {
        int personId = 0;
//        List<Education> educations = SQLEdu.get(getId); == return educations
        return SqlEdu.get(personId);
    }

    public List<SocialInsuranceRecord> getSocialInsuranceRecords(MySqlSocialInsuranceRecordStorage SqlInsStorage) throws DALException {
        return SqlInsStorage.get(personId);
    }

    public void printSocialInsuranceRecords(MySqlSocialInsuranceRecordStorage SqlInsStorage) throws DALException {
        List<SocialInsuranceRecord> records = SqlInsStorage.get(personId);
        for (SocialInsuranceRecord record1 : records) {
            System.out.printf("%d, %d, %.2f%n", record1.getYear(), record1.getMonth(), record1.getAmount());
        }
    }

    public Citizen getCitizen(MySqlPersonStorage SqlPerStorage) throws DALException {
        return SqlPerStorage.get(personId);
    }

    public void printAddress(MySqlAddressStorage SqlAddrStorage) throws DALException {
        Address address = SqlAddrStorage.get(personId);
//        if (address.getFloor() == 0 && address.getApartmentNo() == 0) {
//            System.out.printf("%s, %s, %s, %s, %s, %s%n", address.getCountry(), address.getCity(),
//                    address.getMunicipality(), address.getPostalCode(), address.getStreet(),
//                    address.getNumber());
//        } else if (address.getFloor() == 0) {
//            System.out.printf("%s, %s, %s, %s, %s, %s, %d%n", address.getCountry(), address.getCity(),
//                    address.getMunicipality(), address.getPostalCode(), address.getStreet(),
//                    address.getNumber(), address.getApartmentNo());
//        } else if (address.getApartmentNo() == 0) {
//            System.out.printf("%s, %s, %s, %s, %s, %s, %d%n", address.getCountry(), address.getCity(),
//                    address.getMunicipality(), address.getPostalCode(), address.getStreet(),
//                    address.getNumber(), address.getFloor());
//        } else {
//            System.out.printf("%s, %s, %s, %s, %s, %s, %d, %d%n", address.getCountry(), address.getCity(),
//                    address.getMunicipality(), address.getPostalCode(), address.getStreet(),
//                    address.getNumber(), address.getFloor(), address.getApartmentNo());
//        }
    }

    public Address getAddress(MySqlAddressStorage SqlAddrStorage) throws DALException {

        return SqlAddrStorage.get(personId);
    }
}