package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectBD {

    private static String URL="jdbc:mysql://localhost:3306/advisor";
    private static String USER="root";
    private static String PASSWORD="root";
    private static Connection getConnection () throws SQLException {
        return DriverManager.getConnection(URL,USER,PASSWORD);
    }
}




