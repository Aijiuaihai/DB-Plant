package dao.user;

import classdefine.user.User;

import java.util.List;

public interface UserDao {
    boolean addUser(User user);
    boolean updateUser(User user);
    boolean deleteUser(int userId);
    User getUserById(int userId);
    User getUserByUsername(String name);
    List<User> getAllUsers();
}
