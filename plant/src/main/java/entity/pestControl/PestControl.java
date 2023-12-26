package entity.pestControl;

import java.util.Date;

public class PestControl {
    private int pestControlId; // 病虫害防治编号
    private String pestName; // 病虫害名称
    private String controlMethod; // 防治方法
    private String pesticideName; // 药剂名称
    private String pesticideAmount; // 药剂用量
    private String effectiveDuration; // 作用期限
    private String creator; // 创建人员
    private Date createTime; // 创建时间
    private Date updateTime; // 更新时间

    @Override
    public String toString() {
        return "PestControl{" +
                "pestControlId=" + pestControlId +
                ", pestName='" + pestName + '\'' +
                ", controlMethod='" + controlMethod + '\'' +
                ", pesticideName='" + pesticideName + '\'' +
                ", pesticideAmount='" + pesticideAmount + '\'' +
                ", effectiveDuration='" + effectiveDuration + '\'' +
                ", creator='" + creator + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }

    public int getPestControlId() {
        return pestControlId;
    }

    public void setPestControlId(int pestControlId) {
        this.pestControlId = pestControlId;
    }

    public String getPestName() {
        return pestName;
    }

    public void setPestName(String pestName) {
        this.pestName = pestName;
    }

    public String getControlMethod() {
        return controlMethod;
    }

    public void setControlMethod(String controlMethod) {
        this.controlMethod = controlMethod;
    }

    public String getPesticideName() {
        return pesticideName;
    }

    public void setPesticideName(String pesticideName) {
        this.pesticideName = pesticideName;
    }

    public String getPesticideAmount() {
        return pesticideAmount;
    }

    public void setPesticideAmount(String pesticideAmount) {
        this.pesticideAmount = pesticideAmount;
    }

    public String getEffectiveDuration() {
        return effectiveDuration;
    }

    public void setEffectiveDuration(String effectiveDuration) {
        this.effectiveDuration = effectiveDuration;
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
