package main.java.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectBD {

    private static String URL="jdbc:mysql://localhost:3306/advisor";
    private static String USER="root";
<<<<<<< HEAD
    private static String PASSWORD="root";
    private static Connection getConnection () throws SQLException {
=======
    private static String PASSWORD="diallacoul";
    public static Connection getConnection () throws SQLException {
>>>>>>> 319fee77cf63aa6aee888a3984e329849e3e3b1b
        return DriverManager.getConnection(URL,USER,PASSWORD);
    }
}




