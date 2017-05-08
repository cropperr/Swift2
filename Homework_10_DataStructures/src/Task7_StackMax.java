
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Task7_StackMax {

    public static void main(String[] args) {
        
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        Scanner sc = new Scanner(System.in);
        
        String[] command = sc.nextLine().split(" ");
        
        while (!command[0].equals("END")) {
            switch (command[0]) {
                case "PUSH":
                    stack.push(Integer.parseInt(command[1]));
                    break;
                case "POP":
                    System.out.println(stack.pop());
                    break;
                case "MAX":
                    System.out.println(getMaxNum(stack));
                    break;
            }
            command = sc.nextLine().split(" ");
        }
        stack.forEach(integer -> System.out.print(integer + " "));
    }

    private static int getMaxNum(ArrayDeque<Integer> stack) {
       
        ArrayList<Integer> stackNum = new ArrayList<>();
        
        stackNum.addAll(stack);
        
        stackNum.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return (o1 - o2);
            }
        });
        return stackNum.get(stackNum.size() - 1);
    }
}
