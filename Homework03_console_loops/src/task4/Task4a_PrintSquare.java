package task4;

public class Task4a_PrintSquare {

    public static void main(String[] args) {
        int n = 5;
        int x = 5;
        for (int i = 0; i < x; i++) {
            System.out.print("* ");
        }
        System.out.println("");
        for (int row2 = 0; row2 < n - 2; row2++) {
            System.out.print("* ");
            for (int col = 0; col < x - 2; col++) {
                System.out.print("  ");
            }
            System.out.println("* ");
        }
        for (int i = 0; i < x; i++) {
            System.out.print("* ");
        }
        System.out.println();
    }

}
