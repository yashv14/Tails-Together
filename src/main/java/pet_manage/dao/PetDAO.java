package pet_manage.dao;



import pet_manage.model.Pet;
import pet_manage.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PetDAO {

    // Insert new pet
    private static final String INSERT_SQL =
            "INSERT INTO pets (owner_name, pet_name, species, breed) VALUES (?, ?, ?, ?)";

    // Select all pets
    private static final String SELECT_ALL =
            "SELECT id, owner_name, pet_name, species, breed FROM pets ORDER BY id DESC";

    // Select pet by ID
    private static final String SELECT_BY_ID =
            "SELECT id, owner_name, pet_name, species, breed FROM pets WHERE id = ?";

    // Update pet
    private static final String UPDATE_SQL =
            "UPDATE pets SET owner_name=?, pet_name=?, species=?, breed=? WHERE id=?";

    // Delete pet
    private static final String DELETE_SQL =
            "DELETE FROM pets WHERE id = ?";


    // ---------------------------
    // 1. Insert a new Pet
    // ---------------------------
    public int save(Pet pet) throws SQLException {
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, pet.getOwnerName());
            ps.setString(2, pet.getPetName());
            ps.setString(3, pet.getSpecies());
            ps.setString(4, pet.getBreed());

            int rows = ps.executeUpdate();

            if (rows == 0) return -1;

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) return rs.getInt(1); // return generated ID
            }
            return -1;
        }
    }


    // ---------------------------
    // 2. Get all Pets
    // ---------------------------
    public List<Pet> findAll() throws SQLException {
        List<Pet> list = new ArrayList<>();

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_ALL);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Pet p = new Pet();
                p.setId(rs.getInt("id"));
                p.setOwnerName(rs.getString("owner_name"));
                p.setPetName(rs.getString("pet_name"));
                p.setSpecies(rs.getString("species"));
                p.setBreed(rs.getString("breed"));
                list.add(p);
            }
        }
        return list;
    }


    // ---------------------------
    // 3. Get pet by ID
    // ---------------------------
    public Pet findById(int id) throws SQLException {
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_BY_ID)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    Pet p = new Pet();
                    p.setId(rs.getInt("id"));
                    p.setOwnerName(rs.getString("owner_name"));
                    p.setPetName(rs.getString("pet_name"));
                    p.setSpecies(rs.getString("species"));
                    p.setBreed(rs.getString("breed"));
                    return p;
                }
            }
        }
        return null;
    }


    // ---------------------------
    // 4. Update pet
    // ---------------------------
    public boolean update(Pet pet) throws SQLException {
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(UPDATE_SQL)) {

            ps.setString(1, pet.getOwnerName());
            ps.setString(2, pet.getPetName());
            ps.setString(3, pet.getSpecies());
            ps.setString(4, pet.getBreed());
            ps.setInt(5, pet.getId());

            return ps.executeUpdate() > 0;
        }
    }


    // ---------------------------
    // 5. Delete pet by ID
    // ---------------------------
    public boolean delete(int id) throws SQLException {
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(DELETE_SQL)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }
}
