package dao.maintenancedao;


import entity.maintenancefun.MaintenanceTask;
import util.MySQLConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MaintenanceDaoImpI implements MaintenanceDao{

    @Override
    public MaintenanceTask getMaintenanceTaskById(int taskId) {
        MaintenanceTask maintenanceTask = null;

        try {
            Connection connection = MySQLConnectionPool.getConnection();
            String sql = "SELECT * FROM maintenancetask WHERE taskID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, taskId); // 设置查询参数

            ResultSet rs = ps.executeQuery();

            if (rs.next()) { // 如果找到记录
                maintenanceTask = new MaintenanceTask();
                maintenanceTask.setTaskId(rs.getInt("taskID"));
                maintenanceTask.setTaskName(rs.getString("taskName"));
                maintenanceTask.setExecutionTime(rs.getString("executionTime"));
                maintenanceTask.setLocation(rs.getString("location"));
                maintenanceTask.setPersonnel(rs.getString("personnel"));
                maintenanceTask.setDescription(rs.getString("description"));
                maintenanceTask.setPlantId(rs.getInt("plantID"));
                maintenanceTask.setCreator(rs.getString("creator"));
                maintenanceTask.setCreateTime(rs.getDate("createTime"));
                maintenanceTask.setUpdateTime(rs.getDate("updateTime"));
                maintenanceTask.setbool(rs.getString("bool"));
            }

            rs.close();
            ps.close();
            connection.close(); // 关闭资源

        } catch (Exception e) {
            e.printStackTrace();
        }

        return maintenanceTask;
    }

    @Override
    public boolean addMaintenanceTask(MaintenanceTask maintenanceTask) {
        boolean result = false;

        try {
            Connection connection = MySQLConnectionPool.getConnection();
            String sql = "INSERT INTO maintenancetask (taskName, executionTime, location, personnel, description, plantID, creator, createTime) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql);

            // 设置 PreparedStatement 参数
            ps.setString(1, maintenanceTask.getTaskName());
            ps.setString(2, maintenanceTask.getExecutionTime());
            ps.setString(3, maintenanceTask.getLocation());
            ps.setString(4, maintenanceTask.getPersonnel());
            ps.setString(5, maintenanceTask.getDescription());
            ps.setInt(6, maintenanceTask.getPlantId());
            ps.setString(7, maintenanceTask.getCreator());
            ps.setTimestamp(8, new java.sql.Timestamp(maintenanceTask.getCreateTime().getTime()));
//            ps.setTimestamp(9, new java.sql.Timestamp(maintenanceTask.getUpdateTime().getTime()));

            // 执行更新
            int rowsAffected = ps.executeUpdate();
            result = rowsAffected > 0; // 如果插入了记录，则返回 true
            if (rowsAffected == 1) {
                ResultSet generatedKeys = ps.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int generatedId = generatedKeys.getInt(1); // 获取自动生成的主键值
// 使用生成的主键值进行操作，比如输出到控制台或者存储在对象中
                    System.out.println("刚添加的植物养护信息ID为：" + generatedId);
// 将生成的主键值设置到对象中
                    maintenanceTask.setTaskId(generatedId);
                }
            }
            ps.close();
            connection.close(); // 关闭资源

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }


    @Override
    public boolean updateMaintenanceTask(MaintenanceTask maintenanceTask) {
        boolean result = false;

        try {
            Connection connection = MySQLConnectionPool.getConnection();
            String sql = "UPDATE maintenancetask SET taskName = ?, executionTime = ?, location = ?, personnel = ?, description = ?, plantID = ?, creator = ?, updateTime = ? WHERE taskID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);

            // 设置 PreparedStatement 参数
            ps.setString(1, maintenanceTask.getTaskName());
            ps.setString(2, maintenanceTask.getExecutionTime());
            ps.setString(3, maintenanceTask.getLocation());
            ps.setString(4, maintenanceTask.getPersonnel());
            ps.setString(5, maintenanceTask.getDescription());
            ps.setInt(6, maintenanceTask.getPlantId());
            ps.setString(7, maintenanceTask.getCreator());
//            ps.setTimestamp(8, new java.sql.Timestamp(maintenanceTask.getCreateTime().getTime()));
            ps.setTimestamp(8, new java.sql.Timestamp(maintenanceTask.getUpdateTime().getTime()));
            ps.setInt(9, maintenanceTask.getTaskId()); // 设置 taskID 来指定更新哪一条记录

            // 执行更新
            int rowsAffected = ps.executeUpdate();
            result = rowsAffected > 0; // 如果更新了记录，则返回 true

            ps.close();
            connection.close(); // 关闭资源

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }


    public boolean updateMaintenanceTask2(int taskID,String bool) {
        boolean result = false;
        try {
            Connection connection = MySQLConnectionPool.getConnection();
            String sql = "UPDATE maintenancetask SET bool=? WHERE taskID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1,bool);
            ps.setInt(2, taskID);

            // 执行更新
            int rowsAffected = ps.executeUpdate();
            result = rowsAffected > 0; // 如果更新了记录，则返回 true

            ps.close();
            connection.close(); // 关闭资源

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }



    @Override
    public boolean deleteMaintenanceTask(int taskId) {
        boolean result = false;

        try {
            Connection connection = MySQLConnectionPool.getConnection();
            String sql = "DELETE FROM maintenancetask WHERE taskID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);

            // 设置 PreparedStatement 参数
            ps.setInt(1, taskId); // 设置要删除的任务的 taskId

            // 执行删除
            int rowsAffected = ps.executeUpdate();
            result = rowsAffected > 0; // 如果删除了记录，则返回 true

            ps.close();
            connection.close(); // 关闭资源

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

}

