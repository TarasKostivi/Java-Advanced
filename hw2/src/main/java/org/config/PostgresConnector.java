package org.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresConnector {
    private static final String URL = "jdbc:postgresql://localhost:5432/user_info";
    private static final String USER = "postgres";
    private static final String PWD = "1111";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PWD);
    }
}
