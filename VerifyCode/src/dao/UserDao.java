package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;




import tools.JdbcUtil;
import vo.User;

public class UserDao {
	private Connection con;
	
	public UserDao(Connection con) {
		super();
		this.con = con;
	}

	public User getInfo(String userName){
		User user = null;
		try {
			String sql = "select * from t_user where username=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, userName);
			ResultSet rs = pst.executeQuery();
			if(rs.next()){
				user = new User(rs.getString("userName"),rs.getString("password"),rs.getString("chrName"),rs.getString("role"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	return user;
	}
}
