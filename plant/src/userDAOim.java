import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class userDAOim extends DAObese implements userDAO {
    private static final String PROJECT_SELECT_SQL = "select password from user where username=?";
    public int login_search(String username, String password) {
        Connection con = null;
        user user = new user();
        int flag=0;
        try{
            con = getConnection();
            PreparedStatement psmt = con.prepareStatement(PROJECT_SELECT_SQL);
            psmt.setString(1, username);
            ResultSet rs = psmt.executeQuery();
            while (rs.next()){
                if(rs.getString("password").equals(password)){
                    flag=1;
                }
            }
            psmt.close();
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            try{
                con.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        if(flag==1){
            return 2;
        }
        else {
            return 1;
        }
    }

    @Override

    public String getPwd(String username) {
        try {
            Connection conn = getConnection();
            String sql = "select password from [user] where username = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            if (rs.next()){
                return rs.getString(1);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "-1";
    }
    public List<user> getusers() {
        try {
            Connection conn = getConnection();
            Statement st = conn.createStatement();
            String sql = "select username,usertype from user";
            ResultSet rs = st.executeQuery(sql);

            List<user> userList = new ArrayList<>();
            while (rs.next()){
                String username = rs.getString("username");
                int usertype = rs.getInt("usertype");

                user user = new user();
                user.setname(username);
                user.settype(usertype);
                userList.add(user);
            }
            return userList;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

}

