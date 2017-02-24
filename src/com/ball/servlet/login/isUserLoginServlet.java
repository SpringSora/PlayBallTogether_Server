package com.ball.servlet.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ball.bean.User;
import com.ball.dao.user.UserDao;
import com.ball.impl.user.UserImpl;
import com.google.gson.Gson;

@WebServlet(urlPatterns={"/isUserLoginServlet"})
public class isUserLoginServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UserDao ud;
	public isUserLoginServlet() {
		// TODO Auto-generated constructor stub
		ud = new UserImpl();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		User user = ud.UserLogin(req.getParameter("username"), req.getParameter("password"));
		if(user!=null){
			System.out.println(user.getU_phone());
			Gson gson = new Gson();
			resp.getWriter().write(gson.toJson(user));
		}else{
			resp.getWriter().write("false");
		}
	}


}
