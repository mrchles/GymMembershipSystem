package gym.repositories;

import gym.data.interfaces.IDB;
import gym.models.Member;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MemberRepository {

    private final IDB db;

    public MemberRepository(IDB db) {
        this.db = db;
        createTable();
    }

    private void createTable() {
        String sql = """
            CREATE TABLE IF NOT EXISTS members (
                id SERIAL PRIMARY KEY,
                full_name VARCHAR(100),
                type VARCHAR(20),
                months INT,
                price DOUBLE PRECISION,
                trainer_id INT REFERENCES trainers(id) NULL
            )
        """;

        try (Statement st = db.getConnection().createStatement()) {
            st.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addMember(String name, String type, int months) {
        double price = type.equalsIgnoreCase("PREMIUM") ? 15000 * months : 10000 * months;

        // trainer_id = NULL при добавлении нового участника
        String sql = "INSERT INTO members(full_name, type, months, price, trainer_id) VALUES (?, ?, ?, ?, NULL)";

        try (PreparedStatement st = db.getConnection().prepareStatement(sql)) {
            st.setString(1, name);
            st.setString(2, type);
            st.setInt(3, months);
            st.setDouble(4, price);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Member> getAllMembers() {
        List<Member> members = new ArrayList<>();
        String sql = "SELECT * FROM members";

        try (Statement st = db.getConnection().createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                // trainer_id может быть NULL, поэтому rs.getInt вернёт 0, если null
                Integer trainerId = rs.getObject("trainer_id") != null ? rs.getInt("trainer_id") : null;

                members.add(new Member(
                        rs.getInt("id"),
                        rs.getString("full_name"),
                        rs.getString("type"),
                        rs.getInt("months"),
                        rs.getDouble("price"),
                        trainerId
                ));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return members;
    }

    public void assignTrainer(int memberId, int trainerId) {
        String sql = "UPDATE members SET trainer_id=? WHERE id=?";
        try (PreparedStatement ps = db.getConnection().prepareStatement(sql)) {
            ps.setInt(1, trainerId);
            ps.setInt(2, memberId);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
