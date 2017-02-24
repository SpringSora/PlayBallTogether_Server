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

@WebServlet(urlPatterns={"/GetDateBallServlet"})
public class GetDateBallServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<DateBall> dateBalls ;
	private DateBallDao dbd;
	public GetDateBallServlet() {
		// TODO Auto-generated constructor stub
		dbd = new DateBallImpl();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(req.getParameter("type")==null){
			dateBalls = dbd.getListDateBall(Integer.valueOf(req.getParameter("page")), Integer.valueOf(req.getParameter("item")),req.getParameter("city"),null);
		}else{
			dateBalls = dbd.getListDateBall(Integer.valueOf(req.getParameter("page")), Integer.valueOf(req.getParameter("item")),req.getParameter("city"),Integer.valueOf(req.getParameter("type")));

		}
		if(dateBalls!=null){
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
			String data = gson.toJson(dateBalls);
			resp.getWriter().write(data);
		}else{
			resp.getWriter().write("false");
		}
	}
}
