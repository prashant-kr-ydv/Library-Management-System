import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class ExportInventory {
    public static void main(String[] args) {
        String sql = "SELECT * FROM books";
        String fileName = "Library_Inventory_Report.txt";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql);
             FileWriter writer = new FileWriter(fileName)) {

            writer.write("==========================================\n");
            writer.write("       LIBRARY INVENTORY REPORT           \n");
            writer.write("==========================================\n");
            writer.write(String.format("%-5s %-25s %-20s %-5s\n", "ID", "Title", "Author", "Qty"));
            writer.write("------------------------------------------\n");

            while (rs.next()) {
                writer.write(String.format("%-5d %-25s %-20s %-5d\n",
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getInt("quantity")));
            }

            System.out.println("✅ Success: Inventory exported to " + fileName);

        } catch (Exception e) {
            System.out.println("❌ Export Error: " + e.getMessage());
        }
    }
}