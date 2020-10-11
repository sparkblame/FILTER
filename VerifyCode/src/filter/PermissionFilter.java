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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;






import tools.JdbcUtil;

public class PermissionFilter implements Filter {
	private String notCheckPath;
	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request =(HttpServletRequest) req;
		String path = request.getServletPath();
		JdbcUtil jdbc = new JdbcUtil();
		Connection con = jdbc.getConnection();
		HttpSession session = request.getSession();
		System.out.println("请求地址url-pattern:"+path);
		request.setCharacterEncoding("utf-8");
		if(notCheckPath.indexOf(path)==-1){

			if(session.getAttribute("currentUser")==null){
				request.setAttribute("info", "没有权限访问");
				request.getRequestDispatcher("/error.jsp").forward(request, resp);
				
			}else{
				try {
					String sql = "select permission from t_role where role=?";
					String result[] = null;
					PreparedStatement pst = con.prepareStatement(sql);
					pst.setString(1, (String)session.getAttribute("role"));
					ResultSet rs = pst.executeQuery();
					if(rs.next()){
						result = rs.getString("permission").split("");
						System.out.println(result[1]+" "+result[2]+" "+result[3]+" "+result[4]+" ");
					}
					if(path.equals("/getDownloadList.do")&&Integer.parseInt(result[1])==0){
						request.setAttribute("info", "权限不够");
						request.getRequestDispatcher("/error.jsp").forward(request, resp);
					}
					else if(path.equals("/userMannger.jsp")&&Integer.parseInt(result[2])==0){
						request.setAttribute("info", "权限不够");
						request.getRequestDispatcher("/error.jsp").forward(request, resp);
					}
					else if(path.equals("/resourcesMannger.jsp")&&Integer.parseInt(result[1])==0){
						request.setAttribute("info", "权限不够");
						request.getRequestDispatcher("/error.jsp").forward(request, resp);
					}
					else if(path.equals("/personalPlace.jsp")&&Integer.parseInt(result[4])==0){
						request.setAttribute("info", "权限不够");
						request.getRequestDispatcher("/error.jsp").forward(request, resp);
					}
					else{
						chain.doFilter(request, resp);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}else{

			chain.doFilter(req,resp);
		}
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		notCheckPath = config.getInitParameter("notCheckPath");
	}

}
