package Task4_EmployeeSalarySort;

import java.util.Arrays;
import java.util.Scanner;

public class Program {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int input = sc.nextInt();

        Employee elvis = new Employee(" Elvis ", 1500 , " newbie ", " java ");
        Employee ivan = new Employee("Ivan", 1300, "tehnik", "Computer networks");
        Employee petka = new Employee("Petka", 800, "Servitiorka", "restorant");

        Object[] people = {elvis, ivan, petka};
        System.out.println(Arrays.toString(people));
        System.out.println(elvis.name + elvis.salary + elvis.position + elvis.department);

    }

}
