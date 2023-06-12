package fr.digi.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class TestDelete {
    public static void main(String[] args) {
        ResourceBundle databaseConf = ResourceBundle.getBundle("database");
        String dbUrl = databaseConf.getString("database.url");
        String dbUser = databaseConf.getString("database.user");
        String dbPassword = databaseConf.getString("database.password");
        try (Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
             PreparedStatement statement = connection.prepareStatement("DELETE FROM FOURNISSEUR WHERE ID = ?")) {
            statement.setInt(1, 5);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
