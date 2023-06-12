package fr.digi.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class TestUpdate {
    public static void main(String[] args) {
        ResourceBundle databaseConf = ResourceBundle.getBundle("database");
        String dbUrl = databaseConf.getString("database.url");
        String dbUser = databaseConf.getString("database.user");
        String dbPassword = databaseConf.getString("database.password");
        try (Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
             PreparedStatement statement = connection.prepareStatement("UPDATE FOURNISSEUR SET NOM = ? WHERE ID = ?")) {
            statement.setString(1, "La Maison des Peintures");
            statement.setInt(2, 4);
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
