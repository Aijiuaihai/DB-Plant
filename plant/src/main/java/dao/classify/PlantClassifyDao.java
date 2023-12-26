package dao.classify;

import entity.classify.PlantClassify;
import entity.info.Plant;

import java.util.List;

public interface PlantClassifyDao {
    //根据植物编号获取植物信息
    PlantClassify getPlantClassifyById(int PlantId);

    // 获取所有植物信息
    List<PlantClassify> getAllPlantClassifies();

    // 新增植物信息
    boolean addPlantClassify(PlantClassify plant);

    // 更新植物信息
    boolean updatePlantClassify(PlantClassify plant);

    // 删除植物信息
    boolean deletePlantClassify(int plantID);

    //根据生长环境模糊查询
    List<PlantClassify> queryByGrowthEnvironment(String GrowthEnvironment);

    List<Integer> getPlantIdsByTaxonomy(String taxonomyName);
}
