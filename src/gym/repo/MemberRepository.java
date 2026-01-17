package gym.repo;

import gym.data.interfaces.IDB;
import gym.repo.interfaces.IMemberRepository;

import java.sql.*;

public class MemberRepository implements IMemberRepository {

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
                price DOUBLE PRECISION
                active VARCHAR(20)
            )
        """;

        try (Statement st = db.getConnection().createStatement()) {
            st.execute(sql);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void addMember(String name, String type, int months,Boolean active) {
        double price;

        if (type.equalsIgnoreCase("PREMIUM")) {
            price = 15000 * months;
        } else {
            price = 10000 * months;
        }

        String sql = "INSERT INTO members(full_name, type, months, price,active) VALUES (?, ?, ?, ?,?)";

        try (PreparedStatement st = db.getConnection().prepareStatement(sql)) {
            st.setString(1, name);
            st.setString(2, type);
            st.setInt(3, months);
            st.setDouble(4, price);
            st.setBoolean(5,active);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void showAll() {
        String sql = "SELECT * FROM members";

        try (Statement st = db.getConnection().createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                System.out.println(
                        rs.getInt("id") + ". " +
                                rs.getString("full_name") +
                                " | " + rs.getString("type") +
                                " | months: " + rs.getInt("months") +
                                " | price: " + rs.getDouble("price")
                );
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    @Override
    public void showActiveMemberships() {
        String sql = "SELECT * FROM members WHERE active = 'true'";

        try (Statement st = db.getConnection().createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            System.out.println("active members:");
            while (rs.next()) {
                System.out.println(
                        rs.getInt("id") + ". " +
                                rs.getString("full_name") +
                                " | " + rs.getString("type") +
                                " | months: " + rs.getInt("months") +
                                " | price: " + rs.getDouble("price")
                );
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


}
