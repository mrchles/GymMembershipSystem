    package gym.repo;

import gym.data.interfaces.IDB;
import gym.repo.interfaces.IMemberRepository;
import gym.models.Member;
import gym.models.Subscription;
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
    public void addMember(String name, int subscriptionId, int months, double price, boolean active) {
        String sql = """
                INSERT INTO members(full_name, subscription_id, months, price, active)
                VALUES (?, ?, ?, ?, ?)
                """;

        try (PreparedStatement ps = db.getConnection().prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setInt(2, subscriptionId);
            ps.setInt(3, months);
            ps.setDouble(4, price);
            ps.setBoolean(5, active);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Member> getAllMembers() {
        List<Member> members = new ArrayList<>();
        String sql = "SELECT * FROM members";

        try (Statement st = db.getConnection().createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Subscription sub = getSubscriptionById(rs.getInt("subscription_id"));

                members.add(new Member(
                        rs.getInt("id"),
                        rs.getString("full_name"),
                        rs.getInt("months"),
                        rs.getDouble("price"),
                        rs.getInt("trainer_id"),
                        rs.getBoolean("active"),
                        sub
                ));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return members;
    }

    @Override
    public List<Member> getActiveMembers() {
        List<Member> members = new ArrayList<>();
        String sql = "SELECT * FROM members WHERE active = true";

        try (Statement st = db.getConnection().createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Subscription sub = getSubscriptionById(rs.getInt("subscription_id"));

                members.add(new Member(
                        rs.getInt("id"),
                        rs.getString("full_name"),
                        rs.getInt("months"),
                        rs.getDouble("price"),
                        rs.getInt("trainer_id"),
                        rs.getBoolean("active"),
                        sub
                ));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return members;
    }

    @Override
    public boolean deleteMember(int id) {
        String sql = "DELETE FROM members WHERE id = ?";
        try (PreparedStatement ps = db.getConnection().prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void assignTrainer(int memberId, int trainerId) {
        String sql = "UPDATE members SET trainer_id = ? WHERE id = ?";
        try (PreparedStatement ps = db.getConnection().prepareStatement(sql)) {
            ps.setInt(1, trainerId);
            ps.setInt(2, memberId);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Subscription getSubscriptionById(int id) {
        String sql = "SELECT * FROM subscriptions WHERE id = ?";
        try (PreparedStatement ps = db.getConnection().prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Subscription(
                        rs.getInt("id"),
                        rs.getString("type"),
                        rs.getInt("price_per_month")
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
