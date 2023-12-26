package util;



import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;


public class MySQLConnectionPool {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/plantdb";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "123456";

    private static BasicDataSource dataSource;


    private MySQLConnectionPool() {

        dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl(JDBC_URL);
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(PASSWORD);

        dataSource.setInitialSize(20);
    }

    public static synchronized Connection getConnection() throws SQLException {
        if (dataSource == null) {
            new MySQLConnectionPool();
        }
        return dataSource.getConnection();
    }

    public static synchronized void close() {
        try{
            if (dataSource != null) {
                dataSource.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
