package gym.repo;

import gym.data.interfaces.IDB;
import gym.models.Trainers;
import gym.repo.interfaces.ITrainerRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TrainerRepository implements ITrainerRepository {

    private final IDB db;

    public TrainerRepository(IDB db) {
        this.db = db;
    }

    @Override
    public List<Trainers> getAllTrainers() {
        List<Trainers> trainers = new ArrayList<>();
        String sql = "SELECT * FROM trainers";
        try (Statement st = db.getConnection().createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                trainers.add(new Trainers(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("specialization")
                ));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return trainers;
    }

    @Override
    public void addTrainer(String name, String specialization) {
        String sql = "INSERT INTO trainers (name, specialization) VALUES (?,?)";
        try (PreparedStatement st = db.getConnection().prepareStatement(sql)) {
            st.setString(1, name);
            st.setString(2, specialization);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}