import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    // These are your verified connection details
    private static final String URL = "jdbc:mysql://localhost:3306/LibraryDB";
    private static final String USER = "root";
    private static final String PASSWORD = "Prashant10@"; // Use the password that worked in Check.java

    public static Connection getConnection() throws SQLException {
        try {
            // This loads the driver you added to your lib folder
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.out.println("❌ Driver not found!");
            e.printStackTrace();
            return null;
        }
    }
}