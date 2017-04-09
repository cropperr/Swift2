
package task1;

public class Task1e_Print2dArray {

    public static void main(String[] args) {
        int n = 4;
        int[][] arr = new int[n][n];

        int number = 0;
        
        for (int[] arr1 : arr) {
            for (int col = 0; col < arr1.length; col++) {
                arr1[col] = ++number;
            }
        }

        for (int[] arr1 : arr) {
            for (int col = 0; col < arr1.length; col++) {
                System.out.printf("%4d ", arr1[col]);
            }
            System.out.println();
        }
    }
}

