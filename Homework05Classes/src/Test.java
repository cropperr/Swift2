
public class Test {

    
    public void start() {
        System.out.println("Starting...");
    }
    
    public static void main(String[] args) {
        Test m = new Test() {
            @Override public void start(){
                System.out.println("Woooooo");
            }
        };
        Test m1 = new Test();
        m1.start();
    }
}

