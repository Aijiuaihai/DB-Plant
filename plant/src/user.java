import java.io.Console;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class user {
    private String username;
    private String password;
    private int usertype;

    public void login(){
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("请输入你的用户名");
            username = scanner.nextLine();
            // 使用 Console 类隐藏密码输入
            Console console = System.console();
            if (console != null) {
                char[] passwordArray = console.readPassword("请输入你的密码: ");
                password = new String(passwordArray);
                // 清除密码数组
                java.util.Arrays.fill(passwordArray, ' ');
            } else {
                System.out.println("无法使用 Console,密码输入可见,请注意防护。");
                System.out.println("请输入你的密码");
                password = scanner.nextLine();
            }
        } catch (NoSuchElementException e) {
            System.out.println("用户取消输入。");
            return;
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }
}
