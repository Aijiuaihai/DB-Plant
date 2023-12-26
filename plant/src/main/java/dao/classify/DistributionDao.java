package dao.classify;

import entity.classify.Distribution;
import entity.classify.TaxonomyClassify;

public interface DistributionDao {
    // 根据地区编号获取地区信息
    Distribution getDistributionById(int distributionId);

    // 根据植物编号获取对应的地区信息
    Distribution getDistributionByPlantId(int plantID);

    // 新增地区信息
    boolean addDistribution(Distribution distribution,boolean addParent);

    // 更新地区信息
    boolean updateDistribution(Distribution distribution);

    // 删除地区信息
    boolean deleteDistribution(int distributionId);
}
