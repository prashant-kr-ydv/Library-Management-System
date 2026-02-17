import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class SearchBook {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n=== Search for a Book ===");
        System.out.print("Enter search keyword (Title or Author): ");
        String keyword = scanner.nextLine();

        // The '%' symbols allow for partial matches (e.g., "Java" finds "Learning Java")
        String sql = "SELECT * FROM books WHERE title LIKE ? OR author LIKE ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            String queryKeyword = "%" + keyword + "%";
            pstmt.setString(1, queryKeyword);
            pstmt.setString(2, queryKeyword);

            ResultSet rs = pstmt.executeQuery();

            System.out.println("\n--- Search Results ---");
            System.out.printf("%-5s %-25s %-20s %-10s\n", "ID", "Title", "Author", "Qty");
            System.out.println("------------------------------------------------------------");

            boolean found = false;
            while (rs.next()) {
                found = true;
                System.out.printf("%-5d %-25s %-20s %-10d\n", 
                    rs.getInt("id"), 
                    rs.getString("title"), 
                    rs.getString("author"), 
                    rs.getInt("quantity"));
            }

            if (!found) {
                System.out.println("No books found matching: " + keyword);
            }

        } catch (Exception e) {
            System.out.println("❌ Search Error: " + e.getMessage());
        }
    }
}