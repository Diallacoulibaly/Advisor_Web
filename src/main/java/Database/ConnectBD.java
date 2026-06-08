package main.java.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectBD {

    private static final String URL = "jdbc:mysql://localhost:3306/advisor";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "diallacoul";

    private static final String USER = "root";
    private static final String PASSWORD = "1234";
 main

    public static Connection getConnection() {

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn =
                    DriverManager.getConnection(URL, USERNAME, PASSWORD);

            System.out.println("CONNEXION avec succes");

            return conn;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}