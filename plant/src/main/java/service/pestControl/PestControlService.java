package service.pestControl;

import dao.pestControl.PestControlDao;
import dao.pestControl.PestControlDaoImpI;
import entity.pestControl.PestControl;

import java.util.Date;
import java.util.Scanner;

public class PestControlService {
    public static final String welcome = "欢迎来到 园林植物病虫害防治管理 模块";
    public static final String options = """
            1.增加植物病虫害防治信息
            2.删除植物病虫害防治信息
            3.修改植物病虫害防治信息
            4.查询植物病虫害防治信息
            """;

    public static void service() {
        Scanner scanner = new Scanner(System.in);

        System.out.println(welcome);
        System.out.println(options);

        System.out.print("请选择操作（输入数字）: ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                addPestControl();
                break;
            case 2:
                delectPestControl();
                break;
            case 3:
                updatePestControl();
                break;
            case 4:
                queryPestControl();
                break;
            default:
                System.out.println("无效的选择！");
                break;
        }
    }

    private static void queryPestControl() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("请输入要查询的病虫害防治信息ID: ");
        int pestControlId = Integer.parseInt(scanner.nextLine());

        PestControlDaoImpI pestControlDao = new PestControlDaoImpI();
        PestControl pestControl = pestControlDao.getPestControlById(pestControlId);

        if (pestControl != null) {
            System.out.println("病虫害名称: " + pestControl.getPestName());
            System.out.println("防治方法: " + pestControl.getControlMethod());
            System.out.println("药剂名称: " + pestControl.getPesticideName());
            System.out.println("药剂用量: " + pestControl.getPesticideAmount());
            System.out.println("作用期限: " + pestControl.getEffectiveDuration());
            System.out.println("创建者: " + pestControl.getCreator());
            System.out.println("创建时间: " + pestControl.getCreateTime());
            System.out.println("更新时间: " + pestControl.getUpdateTime());
        } else {
            System.out.println("未找到ID为 " + pestControlId + " 的病虫害防治信息。");
        }
    }


    private static void updatePestControl() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("请输入要更新的病虫害防治信息ID: ");
        int pestControlId = Integer.parseInt(scanner.nextLine());

        System.out.println("请输入新的病虫害防治信息：");
        System.out.print("病虫害名称: ");
        String pestName = scanner.nextLine();

        System.out.print("防治方法: ");
        String controlMethod = scanner.nextLine();

        System.out.print("药剂名称: ");
        String pesticideName = scanner.nextLine();

        System.out.print("药剂用量: ");
        String pesticideAmount = scanner.nextLine();

        System.out.print("作用期限: ");
        String effectiveDuration = scanner.nextLine();

        System.out.print("创建者: ");  // 更新信息时，通常不更改创建者
        String creator = scanner.nextLine();

//        System.out.print("更新时间: ");  // 更新信息时，通常记录当前时间为更新时间
        Date currentTime = new Date();

        PestControl pestControl = new PestControl();
        pestControl.setPestControlId(pestControlId);
        pestControl.setPestName(pestName);
        pestControl.setControlMethod(controlMethod);
        pestControl.setPesticideName(pesticideName);
        pestControl.setPesticideAmount(pesticideAmount);
        pestControl.setEffectiveDuration(effectiveDuration);
        pestControl.setCreator(creator);  // 通常不更改，但如果逻辑允许，也可以更新
        pestControl.setUpdateTime(currentTime);

        PestControlDaoImpI pestControlDao = new PestControlDaoImpI();
        boolean result = pestControlDao.updatePestControl(pestControl);
        if (result) {
            System.out.println("病虫害防治信息更新成功！");
        } else {
            System.out.println("病虫害防治信息更新失败。");
        }
    }


    private static void delectPestControl() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("请输入要删除的病虫害防治信息ID: ");
        int pestControlId = scanner.nextInt();

        PestControlDaoImpI pestControlDao = new PestControlDaoImpI();
        boolean result = pestControlDao.deletePestControl(pestControlId);
        if (result) {
            System.out.println("病虫害防治信息删除成功！");
        } else {
            System.out.println("病虫害防治信息删除失败。");
        }
    }


    private static void addPestControl() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("请输入病虫害防治信息：");
        System.out.print("病虫害名称: ");
        String pestName = scanner.nextLine();

        System.out.print("防治方法: ");
        String controlMethod = scanner.nextLine();

        System.out.print("药剂名称: ");
        String pesticideName = scanner.nextLine();

        System.out.print("药剂用量: ");
        String pesticideAmount = scanner.nextLine();

        System.out.print("作用期限: ");
        String effectiveDuration = scanner.nextLine();

        System.out.print("创建者: ");
        String creator = scanner.nextLine();

        Date currentTime = new Date(); // 获取当前时间作为创建时间和更新时间

        PestControl pestControl = new PestControl();
        pestControl.setPestName(pestName);
        pestControl.setControlMethod(controlMethod);
        pestControl.setPesticideName(pesticideName);
        pestControl.setPesticideAmount(pesticideAmount);
        pestControl.setEffectiveDuration(effectiveDuration);
        pestControl.setCreator(creator);
        pestControl.setCreateTime(currentTime);

        PestControlDaoImpI pestControlDao = new PestControlDaoImpI();
        boolean result = pestControlDao.addPestControl(pestControl);
        if (result) {
            System.out.println("病虫害防治信息添加成功！");
        } else {
            System.out.println("病虫害防治信息添加失败。");
        }
    }

}
