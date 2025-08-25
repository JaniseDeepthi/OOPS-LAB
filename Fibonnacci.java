package oops;
public class Fibonnacci {
    public static int fibonacci(int n) {
        if (n <= 1)
            return n;
        else
            return fibonacci(n - 1) + fibonacci(n - 2);
    }

    public static void main(String[] args) {
    	System.out.println("Janise Deepthi YP");
		System.out.println("2117240070124");
        int n = 10; 
        for (int i = 0; i < n; i++) {
            System.out.print(fibonacci(i) + " ");
        }
    }
}

