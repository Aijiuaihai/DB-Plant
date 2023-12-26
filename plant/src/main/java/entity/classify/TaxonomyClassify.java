package entity.classify;

public class TaxonomyClassify {
    private int taxonomyId;
    private Integer parentTaxonomyId;  // 使用 Integer 以支持 null 值
    private String name;
    private String level;  // "Family", "Genus", "Species"

    @Override
    public String toString() {
        return "TaxonomyClassify{" +
                "taxonomyId=" + taxonomyId +
                ", parentTaxonomyId=" + parentTaxonomyId +
                ", name='" + name + '\'' +
                ", level='" + level + '\'' +
                '}';
    }

    public int getTaxonomyId() {
        return taxonomyId;
    }

    public void setTaxonomyId(int taxonomyId) {
        this.taxonomyId = taxonomyId;
    }

    public Integer getParentTaxonomyId() {
        return parentTaxonomyId;
    }

    public void setParentTaxonomyId(Integer parentTaxonomyId) {
        this.parentTaxonomyId = parentTaxonomyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
