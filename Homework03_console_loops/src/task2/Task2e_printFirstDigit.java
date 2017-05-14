package task2;

import java.util.Scanner;

public class Task2e_printFirstDigit {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int number = sc.nextInt();

        int firstDigit = number;
        while (firstDigit > 9) {
            firstDigit = firstDigit / 10;
        }
        System.out.println(firstDigit);

    }
}
