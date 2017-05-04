
package Taks02_UniversityManagement;

public class Teacher extends Employee {
private String[] subjects;

    public Teacher(String firstName, String phone, String... subjects) {
        super(firstName, phone, 25);
        this.subjects = subjects;
    }

    @Override
    public void work() {
        Person[] people = Task2_UniversityManagement.getPeople();
        for (Person p : people) {
            if (p != null && !p.equals(this)) {
                if (p instanceof Student) {
                    p.increaseTolerance(3);
                } else if (p instanceof MaintenanceEmployee) {
                    p.increaseTolerance(-3);
                } else if (p instanceof AdministrationEmployee) {
                    p.increaseTolerance(-1);
                }
                if (p.getTolerance() <= 0) {
                    Task2_UniversityManagement.setUnhappyPerson(p);
                    return;
                }
            }
        }
    }
}
