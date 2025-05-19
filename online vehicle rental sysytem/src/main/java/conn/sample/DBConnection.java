package conn.sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/linga";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "1234";

    // Static method to establish and return a database connection
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");  // Load MySQL JDBC driver
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }
}
