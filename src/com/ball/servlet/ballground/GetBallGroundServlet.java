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

@WebServlet(urlPatterns={"/GetBallGroundServlet"})
public class GetBallGroundServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7276136793602448394L;
	private BallGroundDao bgd;
	private List<BallGround> ballGrounds;
	public GetBallGroundServlet() {
		// TODO Auto-generated constructor stub
		bgd = new BallGroundImpl();
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(req.getParameter("type")!=null&&!"0".equals(req.getParameter("type"))){
			ballGrounds = bgd.getBallGrounds(Integer.valueOf(req.getParameter("page")), Integer.valueOf(req.getParameter("item")),Integer.valueOf(req.getParameter("type")) , req.getParameter("city"));
		}else{
			ballGrounds = bgd.getBallGrounds(Integer.valueOf(req.getParameter("page")), Integer.valueOf(req.getParameter("item")),null , req.getParameter("city"));
		}
		if(ballGrounds!=null){
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
			resp.getWriter().write(gson.toJson(ballGrounds));
		}else{
			resp.getWriter().write("false");
		}
	}
}
