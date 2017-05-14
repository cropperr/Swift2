
package Taks02_UniversityManagement;

import java.util.Scanner;


public class Task2_UniversityManagement {

    private static Person[] people = new Person[6];
    private static int personCounter = 0;
    private static Person unhappyPerson;
    private static double balance = 500;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String line = scanner.nextLine();
            if (line.equals("END")) {
                break;
            }
            
            String[] command = line.split(" ");

            switch (command[0]) {
                case "NEW":
                    people[personCounter++] = createPersone(command);
                    break;
                case "WORK":
                    work(command);
                    break;
                case "IDLE":
                    idle();
                    break;
            }
            if (unhappyPerson != null) {
                System.out.println(unhappyPerson.getName() + " is not happy");
                return;
            }
            if (balance <= 0 ) {
                System.out.println("Bankrupcy");
                return;
            }
        }
        for (Person p : people) {
            if (p == null) break;
            System.out.println(p);
        }
        
    }

    private static Person createPersone(String[] command) {
        String[] subjects;
        switch (command[1]) {
            case "MAINT":
                return new MaintenanceEmployee(command[2], command[3]);
            case "ADMIN":
                subjects = new String[command.length - 4];
                System.arraycopy(command,3, subjects,0, command.length - 4);
                return new AdministrationEmployee(command[2], command[3], subjects);
            case "TEACH":
                subjects = new String[command.length - 4];
                System.arraycopy(command,3, subjects,0, command.length - 4);
                return new Teacher(command[2], command[3], subjects);
            case "STUD":
                subjects = new String[command.length - 5];
                System.arraycopy(command,3, subjects,0, command.length - 5);
                return new Student(command[2], command[3], command[4], subjects);
            default: return null;
        }
    }

    private static void work(String[] command) {
        for (Person p : people) {
            if (p != null && p.getName().equals(command[1])) {
                p.work();
                if (unhappyPerson != null) {
                    return;
                }
                if (! (p instanceof Student)) {
                    balance -= ((Employee) p).getHourSalary();
                }
                return;
            }
        }
    }

    private static void idle() {
        for (Person p : people) {
            if (p == null) { return;}

            p.increaseTolerance(-5);
            if (p.getTolerance() <= 0) {
                unhappyPerson = p;
                return;
            }
        }
    }

    static Person[] getPeople() {
        return people;
    }

    static void setUnhappyPerson(Person unhappyPerson) {
        Task2_UniversityManagement.unhappyPerson = unhappyPerson;
    }

    static void increaseBalance(int i) {
        balance += i;
    }
}
