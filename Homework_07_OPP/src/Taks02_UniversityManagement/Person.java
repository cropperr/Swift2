
package Taks02_UniversityManagement;


public abstract class Person {
    private String name;
    private String phone;
    private int tolerance;

    public Person(String name, String phone) {
        this.name = name;
        this.phone = phone;
        this.tolerance = 20;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        String result = "First name: " + name + "\n"
                + "Tolerance : " + tolerance + "\n";
        return result;
    }


    abstract public void work();

    public void increaseTolerance(int i) {
            this.tolerance += i;
    }

    public int getTolerance() {
        return tolerance;
    }
}
