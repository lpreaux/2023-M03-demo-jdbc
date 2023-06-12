package fr.digi.jdbc;

import fr.digi.jdbc.entites.Fournisseur;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class TestSelect {
    public static void main(String[] args) {
        ResourceBundle databaseConf = ResourceBundle.getBundle("database");
        String dbUrl = databaseConf.getString("database.url");
        String dbUser = databaseConf.getString("database.user");
        String dbPassword = databaseConf.getString("database.password");
        List<Fournisseur> fournisseurs = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM FOURNISSEUR")) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Fournisseur fournisseur = new Fournisseur(
                            resultSet.getInt("ID"),
                            resultSet.getString("NOM")
                    );
                    fournisseurs.add(fournisseur);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        for (Fournisseur fournisseur : fournisseurs) {
            System.out.println(fournisseur);
        }
    }
}
