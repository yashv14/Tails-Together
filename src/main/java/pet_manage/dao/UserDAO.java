package pet_manage.dao;

import pet_manage.util.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    public boolean validate(String username, String password) {
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next(); // If a row is found, login is valid
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
  //Add this method inside your existing UserDAO class
    public boolean registerUser(String username, String password) {
     String sql = "INSERT INTO users (username, password) VALUES (?, ?)";
     
     try (Connection conn = DBUtil.getConnection();
          PreparedStatement pstmt = conn.prepareStatement(sql)) {
         
         pstmt.setString(1, username);
         pstmt.setString(2, password);
         
         int rows = pstmt.executeUpdate();
         return rows > 0; // Return true if insert was successful
         
     } catch (SQLException e) {
         e.printStackTrace();
         return false; // Returns false if username already exists (duplicate)
     }
    }
    
}

