
package Taks_02;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Не е довършена. 

public class Taks2_FilterFiles {

    public static void main(String[] args) {
//Scanner sc = new Scanner(System.in);
//int count = sc.nextInt();
//        for (int i = 0; i <=count; i++) {
//           String inputs=sc.nextLine(); 
//        }
        String[] inputs = {"documentation.html", "updated_img0912.png", "img0912",
            ".jpg img234912"
        };

        String patten = "(?<=.jpg\\s).*";
        for (int i = 0; i < inputs.length; i++) {
            
            System.out.printf("Looking for pictures  in \"%s\"%n", inputs[i]);
            printAllRegexMatches(inputs[i], patten);
        }
    }

    private static void printAllRegexMatches(String input, String pattern) {
        Pattern regex = Pattern.compile(pattern);

        Matcher matcher = regex.matcher(input);

        while (matcher.find()) {
            System.out.printf("Picture file is %s%n",
                    matcher.group());
        }
    }

}
