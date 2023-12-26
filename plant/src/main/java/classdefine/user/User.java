package classdefine.user;

public class User {
    private int user_id;
    private String username;
    private String password;
    private String user_type;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }

    @Override
    public String toString() {
        return String.format("ID:%d\t 用户名:%s\t  密码:%s\t  身份:%s", user_id, username, password, user_type);
    }
}
