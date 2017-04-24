package task1;

import java.util.Scanner;

public class Task0_NumberNotANumber {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String row = "";
        while (!row.equals("END")) {
            try {
                row = sc.nextLine();
                Integer.parseInt(row);
                System.out.println("Number ! ");
            } catch (NumberFormatException ex) {
                System.out.println("Not a number ! ");
            }
        }

    }
}
