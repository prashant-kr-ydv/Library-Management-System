import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Login {
    public static boolean authenticate() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Librarian Login ===");
        System.out.print("Username: ");
        String user = scanner.nextLine();
        System.out.print("Password: ");
        String pass = scanner.nextLine();

        String sql = "SELECT * FROM admins WHERE username = ? AND password = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, user);
            pstmt.setString(2, pass);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                System.out.println("✅ Login Successful! Welcome, " + user + ".");
                return true;
            } else {
                System.out.println("❌ Invalid username or password.");
                return false;
            }
        } catch (Exception e) {
            System.out.println("❌ Login Error: " + e.getMessage());
            return false;
        }
    }
}