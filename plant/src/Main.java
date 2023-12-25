import java.io.Console;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
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
                    while (true){
                        System.out.println(
                                "-----------------------------------1.向植物基础信息表添加植物-----------------------------------\n" +
                                        "-----------------------------------2.修改植物基础信息表中的信息-----------------------------------\n"+
                                        "-----------------------------------3.查找植物基础信息表中的信息-----------------------------------\n"+
                                        "-----------------------------------4.删除植物基础信息表中的信息-----------------------------------\n"+
                                        "-----------------------------------5.增加植物分类信息表中的信息-----------------------------------\n"+
                                        "-----------------------------------6.修改植物分类信息表中的信息-----------------------------------\n"+
                                        "-----------------------------------7.查找植物分类信息表中的信息-----------------------------------\n"+
                                        "-----------------------------------8.删除植物分类信息表中的信息-----------------------------------\n"+
                                        "-----------------------------------9.增加植物害虫信息表中的信息-----------------------------------\n"+
                                        "-----------------------------------10.修改植物害虫信息表中的信息-----------------------------------\n"+
                                        "-----------------------------------11.查找植物害虫信息表中的信息-----------------------------------\n"+
                                        "-----------------------------------12.删除害虫信息表中的信息-----------------------------------\n"+
                                        "-----------------------------------13.退出系统-----------------------------------\n");
                        Scanner sc1 =new Scanner(System.in);
                        String select =sc1.next();
                        switch (select){
                            case "1":
                                plantsDAOim plantsDAO = new plantsDAOim();
                                System.out.println("请输入植物信息:");
                                Scanner inputScanner = new Scanner(System.in);
                                System.out.println("请输入植物ID:");
                                int plantID = inputScanner.nextInt();
                                System.out.println("请输入植物种名:");
                                String speciesName = inputScanner.next();
                                System.out.println("请输入植物别名:");
                                String alias = inputScanner.next();
                                System.out.println("请输入植物科名:");
                                String familyName = inputScanner.next();
                                System.out.println("请输入植物疾病名称:");
                                String diseaseName = inputScanner.next();
                                System.out.println("请输入植物形态特征:");
                                String morphologicalFeatures = inputScanner.next();
                                System.out.println("请输入植物栽培技术:");
                                String cultivationTechniques = inputScanner.next();
                                System.out.println("请输入植物应用价值:");
                                String applicationValue = inputScanner.next();
                                System.out.println("请输入植物图片ID:");
                                int imageID = inputScanner.nextInt();
                                plants newPlant = new plants(plantID, diseaseName, alias, familyName, speciesName, morphologicalFeatures, cultivationTechniques, applicationValue, imageID);
                                plantsDAO.addPlant(newPlant);
                                System.out.println("添加成功");
                                break;
                            case "2":
                                plantsDAOim plantsDAO2 = new plantsDAOim();
                                System.out.println("请输入要修改的植物ID:");
                                Scanner inputScanner2 = new Scanner(System.in);
                                int plantID2 = inputScanner2.nextInt();
                                System.out.println("请选择要修改的属性：");
                                System.out.println("1. 植物疾病名称");
                                System.out.println("2. 植物别名");
                                System.out.println("3. 植物科名");
                                System.out.println("4. 植物种名");
                                System.out.println("5. 植物形态特征");
                                System.out.println("6. 植物栽培技术");
                                System.out.println("7. 植物应用价值");
                                System.out.println("8. 植物图片ID");
                                int choice = inputScanner2.nextInt();
                                System.out.println("请输入新的属性值:");
                                String newValue = inputScanner2.next();
                                plantsDAO2.updatePlant(plantID2, choice, newValue);
                                System.out.println("修改成功");
                                break;
                            case "3":
                                plantsDAOim plantsDAO3 = new plantsDAOim();
                                System.out.println("请输入要查找的植物ID:");
                                Scanner inputScanner3 = new Scanner(System.in);
                                int plantID3 = inputScanner3.nextInt();
                                plants foundPlant = plantsDAO3.getPlantByID(plantID3);
                                if (foundPlant != null) {
                                    System.out.println("查找成功");
                                    System.out.println("植物ID: " + foundPlant.getPlantID());
                                    System.out.println("植物疾病名称: " + foundPlant.getDiseaseName());
                                    System.out.println("植物别名: " + foundPlant.getAlias());
                                    System.out.println("植物科名: " + foundPlant.getFamilyName());
                                    System.out.println("植物种名: " + foundPlant.getSpeciesName());
                                    System.out.println("植物形态特征: " + foundPlant.getMorphologicalFeatures());
                                    System.out.println("植物栽培技术: " + foundPlant.getCultivationTechniques());
                                    System.out.println("植物应用价值: " + foundPlant.getApplicationValue());
                                    System.out.println("植物图片ID: " + foundPlant.getImageID());
                                } else {
                                    System.out.println("未找到该植物");
                                }
                                break;
                            case "4":
                                plantsDAOim plantsDAO4 = new plantsDAOim();
                                System.out.println("请输入要删除的植物ID:");
                                Scanner inputScanner4 = new Scanner(System.in);
                                int plantID4 = inputScanner4.nextInt();
                                plantsDAO4.deletePlant(plantID4);
                                System.out.println("删除成功");
                                break;
                                case "5":
                                seriesDAOim seriesDAO5 = new seriesDAOim();
                                System.out.println("请输入分类信息:");
                                Scanner inputScanner5 = new Scanner(System.in);
                                System.out.println("请输入植物名称:");
                                String SpaceName = inputScanner5.next();
                                System.out.println("请输入分类别名:");
                                String alias2 = inputScanner5.next();
                                System.out.println("请输入分类科名:");
                                String familyName2 = inputScanner5.next();
                                System.out.println("请输入分类属名:");
                                String genusName = inputScanner5.next();
                                System.out.println("请输入分类所在城市:");
                                String city = inputScanner5.next();
                                System.out.println("请输入分类环境:");
                                String environment = inputScanner5.next();
                                series newSeries = new series(SpaceName, alias2, familyName2, genusName, city, environment);
                                seriesDAO5.addSeries(newSeries);
                                System.out.println("添加成功");
                                break;
                            case "6":
                                seriesDAOim seriesDAO6 = new seriesDAOim();
                                System.out.println("请输入要修改的植物名称:");
                                Scanner inputScanner6 = new Scanner(System.in);
                                String SpaceName6 = inputScanner6.next();
                                System.out.println("请选择要修改的属性：");
                                System.out.println("1. 分类别名");
                                System.out.println("2. 分类科名");
                                System.out.println("3. 分类属名");
                                System.out.println("4. 分类所在城市");
                                System.out.println("5. 分类环境");
                                int choice6 = inputScanner6.nextInt();
                                System.out.println("请输入新的属性值:");
                                String newValue6 = inputScanner6.next();
                                seriesDAO6.updateSeries(SpaceName6, choice6, newValue6);
                                System.out.println("修改成功");
                                break;
                            case "7":
                                seriesDAOim seriesDAO7 = new seriesDAOim();
                                System.out.println("请输入要查找的分类信息:");
                                Scanner inputScanner7 = new Scanner(System.in);
                                System.out.println("请选择要查找的属性：");
                                System.out.println("1. 根据属名查找");
                                System.out.println("2. 根据科名查找");
                                System.out.println("3. 根据城市查找");
                                int choice7 = inputScanner7.nextInt();
                                if (choice7 == 1) {
                                    System.out.println("请输入要查找的分类属名:");
                                    String genusName7 = inputScanner7.next();
                                    List<series> foundSeriesList = seriesDAO7.getSeriesByGenusName(genusName7);
                                    if (!foundSeriesList.isEmpty()) {
                                        System.out.println("查找成功");
                                        for (series foundSeries : foundSeriesList) {
                                            System.out.println("植物名称: " + foundSeries.getSpaceName());
                                            System.out.println("分类别名: " + foundSeries.getAlias());
                                            System.out.println("分类科名: " + foundSeries.getFamilyName());
                                            System.out.println("分类属名: " + foundSeries.getGenusName());
                                            System.out.println("分类所在城市: " + foundSeries.getCity());
                                            System.out.println("分类环境: " + foundSeries.getEnvironment());
                                        }
                                    } else {
                                        System.out.println("未找到该分类");
                                    }
                                } else if (choice7 == 2) {
                                    System.out.println("请输入要查找的分类科名:");
                                    String familyName7 = inputScanner7.next();
                                    List<series> foundSeriesList = seriesDAO7.getSeriesByFamilyName(familyName7);
                                    if (!foundSeriesList.isEmpty()) {
                                        System.out.println("查找成功");
                                        for (series foundSeries : foundSeriesList) {
                                            System.out.println("植物名称: " + foundSeries.getSpaceName());
                                            System.out.println("分类别名: " + foundSeries.getAlias());
                                            System.out.println("分类科名: " + foundSeries.getFamilyName());
                                            System.out.println("分类属名: " + foundSeries.getGenusName());
                                            System.out.println("分类所在城市: " + foundSeries.getCity());
                                            System.out.println("分类环境: " + foundSeries.getEnvironment());
                                        }
                                    } else if (choice7 == 3) {
                                        System.out.println("请输入要查找的分类所在城市:"); // Prompt for city input
                                        String city7 = inputScanner7.next();
                                        List<series> foundSeriesList1 = seriesDAO7.getSeriesByCity(city7);
                                        if (!foundSeriesList1.isEmpty()) {
                                            System.out.println("查找成功");
                                            for (series foundSeries : foundSeriesList1) {
                                                System.out.println("植物名称: " + foundSeries.getSpaceName());
                                                System.out.println("分类别名: " + foundSeries.getAlias());
                                                System.out.println("分类科名: " + foundSeries.getFamilyName());
                                                System.out.println("分类属名: " + foundSeries.getGenusName());
                                                System.out.println("分类所在城市: " + foundSeries.getCity());
                                                System.out.println("分类环境: " + foundSeries.getEnvironment());
                                            }
                                        } else {
                                            System.out.println("未找到该分类");
                                        }
                                    }
                                }
                                break;
                            case "8":
                                seriesDAOim seriesDAO8 = new seriesDAOim();
                                System.out.println("请输入要删除的植物名称:");
                                Scanner inputScanner8 = new Scanner(System.in);
                                String SpaceName8 = inputScanner8.next();
                                seriesDAO8.deleteSeries(SpaceName8);
                                System.out.println("删除成功");
                                break;
                            case "9":
                                System.out.println("添加成功");
                            case "10":
                                System.out.println("修改成功");
                            default:
                                System.out.println("输入有误");
                        }
                    }

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
