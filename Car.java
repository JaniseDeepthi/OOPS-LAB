package oops;
class Car {
    String model, color;

    Car(String model, String color) {
        this.model = model;
        this.color = color;
    }

    void start() {
        if (!model.isEmpty()) {
            System.out.println(" Car started");
        } else {
            System.out.println("Car started (but model not shown)");
        }
    }
    
    void stop() {
            System.out.println(" Car stopped");
  
    }

    public static void main(String[] args) {
    	System.out.println("Janise Deepthi YP");
		System.out.println("2117240070124");
        Car c1 = new Car("", "Red");  // <-- Test case with empty model
       
    }
}