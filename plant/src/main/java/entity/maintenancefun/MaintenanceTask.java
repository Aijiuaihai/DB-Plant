package entity.maintenancefun;

import java.util.Date;

public class MaintenanceTask {
    private int taskId;
    private String taskName;
    private String executionTime;
    private String location;
    private String personnel;
    private String description;
    private int plantId; // 对应植物的ID
    private String creator;
    private Date createTime;
    private Date updateTime;
    private String bool;

    @Override
    public String toString() {
        return "MaintenanceTask{" +
                "taskId=" + taskId +
                ", taskName='" + taskName + '\'' +
                ", executionTime=" + executionTime +
                ", location='" + location + '\'' +
                ", personnel='" + personnel + '\'' +
                ", description='" + description + '\'' +
                ", plantId=" + plantId +
                ", creator='" + creator + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(String executionTime) {
        this.executionTime = executionTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPersonnel() {
        return personnel;
    }

    public void setPersonnel(String personnel) {
        this.personnel = personnel;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPlantId() {
        return plantId;
    }

    public void setPlantId(int plantId) {
        this.plantId = plantId;
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
    public String getbool() {
        return bool;
    }

    public void setbool(String bool) {
        this.bool = bool;
    }
}
