package dao.classify;


import classdefine.classify.PlantClassify;
import pool.MySQLConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlantClassifyDaoImpI implements PlantClassifyDao {

    @Override
    public PlantClassify getPlantClassifyById(int plantId) {
        PlantClassify plantClassify = null;

        try {
            Connection connection = MySQLConnectionPool.getConnection();
            String sql = "SELECT * FROM plant_classify WHERE plantID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, plantId); // 设置查询参数

            ResultSet rs = ps.executeQuery();

            if (rs.next()) { // 如果找到记录
                plantClassify = new PlantClassify();
                plantClassify.setPlantId(rs.getInt("plantID"));
                plantClassify.setCommonName(rs.getString("commonName"));
                plantClassify.setGrowthEnvironment(rs.getString("growthEnvironment"));
                plantClassify.setTaxonomyId(rs.getInt("taxonomyID"));
                plantClassify.setDistributionId(rs.getInt("distributionID"));
                plantClassify.setCreator(rs.getString("creator"));
                plantClassify.setCreateTime(rs.getDate("createTime"));
                plantClassify.setUpdateTime(rs.getDate("updateTime"));
            }

            rs.close();
            ps.close();
            connection.close(); // 关闭资源

        } catch (Exception e) {
            e.printStackTrace();
        }

        return plantClassify;
    }

    @Override
    public List<PlantClassify> getAllPlantClassifies() {
        ArrayList<PlantClassify> plantClassifyArrayList = new ArrayList<>();

        try {
            Connection connection = MySQLConnectionPool.getConnection();
            String sql = "SELECT * FROM plant_classify";
            PreparedStatement ps = connection.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();


            while (rs.next()) {
                PlantClassify p = new PlantClassify();
                p.setPlantId(rs.getInt("plantID")); // 对应数据库字段 PlantID
                p.setCommonName(rs.getString("commonName")); // 对应数据库字段 CommonName
                p.setGrowthEnvironment(rs.getString("growthEnvironment")); // 对应数据库字段 growthEnvironment
                p.setTaxonomyId(rs.getInt("taxonomyID")); // 对应数据库字段 taxonomyID
                p.setDistributionId(rs.getInt("distributionID")); // 对应数据库字段 distributionID
                p.setCreator(rs.getString("creator")); // 对应数据库字段 Creator
                p.setCreateTime(rs.getDate("createTime")); // 对应数据库字段 CreateTime
                p.setUpdateTime(rs.getDate("updateTime")); // 对应数据库字段 UpdateTime

                plantClassifyArrayList.add(p);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }


        return plantClassifyArrayList;
    }

    @Override
    public boolean addPlantClassify(PlantClassify plant) {
        boolean result = false;

        try {
            Connection connection = MySQLConnectionPool.getConnection();
            String sql = "INSERT INTO plant_classify (plantID, commonName, growthEnvironment, taxonomyID, distributionID, creator, createTime) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql);

            // 设置 PreparedStatement 参数
            ps.setInt(1, plant.getPlantId());
            ps.setString(2, plant.getCommonName());
            ps.setString(3, plant.getGrowthEnvironment());
            ps.setInt(4, plant.getTaxonomyId());
            ps.setInt(5, plant.getDistributionId());
            ps.setString(6, plant.getCreator());
            ps.setDate(7, new java.sql.Date(plant.getCreateTime().getTime())); // 转换为 sql.Date
//            ps.setDate(8, new java.sql.Date(plant.getUpdateTime().getTime())); // 转换为 sql.Date

            // 执行更新
            int rowsAffected = ps.executeUpdate();
            result = rowsAffected > 0; // 如果受影响的行数大于0，则表示插入成功
            if (rowsAffected == 1) {
                ResultSet generatedKeys = ps.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int generatedId = generatedKeys.getInt(1); // 获取自动生成的主键值
// 使用生成的主键值进行操作，比如输出到控制台或者存储在对象中
                    System.out.println("刚添加的植物分类信息ID为：" + generatedId);
// 将生成的主键值设置到对象中
                    plant.setPlantId(generatedId);
                }
            }
            // 关闭资源
            ps.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public boolean updatePlantClassify(PlantClassify plant) {
        boolean result = false;

        try {
            Connection connection = MySQLConnectionPool.getConnection();
            String sql = "UPDATE plant_classify SET commonName = ?, growthEnvironment = ?, taxonomyID = ?, distributionID = ?, creator = ?, updateTime = ? WHERE plantID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);

            // 设置 PreparedStatement 参数
            ps.setString(1, plant.getCommonName());
            ps.setString(2, plant.getGrowthEnvironment());
            ps.setInt(3, plant.getTaxonomyId());
            ps.setInt(4, plant.getDistributionId());
            ps.setString(5, plant.getCreator());
//            ps.setDate(6, new java.sql.Date(plant.getCreateTime().getTime())); // 转换为 sql.Date
            ps.setDate(6, new java.sql.Date(plant.getUpdateTime().getTime())); // 转换为 sql.Date
            ps.setInt(7, plant.getPlantId()); // 设置 WHERE 子句的参数

            // 执行更新
            int rowsAffected = ps.executeUpdate();
            result = rowsAffected > 0; // 如果受影响的行数大于0，则表示更新成功

            // 关闭资源
            ps.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public boolean deletePlantClassify(int plantID) {
        boolean result = false;

        try {
            Connection connection = MySQLConnectionPool.getConnection();
            String sql = "DELETE FROM plant_classify WHERE plantID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);

            // 设置 PreparedStatement 参数
            ps.setInt(1, plantID);

            // 执行删除
            int rowsAffected = ps.executeUpdate();
            result = rowsAffected > 0; // 如果受影响的行数大于0，则表示删除操作成功

            // 关闭资源
            ps.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public List<PlantClassify> queryByGrowthEnvironment(String GrowthEnvironment) {
        List<PlantClassify> plantClassifies = new ArrayList<>();

        try {
            Connection connection = MySQLConnectionPool.getConnection();
            String sql = "SELECT * FROM plant_classify WHERE growthEnvironment LIKE ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, "%" + GrowthEnvironment + "%"); // 设置模糊查询参数

            ResultSet rs = ps.executeQuery();

            while (rs.next()) { // 处理所有找到的记录
                PlantClassify plantClassify = new PlantClassify();
                plantClassify.setPlantId(rs.getInt("plantID"));
                plantClassify.setCommonName(rs.getString("commonName"));
                plantClassify.setGrowthEnvironment(rs.getString("growthEnvironment"));
                plantClassify.setTaxonomyId(rs.getInt("taxonomyID"));
                plantClassify.setDistributionId(rs.getInt("distributionID"));
                plantClassify.setCreator(rs.getString("creator"));
                plantClassify.setCreateTime(rs.getDate("createTime"));
                plantClassify.setUpdateTime(rs.getDate("updateTime"));

                plantClassifies.add(plantClassify);
            }

            rs.close();
            ps.close();
            connection.close(); // 关闭资源

        } catch (Exception e) {
            e.printStackTrace();
        }

        return plantClassifies;
    }

    public List<Integer> getPlantIdsByTaxonomy(String taxonomyName) {
        List<Integer> plantIds = new ArrayList<>();

        try {
            Connection connection = MySQLConnectionPool.getConnection();
            String sql = "SELECT pc.PlantID " +
                    "FROM Plant_Classify pc " +
                    "JOIN Taxonomy_Classify tc ON pc.TaxonomyID = tc.TaxonomyID " +
                    "JOIN Taxonomy_Classify tc_genus ON tc.ParentID = tc_genus.TaxonomyID " +
                    "JOIN Taxonomy_Classify tc_family ON tc_genus.ParentID = tc_family.TaxonomyID " +
                    "WHERE tc.Name = ? OR tc_genus.Name = ? OR tc_family.Name = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, taxonomyName);
            ps.setString(2, taxonomyName);
            ps.setString(3, taxonomyName);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                plantIds.add(rs.getInt("PlantID"));
            }

            rs.close();
            ps.close();
            connection.close(); // 关闭资源

        } catch (Exception e) {
            e.printStackTrace();
        }

        return plantIds;
    }

}
