package com.cinema.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PgSqlConnectionFactory {
    private static final String URL = "jdbc:postgresql://localhost:5432/cinema_db";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "thirihtet15102004";
    private Connection connection;
    public Connection createConnection() throws SQLException {
        this.connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        return this.connection;
    }

    public void closeConnection() throws SQLException {
        this.connection.close();
    }
}
