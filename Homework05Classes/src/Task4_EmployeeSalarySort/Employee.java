package Task4_EmployeeSalarySort;

public class Employee {

    String name;
    double salary;
    String position;
    String department;
    int age;
    String email;

    public Employee(String name, double salary, String position, String department) {
        this.name = name;
        this.salary = salary;
        this.position = position;
        this.department = department;
        this.age = -1;
        this.email = "";

    }

    public Employee(String name, double salary, String position, String department, int age) {
        this.name = name;
        this.salary = salary;
        this.position = position;
        this.department = department;
        this.age = age;
        this.email = "";
    }

    public Employee(String name, double salary, String position, String department, int age, String email) {
        this.name = name;
        this.salary = salary;
        this.position = position;
        this.department = department;
        this.age = age;
        this.email = email;
    }

    void sortSalary() {

    }

    String output() {
        String result = String.format("%s,%.2f,%s,%s", name, salary, position, department);
        return result;
    }

}
