package dao.info;

import classdefine.info.Plant;
import pool.MySQLConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class PlantDAOImpl implements PlantDAO {

    @Override
    public Plant getPlantById(int plantID) {
        Plant plant = null;

        try {
            Connection connection = MySQLConnectionPool.getConnection();
            String sql = "SELECT * FROM plant WHERE PlantID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, plantID);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                plant = new Plant();
                plant.setPlantID(rs.getInt("PlantID")); // 对应数据库字段 PlantID
                plant.setDiseaseName(rs.getString("DiseaseName")); // 对应数据库字段 DiseaseName
                plant.setCommonName(rs.getString("CommonName")); // 对应数据库字段 CommonName
                plant.setMorphology(rs.getString("Morphology")); // 对应数据库字段 Morphology
                plant.setCultivationTips(rs.getString("CultivationTips")); // 对应数据库字段 CultivationTips
                plant.setPestControlMeasures(rs.getString("PestControlMeasures")); // 对应数据库字段 PestControlMeasures
                plant.setApplicationValue(rs.getString("ApplicationValue")); // 对应数据库字段 ApplicationValue
                plant.setCreator(rs.getString("Creator")); // 对应数据库字段 Creator
                plant.setCreateTime(rs.getDate("CreateTime")); // 对应数据库字段 CreateTime
                plant.setUpdateTime(rs.getDate("UpdateTime")); // 对应数据库字段 UpdateTime
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return plant;
    }

    @Override
    public List<Plant> getPlantBySingleParam(String sql) {
        System.out.println("sql 语句是");
        System.out.println(sql);
        ArrayList<Plant> plantArrayList = new ArrayList<>();

        try {
            Connection connection = MySQLConnectionPool.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();


            while (rs.next()) {
                Plant p = new Plant();
                p.setPlantID(rs.getInt("PlantID")); // 对应数据库字段 PlantID
                p.setDiseaseName(rs.getString("DiseaseName")); // 对应数据库字段 DiseaseName
                p.setCommonName(rs.getString("CommonName")); // 对应数据库字段 CommonName
                p.setMorphology(rs.getString("Morphology")); // 对应数据库字段 Morphology
                p.setCultivationTips(rs.getString("CultivationTips")); // 对应数据库字段 CultivationTips
                p.setPestControlMeasures(rs.getString("PestControlMeasures")); // 对应数据库字段 PestControlMeasures
                p.setApplicationValue(rs.getString("ApplicationValue")); // 对应数据库字段 ApplicationValue
                p.setCreator(rs.getString("Creator")); // 对应数据库字段 Creator
                p.setCreateTime(rs.getDate("CreateTime")); // 对应数据库字段 CreateTime
                p.setUpdateTime(rs.getDate("UpdateTime")); // 对应数据库字段 UpdateTime

                plantArrayList.add(p);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return plantArrayList;
    }

    @Override
    public List<Plant> getAllPlants() {
        ArrayList<Plant> plantArrayList = new ArrayList<>();

        try {
            Connection connection = MySQLConnectionPool.getConnection();
            String sql = "SELECT * FROM plant";
            PreparedStatement ps = connection.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();


            while (rs.next()) {
                Plant p = new Plant();
                p.setPlantID(rs.getInt("PlantID")); // 对应数据库字段 PlantID
                p.setDiseaseName(rs.getString("DiseaseName")); // 对应数据库字段 DiseaseName
                p.setCommonName(rs.getString("CommonName")); // 对应数据库字段 CommonName
                p.setMorphology(rs.getString("Morphology")); // 对应数据库字段 Morphology
                p.setCultivationTips(rs.getString("CultivationTips")); // 对应数据库字段 CultivationTips
                p.setPestControlMeasures(rs.getString("PestControlMeasures")); // 对应数据库字段 PestControlMeasures
                p.setApplicationValue(rs.getString("ApplicationValue")); // 对应数据库字段 ApplicationValue
                p.setCreator(rs.getString("Creator")); // 对应数据库字段 Creator
                p.setCreateTime(rs.getDate("CreateTime")); // 对应数据库字段 CreateTime
                p.setUpdateTime(rs.getDate("UpdateTime")); // 对应数据库字段 UpdateTime

                plantArrayList.add(p);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }


        return plantArrayList;
    }

    @Override
    public boolean addPlant(Plant plant) {
        int rs = 0;
        try {
            Connection connection = MySQLConnectionPool.getConnection();
            String sql = """
                                    INSERT INTO  plant(diseaseName,commonName,morphology,cultivationTips,
                                    pestControlMeasures,applicationValue,creator,createTime,updateTime)
                                    VALUES (?,?,?,?,?,?,?,?,?)
                    """;
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, plant.getDiseaseName());
            ps.setString(2, plant.getCommonName());
            ps.setString(3, plant.getMorphology());
            ps.setString(4, plant.getCultivationTips());
            ps.setString(5, plant.getPestControlMeasures());
            ps.setString(6, plant.getApplicationValue());
            ps.setString(7, plant.getCreator());
            ps.setTimestamp(8, new Timestamp(plant.getCreateTime().getTime()));
            ps.setTimestamp(9, new Timestamp(plant.getUpdateTime().getTime()));
            rs = ps.executeUpdate();

            if (rs == 1) {
                ResultSet generatedKeys = ps.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int generatedId = generatedKeys.getInt(1); // 获取自动生成的主键值
                    // 使用生成的主键值进行操作，比如输出到控制台或者存储在对象中
                    System.out.println("自动生成的主键值为：" + generatedId);
                    // 将生成的主键值设置到对象中
                    plant.setPlantID(generatedId);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs == 1;
    }

    @Override
    public boolean updatePlant(Plant plant) {
        int rs = 0;
        try {
            Connection connection = MySQLConnectionPool.getConnection();
            String sql = """
                    UPDATE plant
                    SET diseaseName = ?,
                      commonName = ?,
                      morphology = ?,
                      cultivationTips = ?,
                      pestControlMeasures = ?,
                      applicationValue = ?,
                      creator = ?,
                      createTime = ?,
                      updateTime = ?
                    WHERE plantID = ?
                    """;
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, plant.getDiseaseName());
            ps.setString(2, plant.getCommonName());
            ps.setString(3, plant.getMorphology());
            ps.setString(4, plant.getCultivationTips());
            ps.setString(5, plant.getPestControlMeasures());
            ps.setString(6, plant.getApplicationValue());
            ps.setString(7, plant.getCreator());
            ps.setTimestamp(8, new Timestamp(plant.getCreateTime().getTime()));
            ps.setTimestamp(9, new Timestamp(plant.getUpdateTime().getTime()));
            ps.setInt(10,plant.getPlantID());
            rs = ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs == 1;
    }

    @Override
    public boolean deletePlant(int plantID) {
        int rs = 0;
        try {
            Connection connection = MySQLConnectionPool.getConnection();
            PreparedStatement ps = connection.prepareStatement("DELETE FROM plant WHERE plantID = ?");
            ps.setInt(1, plantID);
            rs = ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs == 1;
    }
}
