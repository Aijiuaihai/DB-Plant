import dao.user.UserDaoImpl;
import classdefine.user.User;
import service.monitor.MonitorService;
import service.pestControl.PestControlService;
import service.user.UserService;
import service.classify.ClassifyService;
import service.info.InfoService;
import service.maintenancefun.MaintenanceService;



import java.util.Scanner;

public class Main {
    private static Scanner scanner;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);

        UserService service = new UserService();

        boolean keepLogin = true;
        while (keepLogin) {
            System.out.println("登录系统");
            System.out.println("请输入用户名:");
            String username = scanner.nextLine();
            System.out.println("请输入密码:");
            String password = scanner.nextLine();


            if (service.checkPassword(username, password)) {
                System.out.println("登录成功");
                keepLogin = false;
                mainService(username);
            } else {
                System.out.println("登录失败，请检查用户名、密码");
            }
        }


    }

    private static void mainService(String username) {
        UserDaoImpl userDao = new UserDaoImpl();
        User user = userDao.getUserByUsername(username);

        switch (user.getUser_type()) {
            case "admin" -> adminService();
            case "department" -> departmentService();
            case "careTaker" -> careTakerService();
            case "monitor" -> monitorService();
        }
    }

    private static void adminService() {
        String message = """
                        欢迎来到园林植物综合管理平台
                        本系统提供的功能有：
                        1.园林植物基本信息管理业务
                        2.园林植物分类管理
                        3.用户管理
                                    """;
        boolean keepInput = true;
        while (keepInput){
            System.out.println();
            System.out.println(message);
            System.out.println("请输入您想要的功能序号，例如1");
            System.out.println("输入 'exit' 退出系统");
            switch (scanner.nextLine()) {
                case "1" -> {
                    InfoService service = new InfoService();
                    service.service();
                }
                case "2" -> ClassifyService.service();
                case "3" -> {
                    UserService userService = new UserService();
                    userService.service();
                }
                case "exit" -> {
                    System.out.println("退出系统");
                    keepInput = false;
                }
                default -> System.out.println("输入序号错误，请您重新输入");
            }
        }
    }
    private static void departmentService() {
        String message = """
                        欢迎来到园林植物综合管理平台
                        本系统提供的功能有：
                        1.园林植物基本信息管理业务
                        2.园林植物分类管理
                        3.园林植物养护管理
                        4.园林植物监测管理
                        5.用户管理
                                    """;
        boolean keepInput = true;
        while (keepInput){
            System.out.println();
            System.out.println(message);
            System.out.println("请输入您想要的功能序号，例如1");
            System.out.println("输入 'exit' 退出系统");


            switch (scanner.nextLine()) {
                case "1" -> {
                    InfoService service = new InfoService();
                    service.service2();
                }
                case "2" -> ClassifyService.service2();
                case "3" -> MaintenanceService.service();
                case "4" -> {
                    MonitorService monitorService = new MonitorService();
                    monitorService.service();
                }
                case "5" -> {
                    UserService userService = new UserService();
                    userService.service2();
                }
                case "exit" -> {
                    System.out.println("退出系统");
                    keepInput = false;
                }
                default -> System.out.println("输入序号错误，请您重新输入");
            }

        }
    }
    private static void careTakerService() {
        String message = """
                        欢迎来到园林植物综合管理平台
                        本系统提供的功能有：
                        1.园林植物养护管理
                        2.病虫害防治管理
                                    """;
        boolean keepInput = true;
        while (keepInput){
            System.out.println();
            System.out.println(message);
            System.out.println("请输入您想要的功能序号，例如1");
            System.out.println("输入 'exit' 退出系统");
            switch (scanner.nextLine()) {
                case "1" -> MaintenanceService.service2();
                case "2" -> PestControlService.service();
                case "exit" -> {
                    System.out.println("退出系统");
                    keepInput = false;
                }
                default -> System.out.println("输入序号错误，请您重新输入");
            }
        }
    }
    private static void monitorService() {
        String message = """
                        欢迎来到园林植物综合管理平台
                        本系统提供的功能有：
                        1.园林植物监测管理
                                    """;
        boolean keepInput = true;
        while (keepInput){
            System.out.println();
            System.out.println(message);
            System.out.println("请输入您想要的功能序号，例如1");
            System.out.println("输入 'exit' 退出系统");
            switch (scanner.nextLine()) {
                case "1" -> {
                    MonitorService monitorService = new MonitorService();
                    monitorService.service();
                }
                case "exit" -> {
                    System.out.println("退出系统");
                    keepInput = false;
                }
                default -> System.out.println("输入序号错误，请您重新输入");
            }
        }
    }

}
