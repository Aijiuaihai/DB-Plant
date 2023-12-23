import java.io.Console;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class user {
    private String username="";
    private String password="";
    public int usertype=0;

    user(String username, String password, int type) {
        this.username = username;
        this.password = password;
        this.usertype = type;
    }
    public user() {
        super();
    }
    public String rename(){
        return this.username;
    }
    public String repassword(){
        return this.password;
    }
    public int retype(){
        return this.usertype;
    }

    public void setname(String no) {
        this.username = no;
    }
    public void settype(int type) {
        this.usertype = type;
    }
}
