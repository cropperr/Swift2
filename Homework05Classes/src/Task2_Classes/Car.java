package Task2_Classes;

public class Car {

    String brand;
    String model;
    int productionYear;
    int horsePower;
    int age;

    Car(String brand, String model, int productionYear, int horsePower) {
        this.brand = brand;
        this.model = model;
        this.productionYear = productionYear;
        this.horsePower = horsePower;
        this.age = 2017 - this.productionYear;
    }

    int insuranceCategory() {
        if (age < 8) {
            return 1;
        } else if (age >= 8 && age <= 15) {
            return 2;
        } else if (age > 15 && age < 25) {
            return 3;
        } else {
            return 4;
        }
    }

    double tax() {
        int sum = 0;
        double finalSum;
        int insuranceCat = insuranceCategory();
        switch (insuranceCat) {
            case 1:
                sum = 150;
                break;
            case 2:
                sum = 200;
                break;
            case 3:
                sum = 300;
                break;
            case 4:
                sum = 500;
                break;
            default:
                break;
        }
        if (this.horsePower < 80) {
            finalSum = sum * 1.2;
        } else if (this.horsePower > 140) {
            finalSum = sum * 1.45;
        } else {
            finalSum = sum;
        }
        return finalSum;
    }
}
