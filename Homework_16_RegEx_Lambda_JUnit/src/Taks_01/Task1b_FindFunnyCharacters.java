
package Taks_01;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Task1b_FindFunnyCharacters {
     public static void main(String[] args) {

        String inputs = "廣asd西323平qwe話aa44";

        String patten = "\\W";

        System.out.printf("Looking for Funny Characters ? in \"%s\"%n", inputs);
        printAllRegexMatches(inputs, patten);

    }

    private static void printAllRegexMatches(String input, String pattern) {
        Pattern regex = Pattern.compile(pattern);

        Matcher matcher = regex.matcher(input);

        while (matcher.find()) {
            System.out.printf("Strange characters are: %s%n",
                    matcher.group());
        }
    }

}

