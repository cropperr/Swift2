package Task1_Methods;

public class Task1c_IndexOf {

    int returnArrayIndex(int[] arr, int input) {
        int x = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == input) {
                x = i;
            } else {
                x = -1;
            }
        }
        return x;
    }

}
