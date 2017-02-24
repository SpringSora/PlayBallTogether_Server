package com.ball.servlet.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ball.dao.user.UserDao;
import com.ball.impl.user.UserImpl;
@WebServlet(urlPatterns={"/checkUserServlet"})
public class checkUserServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private UserDao ud;
	
	public checkUserServlet() {
		// TODO Auto-generated constructor stub
		ud = new UserImpl();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	    if(!ud.UserCheckPhone(req.getParameter("phone"))){
	    	resp.getWriter().write("true");
	    }else{
	    	resp.getWriter().write("false");
	    }
	}

}
