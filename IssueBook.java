import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class IssueBook {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Issue a Book ===");
        System.out.print("Enter Book ID: ");
        int bookId = scanner.nextInt();
        System.out.print("Enter Member ID: ");
        int memberId = scanner.nextInt();

        // SQL queries
        String checkSql = "SELECT quantity FROM books WHERE id = ?";
        String updateSql = "UPDATE books SET quantity = quantity - 1 WHERE id = ?";
        String logSql = "INSERT INTO transactions (book_id, member_id, issue_date) VALUES (?, ?, CURDATE())";

        try (Connection conn = DBConnection.getConnection()) {
            // Step 1: Check availability
            PreparedStatement checkStmt = conn.prepareStatement(checkSql);
            checkStmt.setInt(1, bookId);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next() && rs.getInt("quantity") > 0) {
                // Step 2: Start Transaction
                conn.setAutoCommit(false); 

                try {
                    // Update Inventory
                    PreparedStatement updateStmt = conn.prepareStatement(updateSql);
                    updateStmt.setInt(1, bookId);
                    updateStmt.executeUpdate();

                    // Log the loan
                    PreparedStatement logStmt = conn.prepareStatement(logSql);
                    logStmt.setInt(1, bookId);
                    logStmt.setInt(2, memberId);
                    logStmt.executeUpdate();

                    conn.commit(); // Finalize all changes
                    System.out.println("✅ Success: Book ID " + bookId + " issued to Member " + memberId);
                } catch (Exception e) {
                    conn.rollback(); // Undo everything if one step fails
                    System.out.println("❌ Transaction failed. Changes rolled back.");
                    e.printStackTrace();
                }
            } else {
                System.out.println("❌ Error: Book is out of stock or does not exist.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}