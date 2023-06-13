package fr.digi.jdbc.dao;

import fr.digi.jdbc.entites.Article;
import fr.digi.jdbc.entites.Fournisseur;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArticleDaoJdbc implements ArticleDao {
    private final Connection connection;
    private final FournisseurDao fournisseurDao;

    public ArticleDaoJdbc(Connection connection,
                          FournisseurDao fournisseurDao) {
        this.connection = connection;
        this.fournisseurDao = fournisseurDao;
    }

    @Override
    public List<Article> extraire() throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM ARTICLE")) {
            try (ResultSet resultSet = statement.executeQuery()) {
                List<Article> articles = new ArrayList<>();
                while (resultSet.next()) {
                    Article article = new Article(
                            resultSet.getInt("ID"),
                            resultSet.getString("REF"),
                            resultSet.getString("DESIGNATION"),
                            resultSet.getDouble("PRIX"),
                            fournisseurDao.getById(resultSet.getInt("ID_FOU"))
                    );
                    articles.add(article);
                }
                return articles;
            }
        }
    }

    @Override
    public Article getById(int id) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM ARTICLE WHERE ID=?")) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Article(
                            resultSet.getInt("ID"),
                            resultSet.getString("REF"),
                            resultSet.getString("DESIGNATION"),
                            resultSet.getDouble("PRIX"),
                            fournisseurDao.getById(resultSet.getInt("ID_FOU"))
                    );
                } else {
                    throw new RuntimeException("Article not found");
                }
            }
        }
    }

    @Override
    public void insert(Article article) throws SQLException {
        String columnName = "insert_id";
        String[] columnNames = new String[] {columnName};
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO ARTICLE(REF, DESIGNATION, PRIX, ID_FOU) VALUES (?, ?, ?, ?)",
                columnNames
        )) {
            statement.setString(1, article.getRef());
            statement.setString(2, article.getDesignation());
            statement.setDouble(3, article.getPrix());
            statement.setDouble(4, article.getFournisseur().getID());
            int executedUpdate = statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (executedUpdate > 1) {
                throw new RuntimeException("Too much element where inserted : " + generatedKeys);
            } else if (executedUpdate == 1) {
                if(generatedKeys.next()) {
                    int id = generatedKeys.getInt(columnName);
                    article.setID(id);
                } else {
                    throw new RuntimeException("No generatedKey found");
                }
            } else {
                throw new RuntimeException("No row inserted");
            }
        }
    }

    @Override
    public int update(String sql, List<String> parameters) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            for (int i = 0; i < parameters.size(); i++) {
                String parameter = parameters.get(i);
                statement.setString(i+1, parameter);
            }
            return statement.executeUpdate();
        }
    }

    @Override
    public boolean delete(Article article) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM Article WHERE ID = ?")) {
            statement.setInt(1, article.getID());
            return statement.executeUpdate() > 0;
        }
    }

    @Override
    public boolean delete(List<Article> articles) throws SQLException {
        boolean result = true;
        for (Article article : articles) {
            result = result && delete(article);
        }
        return result;
    }

    @Override
    public List<Article> getByDesignationLike(String sqlLikeString) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM ARTICLE WHERE DESIGNATION LIKE ?")) {
            statement.setString(1, sqlLikeString);
            try (ResultSet resultSet = statement.executeQuery()) {
                List<Article> articles = new ArrayList<>();
                while (resultSet.next()) {
                    Article article = new Article(
                            resultSet.getInt("ID"),
                            resultSet.getString("REF"),
                            resultSet.getString("DESIGNATION"),
                            resultSet.getDouble("PRIX"),
                            fournisseurDao.getById(resultSet.getInt("ID_FOU"))
                    );
                    articles.add(article);
                }
                return articles;
            }
        }
    }

    @Override
    public List<Article> getByFournisseur(Fournisseur fournisseur) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM ARTICLE WHERE ID_FOU=?")) {
            statement.setInt(1, fournisseur.getID());
            try (ResultSet resultSet = statement.executeQuery()) {
                List<Article> articles = new ArrayList<>();
                while (resultSet.next()) {
                    Article article = new Article(
                            resultSet.getInt("ID"),
                            resultSet.getString("REF"),
                            resultSet.getString("DESIGNATION"),
                            resultSet.getDouble("PRIX"),
                            fournisseurDao.getById(resultSet.getInt("ID_FOU"))
                    );
                    articles.add(article);
                }
                return articles;
            }
        }
    }


    @Override
    public double getAveragePrice() throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("SELECT AVG(PRIX) as average FROM ARTICLE")) {
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getDouble("average");
                } else {
                    throw new RuntimeException("No Article found");
                }
            }
        }
    }
}
