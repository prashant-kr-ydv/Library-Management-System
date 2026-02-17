import java.sql.Connection;

public class Check {
    public static void main(String[] args) {
        System.out.println("Connecting...");
        
        // We are calling the static method from your new DBConnection class!
        try (Connection conn = DBConnection.getConnection()) {
            if (conn != null) {
                System.out.println("✅ SUCCESS: The DBConnection helper is working!");
            }
        } catch (Exception e) {
            System.out.println("❌ Error: Could not connect using the helper class.");
            e.printStackTrace();
        }
    }
}
  