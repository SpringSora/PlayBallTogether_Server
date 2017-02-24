package com.ball.servlet.ballground;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ball.bean.BallGround;
import com.ball.dao.seller.BallGroundDao;
import com.ball.impl.seller.BallGroundImpl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
@WebServlet(urlPatterns={"/SearchBallGroundServlet"})
public class SearchBallGroundServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BallGroundDao bgd;
	private List<BallGround> ballGrounds;
	public SearchBallGroundServlet() {
		// TODO Auto-generated constructor stub
		bgd = new BallGroundImpl();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String page = req.getParameter("page");
		String item = req.getParameter("item");
		String city = req.getParameter("city");
		String key = req.getParameter("key");
		if(page!=null&&item!=null&&city!=null&&key!=null){
			ballGrounds = bgd.SearchBallGrounds(Integer.valueOf(page), Integer.valueOf(item), city, key);
			if(ballGrounds!=null){
				Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
				resp.getWriter().write(gson.toJson(ballGrounds));
			}else{
				resp.getWriter().write("false");
			}
		}
	}
}
