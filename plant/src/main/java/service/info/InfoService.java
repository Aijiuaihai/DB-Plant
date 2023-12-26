package service.info;

import dao.info.*;
import classdefine.info.FullPlantInfo;
import classdefine.info.Plant;
import classdefine.info.PlantImage;
import classdefine.info.Taxonomy;


import java.util.*;

public class InfoService {

    private static final String welcome = "欢迎来到 园林植物基本信息管理业务 模块";
    private static final String options = """
            1.查看前10个植物的简略信息（不完整）
            2.查询并修改植物信息
            3.删除植物信息
            4.添加植物
            5.统计每科植物的数量
            6.根据属性、属性组合查询植物
            """;
    private Scanner scanner;

    public void service() {
        scanner = new Scanner(System.in);

        System.out.println(welcome);
        System.out.println(options);

        switch (scanner.nextLine()) {
            case "1":
                getFirst5Plants();
                break;
            case "2":
                searchAndModify();
                break;
            case "3":
                deletePlant();
                break;
            case "4":
                addPlant();
                break;
            case "5":
                analyseFamily();
                break;
            case "6":
                searchByMultipleParams();
                break;
            case "7":
                cultivate();
                break;
            case "8":
                maintain();

                break;
            default:
                System.out.println("输入错误");

        }


    }

    private static void addPlant() {
        Scanner scanner = new Scanner(System.in);
        Taxonomy taxonomy = new Taxonomy();
        System.out.println("请输入植物的科名");
        taxonomy.setFamily(scanner.nextLine());

        System.out.println("请输入植物的属名");
        taxonomy.setGenus(scanner.nextLine());

        System.out.println("请输入植物的种名");
        taxonomy.setSpecies(scanner.nextLine());

        Plant plant = new Plant();

        System.out.println("请您输入病名");
        plant.setDiseaseName(scanner.nextLine());

        System.out.println("请您输入植物的别名");
        plant.setCommonName(scanner.nextLine());

        System.out.println("请您输入植物的形态特征");
        plant.setMorphology(scanner.nextLine());

        System.out.println("请您输入植物的栽培技术要点");
        plant.setCultivationTips(scanner.nextLine());

        System.out.println("请您输入病虫害防治措施");
        plant.setPestControlMeasures(scanner.nextLine());

        System.out.println("请您输入植物的应用价值");
        plant.setApplicationValue(scanner.nextLine());

        System.out.println("请您输入创建者姓名");
        plant.setCreator(scanner.nextLine());

        // 设置创建时间和更新时间
        plant.setCreateTime(new Date());
        plant.setUpdateTime(new Date());




        System.out.println("请输入植物图片的路径：");
        PlantImage image = new PlantImage();
        image.setPhotoPath(scanner.nextLine());

        System.out.println("请输入植物图片的拍摄人：");
        image.setPhotographer(scanner.nextLine());

        System.out.println("请输入植物图片的拍摄地点：");
        image.setLocation(scanner.nextLine());

        System.out.println("请输入植物图片的描述信息：");
        image.setDescription(scanner.nextLine());


        PlantDAOImpl plantDAO = new PlantDAOImpl();
        plantDAO.addPlant(plant);

        taxonomy.setPlantID(plant.getPlantID());
        image.setPlantID(plant.getPlantID());

        TaxonomyDAOImpl taxonomyDAO = new TaxonomyDAOImpl();
        taxonomyDAO.addTaxonomy(taxonomy);

        PlantImageDAOImpl plantImageDAO = new PlantImageDAOImpl();
        plantImageDAO.addImage(image);

    }

    public  void getFirst5Plants() {
        PlantDAOImpl plantDAO = new PlantDAOImpl();
        printPlantInfo(getFullPlantInfoByPlant((ArrayList<Plant>) plantDAO.getAllPlants()));
        if (plantDAO.getAllPlants().size() == 0) {
            System.out.println("数据库为空，您可以先添加数据");
        }

    }

    public void searchAndModify() {
        System.out.println("请输入植物的种名");
        String name = scanner.nextLine();

        TaxonomyDAOImpl taxonomyDAO = new TaxonomyDAOImpl();
        PlantDAOImpl plantDAO = new PlantDAOImpl();
        PlantImageDAOImpl plantImageDAO = new PlantImageDAOImpl();

        Taxonomy taxonomy = taxonomyDAO.getTaxonomyBySpecies(name);
        if (taxonomy == null) {
            System.out.println("您查找的植物不存在");
            return;
        }
        Plant plant = plantDAO.getPlantById(taxonomy.getPlantID());

        System.out.println("""
                可修改的信息如下：
                1.植物的病名
                2.植物的别名
                3.植物的形态特征
                4.植物的栽培技术要点
                5.植物的病虫害防治措施
                6.植物的应用价值
                7.植物的科名
                8.植物的属名
                9.植物的种名
                """);
        switch (scanner.nextLine()) {

            case "1":
                System.out.println("请输入新信息：");
                plant.setDiseaseName(scanner.nextLine());
                break;
            case "2":
                System.out.println("请输入新信息：");
                plant.setCommonName(scanner.nextLine());
                break;
            case "3":
                System.out.println("请输入新信息：");
                plant.setMorphology(scanner.nextLine());
                break;
            case "4":
                System.out.println("请输入新信息：");
                plant.setCultivationTips(scanner.nextLine());
                break;
            case "5":
                System.out.println("请输入新信息：");
                plant.setPestControlMeasures(scanner.nextLine());
                break;
            case "6":
                System.out.println("请输入新信息：");
                plant.setApplicationValue(scanner.nextLine());
                break;
            case "7":
                System.out.println("请输入新信息：");
                taxonomy.setFamily(scanner.nextLine());
                break;
            case "8":
                System.out.println("请输入新信息：");
                taxonomy.setGenus(scanner.nextLine());
                break;
            case "9":
                System.out.println("请输入新信息：");
                taxonomy.setSpecies(scanner.nextLine());
                break;
            default:
                System.out.println("输入有误");
        }

        boolean b1 = taxonomyDAO.updateTaxonomy(taxonomy);
        boolean b2 = plantDAO.updatePlant(plant);

        if (b1 && b2) {
            System.out.println("更新完成");
        } else {
            System.out.println("出现错误，更新失败");
        }

    }

    public void deletePlant() {
        System.out.println("您可以通过 1.植物的ID 2.植物的种名 来删除植物的信息");

        switch (scanner.nextLine()) {
            case "1":
                try {
                    System.out.println("请输入信息：");
                    Integer id = scanner.nextInt();
                    scanner.nextLine();
                    PlantDAOImpl plantDAO = new PlantDAOImpl();
                    TaxonomyDAOImpl taxonomyDAO = new TaxonomyDAOImpl();
                    PlantImageDAOImpl plantImageDAO = new PlantImageDAOImpl();

                    boolean b1 = taxonomyDAO.deleteTaxonomyByPlantId(id);
                    boolean b2 = plantDAO.deletePlant(id);
                    boolean b3 = plantImageDAO.deleteImageByPlantId(id);

                    if (b1 && b2 && b3) {
                        System.out.println("删除成功");
                    } else {
                        System.out.println("删除失败");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("输入错误");
                    return;
                }
                break;

            case "2":
                System.out.println("请输入信息：");
                String spe = scanner.nextLine();
                PlantDAOImpl plantDAO = new PlantDAOImpl();
                TaxonomyDAOImpl taxonomyDAO = new TaxonomyDAOImpl();
                PlantImageDAOImpl plantImageDAO = new PlantImageDAOImpl();

                Taxonomy taxonomy = taxonomyDAO.getTaxonomyBySpecies(spe);
                boolean b1 = taxonomyDAO.deleteTaxonomyByPlantId(taxonomy.getPlantID());
                boolean b2 = plantDAO.deletePlant(taxonomy.getPlantID());
                boolean b3 = plantImageDAO.deleteImageByPlantId(taxonomy.getPlantID());

                if (b1 && b2 && b3) {
                    System.out.println("删除成功");
                } else {
                    System.out.println("删除失败");
                }
                break;
            default:
                System.out.println("输入错误。");
        }
    }

    public void analyseFamily() {
        TaxonomyDAOImpl taxonomyDAO = new TaxonomyDAOImpl();
        ArrayList<String> families = taxonomyDAO.getAllFamily();
        System.out.println("所有的科名：");
        for (String s : families) {
            System.out.print(s + " ");
        }
        System.out.println();
        System.out.println("请您输入想要查找的科名植物");
        String familyName = scanner.nextLine();
        for (String s : families) {
            if (s.equals(familyName)) {
                taxonomyDAO.createView(familyName);

            }
        }

        String sql = "select * from " + familyName + "_view";
        ArrayList<Taxonomy> taxonomyArrayList = (ArrayList<Taxonomy>) taxonomyDAO.getTaxonomyBySingleParam(sql);
        for (Taxonomy t : taxonomyArrayList) {
            System.out.println("plantID:" + t.getPlantID() + " 种名:" + t.getSpecies() + " 科名:" + t.getFamily());
        }
    }

    /**
     * 一个或多个参数查询植物
     * 一个植物记录 对应 一个分类记录 对应 多个照片记录
     * 为了简化操作，
     */
    public void searchByMultipleParams() {
        System.out.println("您可以选择 1.单个属性查找 2.多属性查找");
        String option = scanner.nextLine();
        if (option.equals("1")) {
            String sql = "";
            System.out.println("""
                    可查找的属性如下：
                    1.植物的病名
                    2.植物的别名
                    3.植物的形态特征
                    4.植物的栽培技术要点
                    5.植物的病虫害防治措施
                    6.植物的应用价值
                    7.植物的科名
                    8.植物的属名
                    9.植物的种名
                    """);
            String tmp = "";
            switch ((tmp = scanner.nextLine())) {

                case "1":
                    System.out.println("请输入关键词：");
                    sql = "SELECT * FROM plant where diseaseName = \'" + scanner.nextLine() + "\'";
                    break;
                case "2":
                    System.out.println("请输入关键词：");
                    sql = "SELECT * FROM plant where commonName = \'" + scanner.nextLine() + "\'";
                    break;
                case "3":
                    System.out.println("请输入关键词：");
                    sql = "SELECT * FROM plant where morphology = \'" + scanner.nextLine() + "\'";
                    break;
                case "4":
                    System.out.println("请输入关键词：");
                    sql = "SELECT * FROM plant where cultivationTips = \'" + scanner.nextLine() + "\'";
                    break;
                case "5":
                    System.out.println("请输入关键词：");
                    sql = "SELECT * FROM plant where pestControlMeasures = \'" + scanner.nextLine() + "\'";
                    break;
                case "6":
                    System.out.println("请输入关键词：");
                    sql = "SELECT * FROM plant where applicationValue = \'" + scanner.nextLine() + "\'";
                    break;
                case "7":
                    System.out.println("请输入关键词：");
                    sql = "SELECT * FROM taxonomy where family = \'" + scanner.nextLine() + "\'";
                    break;
                case "8":
                    System.out.println("请输入关键词：");
                    sql = "SELECT * FROM taxonomy where genus = \'" + scanner.nextLine() + "\'";
                    break;
                case "9":
                    System.out.println("请输入关键词：");
                    sql = "SELECT * FROM taxonomy where species = \'" + scanner.nextLine() + "\'";
                    break;
                default:
                    System.out.println("输入有误");
                    return;
            }
            if (Integer.parseInt(tmp) <= 6) {
                PlantDAOImpl plantDAO = new PlantDAOImpl();
                ArrayList<Plant> plantArrayList = (ArrayList<Plant>) plantDAO.getPlantBySingleParam(sql);
                printPlantInfo(getFullPlantInfoByPlant(plantArrayList));

            } else {
                TaxonomyDAOImpl taxonomyDAO = new TaxonomyDAOImpl();
                ArrayList<Taxonomy> taxonomyArrayList = (ArrayList<Taxonomy>) taxonomyDAO.getTaxonomyBySingleParam(sql);
                printPlantInfo(getFullPlantInfoByTaxonomy(taxonomyArrayList));

            }
        } else if (option.equals("2")) {
            System.out.println("您可以通过一对属性来进行筛选");

            System.out.println("""
                    第一个属性
                    1.植物的病名
                    2.植物的别名
                    3.植物的形态特征
                    4.植物的栽培技术要点
                    5.植物的病虫害防治措施
                    6.植物的应用价值
                    7.植物的科名
                    8.植物的属名
                    9.植物的种名
                    """);
            String firstParam = scanner.nextLine();
            int firstNum;
            int secondNum;
            try {
                firstNum = Integer.parseInt(firstParam);
            } catch (Exception e) {
                System.out.println("输入有误");
                return;
            }
            if (firstNum <= 6) {

                System.out.println("""
                    第二个属性
                    1.植物的病名
                    2.植物的别名
                    3.植物的形态特征
                    4.植物的栽培技术要点
                    5.植物的病虫害防治措施
                    6.植物的应用价值
                    """);

                String s2 = scanner.nextLine();
                try {
                    secondNum = Integer.parseInt(s2);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("输入错误");
                    return;
                }

                System.out.println("请输入第一个参数");
                String i1 = scanner.nextLine();
                System.out.println("请输入第二个参数");
                String i2 = scanner.nextLine();

                String p1 = getParam(firstNum);
                String p2 = getParam(secondNum);

                String sql = "select * from plant where " + p1 + " = \'" + i1 + "\'" + " and " + p2 + " = \'" + i2 + "\'";
                PlantDAOImpl plantDAO = new PlantDAOImpl();
                ArrayList<Plant> plantArrayList = (ArrayList<Plant>) plantDAO.getPlantBySingleParam(sql);
                printPlantInfo(getFullPlantInfoByPlant(plantArrayList));
            }else{
                System.out.println("""
                    第二个属性
                    7.植物的科名
                    8.植物的属名
                    9.植物的种名
                    """);
                String s2 = scanner.nextLine();
                try {
                    secondNum = Integer.parseInt(s2);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("输入错误");
                    return;
                }

                System.out.println("请输入第一个参数");
                String i1 = scanner.nextLine();
                System.out.println("请输入第二个参数");
                String i2 = scanner.nextLine();

                String p1 = getParam(firstNum);
                String p2 = getParam(secondNum);

                String sql = "select * from taxonomy where " + p1 + " = \'" + i1 + "\'" + " and " + p2 + " = \'" + i2 + "\'";
                TaxonomyDAOImpl taxonomyDAO = new TaxonomyDAOImpl();
                ArrayList<Taxonomy> taxonomyArrayList = (ArrayList<Taxonomy>) taxonomyDAO.getTaxonomyBySingleParam(sql);
                printPlantInfo(getFullPlantInfoByTaxonomy(taxonomyArrayList));

            }

        } else {
            System.out.println("输入错误");
        }
    }

    public void cultivate() {

    }

    public void maintain() {

    }

    private FullPlantInfo getFullPlantInfo(int plantId) {
        TaxonomyDAOImpl taxonomyDAO = new TaxonomyDAOImpl();
        PlantImageDAOImpl plantImageDAO = new PlantImageDAOImpl();
        PlantDAOImpl plantDAO = new PlantDAOImpl();

        FullPlantInfo fullPlantInfo = new FullPlantInfo();
        fullPlantInfo.setTaxonomy(taxonomyDAO.getTaxonomyByPlantId(plantId));
        fullPlantInfo.setImageArrayList((ArrayList<PlantImage>) plantImageDAO.getImagesByPlantId(plantId));
        fullPlantInfo.setPlant(plantDAO.getPlantById(plantId));

        return fullPlantInfo;
    }

    private FullPlantInfo getFullPlantInfo(Plant plant) {
        TaxonomyDAOImpl taxonomyDAO = new TaxonomyDAOImpl();
        PlantImageDAOImpl plantImageDAO = new PlantImageDAOImpl();


        FullPlantInfo fullPlantInfo = new FullPlantInfo();
        fullPlantInfo.setTaxonomy(taxonomyDAO.getTaxonomyByPlantId(plant.getPlantID()));
        fullPlantInfo.setImageArrayList((ArrayList<PlantImage>) plantImageDAO.getImagesByPlantId(plant.getPlantID()));
        fullPlantInfo.setPlant(plant);

        return fullPlantInfo;
    }

    private ArrayList<FullPlantInfo> getFullPlantInfoByPlant(ArrayList<Plant> plants) {
        ArrayList<FullPlantInfo> fullPlantInfos = new ArrayList<>();

        TaxonomyDAOImpl taxonomyDAO = new TaxonomyDAOImpl();
        PlantImageDAOImpl plantImageDAO = new PlantImageDAOImpl();

        for (Plant p : plants) {
            FullPlantInfo info = new FullPlantInfo();
            info.setPlant(p);
            info.setTaxonomy(taxonomyDAO.getTaxonomyByPlantId(p.getPlantID()));
            info.setImageArrayList((ArrayList<PlantImage>) plantImageDAO.getImagesByPlantId(p.getPlantID()));

            fullPlantInfos.add(info);
        }
        return fullPlantInfos;
    }
    private ArrayList<FullPlantInfo> getFullPlantInfoByTaxonomy(ArrayList<Taxonomy> taxonomies) {
        ArrayList<FullPlantInfo> fullPlantInfos = new ArrayList<>();


        PlantDAOImpl plantDAO = new PlantDAOImpl();
        PlantImageDAOImpl plantImageDAO = new PlantImageDAOImpl();

        for (Taxonomy t : taxonomies) {
            FullPlantInfo info = new FullPlantInfo();
            info.setTaxonomy(t);
            info.setPlant(plantDAO.getPlantById(t.getPlantID()));
            info.setImageArrayList((ArrayList<PlantImage>) plantImageDAO.getImagesByPlantId(t.getPlantID()));

            fullPlantInfos.add(info);
        }
        return fullPlantInfos;
    }

    private void printPlantInfo(ArrayList<FullPlantInfo> infos) {
        for (FullPlantInfo info : infos) {
            System.out.print(String.format("""
                            ID:%-5s 种名:%-10s 属名:%-10s 科名:%-10s 病名='%-10s' 别名='%-10s' 形态特征='%-20s' 栽培要点='%-20s' 病害防护='%-20s' 应用价值='%-20s' 创建者='%-10s' 创建时间=%-10s 更新时间=%-20s  """,
                    info.getPlant().getPlantID(),
                    info.getTaxonomy().getSpecies(),
                    info.getTaxonomy().getGenus(),
                    info.getTaxonomy().getFamily(),
                    info.getPlant().getDiseaseName(),
                    info.getPlant().getCommonName(),
                    info.getPlant().getMorphology(),
                    info.getPlant().getCultivationTips(),
                    info.getPlant().getPestControlMeasures(),
                    info.getPlant().getApplicationValue(),
                    info.getPlant().getCreator(),
                    info.getPlant().getCreateTime(),
                    info.getPlant().getUpdateTime()));

            int count = 1;
            for (PlantImage image : info.getImageArrayList()) {
                System.out.println(String.format("照片%d信息： 拍摄地点:%-10s 拍摄人:%-10s 图片路径:%-10s 描述:%-10s",
                        count,
                        image.getLocation(),
                        image.getPhotographer(),
                        image.getPhotoPath(),
                        image.getDescription()));
            }
        }

    }

    public String getParam(int i) {
        switch (i) {
            case 1:
                return "diseaseName";
            case 2:
                return "commonName";
            case 3:
                return "morphology";
            case 4:
                return "cultivationTips";
            case 5:
                return "pestControlMeasures";
            case 6:
                return "applicationValue";
            case 7:
                return "family";
            case 8:
                return "genus";
            case 9:
                return "species";


        }
        return null;
    }
}
