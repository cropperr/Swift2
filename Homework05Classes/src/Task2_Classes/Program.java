package Task2_Classes;

public class Program {

    public static void main(String[] args) {
        Person x = new Person();
        System.out.println(x.age);
        System.out.println(x.name);

        Person joro = new Person("Joro");
        System.out.println(joro.age);
        System.out.println(joro.name);

        Person stefan = new Person("Stefan", 21);
        System.out.println(stefan.age);
        System.out.println(stefan.name);

        System.out.println(x.introduce());
        System.out.println(joro.introduce());
        System.out.println(stefan.introduce());

        System.out.println("");
        System.out.println("Car Task:");

        Car test = new Car("Opel", "Astra", 1996, 85);
        System.out.println("Insurance Category: " + test.insuranceCategory());
        System.out.println("Tax: " + test.tax());

    }
}
