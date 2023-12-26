package dao.info;

import classdefine.info.PlantImage;

import java.util.List;

public interface PlantImageDAO {
    // 根据图片编号获取图片信息
    PlantImage getImageById(int imageID);
    PlantImage getImageByPlantId(int plantID);

    // 根据植物编号获取该植物的所有图片信息
    List<PlantImage> getImagesByPlantId(int plantID);

    // 新增图片信息
    boolean addImage(PlantImage image);

    // 删除图片信息
    boolean deleteImageById(int imageID);
    boolean deleteImageByPlantId(int imageID);
}