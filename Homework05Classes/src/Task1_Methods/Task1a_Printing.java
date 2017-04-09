package Task1_Methods;

class Person {
    int age;
    String name;
    
    String print (String name, int age){
        return name+"is"+age+"years old.";
        
    }
}

public class Task1a_Printing {

    public static void main(String[] args) {
        Person x = new Person();
        x.name = "Stoyan";
        x.age = 31;
        System.out.println(x.name +" Is "+x.age+" years old.");
    }
}
