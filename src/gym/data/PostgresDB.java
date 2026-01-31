package gym.data;

import gym.data.interfaces.IDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresDB implements IDB {

    private Connection connection;

    public PostgresDB(String host, String dbName, String user, String password) {
        try {
            String url = host + "/" + dbName;
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("PostgreSQL connected");
        } catch (SQLException e) {
            System.out.println("DB error: " + e.getMessage());
        }
    }

    @Override
    public Connection getConnection() {
        return connection;
    }

    @Override
    public void close() {
        try {
            if (connection != null) connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}