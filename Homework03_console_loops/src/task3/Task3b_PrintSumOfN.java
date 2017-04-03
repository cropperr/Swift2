package task3;

import java.util.Scanner;

public class Task3b_PrintSumOfN {

    // Не е довършена ! not complete
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int input = sc.nextInt();
        while (input > 0) {
            int x = sc.nextInt();

            int sum = 0;
            while (x > 0) {
                sum = sum + x % 10;
                x = x / 10;
                System.out.println(sum);
            }
            System.out.println(sum);
        }

    }

}
