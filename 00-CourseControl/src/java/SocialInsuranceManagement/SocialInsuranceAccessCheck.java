
package SocialInsuranceManagement;

import DataObjects.education.*;
import DataObjects.insurance.SocialInsuranceRecord;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import DataObjects.personaldetails.*;


public class SocialInsuranceAccessCheck {

    public boolean checkEducation(Citizen person) {
        boolean check = false;
        for (Education education : person.getEducations()) {
            if (education != null && education.getDegree() == EducationDegree.Secondary) {
                if (education.isGraduated() == true) {
                    check = true;
                }
            }
        }
        return check;
    }

    public boolean checkSocialInsuranceInstallments(Citizen person) {
        boolean check = true;
        int year = Calendar.getInstance().get(Calendar.YEAR);
        int month = Calendar.getInstance().get(Calendar.MONTH);
        for (SocialInsuranceRecord socialInsurance : person.getSocialInsuranceRecords()) {
            int yearDifference = year - socialInsurance.getYear();
            if (yearDifference == 0 && month > 3) {
                if ((month - socialInsurance.getMonth()) <= 3) {
                    check = false;
                }
            } else if (yearDifference == 0 && month <= 3) {
                check = false;
            } else if (yearDifference == 1 && month <= 3) {
                if (((month + 12) - socialInsurance.getMonth()) < 3) {
                    check = false;
                }
            }
            break;
        }
        return check;
    }

    public double getSocialInsuranceInstallmentSum(Citizen person) {
        double sum = 0;
        int year = 0;
        int month = Calendar.getInstance().get(Calendar.MONTH);
        if(month>3){
            year = Calendar.getInstance().get(Calendar.YEAR);
        }else{
            year = Calendar.getInstance().get(Calendar.YEAR)-1;
            month = month+12;
        }
        for (SocialInsuranceRecord socialInsurance : person.getSocialInsuranceRecords()) {
            int yearDifference = year - socialInsurance.getYear();
            if (yearDifference < 2) {
                sum = sum + socialInsurance.getAmount();
            } else if (yearDifference == 2 && socialInsurance.getMonth() > month-3) {
                sum = sum + socialInsurance.getAmount();
            }
        }
        return sum / 24;
    }
    public List<SocialInsuranceRecord> getLastTwoYearInsurances(Citizen person){
        List<SocialInsuranceRecord> lastInsurances = new ArrayList<>();
        int year = 0;
        int month = Calendar.getInstance().get(Calendar.MONTH);
        if(month>3){
            year = Calendar.getInstance().get(Calendar.YEAR);
        }else{
            year = Calendar.getInstance().get(Calendar.YEAR)-1;
            month = month+12;
        }
        for (SocialInsuranceRecord socialInsurance : person.getSocialInsuranceRecords()) {
            int yearDifference = year - socialInsurance.getYear();
            if (yearDifference < 2) {
                lastInsurances.add(socialInsurance);
            } else if (yearDifference == 2 && socialInsurance.getMonth() > month-3) {
                lastInsurances.add(socialInsurance);
            }
        }
        return lastInsurances;
    }

}
