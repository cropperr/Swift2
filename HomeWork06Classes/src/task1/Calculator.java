package task1;

public class Calculator {

    public static double calculate(String opeartor, double a, double b) {
        switch (opeartor) {
            case "SUM":
                return a + b;
            case "SUB":
                return a - b;
            case "MUL":
                return a * b;
            case "DIV":
                return a / b;
            case "PER":
                return per(a, b);
            default:
                return 0.00d;
        }
    }

    private static double per(double a, double b) {
        return a * (b / 100);

    }

}
