package oops;
public class Prime {
    public static void main(String[] args) {
        int num = 0;  
        boolean isPrime = true;
        if (num <= 1) {
            isPrime = false;
        } else {
            for (int i = 2; i <= Math.sqrt(num); i++) {
                if (num % i == 0) {
                    isPrime = false;
                    break;
                }
            }
        }
        System.out.println("Janise Deepthi YP");
		System.out.println("2117240070124");

        if (isPrime)
            System.out.println(num + " :prime number.");
        else
            System.out.println(num + " :"
            		+ " prime number.");
    }
}

