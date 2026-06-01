package pet_manage.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

    private static final String DB_HOST = System.getenv("DB_HOST") != null ? System.getenv("DB_HOST") : "localhost";
    
    // 1. URL with the fix for "Public Key Retrieval"
    private static final String URL = "jdbc:mysql://" + DB_HOST + ":3306/pet_clinic?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    
    private static final String USER = System.getenv("DB_USER") != null ? System.getenv("DB_USER") : "root";
    
    // 2. Password is an empty string because you have no password
    private static final String PASSWORD = System.getenv("DB_PASSWORD") != null ? System.getenv("DB_PASSWORD") : ""; 

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException("MySQL JDBC driver not found on classpath", ex);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}