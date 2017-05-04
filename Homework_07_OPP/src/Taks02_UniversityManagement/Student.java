
package Taks02_UniversityManagement;


public class Student extends Person {

    private String facultyNumber;
    private String[] subjects;

    public Student(String firstName, String lastName, String facultyNumber, String... subjects) {
        super(firstName, lastName);
        this.facultyNumber = facultyNumber;
        this.subjects = subjects;
    }


    @Override
    public void work() {
        Person[] people = Task2_UniversityManagement.getPeople();
        for (Person p : people) {
            if (p != null && !p.equals(this)) {
                if (p instanceof Student) {
                    p.increaseTolerance(2);
                } else if (p instanceof MaintenanceEmployee) {
                    p.increaseTolerance(-1);
                }
            }
            if (this.getTolerance() >= 50) {
                Task2_UniversityManagement.increaseBalance(10);
            }
            if (p.getTolerance() <= 0) {
                Task2_UniversityManagement.setUnhappyPerson(p);
                return;
            }
        }
    }
}