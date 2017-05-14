
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Task3_ParenthesesMatching {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        ArrayDeque<Character> stack = new ArrayDeque<>();
        Set<Character> openBracket = new HashSet<>();
        Set<Character> closeBracket = new HashSet<>();
        Map<Character, Character> mapping = new HashMap<>();

        openBracket.addAll(Arrays.asList(new Character[]{'(', '[', '{'}));
        closeBracket.addAll(Arrays.asList(new Character[]{')', ']', '}'}));
        mapping.put('(', ')');
        mapping.put('[', ']');
        mapping.put('{', '}');
        char[] bracketArray = line.toCharArray();

        // The algorithm ignores any char except papped ones!
        for (char c : bracketArray) {
            if (openBracket.contains(c)) {
                stack.push(c);
            } else if (closeBracket.contains(c)) {
                if (mapping.get(stack.peek()) == c) {
                    stack.pop();
                } else {
                    System.out.println(false);
                    return;
                }
            }
        }

        if (stack.size() > 0) {
            System.out.println(false);
        } else {
            System.out.println(true);
        }

    }
}
