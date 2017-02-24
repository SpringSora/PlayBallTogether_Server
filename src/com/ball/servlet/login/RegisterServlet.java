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

@WebServlet(urlPatterns={"/RegisterServlet"})
public class RegisterServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UserDao ud;
	
	public RegisterServlet() {
		// TODO Auto-generated constructor stub
		ud = new UserImpl();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		User user = ud.UserRegister(req.getParameter("phone"), req.getParameter("password"));
		Gson gson = new Gson();
		if(user!=null){
			user.setU_id(ud.QueryuIdbyPhone(req.getParameter("phone")));
			resp.getWriter().write(gson.toJson(user));
		}else{
			resp.getWriter().write(gson.toJson("false"));
		}
			
	}
}
