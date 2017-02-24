package com.ball.servlet.dateball;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ball.bean.DateBall;
import com.ball.dao.dateball.DateBallDao;
import com.ball.impl.dateball.DateBallImpl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


@WebServlet(urlPatterns={"/MyDateBallServlet"})
public class MyDateBallServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<DateBall> dateBalls;
	private DateBallDao dbd;
	public MyDateBallServlet() {
		// TODO Auto-generated constructor stub
		dbd = new DateBallImpl();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String page = req.getParameter("page");
		String item = req.getParameter("item");
		String u_id = req.getParameter("u_id");
		if(page!=null&&item!=null&&u_id!=null){
			dateBalls = dbd.getMyDateBall(Integer.valueOf(page), Integer.valueOf(item), Integer.valueOf(u_id));
			if(dateBalls!=null){
				Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
				resp.getWriter().write(gson.toJson(dateBalls));
			}else{
				resp.getWriter().write("false");
			}
		}
	}

}
