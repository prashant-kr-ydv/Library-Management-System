import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Step 1: Security Gate (from your Login.java)
        if (!Login.authenticate()) {
            System.out.println("Invalid Credentials. Closing Application.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n--- Librarian Dashboard ---");
            System.out.println("1. View All Books");
            System.out.println("2. Add New Book");
            System.out.println("3. Search for a Book");
            System.out.println("4. Issue a Book");
            System.out.println("5. Return a Book");
            System.out.println("6. Export Inventory to TXT");
            System.out.println("7. Logout & Exit");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1: ViewBooks.main(null); break;
                case 2: AddBook.main(null); break;
                case 3: SearchBook.main(null); break;
                case 4: IssueBook.main(null); break;
                case 5: ReturnBook.main(null); break;
                case 6: ExportInventory.main(null); break;
                case 7: 
                    System.out.println("Logging out...");
                    running = false; 
                    break;
                default: System.out.println("Invalid selection.");
            }
        }
        scanner.close();
    }
}



 