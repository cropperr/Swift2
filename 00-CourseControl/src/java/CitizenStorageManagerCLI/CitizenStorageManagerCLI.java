
package CitizenStorageManagerCLI;

import DataAccessLayer.sql.*;
import DataObjects.address.*;
import DataObjects.education.*;
import DataAccessLayer.exception.*;
import DataObjects.insurance.*;
import DataAccessLayer.interfaces.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import DataObjects.personaldetails.*;

public class CitizenStorageManagerCLI {

    public static void main(String[] args) throws FileNotFoundException, DALException {

        String dbmsConnString = "jdbc:mysql://localhost:3306/citizen_registrations";
        String userName = "root";
        String password = "kokolioo123";
        PersonStorage addPerson = new MySqlPersonStorage(dbmsConnString, userName, password);
        DeleteDatabaseStorage deleteDatabase = new MySqlDeleteDatabaseStorage(dbmsConnString, userName, password);

        Scanner sc = getScanner(args);

        int n = sc.nextInt();
        sc.nextLine();
        deleteDatabase.deleteDatabase();
        System.out.println("Database is empty! Import started at "+LocalDateTime.now()+"!");
        // List<Citizen> people = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String input = sc.nextLine();
            String inputInsurance = sc.nextLine();
            addPerson.insertPerson(createPerson(input, inputInsurance));
            if (i % 100 == 0) {
                System.out.println(i + "/" + n+" rows are imported "+LocalDateTime.now());
            }
            // people.add(createPerson(input, inputInsurance));
        }       

        /*for (Citizen person : people) {
            addPerson.insertPerson(person);
        }*/
        System.out.println("Import successful!");
    }

    public static Citizen createPerson(String input, String inputInsurance) {

        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.M.yyyy");
        Citizen person = null;
        String institution;
        LocalDate enrollmentDate;
        LocalDate graduationDate;
        float finalGrade;

        String[] split = input.split(";");

        String firstName = split[0];
        String middleName = split[1];
        String lastName = split[2];
        String gender = split[3];
        short height = Short.parseShort(split[5]);
        LocalDate birthDate = LocalDate.parse(split[4], formatter);

        if (gender.equals("F")) {
            person = new Citizen(firstName, middleName, lastName, Gender.Female, height, birthDate);
        } else {
            person = new Citizen(firstName, middleName, lastName, Gender.Male, height, birthDate);
        }

        String country = split[6];
        String city = split[7];
        String municipality = split[8];
        String zip = split[9];
        String street = split[10];
        String number = split[11];

        Address address;
        if (split.length > 12 && !split[12].equals("")) {
            int floor = Integer.parseInt(split[12]);
            int apartmentNumber = Integer.parseInt(split[13]);

            address = new Address(country, city, municipality, zip, street, number, floor, apartmentNumber);
        } else {
            address = new Address(country, city, municipality, zip, street, number);
        }
        person.setAddress(address);

        if (split.length > 13) {
            for (int i = 14; i < split.length; i++) {
                switch (split[i]) {
                    case "P":
                        institution = split[++i];
                        enrollmentDate = LocalDate.parse(split[++i], formatter);
                        graduationDate = LocalDate.parse(split[++i], formatter);
                        PrimaryEducation pEducation = new PrimaryEducation(institution, enrollmentDate, graduationDate);
                        person.addEducation(pEducation);
                        break;
                    case "S":
                        institution = split[++i];
                        enrollmentDate = LocalDate.parse(split[++i], formatter);
                        graduationDate = LocalDate.parse(split[++i], formatter);
                        SecondaryEducation sEducation = new SecondaryEducation(institution, enrollmentDate, graduationDate);
                        if (graduationDate.isBefore(LocalDate.now())) {
                            finalGrade = Float.parseFloat(split[++i]);
                            ((GradedEducation) sEducation).gotGraduated(finalGrade);
                        }
                        person.addEducation(sEducation);
                        break;
                    case "B":
                    case "M":
                    case "D":
                        EducationDegree degree = null;
                        if (split[i].equals("B")) {
                            degree = EducationDegree.Bachelor;
                        } else if (split[i].equals("M")) {
                            degree = EducationDegree.Master;
                        } else {
                            degree = EducationDegree.Doctorate;
                        }
                        institution = split[++i];
                        enrollmentDate = LocalDate.parse(split[++i], formatter);
                        graduationDate = LocalDate.parse(split[++i], formatter);

                        HigherEducation hEducation = new HigherEducation(institution, enrollmentDate, graduationDate, degree);
                        if (graduationDate.isBefore(LocalDate.now())) {
                            finalGrade = Float.parseFloat(split[++i]);
                            ((GradedEducation) hEducation).gotGraduated(finalGrade);
                        }
                        person.addEducation(hEducation);
                        break;
                }
            }
        }

        String[] insuranceSplit = inputInsurance.split(";");
        for (int i = 0; i < insuranceSplit.length; i++) {
            int year = Integer.parseInt(insuranceSplit[i]);
            int month = Integer.parseInt(insuranceSplit[++i]);
            double amount = Double.parseDouble(insuranceSplit[++i]);
            SocialInsuranceRecord insurance = new SocialInsuranceRecord(year, month, amount);
            person.addSocialInsuranceRecord(insurance);
        }

        return person;
    }

    private static Scanner getScanner(String[] args) {
        Scanner sc = new Scanner(System.in, "UTF-8");

        if (args.length > 0 && !args[0].isEmpty()) {
            File file = new File(args[0]);
            if (file.isFile()) {
                try {
                    sc = new Scanner(new FileInputStream(args[0]));
                } catch (FileNotFoundException ex) {
                    System.out.println("File " + file.getName() + " was not found.");
                    return sc;
                }
            }
        }

        return sc;
    }
}