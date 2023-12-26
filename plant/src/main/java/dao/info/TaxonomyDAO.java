package dao.info;


import entity.info.Taxonomy;

import java.util.ArrayList;
import java.util.List;

public interface TaxonomyDAO {
    // 根据分类学编号获取分类学信息
    Taxonomy getTaxonomyById(int taxonomyID);

    List<Taxonomy> getTaxonomyBySingleParam(String sql);
    Taxonomy getTaxonomyBySpecies(String species);

    // 根据植物编号获取对应的分类学信息
    Taxonomy getTaxonomyByPlantId(int plantID);

    // 新增分类学信息
    boolean addTaxonomy(Taxonomy taxonomy);

    // 更新分类学信息
    boolean updateTaxonomy(Taxonomy taxonomy);

    // 删除分类学信息
    boolean deleteTaxonomy(int taxonomyID);
    boolean deleteTaxonomyByPlantId(int id);

    ArrayList<String> getAllFamily();
    ArrayList<Taxonomy> getByFamilyName(String name);

    boolean createView(String s);
}