package pl.iseebugs.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {

    private static String URL = "jdbc:postgresql://localhost:5432/jdbcframe";
    private static String USER = "postgres";
    private static String PASSWORD = "123";

    public static Connection connect(){

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL,USER, PASSWORD);
            System.out.println("Connected with database");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }
}
