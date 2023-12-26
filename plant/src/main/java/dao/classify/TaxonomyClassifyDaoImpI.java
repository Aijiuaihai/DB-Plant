package dao.classify;

import entity.classify.PlantClassify;
import entity.classify.TaxonomyClassify;
import entity.info.Taxonomy;
import util.MySQLConnectionPool;

import java.sql.*;

public class TaxonomyClassifyDaoImpI implements TaxonomyClassifyDao{
    @Override
    public TaxonomyClassify getTaxonomyClassifyById(int taxonomyID) {
        TaxonomyClassify taxonomyClassify = null;

        try {
            Connection connection = MySQLConnectionPool.getConnection();
            String sql = "SELECT * FROM taxonomy_classify WHERE taxonomyID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, taxonomyID); // 设置查询参数

            ResultSet rs = ps.executeQuery();

            if (rs.next()) { // 如果找到记录
                taxonomyClassify = new TaxonomyClassify();
                taxonomyClassify.setTaxonomyId(rs.getInt("taxonomyID"));
                taxonomyClassify.setParentTaxonomyId(rs.getInt("parentID"));
                taxonomyClassify.setName(rs.getString("name"));
                taxonomyClassify.setLevel(rs.getString("level"));
            }

            rs.close();
            ps.close();
            connection.close(); // 关闭资源

        } catch (Exception e) {
            e.printStackTrace();
        }

        return taxonomyClassify;
    }

    @Override
    public TaxonomyClassify getTaxonomyClassifyByPlantId(int plantID) {
        TaxonomyClassify taxonomyClassify = null;

        try {
            Connection connection = MySQLConnectionPool.getConnection();

            // 第一步：从 plant_classify 表获取 taxonomyID
            String sql = "SELECT taxonomyID FROM plant_classify WHERE plantID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, plantID);

            ResultSet rs = ps.executeQuery();

            int taxonomyID = 0;
            if (rs.next()) {
                taxonomyID = rs.getInt("taxonomyID");
            }

            rs.close();
            ps.close();

            // 第二步：使用 taxonomyID 从 taxonomy_classify 表获取 TaxonomyClassify 信息
            if (taxonomyID != 0) {
                sql = "SELECT * FROM taxonomy_classify WHERE taxonomyID = ?";
                ps = connection.prepareStatement(sql);
                ps.setInt(1, taxonomyID);

                rs = ps.executeQuery();

                if (rs.next()) {
                    taxonomyClassify = new TaxonomyClassify();
                    taxonomyClassify.setTaxonomyId(rs.getInt("taxonomyID"));
                    taxonomyClassify.setParentTaxonomyId(rs.getInt("parentID"));
                    taxonomyClassify.setName(rs.getString("name"));
                    taxonomyClassify.setLevel(rs.getString("level"));
                }

                rs.close();
                ps.close();
            }

            connection.close(); // 关闭资源

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return taxonomyClassify;
    }

    @Override
    public boolean addTaxonomyClassify(TaxonomyClassify taxonomy,boolean addClassify) {
        boolean result = false;

        try {
            Connection connection = MySQLConnectionPool.getConnection();
            String sql = "INSERT INTO taxonomy_classify (taxonomyID, parentID, name, level) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql);

            // 设置 PreparedStatement 参数
            ps.setInt(1, taxonomy.getTaxonomyId());
            if (!addClassify){
                ps.setNull(2, Types.INTEGER);
            }else {
                ps.setInt(2, taxonomy.getParentTaxonomyId());
            }
            ps.setString(3, taxonomy.getName());
            ps.setString(4, taxonomy.getLevel());

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
                    taxonomy.setTaxonomyId(generatedId);
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
    public boolean updateTaxonomyClassify(TaxonomyClassify taxonomy) {
        boolean result = false;

        try {
            Connection connection = MySQLConnectionPool.getConnection();
            String sql = "UPDATE taxonomy_classify SET parentID = ?, name = ?, level = ? WHERE taxonomyID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);

            // 设置 PreparedStatement 参数
            ps.setInt(1, taxonomy.getParentTaxonomyId());
            ps.setString(2, taxonomy.getName());
            ps.setString(3, taxonomy.getLevel());
            ps.setInt(4, taxonomy.getTaxonomyId()); // 设置 WHERE 子句的参数

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
    public boolean deleteTaxonomyClassify(int taxonomyID) {
        boolean result = false;

        try {
            Connection connection = MySQLConnectionPool.getConnection();
            String sql = "DELETE FROM taxonomy_classify WHERE taxonomyID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);

            // 设置 PreparedStatement 参数
            ps.setInt(1, taxonomyID);

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
