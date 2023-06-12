package fr.digi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class TestConnexionJdbc {
    public static void main(String[] args) {
        ResourceBundle databaseConf = ResourceBundle.getBundle("database");
        String dbUrl = databaseConf.getString("database.url");
        String dbUser = databaseConf.getString("database.user");
        String dbPassword = databaseConf.getString("database.password");
        try (Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword)) {
            System.out.println(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
