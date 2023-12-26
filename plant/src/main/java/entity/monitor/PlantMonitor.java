package entity.monitor;

import java.util.Date;

public class PlantMonitor {
    private int id;
    private Date monitoringTime;
    private String monitoringPersonnel;
    private String monitoringLocation;
    private String monitoredObject;
    private int monitoredObjectId;
    private float monitorValue;

    private String monitoringIndex;
    private String monitoringDevice;
    private String createdBy;
    private Date createdTime;
    private Date updatedTime;

    // 默认构造函数
    public PlantMonitor() {
        // 可选：在这里进行初始化操作
    }

    // Getters 和 Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getMonitoringTime() {
        return monitoringTime;
    }

    public void setMonitoringTime(Date monitoringTime) {
        this.monitoringTime = monitoringTime;
    }

    public String getMonitoringPersonnel() {
        return monitoringPersonnel;
    }

    public void setMonitoringPersonnel(String monitoringPersonnel) {
        this.monitoringPersonnel = monitoringPersonnel;
    }

    public String getMonitoringLocation() {
        return monitoringLocation;
    }

    public void setMonitoringLocation(String monitoringLocation) {
        this.monitoringLocation = monitoringLocation;
    }

    public String getMonitoredObject() {
        return monitoredObject;
    }

    public void setMonitoredObject(String monitoredObject) {
        this.monitoredObject = monitoredObject;
    }

    public String getMonitoringIndex() {
        return monitoringIndex;
    }

    public void setMonitoringIndex(String monitoringIndex) {
        this.monitoringIndex = monitoringIndex;
    }

    public String getMonitoringDevice() {
        return monitoringDevice;
    }

    public void setMonitoringDevice(String monitoringDevice) {
        this.monitoringDevice = monitoringDevice;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    public int getMonitoredObjectId() {
        return monitoredObjectId;
    }

    public void setMonitoredObjectId(int monitoredObjectId) {
        this.monitoredObjectId = monitoredObjectId;
    }

    public float getMonitorValue() {
        return monitorValue;
    }

    public void setMonitorValue(float monitorValue) {
        this.monitorValue = monitorValue;
    }

    @Override
    public String toString() {

        return "ID=" + id +
                " 监测时间=" + monitoringTime +
                " 检测人='" + monitoringPersonnel + '\'' +
                " 地点='" + monitoringLocation + '\'' +
                " 植物名='" + monitoredObject + '\'' +
                " 植物ID=" + monitoredObjectId +
                " 检测值=" + monitorValue +
                " 检测指标='" + monitoringIndex + '\'' +
                " 检测设备='" + monitoringDevice + '\'' +
                " 创建者='" + createdBy + '\'' +
                " 创建时间=" + createdTime +
                " 更新时间=" + updatedTime;

    }


}
