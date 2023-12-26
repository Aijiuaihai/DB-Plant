package entity.classify;

public class Distribution {
    private int distributionId;
    private Integer parentDistributionId;  // 使用 Integer 以支持 null 值
    private String name;
    private String level;  // "Province", "City", "County"

    @Override
    public String toString() {
        return "Distribution{" +
                "distributionId=" + distributionId +
                ", parentDistributionId=" + parentDistributionId +
                ", name='" + name + '\'' +
                ", level='" + level + '\'' +
                '}';
    }

    public int getDistributionId() {
        return distributionId;
    }

    public void setDistributionId(int distributionId) {
        this.distributionId = distributionId;
    }

    public Integer getParentDistributionId() {
        return parentDistributionId;
    }

    public void setParentDistributionId(Integer parentDistributionId) {
        this.parentDistributionId = parentDistributionId;
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
