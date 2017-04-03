package task1;

import java.util.Scanner;

public class Task1f_readNumbersOnNewLines {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        System.out.println(num);
        while (sc.hasNext()) {
            if (sc.hasNextInt()) {
                System.out.print(" " + sc.nextInt());
            } else {
                sc.next();
            }

        }

    }
}
