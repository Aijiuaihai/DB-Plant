package entity.info;

import java.util.Date;

// 植物实体类
public class Plant {
    private int plantID; // 植物编号
    private String diseaseName; // 病名
    private String commonName; // 别名
    private String morphology; // 形态特征
    private String cultivationTips; // 栽培技术要点
    private String pestControlMeasures; // 病虫害防治措施
    private String applicationValue; // 应用价值
    private String creator; // 创建者
    private Date createTime; // 创建时间
    private Date updateTime; // 更新时间

    // 构造函数

    public Plant(int plantID, String diseaseName, String commonName, String morphology, String cultivationTips, String pestControlMeasures, String applicationValue, String creator, Date createTime, Date updateTime) {
        this.plantID = plantID;
        this.diseaseName = diseaseName;
        this.commonName = commonName;
        this.morphology = morphology;
        this.cultivationTips = cultivationTips;
        this.pestControlMeasures = pestControlMeasures;
        this.applicationValue = applicationValue;
        this.creator = creator;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Plant() {

    }

    // Getter 和 Setter 方法

    public int getPlantID() {
        return plantID;
    }

    public void setPlantID(int plantID) {
        this.plantID = plantID;
    }

    public String getDiseaseName() {
        return diseaseName;
    }

    public void setDiseaseName(String diseaseName) {
        this.diseaseName = diseaseName;
    }

    public String getCommonName() {
        return commonName;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    public String getMorphology() {
        return morphology;
    }

    public void setMorphology(String morphology) {
        this.morphology = morphology;
    }

    public String getCultivationTips() {
        return cultivationTips;
    }

    public void setCultivationTips(String cultivationTips) {
        this.cultivationTips = cultivationTips;
    }

    public String getPestControlMeasures() {
        return pestControlMeasures;
    }

    public void setPestControlMeasures(String pestControlMeasures) {
        this.pestControlMeasures = pestControlMeasures;
    }

    public String getApplicationValue() {
        return applicationValue;
    }

    public void setApplicationValue(String applicationValue) {
        this.applicationValue = applicationValue;
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

    @Override
    public String toString() {
        return String.format(
                "ID=%-4d, 病名='%-10s', 别名='%-10s', 形态特征='%-20s', 栽培要点='%-20s', " +
                        "病害防护='%-20s', 应用价值='%-20s', 创建者='%-10s', 创建时间=%-10s, 更新时间=%-10s",

                plantID,
                diseaseName,
                commonName,
                morphology,
                cultivationTips,
                pestControlMeasures,
                applicationValue,
                creator,
                createTime,
                updateTime);
    }


}



