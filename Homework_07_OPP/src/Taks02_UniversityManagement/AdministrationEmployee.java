
package Taks02_UniversityManagement;

public class AdministrationEmployee extends Employee {
private String[] subjects;

    public AdministrationEmployee(String firstName, String phone, String... subjects) {
        super(firstName, phone, 19);
        this.subjects = subjects;
    }

    @Override
    public void work() {
        Person[] people = Task2_UniversityManagement.getPeople();
        for (Person p : people) {
            if (p != null && !p.equals(this)) {
                if (p instanceof Student) {
                    p.increaseTolerance(3);
                } else if (p instanceof Teacher) {
                    p.increaseTolerance(3);
                } else if (p instanceof MaintenanceEmployee) {
                    p.increaseTolerance(1);
                }
            }
        }
    }
}
