package dao.pestControl;

import classdefine.pestControl.PestControl;
import pool.MySQLConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PestControlDaoImpI implements PestControlDao{
    @Override
    public PestControl getPestControlById(int pestControlId) {
        PestControl pestControl = null;

        try {
            Connection connection = MySQLConnectionPool.getConnection();
            String sql = "SELECT * FROM pestControl WHERE pestControlID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);

            // 设置查询参数
            ps.setInt(1, pestControlId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) { // 如果找到了记录
                pestControl = new PestControl();
                pestControl.setPestControlId(rs.getInt("pestControlID"));
                pestControl.setPestName(rs.getString("pestName"));
                pestControl.setControlMethod(rs.getString("controlMethod"));
                pestControl.setPesticideName(rs.getString("pesticideName"));
                pestControl.setPesticideAmount(rs.getString("pesticideAmount"));
                pestControl.setEffectiveDuration(rs.getString("effectiveDuration"));
                pestControl.setCreator(rs.getString("creator"));
                pestControl.setCreateTime(rs.getTimestamp("createTime"));
                pestControl.setUpdateTime(rs.getTimestamp("updateTime"));
            }

            rs.close();
            ps.close();
            connection.close(); // 关闭资源

        } catch (Exception e) {
            e.printStackTrace();
        }

        return pestControl;
    }


    @Override
    public boolean addPestControl(PestControl pestControl) {
        boolean result = false;

        try {
            Connection connection = MySQLConnectionPool.getConnection();
            String sql = "INSERT INTO pestControl (pestName, controlMethod, pesticideName, pesticideAmount, effectiveDuration, creator, createTime) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql);

            // 设置 PreparedStatement 参数
            ps.setString(1, pestControl.getPestName());
            ps.setString(2, pestControl.getControlMethod());
            ps.setString(3, pestControl.getPesticideName());
            ps.setString(4, pestControl.getPesticideAmount());
            ps.setString(5, pestControl.getEffectiveDuration());
            ps.setString(6, pestControl.getCreator());
            ps.setTimestamp(7, new java.sql.Timestamp(pestControl.getCreateTime().getTime()));
//            ps.setTimestamp(8, new java.sql.Timestamp(pestControl.getUpdateTime().getTime()));

            // 执行更新
            int rowsAffected = ps.executeUpdate();
            result = rowsAffected > 0; // 如果插入了记录，则返回 true
            if (rowsAffected == 1) {
                ResultSet generatedKeys = ps.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int generatedId = generatedKeys.getInt(1); // 获取自动生成的主键值
// 使用生成的主键值进行操作，比如输出到控制台或者存储在对象中
                    System.out.println("刚添加的植物病虫害防治信息ID为：" + generatedId);
// 将生成的主键值设置到对象中
                    pestControl.setPestControlId(generatedId);
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
    public boolean updatePestControl(PestControl pestControl) {
        boolean result = false;

        try {
            Connection connection = MySQLConnectionPool.getConnection();
            String sql = "UPDATE pestControl SET pestName = ?, controlMethod = ?, pesticideName = ?, pesticideAmount = ?, effectiveDuration = ?, creator = ?, updateTime = ? WHERE PestControlID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);

            // 设置 PreparedStatement 参数
            ps.setString(1, pestControl.getPestName());
            ps.setString(2, pestControl.getControlMethod());
            ps.setString(3, pestControl.getPesticideName());
            ps.setString(4, pestControl.getPesticideAmount());
            ps.setString(5, pestControl.getEffectiveDuration());
            ps.setString(6, pestControl.getCreator());
//            ps.setTimestamp(7, new java.sql.Timestamp(pestControl.getCreateTime().getTime()));
            ps.setTimestamp(7, new java.sql.Timestamp(pestControl.getUpdateTime().getTime()));
            ps.setInt(8, pestControl.getPestControlId());

            // 执行更新
            int rowsAffected = ps.executeUpdate();
            result = rowsAffected > 0;

            ps.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }


    @Override
    public boolean deletePestControl(int pestControlId) {
        boolean result = false;

        try {
            Connection connection = MySQLConnectionPool.getConnection();
            String sql = "DELETE FROM pestControl WHERE pestControlID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);

            // 设置 PreparedStatement 参数
            ps.setInt(1, pestControlId);

            // 执行删除
            int rowsAffected = ps.executeUpdate();
            result = rowsAffected > 0;

            ps.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

}
