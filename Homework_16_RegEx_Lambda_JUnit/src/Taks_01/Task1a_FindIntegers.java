package taks_01;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task1a_FindIntegers {

    public static void main(String[] args) {

        String inputs = "Pesho have 4 dogs and 3 cats, but his money are -205";

        String patten = "\\d";

        System.out.printf("Looking for Integers in \"%s\"%n", inputs);
        printAllRegexMatches(inputs, patten);

    }

    private static void printAllRegexMatches(String input, String pattern) {
        Pattern regex = Pattern.compile(pattern);

        Matcher matcher = regex.matcher(input);

        while (matcher.find()) {
            System.out.printf("Number %s%n",
                    matcher.group());
        }
    }

}
