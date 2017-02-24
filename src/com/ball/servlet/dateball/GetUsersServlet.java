package com.ball.servlet.dateball;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ball.bean.User;
import com.ball.dao.promise.PromiseDao;
import com.ball.impl.promise.PromiseImpl;
import com.google.gson.Gson;

@WebServlet(urlPatterns={"/GetUsersServlet"})
public class GetUsersServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PromiseDao pd;
	private List<User> users;
	public GetUsersServlet() {
		// TODO Auto-generated constructor stub
		pd = new PromiseImpl();
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String d_id = req.getParameter("d_id");
		if(d_id!=null){
			users = pd.getUsers(Integer.valueOf(d_id));
			if(users!=null){
				Gson gson = new Gson();
				resp.getWriter().write(gson.toJson(users));
			}else{
				resp.getWriter().write("false");
			}
		}
	}

}
