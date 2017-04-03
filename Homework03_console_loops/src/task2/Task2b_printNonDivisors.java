
package task2;

import java.util.Scanner;



public class Task2b_printNonDivisors {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int number = sc.nextInt();

        for (int i = 1; i <= number; i++) {
            if (i % 3 != 0 && i % 7 != 0) {

                System.out.print(i+" ");
            }
        }
        }
    }