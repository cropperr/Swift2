
package Taks02_UniversityManagement;


public abstract class Employee extends Person {

    private double hourSalary;
    private int tolerance;

    public Employee(String firstName, String lastName, double hourSalary) {
        super(firstName, lastName);
        this.hourSalary = hourSalary;
    }

    public double getHourSalary() {
        return hourSalary;
    }
}
