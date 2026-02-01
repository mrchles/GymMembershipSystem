package gym.repo;

import gym.data.interfaces.IDB;
import gym.models.Role;
import gym.models.User;
import gym.repo.interfaces.IUserRepository;

import java.sql.*;

public class UserRepository implements IUserRepository {

    private final IDB db;

    public UserRepository(IDB db) {
        this.db = db;
    }

    @Override
    public User login(String username, String password) {
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";

        try (PreparedStatement st = db.getConnection().prepareStatement(sql)) {
            st.setString(1, username);
            st.setString(2, password);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                return new User(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        Role.valueOf(rs.getString("role")),
                        rs.getInt("member_id")
                );
            }
        } catch (SQLException e) {
            System.out.println("Login error: " + e.getMessage());
        }
        return null;
    }
}