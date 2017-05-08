
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;


public class Task1_UniqueNumbers {
    
    
    public static void main(String[] args) {
        Set<Integer> integers = new HashSet<>();
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        for (int i = 0; i < num; i++) {
            integers.add(sc.nextInt());
            
            
        }
        integers.forEach(integer -> System.out.print(integer+ " "));
    }
}
