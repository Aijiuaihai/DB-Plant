import java.util.List;
public interface userDAO {
    int login_search(String username, String password);

    String getPwd(String username);

    List<user> getusers() ;
}