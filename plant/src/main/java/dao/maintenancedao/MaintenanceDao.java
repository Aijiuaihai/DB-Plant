package dao.maintenancedao;


import entity.maintenancefun.MaintenanceTask;

public interface MaintenanceDao {
    // 根据养护编号获取养护信息
    MaintenanceTask getMaintenanceTaskById(int taskId);

    // 新增养护信息
    boolean addMaintenanceTask(MaintenanceTask maintenanceTask);

    // 更新养护信息
    boolean updateMaintenanceTask(MaintenanceTask maintenanceTask);

    // 删除养护信息
    boolean deleteMaintenanceTask(int taskId);
}
