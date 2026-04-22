package pet_manage.dao;

import pet_manage.model.Review;
import pet_manage.util.DBUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReviewDAO {

    // 1. Add a new review
    public boolean addReview(Review r) {
        String sql = "INSERT INTO reviews (username, rating, comment) VALUES (?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, r.getUsername());
            ps.setInt(2, r.getRating());
            ps.setString(3, r.getComment());
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 2. Get all reviews (Newest first)
    public List<Review> getAllReviews() {
        List<Review> list = new ArrayList<>();
        String sql = "SELECT * FROM reviews ORDER BY review_date DESC";
        
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                Review r = new Review();
                r.setId(rs.getInt("id"));
                r.setUsername(rs.getString("username"));
                r.setRating(rs.getInt("rating"));
                r.setComment(rs.getString("comment"));
                r.setReviewDate(rs.getTimestamp("review_date"));
                list.add(r);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}