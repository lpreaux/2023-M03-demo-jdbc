package fr.digi.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DbConnection implements AutoCloseable {
    private final Connection connection;

    public static DbConnection createDbConnection() throws SQLException {
        ResourceBundle databaseConf = ResourceBundle.getBundle("database");
        String dbUrl = databaseConf.getString("database.url");
        String dbUser = databaseConf.getString("database.user");
        String dbPassword = databaseConf.getString("database.password");
        return new DbConnection(dbUrl, dbUser, dbPassword);
    }

    private DbConnection(String url, String user, String password) throws SQLException {
        connection = DriverManager.getConnection(url, user, password);
    }


    @Override
    public void close() throws Exception {
        connection.close();
    }

    public Connection getconnection() {
        return connection;
    }
}
