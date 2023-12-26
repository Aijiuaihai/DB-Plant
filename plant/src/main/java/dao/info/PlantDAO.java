package dao.info;

import classdefine.info.Plant;

import java.util.List;

public interface PlantDAO {
    // 根据植物编号获取植物信息
    Plant getPlantById(int plantID);
    List<Plant> getPlantBySingleParam(String sql);

    // 获取所有植物信息
    List<Plant> getAllPlants();

    // 新增植物信息
    boolean addPlant(Plant plant);

    // 更新植物信息
    boolean updatePlant(Plant plant);

    // 删除植物信息
    boolean deletePlant(int plantID);

}



