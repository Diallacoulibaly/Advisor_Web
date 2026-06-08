package main.java.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectBD {

    private static final String URL = "jdbc:mysql://localhost:3306/advisor";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "diallacoul";

    private static Connection connection;


    private ConnectBD() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("CONNEXION avec succès !");
        } catch (Exception e) {
            System.err.println("Erreur de connexion à la BDD : " + e.getMessage());
        }
    }

    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                new ConnectBD();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}