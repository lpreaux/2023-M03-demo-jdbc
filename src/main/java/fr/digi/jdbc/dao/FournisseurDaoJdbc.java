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
    public List<Fournisseur> extraire() {
        List<Fournisseur> fournisseurs = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM FOURNISSEUR")) {
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
        return fournisseurs;
    }

    @Override
    public void insert(Fournisseur fournisseur) {
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO FOURNISSEUR(NOM) VALUES (?)")) {
            statement.setString(1, fournisseur.getNom());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int update(String ancienNom, String nouveauNom) {
        int result;
        try (PreparedStatement statement = connection.prepareStatement("UPDATE FOURNISSEUR SET NOM = ? WHERE NOM = ?")) {
            statement.setString(1, nouveauNom);
            statement.setString(2, ancienNom);
            result = statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public boolean delete(Fournisseur fournisseur) {
        boolean result;
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM FOURNISSEUR WHERE ID = ?")) {
            statement.setInt(1, fournisseur.getID());
            result = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
