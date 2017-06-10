package Taks_02;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Task2_FilterFiles {
// Готова
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        String res = "";
        for (int i = 0; i <= n; i++) {
            String inp = sc.nextLine();

            if (Pattern.matches(".*\\.(jpg|gif|png)\\s*$", inp.toLowerCase().subSequence(0, inp.length()))) {
                res += inp + "\n";
            }
        }
        System.out.printf("-------------%nImage files:%n%s",res);
    }
}
