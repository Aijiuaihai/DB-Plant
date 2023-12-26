package service.maintenancefun;

import dao.maintenancedao.MaintenanceDaoImpI;
import entity.maintenancefun.MaintenanceTask;


import java.util.Date;
import java.util.Scanner;

public class MaintenanceService {
    public static final String welcome = "欢迎来到 园林植物养护管理 模块";
    public static final String options = """
            1.增加植物养护信息
            2.删除植物养护信息
            3.修改植物养护信息
            4.查询植物养护信息
            """;

    public static void service() {
        Scanner scanner = new Scanner(System.in);

        System.out.println(welcome);
        System.out.println(options);

        System.out.print("请选择操作（输入数字）: ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                addMaintenanceTask();
                break;
            case 2:
                deleteMaintenanceTask();
                break;
            case 3:
                updateMaintenanceTask();
                break;
            case 4:
                queryMaintenanceTask();
                break;
            default:
                System.out.println("无效的选择！");
                break;
        }
    }

    public static void addMaintenanceTask(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("请输入植物养护信息：");
        System.out.print("任务名称: ");
        String TaskName = scanner.nextLine();

        System.out.print("执行时间: ");
        String ExecutionTime  = scanner.nextLine();

        System.out.print("执行地点: ");
        String Location = scanner.nextLine();

        System.out.print("执行人员: ");
        String Personnel = scanner.nextLine();

        System.out.print("任务描述: ");
        String Description = scanner.nextLine();

        System.out.print("养护对象ID: ");
        String PlantID = scanner.nextLine();


        System.out.print("创建者: ");
        String creator = scanner.nextLine();

        Date currentTime = new Date(); // 获取当前时间作为创建时间和更新时间

        MaintenanceTask maintenanceTask = new MaintenanceTask();
        maintenanceTask.setTaskName(TaskName);
        maintenanceTask.setExecutionTime(ExecutionTime);
        maintenanceTask.setLocation(Location);
        maintenanceTask.setPersonnel(Personnel);
        maintenanceTask.setDescription(Description);
        maintenanceTask.setPlantId(Integer.parseInt(PlantID));
        maintenanceTask.setCreator(creator);
        maintenanceTask.setCreateTime(currentTime);
//        maintenanceTask.setUpdateTime(null);

        MaintenanceDaoImpI maintenanceDao = new MaintenanceDaoImpI();
        boolean result = maintenanceDao.addMaintenanceTask(maintenanceTask);
        if (result) {
            System.out.println("植物养护信息添加成功！");
        } else {
            System.out.println("植物养护信息添加失败。");
        }
    }

    public static void deleteMaintenanceTask() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("请输入要删除的植物养护任务ID：");
        System.out.print("任务ID: ");
        int taskId = Integer.parseInt(scanner.nextLine());

        MaintenanceDaoImpI maintenanceDao = new MaintenanceDaoImpI();
        boolean result = maintenanceDao.deleteMaintenanceTask(taskId);

        if (result) {
            System.out.println("植物养护任务删除成功！");
        } else {
            System.out.println("植物养护任务删除失败。");
        }
    }


    public static void updateMaintenanceTask(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("请输入要更新的植物养护任务ID：");
        System.out.print("任务ID: ");
        int taskId = Integer.parseInt(scanner.nextLine());

        System.out.println("请输入新的植物养护信息：");
        System.out.print("任务名称: ");
        String taskName = scanner.nextLine();

        System.out.print("执行时间: ");
        String executionTime = scanner.nextLine();

        System.out.print("执行地点: ");
        String location = scanner.nextLine();

        System.out.print("执行人员: ");
        String personnel = scanner.nextLine();

        System.out.print("任务描述: ");
        String description = scanner.nextLine();

        System.out.print("养护对象ID: ");
        int plantId = Integer.parseInt(scanner.nextLine());

        System.out.print("创建者: ");
        String creator = scanner.nextLine();

        // 获取当前时间作为更新时间
        Date currentTime = new Date();

        MaintenanceTask maintenanceTask = new MaintenanceTask();
        maintenanceTask.setTaskId(taskId);
        maintenanceTask.setTaskName(taskName);
        maintenanceTask.setExecutionTime(executionTime);
        maintenanceTask.setLocation(location);
        maintenanceTask.setPersonnel(personnel);
        maintenanceTask.setDescription(description);
        maintenanceTask.setPlantId(plantId);
        maintenanceTask.setCreator(creator);
        maintenanceTask.setCreateTime(null); // 更新操作不改变创建时间
        maintenanceTask.setUpdateTime(currentTime);

        MaintenanceDaoImpI maintenanceDao = new MaintenanceDaoImpI();
        boolean result = maintenanceDao.updateMaintenanceTask(maintenanceTask);
        if (result) {
            System.out.println("植物养护任务更新成功！");
        } else {
            System.out.println("植物养护任务更新失败。");
        }
    }


    public static void queryMaintenanceTask() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("请输入要查询的植物养护任务ID：");
        System.out.print("任务ID: ");
        int taskId = Integer.parseInt(scanner.nextLine());

        MaintenanceDaoImpI maintenanceDao = new MaintenanceDaoImpI();
        MaintenanceTask maintenanceTask = maintenanceDao.getMaintenanceTaskById(taskId);

        if (maintenanceTask != null) {
            System.out.println("任务名称: " + maintenanceTask.getTaskName());
            System.out.println("执行时间: " + maintenanceTask.getExecutionTime());
            System.out.println("执行地点: " + maintenanceTask.getLocation());
            System.out.println("执行人员: " + maintenanceTask.getPersonnel());
            System.out.println("任务描述: " + maintenanceTask.getDescription());
            System.out.println("养护对象ID: " + maintenanceTask.getPlantId());
            System.out.println("创建者: " + maintenanceTask.getCreator());
            System.out.println("创建时间: " + maintenanceTask.getCreateTime());
            System.out.println("更新时间: " + maintenanceTask.getUpdateTime());
        } else {
            System.out.println("未找到任务ID为 " + taskId + " 的植物养护任务。");
        }
    }

}
