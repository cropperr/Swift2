package Task2_Classes;

public final class Person {

    String name;
    int age;

    Person() {
        this.name = "No Name";
        this.age = -1;
    }

    Person(String name) {
        this.name = name;
        this.age = -1;
    }

    Person(String name, int age) {
        this.name = name;
        setAge(age);
    }

    void setAge(int age) {
        if (0 <= age && age <= 150) {
            this.age = age;
        } else {
            System.out.println("ERROR");
        }
    }

    String introduce() {
        String result = "";
        if (name.equals("No Name")) {
            result = "I am John Doe";
            return result;
        } else if (age == -1) {
            return "Hello I am " + name;
        } else {
            return "Hello I am " + name + ". I am " + age + " years old.";
        }
    }
}
