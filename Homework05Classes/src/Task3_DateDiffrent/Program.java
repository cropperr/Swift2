package Task3_DateDiffrent;

import java.util.Scanner;

public class Program {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SwiftDate date01 = new SwiftDate(
                scanner.nextInt(),
                scanner.nextInt(),
                scanner.nextInt()
        );

        SwiftDate date02 = new SwiftDate(
                scanner.nextInt(),
                scanner.nextInt(),
                scanner.nextInt()
        );
        System.out.println("");

        System.out.println(date01.getDaysDifference(date02));
        System.out.println(date01.getInfo());
        System.out.println(date02.getInfo());
    }
}
