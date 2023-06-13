package fr.digi.jdbc.dao;

import fr.digi.jdbc.entites.Fournisseur;

import java.sql.SQLException;
import java.util.List;

public interface FournisseurDao {
    List<Fournisseur> extraire() throws SQLException;

    Fournisseur getById(int id) throws SQLException;

    void insert(Fournisseur fournisseur) throws SQLException;
    int update(String ancienNom, String nouveauNom) throws SQLException;
    boolean delete(Fournisseur fournisseur) throws SQLException;
}
