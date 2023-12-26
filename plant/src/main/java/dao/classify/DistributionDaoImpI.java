package dao.classify;

import entity.classify.Distribution;
import entity.classify.TaxonomyClassify;
import util.MySQLConnectionPool;

import java.sql.*;

public class DistributionDaoImpI implements DistributionDao{
    @Override
    public Distribution getDistributionById(int distributionId) {
        Distribution distribution = null;

        try {
            Connection connection = MySQLConnectionPool.getConnection();
            String sql = "SELECT * FROM distribution WHERE distributionID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, distributionId); // 设置查询参数

            ResultSet rs = ps.executeQuery();

            if (rs.next()) { // 如果找到记录
                distribution = new Distribution();
                distribution.setDistributionId(rs.getInt("distributionID"));
                distribution.setParentDistributionId(rs.getInt("parentID"));
                distribution.setName(rs.getString("name"));
                distribution.setLevel(rs.getString("level"));
            }

            rs.close();
            ps.close();
            connection.close(); // 关闭资源

        } catch (Exception e) {
            e.printStackTrace();
        }

        return distribution;
    }

    @Override
    public Distribution getDistributionByPlantId(int plantID) {
        Distribution distribution = null;

        try {
            Connection connection = MySQLConnectionPool.getConnection();

            // 第一步：从 plant_classify 表获取 distributionId
            String sql = "SELECT distributionID FROM plant_classify WHERE plantID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, plantID);

            ResultSet rs = ps.executeQuery();

            int distributionId = 0;
            if (rs.next()) {
                distributionId = rs.getInt("distributionID");
            }

            rs.close();
            ps.close();

            // 第二步：使用 taxonomyID 从 taxonomy_classify 表获取 TaxonomyClassify 信息
            if (distributionId != 0) {
                sql = "SELECT * FROM distribution WHERE distributionID = ?";
                ps = connection.prepareStatement(sql);
                ps.setInt(1, distributionId);

                rs = ps.executeQuery();

                if (rs.next()) {
                    distribution = new Distribution();
                    distribution.setDistributionId(rs.getInt("distributionID"));
                    distribution.setParentDistributionId(rs.getInt("parentID"));
                    distribution.setName(rs.getString("name"));
                    distribution.setLevel(rs.getString("level"));
                }

                rs.close();
                ps.close();
            }

            connection.close(); // 关闭资源

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return distribution;
    }

    @Override
    public boolean addDistribution(Distribution distribution,boolean addParent) {
        boolean result = false;

        try {
            Connection connection = MySQLConnectionPool.getConnection();
            String sql = "INSERT INTO distribution (distributionID, parentID, name, level) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql);

            // 设置 PreparedStatement 参数
            ps.setInt(1, distribution.getDistributionId());
            if(!addParent){
                ps.setNull(2, Types.INTEGER);
            }else {
                ps.setInt(2, distribution.getParentDistributionId());
            }

            ps.setString(3, distribution.getName());
            ps.setString(4, distribution.getLevel());

            // 执行插入
            int rowsAffected = ps.executeUpdate();
            result = rowsAffected > 0; // 如果受影响的行数大于0，则表示插入成功
            if (rowsAffected == 1) {
                ResultSet generatedKeys = ps.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int generatedId = generatedKeys.getInt(1); // 获取自动生成的主键值
// 使用生成的主键值进行操作，比如输出到控制台或者存储在对象中
//                    System.out.println("自动生成的主键值为：" + generatedId);
// 将生成的主键值设置到对象中
                    distribution.setDistributionId(generatedId);
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
    public boolean updateDistribution(Distribution distribution) {
        boolean result = false;

        try {
            Connection connection = MySQLConnectionPool.getConnection();
            String sql = "UPDATE distribution SET parentID = ?, name = ?, level = ? WHERE distributionID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);

            // 设置 PreparedStatement 参数
            ps.setInt(1, distribution.getParentDistributionId());
            ps.setString(2, distribution.getName());
            ps.setString(3, distribution.getLevel());
            ps.setInt(4, distribution.getDistributionId()); // 设置 WHERE 子句的参数

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
    public boolean deleteDistribution(int distributionId) {
        boolean result = false;

        try {
            Connection connection = MySQLConnectionPool.getConnection();
            String sql = "DELETE FROM distribution WHERE distributionID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);

            // 设置 PreparedStatement 参数
            ps.setInt(1, distributionId);

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
}
