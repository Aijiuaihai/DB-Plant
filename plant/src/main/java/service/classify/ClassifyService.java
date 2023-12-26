package service.classify;

import dao.classify.*;
import dao.info.PlantDAOImpl;
import classdefine.classify.Distribution;
import classdefine.classify.PlantClassify;
import classdefine.classify.TaxonomyClassify;
import classdefine.info.Plant;

import java.util.*;

public class ClassifyService {
    public static final String welcome = "欢迎来到 园林植物分类管理 模块";
    public static final String options = """
            1.增加植物分类信息
            2.删除植物分类信息
            3.修改植物分类信息
            4.查询植物分类信息
            5.根据科或属或种查询下属植物信息
            6.根据生长环境查询
            """;

    public static void service() {
        Scanner scanner = new Scanner(System.in);

        System.out.println(welcome);
        System.out.println(options);

        System.out.print("请选择操作（输入数字）: ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                // 增加植物分类信息的代码
                addClassifyInfo();
//                System.out.println("增加植物分类信息");
                break;
            case 2:
                // 删除植物分类信息的代码
                delectClassInfo();
//                System.out.println("删除植物分类信息");
                break;
            case 3:
                // 修改植物分类信息的代码
                updateClassInfo();
//                System.out.println("修改植物分类信息");
                break;
            case 4:
                // 查询植物分类信息的代码
                queryClassInfo();
//                System.out.println("查询植物分类信息");
                break;
            case 5:
                // 根据分布区域查询下属植物信息的代码
                queryByTaxonomyName();
//                System.out.println("根据分布区域查询下属植物信息");
                break;
            case 6:
                // 根据生长环境查询的代码
                queryByGrowthEnvironment();
//                System.out.println("根据生长环境查询");
                break;
            default:
                System.out.println("无效的选择！");
                break;
        }
    }

    public static void addClassifyInfo(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("请输入植物分类信息：");
        System.out.print("植物ID: ");
        int plantId = scanner.nextInt();
        scanner.nextLine(); // 消耗掉多余的换行符

        PlantDAOImpl plantDAO = new PlantDAOImpl();
        Plant p = plantDAO.getPlantById(plantId);

        if (p == null) {
            System.out.println("您输入的植物未找到。");
            return;
        }

        System.out.print("科名: ");
        String FamilyName = scanner.nextLine();

        System.out.print("属名: ");
        String GenusName = scanner.nextLine();

        System.out.print("种名: ");
        String SpeciesName = scanner.nextLine();

        System.out.print("别名: ");
        String commonName = scanner.nextLine();

        System.out.print("生长环境: ");
        String growthEnvironment = scanner.nextLine();

        System.out.print("所在省: ");
        String ProvinceName = scanner.nextLine();

        System.out.print("所在市: ");
        String CityName = scanner.nextLine();

        System.out.print("所在县: ");
        String CountyName = scanner.nextLine();

//        System.out.print("分类ID: ");
//        int taxonomyId = scanner.nextInt();
//
//        System.out.print("分布区域ID: ");
//        int distributionId = scanner.nextInt();
//        scanner.nextLine();

        System.out.print("创建者: ");
        String creator = scanner.nextLine();

        Date currentTime = new Date(); // 获取当前时间作为创建时间和更新时间
        //添加省份
        Distribution distribution = new Distribution();
        distribution.setName(ProvinceName);
        distribution.setLevel("Province");
//        distribution.setParentDistributionId(-1);
        DistributionDaoImpI distributionDao = new DistributionDaoImpI();
        Boolean result1 = distributionDao.addDistribution(distribution,false);
        if (result1) {
            System.out.println("植物分类省份信息添加成功！");
        } else {
            System.out.println("植物分类信息省份添加失败。");
        }
        //添加市
        Distribution distribution1 = new Distribution();
        distribution1.setName(CityName);
        distribution1.setLevel("City");
        distribution1.setParentDistributionId(distribution.getDistributionId());
        Boolean result2 = distributionDao.addDistribution(distribution1,true);
        if (result2) {
            System.out.println("植物分类市信息添加成功！");
        } else {
            System.out.println("植物分类市信息添加失败。");
        }
        //添加县
        Distribution distribution2 = new Distribution();
        distribution2.setName(CountyName);
        distribution2.setLevel("County");
        distribution2.setParentDistributionId(distribution1.getDistributionId());
        Boolean result3 = distributionDao.addDistribution(distribution2,true);
        if (result3) {
            System.out.println("植物分类县信息添加成功！");
        } else {
            System.out.println("植物分类县信息添加失败。");
        }

        //添加科名
        TaxonomyClassify taxonomyClassify = new TaxonomyClassify();
        taxonomyClassify.setName(FamilyName);
        taxonomyClassify.setLevel("Family");
        taxonomyClassify.setParentTaxonomyId(-1);
        TaxonomyClassifyDaoImpI taxonomyClassifyDao = new TaxonomyClassifyDaoImpI();
        Boolean result4 = taxonomyClassifyDao.addTaxonomyClassify(taxonomyClassify,false);
        if (result4) {
            System.out.println("植物分类科名信息添加成功！");
        } else {
            System.out.println("植物分类科名信息添加失败。");
        }
        //添加属名
        TaxonomyClassify taxonomyClassify1 = new TaxonomyClassify();
        taxonomyClassify1.setName(GenusName);
        taxonomyClassify1.setLevel("Genus");
        taxonomyClassify1.setParentTaxonomyId(taxonomyClassify.getTaxonomyId());
        Boolean result5 = taxonomyClassifyDao.addTaxonomyClassify(taxonomyClassify1,true);
        if (result5) {
            System.out.println("植物分类属名信息添加成功！");
        } else {
            System.out.println("植物分类属名信息添加失败。");
        }
        //添加种名
        TaxonomyClassify taxonomyClassify2 = new TaxonomyClassify();
        taxonomyClassify2.setName(SpeciesName);
        taxonomyClassify2.setLevel("Species");
        taxonomyClassify2.setParentTaxonomyId(taxonomyClassify1.getTaxonomyId());
        Boolean result6 = taxonomyClassifyDao.addTaxonomyClassify(taxonomyClassify2,true);
        if (result6) {
            System.out.println("植物分类种名信息添加成功！");
        } else {
            System.out.println("植物分类种名信息添加失败。");
        }


        PlantClassify plant = new PlantClassify();
        // 设置 PlantClassify 对象的各个属性
        plant.setPlantId(plantId);
        plant.setCommonName(commonName);
        plant.setGrowthEnvironment(growthEnvironment);
        plant.setTaxonomyId(taxonomyClassify2.getTaxonomyId());
        plant.setDistributionId(distribution2.getDistributionId());
        plant.setCreator(creator);
        plant.setCreateTime(currentTime);
//        plant.setUpdateTime(null);

        PlantClassifyDaoImpI plantClassifyDao = new PlantClassifyDaoImpI();
        boolean result = plantClassifyDao.addPlantClassify(plant);

        if (result) {
            System.out.println("植物分类信息添加成功！");
        } else {
            System.out.println("植物分类信息添加失败。");
        }

    }

    public static void delectClassInfo(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入要删除植物的id");
        int plantId = scanner.nextInt();

        PlantClassifyDaoImpI plantClassifyDao = new PlantClassifyDaoImpI();
        PlantClassify plantClassify = new PlantClassify();
        plantClassify = plantClassifyDao.getPlantClassifyById(plantId);
        int tax = plantClassify.getTaxonomyId();
        int dis = plantClassify.getDistributionId();

        Boolean result = plantClassifyDao.deletePlantClassify(plantId);
        if(result){
            System.out.println("已成功删除");
        }else {
            System.out.println("删除失败");
        }

        //删除科属种
        TaxonomyClassifyDaoImpI taxonomyClassifyDao = new TaxonomyClassifyDaoImpI();
        TaxonomyClassify taxonomyClassify = taxonomyClassifyDao.getTaxonomyClassifyById(tax);
        TaxonomyClassify taxonomyClassify1 = taxonomyClassifyDao.getTaxonomyClassifyById(taxonomyClassify.getParentTaxonomyId());
        Boolean result1 = taxonomyClassifyDao.deleteTaxonomyClassify(tax);
        Boolean result2 = taxonomyClassifyDao.deleteTaxonomyClassify(taxonomyClassify.getParentTaxonomyId());
        Boolean result3 = taxonomyClassifyDao.deleteTaxonomyClassify(taxonomyClassify1.getParentTaxonomyId());
        if(result1 && result2 && result3 ){
            System.out.println("科属种已成功删除");
        }else {
            System.out.println("科属种删除失败");
        }
        //删除省市县
        DistributionDaoImpI distributionDao = new DistributionDaoImpI();
        Distribution distribution = distributionDao.getDistributionById(dis);
        Distribution distribution1 = distributionDao.getDistributionById(distribution.getParentDistributionId());
        Boolean result4 = distributionDao.deleteDistribution(dis);
        Boolean result5 = distributionDao.deleteDistribution(distribution.getParentDistributionId());
        Boolean result6 = distributionDao.deleteDistribution(distribution1.getParentDistributionId());
        if(result4 && result5 && result6){
            System.out.println("省市县已成功删除");
        }else {
            System.out.println("省市县删除失败");
        }

    }

    public static void updateClassInfo() {
        Scanner scanner = new Scanner(System.in);

        // 首先，需要输入要更新的记录的标识符（例如植物分类ID）
        System.out.print("请输入需要更新的植物分类ID: ");
        int plantClassifyId = scanner.nextInt();
        scanner.nextLine(); // 消耗掉多余的换行符

        // 接下来，获取用户输入的更新数据
        System.out.print("科名: ");
        String familyName = scanner.nextLine();

        System.out.print("属名: ");
        String genusName = scanner.nextLine();

        System.out.print("种名: ");
        String speciesName = scanner.nextLine();

        System.out.print("别名: ");
        String commonName = scanner.nextLine();

        System.out.print("生长环境: ");
        String growthEnvironment = scanner.nextLine();

        System.out.print("所在省: ");
        String provinceName = scanner.nextLine();

        System.out.print("所在市: ");
        String cityName = scanner.nextLine();

        System.out.print("所在县: ");
        String countyName = scanner.nextLine();

        System.out.print("更新者: ");
        String updater = scanner.nextLine();

        Date currentTime = new Date(); // 获取当前时间作为更新时间

        // 这里添加更新植物分类信息的逻辑
        // 可能需要先通过 plantClassifyId 查询到原有的记录，然后对其进行更新
        // 更新的逻辑类似于添加，但是需要考虑如何处理已存在的关联数据
        PlantClassifyDaoImpI plantClassifyDao = new PlantClassifyDaoImpI();
        PlantClassify plantClassify = plantClassifyDao.getPlantClassifyById(plantClassifyId);
        int tax = plantClassify.getTaxonomyId();
        int dis = plantClassify.getDistributionId();
        Date time = plantClassify.getCreateTime();

        PlantClassify plant = new PlantClassify();
        plant.setPlantId(plantClassifyId);
        plant.setCommonName(commonName);
        plant.setGrowthEnvironment(growthEnvironment);
        plant.setCreator(updater);
        plant.setCreateTime(time);
        plant.setTaxonomyId(tax);
        plant.setDistributionId(dis);
        plant.setUpdateTime(currentTime);
        boolean result = plantClassifyDao.updatePlantClassify(plant);
        if (result) {
            System.out.println("植物分类信息更新成功！");
        } else {
            System.out.println("植物分类信息更新失败。");
        }

        //更新科属种
        TaxonomyClassifyDaoImpI taxonomyClassifyDao = new TaxonomyClassifyDaoImpI();
        TaxonomyClassify taxonomyClassify = taxonomyClassifyDao.getTaxonomyClassifyById(tax);
        taxonomyClassify.setName(speciesName);
        TaxonomyClassify taxonomyClassify1 = taxonomyClassifyDao.getTaxonomyClassifyById(taxonomyClassify.getParentTaxonomyId());
        taxonomyClassify1.setName(genusName);
        TaxonomyClassify taxonomyClassify2 = taxonomyClassifyDao.getTaxonomyClassifyById(taxonomyClassify1.getParentTaxonomyId());
        taxonomyClassify2.setName(familyName);
        Boolean result1 = taxonomyClassifyDao.updateTaxonomyClassify(taxonomyClassify);
        Boolean result2 = taxonomyClassifyDao.updateTaxonomyClassify(taxonomyClassify1);
        Boolean result3 = taxonomyClassifyDao.updateTaxonomyClassify(taxonomyClassify2);
        if (result1 && result2 && result3) {
            System.out.println("科属种已成功更新");
        } else {
            System.out.println("科属种更新失败");
        }
        //更新省市县
        DistributionDaoImpI distributionDao = new DistributionDaoImpI();
        Distribution distribution = distributionDao.getDistributionById(dis);
        distribution.setName(countyName);
        Distribution distribution1 = distributionDao.getDistributionById(distribution.getParentDistributionId());
        distribution1.setName(cityName);
        Distribution distribution2 = distributionDao.getDistributionById(distribution1.getParentDistributionId());
        distribution2.setName(provinceName);
        Boolean result4 = distributionDao.updateDistribution(distribution);
        Boolean result5 = distributionDao.updateDistribution(distribution1);
        Boolean result6 = distributionDao.updateDistribution(distribution2);
        if (result4 && result5 && result6) {
            System.out.println("省市县已成功更新");
        } else {
            System.out.println("省市县更新失败");
        }
    }

    public static void queryClassInfo(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("输入要查询植物的id");
        int plantClassifyId = scanner.nextInt();
        scanner.nextLine(); // 消耗掉多余的换行符

        PlantClassifyDaoImpI plantClassifyDao = new PlantClassifyDaoImpI();
        PlantClassify plantClassify = plantClassifyDao.getPlantClassifyById(plantClassifyId);

        TaxonomyClassifyDaoImpI taxonomyClassifyDao = new TaxonomyClassifyDaoImpI();
        TaxonomyClassify taxonomyClassify = taxonomyClassifyDao.getTaxonomyClassifyById(plantClassify.getTaxonomyId());
        TaxonomyClassify taxonomyClassify1 = taxonomyClassifyDao.getTaxonomyClassifyById(taxonomyClassify.getParentTaxonomyId());
        TaxonomyClassify taxonomyClassify2 = taxonomyClassifyDao.getTaxonomyClassifyById(taxonomyClassify1.getParentTaxonomyId());

        DistributionDaoImpI distributionDao = new DistributionDaoImpI();
        Distribution distribution = distributionDao.getDistributionById(plantClassify.getDistributionId());
        Distribution distribution1 = distributionDao.getDistributionById(distribution.getParentDistributionId());
        Distribution distribution2 = distributionDao.getDistributionById(distribution1.getParentDistributionId());

        System.out.println("科名："+ taxonomyClassify2.getName());
        System.out.println("属名："+ taxonomyClassify1.getName());
        System.out.println("种名："+ taxonomyClassify.getName());
        System.out.println("别名："+ plantClassify.getCommonName());
        System.out.println("生长环境；"+ plantClassify.getGrowthEnvironment());
        System.out.println("所在省份："+ distribution2.getName());
        System.out.println("所在市："+ distribution1.getName());
        System.out.println("所在县："+ distribution.getName());
        System.out.println("创建人员："+ plantClassify.getCreator());
        System.out.println("创建时间："+ plantClassify.getCreateTime());
        System.out.println("更新时间："+ plantClassify.getUpdateTime());


    }

    public static void queryByGrowthEnvironment(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("输入要查询植物的生长环境");
        String ge = scanner.nextLine();
//        scanner.nextLine(); // 消耗掉多余的换行符

        PlantClassifyDaoImpI plantClassifyDao = new PlantClassifyDaoImpI();
        List<PlantClassify> plantClassifyList = plantClassifyDao.queryByGrowthEnvironment(ge);
        if(plantClassifyList != null){
            for(PlantClassify plantClassify : plantClassifyList){
                TaxonomyClassifyDaoImpI taxonomyClassifyDao = new TaxonomyClassifyDaoImpI();
                TaxonomyClassify taxonomyClassify = taxonomyClassifyDao.getTaxonomyClassifyById(plantClassify.getTaxonomyId());
                TaxonomyClassify taxonomyClassify1 = taxonomyClassifyDao.getTaxonomyClassifyById(taxonomyClassify.getParentTaxonomyId());
                TaxonomyClassify taxonomyClassify2 = taxonomyClassifyDao.getTaxonomyClassifyById(taxonomyClassify1.getParentTaxonomyId());

                DistributionDaoImpI distributionDao = new DistributionDaoImpI();
                Distribution distribution = distributionDao.getDistributionById(plantClassify.getDistributionId());
                Distribution distribution1 = distributionDao.getDistributionById(distribution.getParentDistributionId());
                Distribution distribution2 = distributionDao.getDistributionById(distribution1.getParentDistributionId());

                System.out.println("科名："+ taxonomyClassify2.getName());
                System.out.println("属名："+ taxonomyClassify1.getName());
                System.out.println("种名："+ taxonomyClassify.getName());
                System.out.println("别名："+ plantClassify.getCommonName());
                System.out.println("生长环境；"+ plantClassify.getGrowthEnvironment());
                System.out.println("所在省份："+ distribution2.getName());
                System.out.println("所在市："+ distribution1.getName());
                System.out.println("所在县："+ distribution.getName());
                System.out.println("创建人员："+ plantClassify.getCreator());
                System.out.println("创建时间："+ plantClassify.getCreateTime());
                System.out.println("更新时间："+ plantClassify.getUpdateTime());
                System.out.println(" ------------------------------------");
            }
        }else {
            System.out.println("未检索到对应的植物信息");
        }


    }

    public static void queryByTaxonomyName(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("输入要查询植物的科或属或种名");
        String tn = scanner.nextLine();
        PlantClassifyDaoImpI plantClassifyDao = new PlantClassifyDaoImpI();
        List<Integer> integerList = plantClassifyDao.getPlantIdsByTaxonomy(tn);
        if(integerList != null){
            for (Integer integer:integerList){
                PlantClassify plantClassify = plantClassifyDao.getPlantClassifyById(integer);
                TaxonomyClassifyDaoImpI taxonomyClassifyDao = new TaxonomyClassifyDaoImpI();
                TaxonomyClassify taxonomyClassify = taxonomyClassifyDao.getTaxonomyClassifyById(plantClassify.getTaxonomyId());
                TaxonomyClassify taxonomyClassify1 = taxonomyClassifyDao.getTaxonomyClassifyById(taxonomyClassify.getParentTaxonomyId());
                TaxonomyClassify taxonomyClassify2 = taxonomyClassifyDao.getTaxonomyClassifyById(taxonomyClassify1.getParentTaxonomyId());

                DistributionDaoImpI distributionDao = new DistributionDaoImpI();
                Distribution distribution = distributionDao.getDistributionById(plantClassify.getDistributionId());
                Distribution distribution1 = distributionDao.getDistributionById(distribution.getParentDistributionId());
                Distribution distribution2 = distributionDao.getDistributionById(distribution1.getParentDistributionId());

                System.out.println("科名："+ taxonomyClassify2.getName());
                System.out.println("属名："+ taxonomyClassify1.getName());
                System.out.println("种名："+ taxonomyClassify.getName());
                System.out.println("别名："+ plantClassify.getCommonName());
                System.out.println("生长环境；"+ plantClassify.getGrowthEnvironment());
                System.out.println("所在省份："+ distribution2.getName());
                System.out.println("所在市："+ distribution1.getName());
                System.out.println("所在县："+ distribution.getName());
                System.out.println("创建人员："+ plantClassify.getCreator());
                System.out.println("创建时间："+ plantClassify.getCreateTime());
                System.out.println("更新时间："+ plantClassify.getUpdateTime());
                System.out.println(" ------------------------------------");
            }
        }else {
            System.out.println("未检索到对应植物信息");
        }

    }


}