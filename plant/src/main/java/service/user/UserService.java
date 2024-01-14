package service.user;

import dao.user.UserDaoImpl;
import classdefine.user.User;

import java.util.ArrayList;
import java.util.Scanner;

public class UserService {

    private UserDaoImpl userDao;
    private Scanner scanner;

    public UserService( ) {
        userDao = new UserDaoImpl();
        scanner = new Scanner(System.in);
    }

    public boolean checkPassword(String username, String password) {
        User user = userDao.getUserByUsername(username);
        if (user == null) {
            return false;
        }
        return user.getPassword().equals(password);
    }
    public boolean isUserExisted(String username) {
        User user = userDao.getUserByUsername(username);
        return user != null;
    }

    public void addUser(String username, String password, String level,String email) {
        if (isUserExisted(username)) {
            System.out.println("已有重名用户,回车继续");
            scanner.nextLine();
        }else {
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            user.setUser_type(level);
            user.seteamil(email);
            userDao.addUser(user);
            System.out.println("添加成功(回车继续)");
            scanner.nextLine();
        }
    }
    public void service() {
        String message = """
                1.显示所有用户
                2.添加用户信息
                3.修改用户信息
                4.删除用户信息
                5.找回用户密码
                """;
        Scanner scanner = new Scanner(System.in);
        System.out.println(message);
        switch (scanner.nextLine()) {
            case "1":
                getAllUsers();
                break;
            case "2":
                addUser();
                break;
            case "3":
                modifyUser();
                break;
            case "4":
                deleteUser();
                break;
            case "5":
                findUser();
                break;
            default:
                System.out.println("输入有误。");
        }
    }
    public void service2() {
        String message = """
                1.显示所有用户
                """;
        Scanner scanner = new Scanner(System.in);
        System.out.println(message);
        switch (scanner.nextLine()) {
            case "1":
                getAllUsersNoadmin();
                break;
            default:
                System.out.println("输入有误。");
        }
    }

    private void getAllUsers(){
        ArrayList<User> users = (ArrayList<User>) userDao.getAllUsers();
        for (User u : users) {
            System.out.println(u);
        }
    }
    private void getAllUsersNoadmin(){
        ArrayList<User> users = (ArrayList<User>) userDao.getAllUsersNoadmin();
        for (User u : users) {
            System.out.println(u);
        }
    }
    private void addUser() {
        System.out.println("请输入用户名");
        String username = scanner.nextLine();
        System.out.println("请输入密码");
        String password = scanner.nextLine();
        System.out.println("""
                请选择用户级别
                1.养护人员
                2.监测人员
                3.上级主管部门""");
        String s = scanner.nextLine();
        System.out.println("输入用户邮箱");
        String emali = scanner.nextLine();
        String level = null;
        switch (s) {
            case "1":
                level = "careTaker";
                break;
            case "2":
                level = "monitor";
                break;
            case "3":
                level = "department";
                break;
            default:
                System.out.println("输入有误,退出本功能");
                return;

        }

        addUser(username, password, level,emali);

    }
    private void modifyUser(){
        System.out.println("请输入您想修改的用户的用户名");
        String username = scanner.nextLine();
        if (!isUserExisted(username)) {
            System.out.println("用户不存在");
            return;
        }

        User user = userDao.getUserByUsername(username);
        System.out.println("""
                请输入您想修改的项目
                1.用户名
                2.密码
                3.用户级别""");
        switch (scanner.nextLine()) {
            case "1":
                System.out.println("请输入新的用户名");
                String tmp = scanner.nextLine();
                if (isUserExisted(tmp)) {
                    System.out.println("您输入的用户名已被占用，退出本功能");
                    return;
                }
                user.setUsername(tmp);
                break;
            case "2":
                System.out.println("请输入新密码");
                user.setPassword(scanner.nextLine());
                break;
            case "3":

                System.out.println("""
                请选择用户新级别
                1.养护人员
                2.监测人员
                3.上级主管部门""");
                switch (scanner.nextLine()) {
                    case "1":
                        user.setUser_type("careTaker");
                        break;
                    case "2":
                        user.setUser_type("monitor");
                        break;
                    case "3":
                        user.setUser_type("department");
                        break;
                    default:
                        System.out.println("输入有误。退出本功能");
                }

                break;
        }
        userDao.updateUser(user);
    }

    private void deleteUser(){
        System.out.println("请输入您想删除的用户的ID");
        Integer id;
        try {
            id = Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("您的输入有误。");
            return;
        }

        if(userDao.deleteUser(id)){
            System.out.println("删除成功(回车继续)");
            scanner.nextLine();
        }else {
            System.out.println("删除失败");
        }
    }
    private void findUser(){
        System.out.println("请输入您想找回密码的邮箱");
        String email;
        try {
            email =  scanner.nextLine();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("您的输入有误。");
            return;
        }
        User user = userDao.getUserByemail(email);
        if(user!=null){
            System.out.println(user);
        }else {
            System.out.println("没有用户绑定该邮箱");
        }
    }
}
