package dao.classify;

import entity.classify.TaxonomyClassify;
import entity.info.Taxonomy;

public interface TaxonomyClassifyDao {
    // 根据分类学编号获取分类学信息
    TaxonomyClassify getTaxonomyClassifyById(int taxonomyID);

    // 根据植物编号获取对应的分类学信息
    TaxonomyClassify getTaxonomyClassifyByPlantId(int plantID);

    // 新增分类学信息
    boolean addTaxonomyClassify(TaxonomyClassify taxonomy,boolean addClassify);

    // 更新分类学信息
    boolean updateTaxonomyClassify(TaxonomyClassify taxonomy);

    // 删除分类学信息
    boolean deleteTaxonomyClassify(int taxonomyID);
}
