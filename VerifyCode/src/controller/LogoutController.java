package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet(urlPatterns="/logout.do")
public class LogoutController extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.removeAttribute("currentUser");
		Cookie cookie = new Cookie("password","aaa");
		cookie.setMaxAge(0);
		response.addCookie(cookie);
		System.out.println("password����");
		cookie = new Cookie("userName","aaa");
		cookie.setMaxAge(0);
		response.addCookie(cookie);
		System.out.println("userName����");
		response.sendRedirect("login.html");
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
