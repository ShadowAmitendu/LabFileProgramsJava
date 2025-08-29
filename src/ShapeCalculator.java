import java.util.Scanner;

// ABSTRACT CLASS
abstract class Shape {
    private final String shapeName;

    Shape(String shapeName) {
        this.shapeName = shapeName;
    }

    public String getShapeName() {
        return shapeName;
    }

    abstract double calculateArea();

    abstract double calculatePerimeter();
}

class Circle extends Shape {
    private double r;

    Circle() {
        super("CIRCLE");
    }

    // METHOD OVERLOADING: SET DIMENSIONS
    public void setDimensions(double radius) {
        this.r = radius;
    }

    public double getRadius() {
        return r;
    }

    @Override
    double calculateArea() {
        return Math.PI * r * r;
    }

    @Override
    double calculatePerimeter() {
        return 2 * Math.PI * r;
    }
}

// RECTANGLE CLASS
class Rectangle extends Shape {
    private double length;
    private double breadth;

    Rectangle() {
        super("RECTANGLE");
    }

    public void setDimensions(double length, double breadth) {
        this.length = length;
        this.breadth = breadth;
    }

    public double getLength() {
        return length;
    }

    public double getBreadth() {
        return breadth;
    }

    @Override
    double calculateArea() {
        return length * breadth;
    }

    @Override
    double calculatePerimeter() {
        return 2 * (length + breadth);
    }
}

public class ShapeCalculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("===== SHAPE AREA & PERIMETER CALCULATOR =====");
        System.out.println("1. CIRCLE");
        System.out.println("2. RECTANGLE");
        System.out.println("0. EXIT");
        System.out.print("ENTER YOUR CHOICE: ");
        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                Circle circle = new Circle();
                System.out.print("ENTER RADIUS: ");
                double radius = sc.nextDouble();
                circle.setDimensions(radius);
                System.out.println("\n===== RESULT =====");
                System.out.println("SHAPE: " + circle.getShapeName());
                System.out.println("RADIUS: " + circle.getRadius());
                System.out.println("AREA: " + circle.calculateArea());
                System.out.println("PERIMETER: " + circle.calculatePerimeter());
                break;
            case 2:
                Rectangle rectangle = new Rectangle();
                System.out.print("ENTER LENGTH: ");
                double length = sc.nextDouble();
                System.out.print("ENTER BREADTH: ");
                double breadth = sc.nextDouble();
                rectangle.setDimensions(length, breadth);
                System.out.println("\n===== RESULT =====");
                System.out.println("SHAPE: " + rectangle.getShapeName());
                System.out.println("LENGTH: " + rectangle.getLength());
                System.out.println("BREADTH: " + rectangle.getBreadth());
                System.out.println("AREA: " + rectangle.calculateArea());
                System.out.println("PERIMETER: " + rectangle.calculatePerimeter());
                break;
            case 0:
                System.out.println("EXITING...");
                System.exit(0);
            default:
                System.out.println("INVALID CHOICE!");
        }
    }
}
