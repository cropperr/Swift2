
package Taks02_UniversityManagement;

public class MaintenanceEmployee extends Employee {

    public MaintenanceEmployee(String firstName, String phone) {
        super(firstName, phone, 15);
    }

    @Override
    public void work() {
        Person[] people = Task2_UniversityManagement.getPeople();
        for (Person p : people) {
            if (p != null && !p.equals(this)) {
                p.increaseTolerance(2);
            }
        }
    }


}
