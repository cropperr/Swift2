package task1;

public class Calculator {

    public static double calculate(String opeartor, double a, double b) {
        switch (opeartor) {
            case "SUM":
                return sum(a, b);
            case "SUB":
                return sub(a, b);
            case "MUL":
                return mul(a, b);
            case "DIV":
                return div(a, b);
            case "PER":
                return per(a, b);
            default:
                return 0.00d;
        }
    }

    private static double sum(double a, double b) {
        return a + b;
    }

    private static double sub(double a, double b) {
        return a - b;
    }

    private static double mul(double a, double b) {
        return a * b;
    }

    private static double div(double a, double b) {
        return a / b;
    }

    private static double per(double a, double b) {
        return a * (b / 100);

    }

}
