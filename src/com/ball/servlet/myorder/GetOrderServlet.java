package com.ball.servlet.myorder;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ball.bean.MyOrder;
import com.ball.dao.myorder.MyOrderDao;
import com.ball.impl.myorder.MyOrderImpl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@WebServlet(urlPatterns={"/GetOrderServlet"})
public class GetOrderServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MyOrderDao mod;
	private List<MyOrder> myOrders;

	public GetOrderServlet() {
		// TODO Auto-generated constructor stub
		mod = new MyOrderImpl();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(req.getParameter("u_id")!=null&&!"".equals(req.getParameter("u_id"))){
			myOrders = mod.GetOrder(Integer.valueOf(req.getParameter("u_id")));
			if(myOrders!=null){
				Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
				String data = gson.toJson(myOrders);
				resp.getWriter().write(data);
			}else{
				resp.getWriter().write("false");
			}
		}else{
			resp.getWriter().write("false");
		}
	}

}
