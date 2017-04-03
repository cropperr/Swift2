
package task4;


public class Task4c_PrintChristmasTree {
    public static void main (String[]args){
        for (int i = 0; i < 4; i++) 
            System.out.println("   *******".substring(i, 4 + 2*i));
        for (int row = 2; row < 4; row++) {
            for (int col = 2; col <= 4 ; col++) {
                System.out.print(" ");

            }
            System.out.println("** ");
        }
// ??
    }
}


