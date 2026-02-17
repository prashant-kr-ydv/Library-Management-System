import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class ReturnBook {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Return a Book ===");
        System.out.print("Enter Book ID to return: ");
        int bookId = scanner.nextInt();
        System.out.print("Enter Member ID: ");
        int memberId = scanner.nextInt();

        // SQL queries
        // 1. Update the inventory
        String updateQtySql = "UPDATE books SET quantity = quantity + 1 WHERE id = ?";
        // 2. Remove the loan record (or you could update a 'return_date' column if you added one)
        String deleteTransSql = "DELETE FROM transactions WHERE book_id = ? AND member_id = ? LIMIT 1";

        try (Connection conn = DBConnection.getConnection()) {
            conn.setAutoCommit(false); // Start Transaction

            try {
                // Step 1: Increase Book Quantity
                PreparedStatement updateStmt = conn.prepareStatement(updateQtySql);
                updateStmt.setInt(1, bookId);
                int rowsUpdated = updateStmt.executeUpdate();

                // Step 2: Remove the transaction record
                PreparedStatement deleteStmt = conn.prepareStatement(deleteTransSql);
                deleteStmt.setInt(1, bookId);
                deleteStmt.setInt(2, memberId);
                int rowsDeleted = deleteStmt.executeUpdate();

                if (rowsUpdated > 0 && rowsDeleted > 0) {
                    conn.commit(); // Save changes
                    System.out.println("✅ Success: Book returned and inventory updated!");
                } else {
                    conn.rollback();
                    System.out.println("❌ Error: No matching transaction found for this Book/Member.");
                }

            } catch (Exception e) {
                conn.rollback(); // Undo changes if something goes wrong
                System.out.println("❌ Error during return process.");
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}