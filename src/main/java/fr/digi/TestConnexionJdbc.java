package fr.digi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestConnexionJdbc {
    public static final String DB_URL = "jdbc:mariadb://localhost:3306/compta";
    public static final String DB_LOGIN = "root";
    public static final String DB_PASSWORD = "";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_LOGIN, DB_PASSWORD)) {
            System.out.println(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
