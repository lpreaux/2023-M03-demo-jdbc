package fr.digi;

import fr.digi.jdbc.DbConnection;
import fr.digi.jdbc.dao.ArticleDao;
import fr.digi.jdbc.dao.ArticleDaoJdbc;
import fr.digi.jdbc.dao.FournisseurDao;
import fr.digi.jdbc.dao.FournisseurDaoJdbc;
import fr.digi.jdbc.entites.Article;
import fr.digi.jdbc.entites.Fournisseur;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class TestJdbcArticles {
    public static final Logger LOG = LoggerFactory.getLogger(TestJdbcArticles.class);
    public static void main(String[] args) {
        try(DbConnection dbConnection = DbConnection.createDbConnection()) {
            FournisseurDao fournisseurDao = new FournisseurDaoJdbc(dbConnection.getconnection());
            ArticleDao articleDao = new ArticleDaoJdbc(dbConnection.getconnection(), fournisseurDao);

            Fournisseur laMaisonDeLaPeinture = new Fournisseur("La Maison de la Peinture");

            fournisseurDao.insert(laMaisonDeLaPeinture);

            Article pb1L = new Article("PB1L", "Peinture blanche 1L", 12.5, laMaisonDeLaPeinture);
            Article prm1L = new Article("PRM1L", "Peinture rouge mate 1L", 17.8, laMaisonDeLaPeinture);
            Article pnl1L = new Article("PNL1L", "Peinture noire laquée 1L", 17.8, laMaisonDeLaPeinture);
            Article pbm1L = new Article("PBM1L", "Peinture bleue mate 1L", 15.5, laMaisonDeLaPeinture);

            articleDao.insert(pb1L);
            articleDao.insert(prm1L);
            articleDao.insert(pnl1L);
            articleDao.insert(pbm1L);


            int updated = articleDao.update("UPDATE ARTICLE SET PRIX = PRIX*0.75 WHERE DESIGNATION LIKE '%mate%'", new ArrayList<>());
            LOG.info("{} row updated", updated);

            for (Article article : articleDao.extraire()) {
                LOG.info("{}", article);
            }

            double average = articleDao.getAveragePrice();
            LOG.info("Moyenne prix des articles: {}", average);

            List<Article> articlesPeinture = articleDao.getByDesignationLike("%Peinture%");
            boolean deleted = articleDao.delete(articlesPeinture);
            LOG.info("Articles correctement supprimé : {}", deleted);

            List<Article> articlesLaMaisonDeLaPeinture = articleDao.getByFournisseur(laMaisonDeLaPeinture);
            articleDao.delete(articlesLaMaisonDeLaPeinture);
            fournisseurDao.delete(laMaisonDeLaPeinture);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }
}
