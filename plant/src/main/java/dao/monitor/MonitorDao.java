package dao.monitor;

import classdefine.monitor.PlantMonitor;

import java.util.List;

public interface MonitorDao {
    // 创建新的监测记录
    boolean addPlantMonitor(PlantMonitor plantMonitor);

    // 根据 ID 获取监测记录
    PlantMonitor getPlantMonitorById(int id);

    // 更新监测记录
    boolean updatePlantMonitor(PlantMonitor plantMonitor);

    // 删除监测记录
    boolean deletePlantMonitor(int id);

    // 获取所有监测记录
    List<PlantMonitor> getAllPlantMonitors();

    List<PlantMonitor> getAllMonitorsByPlantId(int id);

    Float getMaxMonitorValue(int monitoredObjectId, String monitoringIndex);
    Float getAverageMonitorValue(int monitoredObjectId, String monitoringIndex);

    // 根据监测地点获取监测记录
    List<PlantMonitor> getPlantMonitorsByLocation(String location);

    // 根据监测对象获取监测记录
    List<PlantMonitor> getPlantMonitorsByObjectId(int id);

    // 根据创建人员获取监测记录
    List<PlantMonitor> getPlantMonitorsByCreator(String creator);
}
