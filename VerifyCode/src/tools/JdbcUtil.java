package tools;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcUtil {
    private Connection con;
    private static String className;
    private static String url;
    private static String userName;
    private static String password;
    static {
        try{
            Properties pro = new Properties();
            InputStream is = JdbcUtil.class.getResourceAsStream("/resources/jdbc.properties");
            pro.load(is);
            className=pro.getProperty("className");
            url = pro.getProperty("url");
            userName=pro.getProperty("userName");
            password=pro.getProperty("password");
            Class.forName(className);
            is.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }
    public JdbcUtil(){

    }
    public Connection getConnection(){
        try{
           con= DriverManager.getConnection(url,userName,password);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return con;
    }
    public void close(){
        if (con!=null){
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
