package Task1_PeoplePresentation;

import java.util.Scanner;

public class main {

    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String line = scanner.nextLine();
            if (line.equals("END")) {
                break;
            }
            Persons p = newPersons(line.split(" "));
            System.out.println(p + "\n");
        }
    }

    private static Persons newPersons(String[] personsStrings) throws Exception {
        if (personsStrings.length == 5) {
            return new Student(personsStrings[0],
                    personsStrings[1],
                    personsStrings[2],
                    Integer.parseInt(personsStrings[3]),
                    Integer.parseInt(personsStrings[4])
            );
        } else {
            return new Worker(personsStrings[0],
                    personsStrings[1],
                    Double.parseDouble(personsStrings[2]),
                    Double.parseDouble(personsStrings[3])
            );
        }
    }
}
