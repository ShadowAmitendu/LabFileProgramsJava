import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        int numA, numB;
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Number 1: ");
        numA = sc.nextInt();
        System.out.print("Enter Number 2: ");
        numB = sc.nextInt();

        Calculator cal = new Calculator(numA, numB);

        System.out.println("Original Values:\n");
        cal.displayValues();

        System.out.println("Addition: " + cal.addition());
        System.out.println("Subtraction: " + cal.subtract());
        System.out.println("Multiply: " + cal.multiply());
        System.out.println("Divide: " + cal.divide());
        System.out.println("Modulus: " + cal.modulus());
    }
}

class Calculator {
    private final int num1;
    private final int num2;

    Calculator (int num1, int num2) {
        this.num1 = num1;
        this.num2 = num2;
    }

    double addition() {
        return num1 + num2;
    }
    double subtract() {
        return num1 + num2;
    }
    double multiply() {
        return num1 + num2;
    }
    double divide() {
        return num1 + num2;
    }

    double modulus() {
        return num1 % num2;
    }

    void displayValues() {
        System.out.println(this.num1);
        System.out.println(this.num2);
    }
}

