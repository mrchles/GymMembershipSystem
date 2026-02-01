    package gym.repo;

import gym.data.interfaces.IDB;
import gym.factory.MemberFactory;
import gym.models.SubCategory;
import gym.repo.interfaces.IMemberRepository;
import gym.models.Member;
import gym.models.Subscription;


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
        String sql = "INSERT INTO members(full_name, subscription_id, months, price, active) VALUES (?, ?, ?, ?, ?)";

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
        String sql = """
            SELECT m.id, m.full_name, m.months, m.price, m.active,
                   m.trainer_id,
                   t.name AS trainer_name,
                   t.specialization AS trainer_specialization,
                   s.id AS sub_id,
                   s.type AS sub_name,
                   s.price_per_month,
                   s.category AS sub_category
            FROM members m
            LEFT JOIN trainers t ON m.trainer_id = t.id
            JOIN subscriptions s ON m.subscription_id = s.id
        """;

        try (Statement st = db.getConnection().createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                members.add(MemberFactory.createMemberFromResultSet(rs));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return members;
    }


    @Override
    public List<Member> getActiveMembers() {
        List<Member> members = new ArrayList<>();
        String sql = """
            SELECT m.id, m.full_name, m.months, m.price, m.active,
                   m.trainer_id,
                   t.name AS trainer_name,
                   t.specialization AS trainer_specialization,
                   s.id AS sub_id,
                   s.type AS sub_name,
                   s.price_per_month,
                   s.category AS sub_category
            FROM members m
            LEFT JOIN trainers t ON m.trainer_id = t.id
            JOIN subscriptions s ON m.subscription_id = s.id
            WHERE m.active = true
        """;

        try (Statement st = db.getConnection().createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                members.add(MemberFactory.createMemberFromResultSet(rs));
            }

        } catch (SQLException e) {
            System.out.println( e.getMessage());
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

    @Override
    public Subscription getSubscriptionById(int id) {
        String sql = "SELECT id, type, price_per_month, category FROM subscriptions WHERE id = ?";
        try (PreparedStatement ps = db.getConnection().prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String catStr = rs.getString("category");
                SubCategory subCategory ;
                if ("base".equalsIgnoreCase(catStr)) subCategory = SubCategory.base;
                else if ("premium".equalsIgnoreCase(catStr)) subCategory = SubCategory.premium;
                else if ("elite".equalsIgnoreCase(catStr)) subCategory = SubCategory.elite;
                else subCategory = SubCategory.base;

                return new Subscription(
                        rs.getInt("id"),
                        rs.getString("type"),
                        rs.getInt("price_per_month"),
                        subCategory
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Subscription> getAllSubscriptions() {
        List<Subscription> subscriptions = new ArrayList<>();
        String sql = "SELECT id, type, price_per_month, category FROM subscriptions";

        try (Statement st = db.getConnection().createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                String catStr = rs.getString("category");
                SubCategory subCategory;
                if ("base".equalsIgnoreCase(catStr)) subCategory = SubCategory.base;
                else if ("premium".equalsIgnoreCase(catStr)) subCategory = SubCategory.premium;
                else if ("elite".equalsIgnoreCase(catStr)) subCategory = SubCategory.elite;
                else subCategory = SubCategory.base;

                Subscription sub = new Subscription(
                        rs.getInt("id"),
                        rs.getString("type"),
                        rs.getInt("price_per_month"),
                        subCategory
                );

                subscriptions.add(sub);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return subscriptions;
    }
    @Override
    public Member getMemberById(int id) {
        String sql = """
            SELECT m.id, m.full_name, m.months, m.price, m.active,
                   m.trainer_id,
                   t.name AS trainer_name,
                   t.specialization AS trainer_specialization,
                   s.id AS sub_id,
                   s.type AS sub_name,
                   s.price_per_month,
                   s.category AS sub_category
            FROM members m
            LEFT JOIN trainers t ON m.trainer_id = t.id
            JOIN subscriptions s ON m.subscription_id = s.id
            WHERE m.id = ?
        """;
        try (PreparedStatement ps = db.getConnection().prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return MemberFactory.createMemberFromResultSet(rs);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Member> findMembersByName(String name) {
        List<Member> members = new ArrayList<>();
        String sql = """
            SELECT m.id, m.full_name, m.months, m.price, m.active,
                   m.trainer_id,
                   t.name AS trainer_name,
                   t.specialization AS trainer_specialization,
                   s.id AS sub_id,
                   s.type AS sub_name,
                   s.price_per_month,
                   s.category AS sub_category
            FROM members m
            LEFT JOIN trainers t ON m.trainer_id = t.id
            JOIN subscriptions s ON m.subscription_id = s.id
            WHERE LOWER(m.full_name) LIKE LOWER(?)
        """;

        try (PreparedStatement ps = db.getConnection().prepareStatement(sql)) {
            ps.setString(1, "%" + name + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                members.add(MemberFactory.createMemberFromResultSet(rs));
            }
        } catch (SQLException e) {
            System.out.println( e.getMessage());
        }
        return members;
    }
}



