package task1;

/**
 *
 * @author Kremena
 */
public class task2c {

    public static void main(String[] args) {
        int n = 4;
        int[][] arr = new int[n][n];
        int idx = 1;
        int i = 0;
        int j = 1;

        // while (true) {
            while (j >= 0) {
                arr[i][j] = idx;
                idx++;
                i++;
                j--;
            }
            
            j++;
        }

    }

   
