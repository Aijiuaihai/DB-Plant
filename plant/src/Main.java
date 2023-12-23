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
                "-----------------------------------教师登陆-----------------------------------\n" +
                        "请输入教师号:");
        Scanner sc0 = new Scanner(System.in);
        String username = sc0.next();
        System.out.println("请输入密码:");
        sc0 = new Scanner(System.in);
        String pwd = sc0.next();
        String p1 = DAOFactory.getInstance().getuserDAO().getPwd(username);
        if (pwd.equals(p1)) {
            System.out.println(
                    "-----------------------------------欢迎使用助教子系统-----------------------------------\n" +
                            "1.批阅研究生选课\n" +
                            "2.教师评价\n" +
                            "3.课程录入\n" +
                            "4.退出系统\n" +
                            "请输入您要选择的功能序号:"
            );

        }
    }
}
