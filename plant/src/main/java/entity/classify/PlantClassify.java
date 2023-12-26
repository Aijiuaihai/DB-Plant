package entity.classify;

import java.util.Date;

public class PlantClassify {
    private int plantId;
    private String commonName;
    private String growthEnvironment;
    private int taxonomyId;  // 关联到分类
    private int distributionId;  // 关联到分区
    private String creator;
    private Date createTime;
    private Date updateTime;

    @Override
    public String toString() {
        return "PlantClassify{" +
                "plantId=" + plantId +
                ", commonName='" + commonName + '\'' +
                ", growthEnvironment='" + growthEnvironment + '\'' +
                ", taxonomyId=" + taxonomyId +
                ", distributionId=" + distributionId +
                ", creator='" + creator + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }

    public int getPlantId() {
        return plantId;
    }

    public void setPlantId(int plantId) {
        this.plantId = plantId;
    }

    public String getCommonName() {
        return commonName;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    public String getGrowthEnvironment() {
        return growthEnvironment;
    }

    public void setGrowthEnvironment(String growthEnvironment) {
        this.growthEnvironment = growthEnvironment;
    }

    public int getTaxonomyId() {
        return taxonomyId;
    }

    public void setTaxonomyId(int taxonomyId) {
        this.taxonomyId = taxonomyId;
    }

    public int getDistributionId() {
        return distributionId;
    }

    public void setDistributionId(int distributionId) {
        this.distributionId = distributionId;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
