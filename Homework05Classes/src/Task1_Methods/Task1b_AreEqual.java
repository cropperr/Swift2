package Task1_Methods;

public class Task1b_AreEqual {

    public static String areEquals(int a, int b) {
        if (a == b) {
            return "true";
        }
        {
            return "false";
        }
 
    }

    public static void main(String[] args) {
        String res = areEquals(0, 10);
        System.out.println(res);

    }
}
