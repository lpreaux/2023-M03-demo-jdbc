package fr.digi.jdbc.dao;

import fr.digi.jdbc.entites.Fournisseur;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FournisseurDaoJdbc implements FournisseurDao {
    private final Connection connection;

    public FournisseurDaoJdbc(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Fournisseur> extraire() throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM FOURNISSEUR")) {
            try (ResultSet resultSet = statement.executeQuery()) {
                List<Fournisseur> fournisseurs = new ArrayList<>();
                while (resultSet.next()) {
                    Fournisseur fournisseur = new Fournisseur(
                            resultSet.getInt("ID"),
                            resultSet.getString("NOM")
                    );
                    fournisseurs.add(fournisseur);
                }
                return fournisseurs;
            }
        }
    }

    @Override
    public Fournisseur getById(int id) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM FOURNISSEUR WHERE ID=?")) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Fournisseur(
                            resultSet.getInt("ID"),
                            resultSet.getString("NOM")
                    );
                } else {
                    throw new RuntimeException("Fournisseur not found");
                }
            }
        }
    }

    @Override
    public void insert(Fournisseur fournisseur) throws SQLException {
        String columnName = "insert_id";
        String[] columnNames = new String[] {columnName};
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO FOURNISSEUR(NOM) VALUES (?)", columnNames)) {
            statement.setString(1, fournisseur.getNom());
            int executedUpdate = statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (executedUpdate > 1) {
                throw new RuntimeException("Too much element where inserted : " + generatedKeys);
            } else if (executedUpdate == 1) {
                if(generatedKeys.next()) {
                    int id = generatedKeys.getInt(columnName);
                    fournisseur.setID(id);
                } else {
                    throw new RuntimeException("No generatedKey found");
                }
            } else {
                throw new RuntimeException("No row inserted");
            }
        }
    }

    @Override
    public int update(String ancienNom, String nouveauNom) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("UPDATE FOURNISSEUR SET NOM = ? WHERE NOM = ?")) {
            statement.setString(1, nouveauNom);
            statement.setString(2, ancienNom);
            return statement.executeUpdate();
        }
    }

    @Override
    public boolean delete(Fournisseur fournisseur) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM FOURNISSEUR WHERE ID = ?")) {
            statement.setInt(1, fournisseur.getID());
            return statement.executeUpdate() > 0;
        }
    }
}
