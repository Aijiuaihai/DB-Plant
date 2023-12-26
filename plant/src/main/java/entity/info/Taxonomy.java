package entity.info;

// 分类学实体类
public class Taxonomy {
    private int taxonomyID; // 分类学编号
    private String family; // 科名
    private String genus; // 属名
    private String species; // 种名
    private int plantID; // 植物编号

    // 构造函数
    public Taxonomy(){

    }
    public Taxonomy(int taxonomyID, String family, String genus, String species, int plantID) {
        this.taxonomyID = taxonomyID;
        this.family = family;
        this.genus = genus;
        this.species = species;
        this.plantID = plantID;
    }

    // Getter 和 Setter 方法

    public int getTaxonomyID() {
        return taxonomyID;
    }

    public void setTaxonomyID(int taxonomyID) {
        this.taxonomyID = taxonomyID;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getGenus() {
        return genus;
    }

    public void setGenus(String genus) {
        this.genus = genus;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public int getPlantID() {
        return plantID;
    }

    public void setPlantID(int plantID) {
        this.plantID = plantID;
    }

    @Override
    public String toString() {
        return "Taxonomy{" +
                "taxonomyID=" + taxonomyID +
                ", family='" + family + '\'' +
                ", genus='" + genus + '\'' +
                ", species='" + species + '\'' +
                ", plantID=" + plantID +
                '}';
    }
}