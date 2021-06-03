package bts;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresConnector {
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "1111";
    private static final String URL = "jdbc:postgresql://localhost:5432/shop";

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

}
