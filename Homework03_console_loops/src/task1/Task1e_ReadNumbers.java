package task1;

import java.util.Scanner;

public class Task1e_ReadNumbers {

       public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int input = sc.nextInt();
        while (input > 0) {
            int x = sc.nextInt();
            System.out.println(x);
            input--;
        }

    }

}
