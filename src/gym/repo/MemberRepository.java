package gym.repo;

import gym.data.interfaces.IDB;
import gym.models.Member;
import gym.repo.interfaces.IMemberRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MemberRepository implements IMemberRepository {

    private final IDB db;

    public MemberRepository(IDB db) {
        this.db = db;
    }

    @Override
    public void addMember(String name, String type, int months, double price, boolean active) {
        String sql = "INSERT INTO members (full_name, type, months, price, archive) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement st = db.getConnection().prepareStatement(sql);
            st.setString(1, name);
            st.setString(2, type);
            st.setInt(3, months);
            st.setDouble(4, price);
            st.setBoolean(5, !active); // archive = false если active = true
            st.executeUpdate();
            st.close();
            System.out.println("Member added successfully");
        } catch (SQLException e) {
            System.out.println("Add member error: " + e.getMessage());
        }
    }

    @Override
    public List<Member> getAllMembers() {
        List<Member> members = new ArrayList<>();

        String sql = """
        SELECT 
            id,
            full_name AS name,
            type,
            months,
            price,
            archive
        FROM members
    """;

        try {
            Statement st = db.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                members.add(new Member(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("type"),
                        rs.getInt("months"),
                        rs.getDouble("price"),
                        !rs.getBoolean("archive")
                ));
            }

            rs.close();
            st.close();

        } catch (SQLException e) {
            System.out.println("Get members error: " + e.getMessage());
        }

        return members;
    }

    @Override
    public List<Member> getActiveMembers() {
        List<Member> members = new ArrayList<>();
        String sql = """
    SELECT 
        id,
        full_name AS name,
        type,
        months,
        price,
        archive
    FROM members
    WHERE archive = false
""";

        try {
            Statement st = db.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                members.add(new Member(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("type"),
                        rs.getInt("months"),
                        rs.getDouble("price"),
                        true
                ));
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            System.out.println("Get active members error: " + e.getMessage());
        }
        return members;
    }

    @Override
    public boolean deleteMember(int id) {
        String sql = "DELETE FROM members WHERE id = ?";
        try {
            PreparedStatement st = db.getConnection().prepareStatement(sql);
            st.setInt(1, id);
            int rows = st.executeUpdate();
            st.close();
            return rows > 0;
        } catch (SQLException e) {
            System.out.println("Delete member error: " + e.getMessage());
            return false;
        }
    }

    @Override
    public void assignTrainer(int memberId, int trainerId) {
        String sql = "UPDATE members SET trainer_id = ? WHERE id = ?";
        try {
            PreparedStatement st = db.getConnection().prepareStatement(sql);
            st.setInt(1, trainerId);
            st.setInt(2, memberId);
            st.executeUpdate();
            st.close();
            System.out.println("Trainer assigned successfully");
        } catch (SQLException e) {
            System.out.println("Assign trainer error: " + e.getMessage());
        }
    }
}