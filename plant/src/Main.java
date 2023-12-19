import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.io.Console;

public class Main {
    public static void loginsql() {
        System.out.println("欢迎使用植物信息管理系统");
        String url = "jdbc:sqlserver://localhost:18546;databaseName=plant;encrypt=false";
        String sqlusername= "msy";
        String sqlpassword= "123456";
        try (Connection connection = DriverManager.getConnection(url, sqlusername, sqlpassword)) {
            // 连接成功，可以进行后续操作
            System.out.println("数据库连接成功");
        } catch (SQLException e) {
            System.out.println("数据库连接失败: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        loginsql();
    }
}
