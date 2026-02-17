import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class AddBook {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Add New Book ===");
        System.out.print("Enter Book Title: ");
        String title = scanner.nextLine();
        
        System.out.print("Enter Author Name: ");
        String author = scanner.nextLine();
        
        System.out.print("Enter Quantity: ");
        int quantity = scanner.nextInt();

        String sql = "INSERT INTO books (title, author, quantity) VALUES (?, ?, ?)";

        // Using our DBConnection helper
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, title);
            pstmt.setString(2, author);
            pstmt.setInt(3, quantity);

            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("✅ Book added successfully!");
            }
        } catch (Exception e) {
            System.out.println("❌ Error: Could not add book.");
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}