package fr.digi;

import fr.digi.jdbc.DbConnection;
import fr.digi.jdbc.dao.FournisseurDao;
import fr.digi.jdbc.dao.FournisseurDaoJdbc;
import fr.digi.jdbc.entites.Fournisseur;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestDaoJdbc {
    public static final Logger LOG = LoggerFactory.getLogger(TestDaoJdbc.class);

    public static void main(String[] args) {
        try (DbConnection dbConnection = DbConnection.createDbConnection()) {
            FournisseurDao fournisseurDao = new FournisseurDaoJdbc(dbConnection.getconnection());
            for (Fournisseur fournisseur : fournisseurDao.extraire()) {
                LOG.info("{}", fournisseur);
            }

            Fournisseur diginamic = new Fournisseur("Diginamic");
            fournisseurDao.insert(diginamic);
            LOG.info("1 ligne insérée");

            for (Fournisseur fournisseur : fournisseurDao.extraire()) {
                LOG.info("{}", fournisseur);
            }

            int updated = fournisseurDao.update("Diginamic", "diginamic");
            LOG.info("{} ligne modifiée(s)", updated);

            for (Fournisseur fournisseur : fournisseurDao.extraire()) {
                LOG.info("{}", fournisseur);
            }

            boolean deleted = fournisseurDao.delete(diginamic);
            if (deleted) LOG.info("1 ligne correctement supprimée");
            else LOG.info("La ligne n'a pas pu être supprimée");

            for (Fournisseur fournisseur : fournisseurDao.extraire()) {
                LOG.info("{}", fournisseur);
            }
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }
}
