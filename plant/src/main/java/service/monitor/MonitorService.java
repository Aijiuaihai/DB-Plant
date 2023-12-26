package service.monitor;

import dao.info.PlantDAOImpl;
import dao.info.TaxonomyDAOImpl;
import dao.monitor.MonitorDaoImpl;
import classdefine.info.Plant;
import classdefine.info.Taxonomy;
import classdefine.monitor.PlantMonitor;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class MonitorService {
    private static final String welcome = "欢迎来到 园林植物检测管理 模块";
    private static final String options = """
            1.添加监测信息
            2.查询某植物的监测信息
            3.更新监测信息
            4.查询某植物的某指标的最大值
            5.查询某植物的某指标的平均值
            """;
    private Scanner scanner;
    private MonitorDaoImpl monitorDao;

    public void service() {
        scanner = new Scanner(System.in);
        monitorDao = new MonitorDaoImpl();

        System.out.println();
        System.out.println(welcome);
        System.out.println(options);


        switch (scanner.nextLine()) {
            case "1":
                addMonitorItem();
                break;
            case "2":
                getPlantMonitorInfo();
                break;
            case "3":
                updateMonitorInfo();
                break;
            case "4":
                getMaxValue(true);
                break;
            case "5":
                getAverageValue();
                break;

            default:
                System.out.println("输入错误");
        }
    }

    private void addMonitorItem() {
        System.out.println("请输入您想添加监测信息的植物的ID");
        Integer id = -1;
        try {
            id = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("输入有误，退出本功能");
            return;
        }

        PlantDAOImpl plantDAO = new PlantDAOImpl();
        Plant plant = plantDAO.getPlantById(id);
        if (plant == null) {
            System.out.println("没找到对应的植物");
            return;
        }

        PlantMonitor plantMonitor = new PlantMonitor();

        plantMonitor.setMonitoringTime(new Date());

        System.out.println("请输入检测人员:");
        String monitoringPersonnel = scanner.nextLine();
        plantMonitor.setMonitoringPersonnel(monitoringPersonnel);

        System.out.println("请输入检测地点:");
        String monitoringLocation = scanner.nextLine();
        plantMonitor.setMonitoringLocation(monitoringLocation);

//        System.out.println("请输入监测对象:");
        TaxonomyDAOImpl taxonomyDAO = new TaxonomyDAOImpl();
        Taxonomy taxonomy = taxonomyDAO.getTaxonomyByPlantId(plant.getPlantID());
        plantMonitor.setMonitoredObject(taxonomy.getSpecies());

//        System.out.println("请输入监测对象ID:");
        plantMonitor.setMonitoredObjectId(plant.getPlantID());


        System.out.println("请输入监测指标,例如PH值、土壤湿度等:");
        String monitoringIndex = scanner.nextLine();
        plantMonitor.setMonitoringIndex(monitoringIndex);

        System.out.println("请输入监测数值:");
        float monitorValue = scanner.nextFloat();
        plantMonitor.setMonitorValue(monitorValue);

        scanner.nextLine(); // 清空输入缓冲区


        System.out.println("请输入监测设备:");
        String monitoringDevice = scanner.nextLine();
        plantMonitor.setMonitoringDevice(monitoringDevice);

        System.out.println("请输入创建人员:");
        String createdBy = scanner.nextLine();
        plantMonitor.setCreatedBy(createdBy);
        plantMonitor.setCreatedTime(new Date());
        plantMonitor.setUpdatedTime(new Date());

        MonitorDaoImpl monitorDao = new MonitorDaoImpl();
        monitorDao.addPlantMonitor(plantMonitor);

    }

    private void getPlantMonitorInfo() {
        System.out.println("请输入植物的ID");
        Integer id;
        try {
            id = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("输入有误，退出本功能");
            return;
        }

        PlantDAOImpl plantDAO = new PlantDAOImpl();
        Plant plant = plantDAO.getPlantById(id);
        if (plant == null) {
            System.out.println("没找到对应的植物");
            return;
        }
        ArrayList<PlantMonitor> plantMonitors = (ArrayList<PlantMonitor>) monitorDao.getAllMonitorsByPlantId(id);
        for (PlantMonitor p : plantMonitors) {
            System.out.println(p);
        }

    }

    private void updateMonitorInfo() {
        System.out.println("请输入您想修改的监测记录的ID");
        Integer id;
        try {
            id = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("输入有误，退出本功能");
            return;
        }
        PlantMonitor plantMonitor = monitorDao.getPlantMonitorById(id);
        if (plantMonitor == null) {
            System.out.println("未查找到您想要的监测记录");
            return;
        }

        System.out.println("""
                输入您想修改的项目
                1.监测设备
                2.监测指标
                3.监测值
                4.监测地点
                """);

        switch (scanner.nextLine()) {
            case "1":
                System.out.println("请您输入新值：");
                plantMonitor.setMonitoringDevice(scanner.nextLine());
                break;
            case "2":
                System.out.println("请您输入新值：");
                plantMonitor.setMonitoringIndex(scanner.nextLine());

                break;
            case "3":
                System.out.println("请您输入新值：");
                plantMonitor.setMonitorValue(scanner.nextFloat());
                scanner.nextLine();
                break;
            case "4":
                System.out.println("请您输入新值：");
                plantMonitor.setMonitoringLocation(scanner.nextLine());
                break;
        }
        System.out.println(plantMonitor);
        System.out.println(monitorDao.updatePlantMonitor(plantMonitor));
        System.out.println("更新完成");
    }

    private void getMaxValue(boolean max) {
        System.out.println("请您输入想要查询的植物的ID");
        int id;
        try {
            id = scanner.nextInt();
            scanner.nextLine();
        } catch (NumberFormatException e) {
            System.out.println("输入有误，退出本功能");
            return;
        }

        PlantDAOImpl plantDAO = new PlantDAOImpl();
        Plant plant = plantDAO.getPlantById(id);
        if (plant == null) {
            System.out.println("没找到对应的植物");
            return;
        }

        System.out.println("请输入您想查找的监测指标，例如PH、土壤湿度等");
        String option = scanner.nextLine();
        Float result = null;
        if (max) {
            result = monitorDao.getMaxMonitorValue(id, option);
        } else {
            result = monitorDao.getAverageMonitorValue(id, option);
        }

        if (result == null) {
            System.out.println("查找失败");
            return;
        }
        if (max) {
            System.out.println("您查找的" + option + "最大值为:" + result);
        } else {
            System.out.println("您查找的" + option + "平均值为:" + result);
        }

    }

    private void getAverageValue() {
        getMaxValue(false);
    }

    public static void main(String[] args) {

        new MonitorService().service();

    }
}
