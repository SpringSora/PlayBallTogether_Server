package com.ball.servlet.user;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ball.bean.User;
import com.ball.dao.user.UserDao;
import com.ball.impl.user.UserImpl;
import com.google.gson.Gson;
@WebServlet(urlPatterns = {"/SearchUserServlet"})
public class SearchUserServlet  extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UserDao ud;
	private List<User> users;
	
	public SearchUserServlet() {
		// TODO Auto-generated constructor stub
		ud = new UserImpl();
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String page = req.getParameter("page");
		String item = req.getParameter("item");
		String key = req.getParameter("key");
		if(page!=null&&item!=null&&key!=null){
			users = ud.SearchUser(Integer.valueOf(page), Integer.valueOf(item), key);
			if(users!=null){
				Gson gson = new Gson();
				resp.getWriter().write(gson.toJson(users));
			}else{
				resp.getWriter().write("false");
			}
		}
	}
	
}
