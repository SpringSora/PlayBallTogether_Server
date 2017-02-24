package com.ball.servlet.myorder;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ball.dao.dateball.DateBallDao;
import com.ball.dao.myorder.MyOrderDao;
import com.ball.dao.promise.PromiseDao;
import com.ball.impl.dateball.DateBallImpl;
import com.ball.impl.myorder.MyOrderImpl;
import com.ball.impl.promise.PromiseImpl;

@WebServlet(urlPatterns={"/GenerateOrderServlet"})
public class GenerateOrderServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MyOrderDao mod;
	private PromiseDao pd;
	private DateBallDao dbd ;
	public GenerateOrderServlet() {
		// TODO Auto-generated constructor stub
		mod = new MyOrderImpl();
		pd = new PromiseImpl();
		dbd = new DateBallImpl();
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		Integer flag = 0;
		if(req.getParameter("u_id")!=null&&req.getParameter("p_id")!=null&&req.getParameter("o_price")!=null){
			flag = mod.WriteOrder(Integer.valueOf(req.getParameter("u_id")), Integer.valueOf(req.getParameter("p_id")),Float.valueOf(req.getParameter("o_price")) , req.getParameter("o_date"),req.getParameter("o_starttime"),req.getParameter("o_endtime"));
		}
		
		if(req.getParameter("type").equals("promise")){
			System.out.println(req.getParameter("u_id")+"    d_id:"+req.getParameter("d_id"));
			pd.UpdateO_id(Integer.valueOf(req.getParameter("u_id")),Integer.valueOf(req.getParameter("d_id")), flag);
		}
		if(req.getParameter("type").equals("ballground")){
			dbd.UpdateO_id(Integer.valueOf(req.getParameter("d_id")), flag);
		}

		if(flag.intValue()!=0){
			resp.getWriter().write("true");
		}else{
			resp.getWriter().write("false");
		}
	}

}
