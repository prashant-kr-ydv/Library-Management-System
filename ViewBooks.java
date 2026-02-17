import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class ViewBooks {
    public static void main(String[] args) {
        String sql = "SELECT * FROM books";

        System.out.println("\n--- Current Library Inventory ---");
        System.out.printf("%-5s %-25s %-20s %-10s\n", "ID", "Title", "Author", "Qty");
        System.out.println("------------------------------------------------------------");

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String author = rs.getString("author");
                int qty = rs.getInt("quantity");

                // Printing in a clean table format
                System.out.printf("%-5d %-25s %-20s %-10d\n", id, title, author, qty);
            }
        } catch (Exception e) {
            System.out.println("❌ Error fetching books: " + e.getMessage());
        }
    }
}