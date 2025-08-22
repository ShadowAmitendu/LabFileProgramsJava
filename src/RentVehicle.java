import java.util.Scanner;

abstract class Vehicle {
    private final String model;
    private final int registrationNumber;

    Vehicle(String model, int registrationNumber) {
        this.model = model;
        this.registrationNumber = registrationNumber;
    }

    public String getModel() {
        return model;
    }

    public int getRegistrationNumber() {
        return registrationNumber;
    }

    abstract double calculateRent(int days);

    abstract double calculateRent(int days, double discountPercentage);
}

class Car extends Vehicle {
    Car(String model, int registrationNumber) {
        super(model, registrationNumber);
    }

    @Override
    double calculateRent(int days) {
        return 1000 * days;
    }

    @Override
    double calculateRent(int days, double discountPercentage) {
        return 1000 * days * (1 - discountPercentage / 100);
    }
}

class Bike extends Vehicle {
    Bike(String model, int registrationNumber) {
        super(model, registrationNumber);
    }

    @Override
    double calculateRent(int days) {
        return 400 * days;
    }

    @Override
    double calculateRent(int days, double discountPercentage) {
        return 400 * days * (1 - discountPercentage / 100);
    }
}

public class RentVehicle {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("===== VEHICLE RENTAL SYSTEM =====");
        System.out.println("1. RENT A CAR");
        System.out.println("2. RENT A BIKE");
        System.out.println("0. EXIT");
        System.out.print("ENTER YOUR CHOICE: ");
        int choice = input.nextInt();
        input.nextLine(); // consume newline

        if (choice == 0) {
            System.out.println("EXITING...");
            System.exit(0);
        }

        System.out.print("ENTER THE MODEL OF THE VEHICLE: ");
        String model = input.nextLine();

        System.out.print("ENTER THE REGISTRATION NUMBER: ");
        int registrationNumber = input.nextInt();

        Vehicle vehicle;
        if (choice == 1) {
            vehicle = new Car(model, registrationNumber);
        } else if (choice == 2) {
            vehicle = new Bike(model, registrationNumber);
        } else {
            System.out.println("INVALID CHOICE!");
            return;
        }

        System.out.print("ENTER NUMBER OF DAYS TO RENT: ");
        int days = input.nextInt();

        System.out.print("DO YOU HAVE A DISCOUNT? (YES=1 / NO=0): ");
        int hasDiscount = input.nextInt();

        double totalCost;
        if (hasDiscount == 1) {
            System.out.print("ENTER DISCOUNT PERCENTAGE: ");
            double discount = input.nextDouble();
            totalCost = vehicle.calculateRent(days, discount);
        } else {
            totalCost = vehicle.calculateRent(days);
        }

        System.out.println("\n===== RENTAL DETAILS =====");
        System.out.println("VEHICLE MODEL       : " + vehicle.getModel());
        System.out.println("REGISTRATION NUMBER : " + vehicle.getRegistrationNumber());
        System.out.println("TOTAL RENT          : " + totalCost);
    }
}
