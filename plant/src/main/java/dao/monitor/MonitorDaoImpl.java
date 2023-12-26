package dao.monitor;

import classdefine.monitor.PlantMonitor;
import pool.MySQLConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MonitorDaoImpl implements MonitorDao {


    @Override
    public boolean addPlantMonitor(PlantMonitor plantMonitor) {

        int rs = 0;
        try {
            Connection connection = MySQLConnectionPool.getConnection();
            String sql = """
                                    INSERT INTO plant_monitoring(monitoring_time, monitoring_personnel, monitoring_location,
                                    monitored_object, monitored_objectId, monitored_value, monitoring_index, monitoring_device,
                                    created_by, created_time, updated_time)
                                    VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                    """;
            PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setTimestamp(1, new Timestamp(plantMonitor.getMonitoringTime().getTime()));
            ps.setString(2, plantMonitor.getMonitoringPersonnel());
            ps.setString(3, plantMonitor.getMonitoringLocation());
            ps.setString(4, plantMonitor.getMonitoredObject());
            ps.setInt(5, plantMonitor.getMonitoredObjectId());
            ps.setFloat(6, plantMonitor.getMonitorValue());
            ps.setString(7, plantMonitor.getMonitoringIndex());
            ps.setString(8, plantMonitor.getMonitoringDevice());
            ps.setString(9, plantMonitor.getCreatedBy());
            ps.setTimestamp(10, new Timestamp(plantMonitor.getCreatedTime().getTime()));
            ps.setTimestamp(11, new Timestamp(plantMonitor.getUpdatedTime().getTime()));
            rs = ps.executeUpdate();

            if (rs == 1) {
                ResultSet generatedKeys = ps.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int generatedId = generatedKeys.getInt(1); // 获取自动生成的主键值
                    // 使用生成的主键值进行操作，比如输出到控制台或者存储在对象中
                    System.out.println("自动生成的主键值为：" + generatedId);
                    // 将生成的主键值设置到对象中
                    plantMonitor.setId(generatedId);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs == 1;

    }

    @Override
    public PlantMonitor getPlantMonitorById(int id) {
        PlantMonitor plantMonitor = null;
        try {
            Connection connection = MySQLConnectionPool.getConnection();
            String sql = "SELECT * FROM plant_monitoring WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                plantMonitor = new PlantMonitor();
                plantMonitor.setMonitoringTime(rs.getTimestamp("monitoring_time"));
                plantMonitor.setMonitoringPersonnel(rs.getString("monitoring_personnel"));
                plantMonitor.setMonitoringLocation(rs.getString("monitoring_location"));
                plantMonitor.setMonitoredObject(rs.getString("monitored_object"));
                plantMonitor.setMonitoredObjectId(rs.getInt("monitored_objectId"));
                plantMonitor.setMonitorValue(rs.getFloat("monitored_value"));
                plantMonitor.setMonitoringIndex(rs.getString("monitoring_index"));
                plantMonitor.setMonitoringDevice(rs.getString("monitoring_device"));
                plantMonitor.setCreatedBy(rs.getString("created_by"));
                plantMonitor.setCreatedTime(rs.getTimestamp("created_time"));
                plantMonitor.setUpdatedTime(rs.getTimestamp("updated_time"));
                plantMonitor.setId(rs.getInt("id"));

            }

            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return plantMonitor;
    }

    @Override
    public boolean updatePlantMonitor(PlantMonitor plantMonitor) {
        int rs = 0;
        try {
            Connection connection = MySQLConnectionPool.getConnection();
            String sql = """
            UPDATE plant_monitoring
            SET monitoring_time = ?, monitoring_personnel = ?, monitoring_location = ?,
            monitored_objectId = ?, monitored_object = ?, monitored_value = ?,
            monitoring_index = ?, monitoring_device = ?, created_by = ?, created_time = ?,
            updated_time = ?
            WHERE id = ?
        """;

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setTimestamp(1, new Timestamp(plantMonitor.getMonitoringTime().getTime()));
            ps.setString(2, plantMonitor.getMonitoringPersonnel());
            ps.setString(3, plantMonitor.getMonitoringLocation());
            ps.setInt(4, plantMonitor.getMonitoredObjectId());
            ps.setString(5, plantMonitor.getMonitoredObject());
            ps.setFloat(6, plantMonitor.getMonitorValue());
            ps.setString(7, plantMonitor.getMonitoringIndex());
            ps.setString(8, plantMonitor.getMonitoringDevice());
            ps.setString(9, plantMonitor.getCreatedBy());
            ps.setTimestamp(10, new Timestamp(plantMonitor.getCreatedTime().getTime()));
            ps.setTimestamp(11, new Timestamp(plantMonitor.getUpdatedTime().getTime()));
            ps.setInt(12, plantMonitor.getId());

            rs = ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs == 1;
    }


    @Override
    public boolean deletePlantMonitor(int id) {
        int rs = 0;
        try {
            Connection connection = MySQLConnectionPool.getConnection();
            String sql = "DELETE FROM plant_monitoring WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);

            rs = ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs == 1;
    }

    @Override
    public List<PlantMonitor> getAllPlantMonitors() {
        List<PlantMonitor> plantMonitors = new ArrayList<>();
        try {
            Connection connection = MySQLConnectionPool.getConnection();
            String sql = "SELECT * FROM plant_monitoring";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            getParameters(plantMonitors, ps, rs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return plantMonitors;
    }

    @Override
    public List<PlantMonitor> getAllMonitorsByPlantId(int id) {
        List<PlantMonitor> plantMonitors = new ArrayList<>();
        try {
            Connection connection = MySQLConnectionPool.getConnection();
            String sql = "SELECT * FROM plant_monitoring WHERE monitored_objectId = ? ";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            getParameters(plantMonitors, ps, rs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return plantMonitors;
    }

    @Override
    public Float getMaxMonitorValue(int monitoredObjectId,String monitoringIndex) {
        Float maxValue = null;
        try {
            Connection connection = MySQLConnectionPool.getConnection();
            String sql = "SELECT MAX(monitored_value) FROM plant_monitoring WHERE monitored_objectId = ? AND monitoring_index = ? GROUP BY monitored_objectId, monitoring_index";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, monitoredObjectId);
            ps.setString(2, monitoringIndex);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                maxValue = rs.getFloat(1);
            }

            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return maxValue;
    }

    @Override
    public Float getAverageMonitorValue(int monitoredObjectId, String monitoringIndex) {
        Float averageValue = null;
        try {
            Connection connection = MySQLConnectionPool.getConnection();
            String sql = "SELECT AVG(monitored_value) FROM plant_monitoring WHERE monitored_objectId = ? AND monitoring_index = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, monitoredObjectId);
            ps.setString(2, monitoringIndex);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                averageValue = rs.getFloat(1);
            }

            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return averageValue;
    }


    @Override
    public List<PlantMonitor> getPlantMonitorsByLocation(String location) {
        return null;
    }

    @Override
    public List<PlantMonitor> getPlantMonitorsByObjectId(int id) {
        List<PlantMonitor> plantMonitors = new ArrayList<>();
        try {
            Connection connection = MySQLConnectionPool.getConnection();
            String sql = "SELECT * FROM plant_monitoring WHERE monitored_objectId = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            getParameters(plantMonitors, ps, rs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return plantMonitors;
    }

    private void getParameters(List<PlantMonitor> plantMonitors, PreparedStatement ps, ResultSet rs) throws SQLException {
        while (rs.next()) {
            PlantMonitor plantMonitor = new PlantMonitor();
            plantMonitor.setId(rs.getInt("id"));
            plantMonitor.setMonitoringTime(rs.getTimestamp("monitoring_time"));
            plantMonitor.setMonitoringPersonnel(rs.getString("monitoring_personnel"));
            plantMonitor.setMonitoringLocation(rs.getString("monitoring_location"));
            plantMonitor.setMonitoredObject(rs.getString("monitored_object"));
            plantMonitor.setMonitoredObjectId(rs.getInt("monitored_objectId"));
            plantMonitor.setMonitorValue(rs.getFloat("monitored_value"));
            plantMonitor.setMonitoringIndex(rs.getString("monitoring_index"));
            plantMonitor.setMonitoringDevice(rs.getString("monitoring_device"));
            plantMonitor.setCreatedBy(rs.getString("created_by"));
            plantMonitor.setCreatedTime(rs.getTimestamp("created_time"));
            plantMonitor.setUpdatedTime(rs.getTimestamp("updated_time"));
            plantMonitors.add(plantMonitor);
        }

        rs.close();
        ps.close();
    }

    @Override
    public List<PlantMonitor> getPlantMonitorsByCreator(String creator) {
        return null;
    }
}
