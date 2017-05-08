
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Task6_WordReplacement {

    public static void main(String[] args) {
        Map<String, String> map = new HashMap();
        Scanner sc = new Scanner(System.in);
        
        String sentence = sc.nextLine();
        String[] sentenceArray = sentence.split(" ");
        int mapEntryCount = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < mapEntryCount; i++) {
            String[] split = sc.nextLine().split(" ");
            map.put(split[0], split[1]);
        }

        for (int i = 0; i < sentenceArray.length; i++) {
            String key = sentenceArray[i];
            if (map.containsKey(key.toLowerCase())) {
                sentenceArray[i] = map.get(key.toLowerCase());
            }
        }
        String newSentence = "";
        for (String str : sentenceArray) {
            newSentence += str + " ";
        }

        System.out.println(newSentence);

    }
}
