package gym.repositories;

import gym.data.interfaces.IDB;
import gym.models.Trainer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TrainerRepository {

    private final IDB db;

    public TrainerRepository(IDB db) {
        this.db = db;
    }

    public List<Trainer> getAllTrainers() {
        List<Trainer> trainers = new ArrayList<>();
        String sql = "SELECT * FROM trainers";

        try (Statement st = db.getConnection().createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                trainers.add(new Trainer(
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
}
