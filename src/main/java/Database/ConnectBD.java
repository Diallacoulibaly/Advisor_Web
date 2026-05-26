package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectBD {

    private static final String URL = "jdbc:mysql://localhost:3306/advisor";
    private static final String USER = "root";
    private static final String PASSWORD = "diallacoul";

    private static Connection connection = null; // instance unique

    private ConnectBD() {}

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (SQLException e) {
                System.err.println("Erreur de connexion à la bdd : " + e.getMessage());
                throw e;
            }
        }
        return connection;
    }
}

