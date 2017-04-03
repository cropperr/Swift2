package task1;

import java.util.Scanner;

public class Task1c_ReadAge {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();

        if (x <= 0) {
            System.out.println("Error");
        } else if (x <= 17) {
            System.out.println("No");
        } else if (x >= 18) {
            System.out.println("Yes");
        }
    }
}
