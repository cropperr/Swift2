
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Task2_CommonSubset {

    public static void main(String[] args) {

        Set<Integer> integersRow01 = new HashSet<>();
        Set<Integer> integersRow02 = new HashSet<>();

        Scanner scanner = new Scanner(System.in);
        int firstSequence = scanner.nextInt();
        int secondSequence = scanner.nextInt();

        for (int i = 0; i < firstSequence; i++) {
            integersRow01.add(scanner.nextInt());
        }

        for (int i = 0; i < secondSequence; i++) {
            integersRow02.add(scanner.nextInt());
        }

        integersRow02.retainAll(integersRow01);
        if (integersRow02.size() < 1) {
            System.out.println("No");
        } else {
            integersRow02.forEach(integer -> System.out.print(integer + " "));
        }
    }
}
