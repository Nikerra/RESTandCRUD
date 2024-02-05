package org.example.dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresqlDB implements AutoCloseable {
    private static final String URL_MEM = "jdbc:postgresql://localhost:8082/postgres";
    private static final String USER = "postgres";
    public static final String PASSWD = "123456";
    private static Connection connection;

    public static Connection getConnection() throws SQLException {
        connection = connection == null ? DriverManager.getConnection(URL_MEM, USER, PASSWD) : connection;
        return connection;
    }

    @Override
    public void close() throws Exception {
        if (connection == null) {
            return;
        } else {
            connection.close();
        }
    }
}
