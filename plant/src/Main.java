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
        while (true) {
            System.out.println(
                    "-----------------------------------植物登陆-----------------------------------\n" +
                            "请输入用户名:");
            Scanner sc0 = new Scanner(System.in);
            String username = sc0.next();
            System.out.println("请输入密码:");
            sc0 = new Scanner(System.in);
            String pwd = sc0.nextLine().trim();  // 修剪掉首尾空格
            String p1 = DAOFactory.getInstance().getuserDAO().getPwd(username).trim();  // 修剪数据库中的密码
            if (pwd.equals(p1)) {
                System.out.println("登录成功");
                int p2 = DAOFactory.getInstance().getuserDAO().gettype(username);

                if (p2 == 1) {
                    System.out.println("您当前用户的身份为系统管理员");

                } else if (p2 == 2) {
                    System.out.println("您当前用户的身份为养护人员");

                } else if (p2 == 3) {
                    System.out.println("您当前用户的身份为检测人员");

                } else {
                    System.out.println("您当前用户的身份为上层管理");

                }
            } else {
                System.out.println("密码错误 ");
            }

        }
    }
}
