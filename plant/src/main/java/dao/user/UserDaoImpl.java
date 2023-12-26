package dao.user;

import classdefine.user.User;
import pool.MySQLConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {

    @Override
    public boolean addUser(User user) {
        try {
            Connection connection = MySQLConnectionPool.getConnection();
            String sql = "INSERT INTO `user` (username, password, user_type) VALUES (?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getUser_type());

            int rowsInserted = ps.executeUpdate();
            ps.close();
            return rowsInserted > 0; // 如果插入了一行或多行数据，则返回 true
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateUser(User user) {
        try {
            Connection connection = MySQLConnectionPool.getConnection();
            String sql = "UPDATE `user` SET username = ?, password = ?, user_type = ? WHERE user_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getUser_type());
            ps.setInt(4, user.getUser_id());

            int rowsUpdated = ps.executeUpdate();
            ps.close();
            return rowsUpdated > 0; // 如果更新了一行或多行数据，则返回 true
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteUser(int userId) {
        try {
            Connection connection = MySQLConnectionPool.getConnection();
            String sql = "DELETE FROM `user` WHERE user_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, userId);

            int rowsDeleted = ps.executeUpdate();
            ps.close();
            return rowsDeleted > 0; // 如果删除了一行或多行数据，则返回 true
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public User getUserById(int userId) {
        User user = null;
        try {
            Connection connection = MySQLConnectionPool.getConnection();
            String sql = "SELECT * FROM `user` WHERE user_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                user = new User();
                user.setUser_id(rs.getInt("user_id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setUser_type(rs.getString("user_type"));
            }

            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User getUserByUsername(String name) {
        User user = null;
        try {
            Connection connection = MySQLConnectionPool.getConnection();
            String sql = "SELECT * FROM `user` WHERE username = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, name);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                user = new User();
                user.setUser_id(rs.getInt("user_id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setUser_type(rs.getString("user_type"));
            }

            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try {
            Connection connection = MySQLConnectionPool.getConnection();
            String sql = "SELECT * FROM `user`";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                User user = new User();
                user.setUser_id(rs.getInt("user_id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setUser_type(rs.getString("user_type"));
                userList.add(user);
            }

            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userList;
    }public List<User> getAllUsersNoadmin() {
        List<User> userList = new ArrayList<>();
        try {
            Connection connection = MySQLConnectionPool.getConnection();
            String sql = "SELECT * FROM `user` WHERE user_type != 'admin' AND user_type != 'department'";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                User user = new User();
                user.setUser_id(rs.getInt("user_id"));
                user.setUsername(rs.getString("username"));
                user.setUser_type(rs.getString("user_type"));
                userList.add(user);
            }

            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userList;
    }
}
