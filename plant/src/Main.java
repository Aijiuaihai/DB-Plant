import java.io.Console;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {

        System.out.println(
                "-----------------------------------登陆-----------------------------------\n" +
                        "name:");
        Scanner sc0 = new Scanner(System.in);
        String username = sc0.next();
        System.out.println("请密码:");
        sc0 = new Scanner(System.in);
        String pwd = sc0.next();
        String p1 = DAOFactory.getInstance().getuserDAO().getPwd(username);
        if (pwd.equals(p1)) {
            System.out.println(
                    "OK!!!!"
            );

        }
    }
}
