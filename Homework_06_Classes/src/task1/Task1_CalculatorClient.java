package task1;

import java.util.Scanner;

public class Task1_CalculatorClient {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String command = scanner.next();
            if (command.equals("END")) {
                break;
            }
            double opr1 = scanner.nextDouble();
            double opr2 = scanner.nextDouble();

            System.out.printf("%.3f\n", Calculator.calculate(command, opr1, opr2));
        }

    }
}
