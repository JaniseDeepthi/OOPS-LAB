package oops;
class BaseShape {
    public String area() {
        return "Default area";
    }
}
class Circle extends BaseShape {
    double radius;

    Circle(double radius) {
        this.radius = radius;
    }

    @Override
    
    public String area() {
        double result = Math.PI * radius * radius;
        return String.format("%.2f", result);
    }
}
class Rectangle extends BaseShape {
    double width, height;

    Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public String area() {
        double result = width * height;
        return String.format("%.2f", result);
    }
}
public class Shape {
    public static void main(String[] args) {
        System.out.println("Name: Janise Deepthi YP");
        System.out.println("ID: 2117240070124\n");

        // -------- Test Case 1: Circle with radius 5 --------
        Circle c1 = new Circle(5);
        System.out.println("Test Case 1: Circle with radius 5");
        System.out.println("Expected Area ≈ 78.54");
        System.out.println("Calculated Area = " + c1.area());

        
        Rectangle r1 = new Rectangle(10, 5);
        System.out.println("\nTest Case 2: Rectangle 10 x 5");
        System.out.println("Expected Area = 50.00");
        System.out.println("Calculated Area = " + r1.area());
        

        
        BaseShape s1 = new BaseShape();
        System.out.println("\nTest Case 3: BaseShape without override");
        System.out.println("Expected Output = Default area");
        System.out.println("Calculated Output = " + s1.area());
        

        
        
        Circle c2 = new Circle(0);
        System.out.println("\nTest Case 4: Zero radius circle");
        System.out.println("Expected Area = 0.00");
        System.out.println("Calculated Area = " + c2.area());
        

        
        Rectangle r2 = new Rectangle(0, 10);
        System.out.println("\nTest Case 5: Rectangle with width 0");
        System.out.println("Expected Area = 0.00");
        System.out.println("Calculated Area = " + r2.area());
        
    }
}
