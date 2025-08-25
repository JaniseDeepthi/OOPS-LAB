package oops;

import java.util.Scanner;

public class DivbyZero {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            // Take input
            System.out.print("Enter first number (dividend): ");
            int a = sc.nextInt();
            
            System.out.print("Enter second number (divisor): ");
            int b = sc.nextInt();

            // Try block for division
            int result = a / b;
            System.out.println("Result: " + result);
        } 
        catch (ArithmeticException e) {
            // Catch block if divisor is zero
            System.out.println("Error: Division by zero is not allowed!");
        } 
        finally {
            // This block always executes
            System.out.println("Program execution completed.");
        }
    }
}
