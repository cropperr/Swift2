
import java.util.ArrayList;
import java.util.Scanner;

public class Task5_ChildPlay {

    public static void main(String[] args) {
        int countRounds = 0;
        ArrayList<Integer> childHeight = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        int childrens = scanner.nextInt();

        for (int i = 0; i < childrens; i++) {
            childHeight.add(scanner.nextInt());
        }

        int tmp;
        do {
            tmp = childHeight.size();
            for (int i = childHeight.size() - 1; i > 1; i--) {
                if (childHeight.get(i) > childHeight.get(i - 1)) {
                    childHeight.remove(i);
                }
            }
            if (childHeight.size() != tmp) {
                countRounds++;
            }
        } while (tmp != childHeight.size());

        System.out.println(countRounds);
    }
}
