package controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tools.JdbcUtil;
import vo.Download;
import dao.DownloadDao;
@WebServlet(urlPatterns="/download.do")
public class DownloadController extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		JdbcUtil jdbc = new JdbcUtil();
		Connection con = jdbc.getConnection();
		String id = request.getParameter("id");
		try{
		DownloadDao dao = new DownloadDao(con);
		Download download =dao.get(Integer.parseInt(id));
		String path = request.getServletContext().getRealPath("/WEB-INF/"+download.getPath());
		String fileName = path.substring(path.lastIndexOf("\\")+1);
		System.out.println(fileName);
		response.setHeader("content-disposition", "attachment;filename="+URLEncoder.encode(fileName,"UTF-8"));
		InputStream in = new FileInputStream(path);
		int len =0;
		byte[] buffer = new byte[1024];
		ServletOutputStream out = response.getOutputStream();
		while((len=in.read(buffer))!=-1){
			out.write(buffer,0,len);
		}
		in.close();
		out.close();
		jdbc.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}

}
