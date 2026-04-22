package pet_manage.dao;

import pet_manage.model.Appointment;
import pet_manage.util.DBUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDAO {

    // 1. SAVE (Create)
    public int save(Appointment a) throws SQLException {
        String sql = "INSERT INTO appointments (owner_name, pet_name, service, appt_date, appt_time, notes, username, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, a.getOwnerName());
            ps.setString(2, a.getPetName());
            ps.setString(3, a.getService());
            if (a.getApptDate() != null) ps.setDate(4, Date.valueOf(a.getApptDate())); else ps.setNull(4, Types.DATE);
            if (a.getApptTime() != null) ps.setTime(5, Time.valueOf(a.getApptTime())); else ps.setNull(5, Types.TIME);
            ps.setString(6, a.getNotes());
            ps.setString(7, a.getUsername());
            ps.setString(8, "Pending"); // Default status
            return ps.executeUpdate();
        }
    }

    // 2. GET ALL (For Admin - Fixes Status Issue)
    public List<Appointment> getAllAppointments() {
        List<Appointment> list = new ArrayList<>();
        String sql = "SELECT * FROM appointments ORDER BY CASE WHEN status = 'Pending' THEN 1 ELSE 2 END, appt_date";
        return executeQuery(sql); // Helper method below
    }

    // 3. SEARCH (For Admin - Fixes Search Issue)
    public List<Appointment> searchAppointments(String query) {
        List<Appointment> list = new ArrayList<>();
        String sql = "SELECT * FROM appointments WHERE owner_name LIKE ? OR pet_name LIKE ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            String pattern = "%" + query + "%";
            ps.setString(1, pattern);
            ps.setString(2, pattern);
            
            // Extract Logic
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(mapRow(rs));
                }
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    // 4. UPDATE STATUS (Fixes Confirmation Button)
    public void updateStatus(int id, String newStatus) {
        String sql = "UPDATE appointments SET status = ? WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, newStatus);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    // 5. DELETE
    public void deleteAppointment(int id) {
        String sql = "DELETE FROM appointments WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    // --- HELPER METHODS ---
    
    // Helper to run simple queries
    private List<Appointment> executeQuery(String sql) {
        List<Appointment> list = new ArrayList<>();
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(mapRow(rs));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    // Helper to map Database Row to Java Object
    private Appointment mapRow(ResultSet rs) throws SQLException {
        Appointment a = new Appointment();
        a.setId(rs.getInt("id"));
        a.setOwnerName(rs.getString("owner_name"));
        a.setPetName(rs.getString("pet_name"));
        a.setService(rs.getString("service"));
        a.setNotes(rs.getString("notes"));
        a.setUsername(rs.getString("username"));
        
        // *** THIS IS THE FIX FOR YOUR STATUS ISSUE ***
        a.setStatus(rs.getString("status")); 
        
        Date d = rs.getDate("appt_date");
        if (d != null) a.setApptDate(d.toLocalDate());
        Time t = rs.getTime("appt_time");
        if (t != null) a.setApptTime(t.toLocalTime());
        return a;
    }
    
    // Stats Methods
    public int getPendingCount() { return getCount("SELECT COUNT(*) FROM appointments WHERE status = 'Pending'"); }
    public int getTotalCount() { return getCount("SELECT COUNT(*) FROM appointments"); }
    public int getTodayCount() { return getCount("SELECT COUNT(*) FROM appointments WHERE appt_date = CURDATE()"); }
    
    private int getCount(String sql) {
        try (Connection conn = DBUtil.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) return rs.getInt(1);
        } catch (SQLException e) { e.printStackTrace(); }
        return 0;
    }
    
    // Get By User (for User History)
    public List<Appointment> getAppointmentsByUser(String username) {
        List<Appointment> list = new ArrayList<>();
        String sql = "SELECT * FROM appointments WHERE username = ? ORDER BY appt_date DESC";
        try (Connection conn = DBUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                while(rs.next()) list.add(mapRow(rs));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }
}