package filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import tools.JdbcUtil;
import vo.User;

public class AutoLoginFilter implements Filter {
	private Cookie[] cookies;
	private String checkPath;
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest Request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) Request;
		cookies = request.getCookies();
		String path = request.getServletPath();
		String userName=null,password=null;
		System.out.println("请求地址url-pattern:"+path);
		HttpSession session = request.getSession();
		if(checkPath.indexOf(path)!=-1&&cookies.length>=2){
			JdbcUtil jdbc = new JdbcUtil();
			Connection con = jdbc.getConnection();
			String sql = "select * from t_user";
			try {
				PreparedStatement pst = con.prepareStatement(sql);
				ResultSet rs = pst.executeQuery();
				System.out.println("开始免登录");
				for(int i=0;i<cookies.length;i++){
					if(cookies[i].getName().equals("userName")){
						userName = cookies[i].getValue();
					}
					if(cookies[i].getName().equals("password")){
						password = cookies[i].getValue();
					}
				}
				while(rs.next()){
					User user = new User(rs.getString("userName"),rs.getString("password"),rs.getString("chrName"),rs.getString("role"));

					if((userName.equals(user.getUserName())&&password.equals(user.getPassword()))){
						session.setAttribute("userName",userName);
						session.setAttribute("currentUser",user);
						request.getRequestDispatcher("/main.jsp").forward(request, response);
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		checkPath = config.getInitParameter("CheckPath");
	}

}
