package fr.digi.jdbc.dao;

import fr.digi.jdbc.entites.Article;
import fr.digi.jdbc.entites.Fournisseur;

import java.sql.SQLException;
import java.util.List;

public interface ArticleDao {
    List<Article> extraire() throws SQLException;

    Article getById(int id) throws SQLException;

    void insert(Article Article) throws SQLException;
    int update(String sql, List<String> parameters) throws SQLException;
    boolean delete(Article Article) throws SQLException;

    boolean delete(List<Article> articles) throws SQLException;

    List<Article> getByDesignationLike(String sqlLikeString) throws SQLException;

    List<Article> getByFournisseur(Fournisseur fournisseur) throws SQLException;

    double getAveragePrice() throws SQLException;
}
