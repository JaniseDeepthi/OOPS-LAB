package oops;
import java.util.*;

public class Library {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> books = new ArrayList<>();

        while (true) {
            try {
                System.out.println("\n1.Add Book  2.Delete Book  3.List Books  4.Exit");
                System.out.print("Enter choice: ");
                int ch = sc.nextInt();
                sc.nextLine(); // consume newline

                switch (ch) {
                    case 1:
                        System.out.print("Enter book name: ");
                        String add = sc.nextLine().trim();
                        if (add.isEmpty()) throw new Exception("Book name cannot be empty!");
                        books.add(add);
                        System.out.println("✅ Added: " + add);
                        break;

                    case 2:
                        System.out.print("Enter book name: ");
                        String del = sc.nextLine().trim();
                        if (books.remove(del))
                            System.out.println("❌ Deleted: " + del);
                        else
                            System.out.println("⚠ Not found!");
                        break;

                    case 3:
                        if (books.isEmpty()) System.out.println("📚 Library is empty.");
                        else {
                            System.out.println("--- Books ---");
                            for (int i = 0; i < books.size(); i++)
                                System.out.println((i + 1) + ". " + books.get(i));
                        }
                        break;

                    case 4:
                        System.out.println("👋 Goodbye!");
                        sc.close();
                        return;

                    default:
                        System.out.println("⚠ Enter 1-4 only.");
                }
            } catch (InputMismatchException e) {
                System.out.println("⚠ Enter a valid number!");
                sc.nextLine(); // clear wrong input
            } catch (Exception e) {
                System.out.println("⚠ " + e.getMessage());
            }
        }
    }
}
