package com.ball.servlet.myorder;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ball.dao.myorder.MyOrderDao;
import com.ball.impl.myorder.MyOrderImpl;

@WebServlet(urlPatterns={"/ModifyOrderStatusServlet"})
public class ModifyOrderStatusServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MyOrderDao mod;

	public ModifyOrderStatusServlet() {
		// TODO Auto-generated constructor stub
		mod = new MyOrderImpl();
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		boolean flag = false;
		if(req.getParameter("isrefund")!=null){
			flag = mod.ModifyOrderStatus(null, Integer.valueOf(req.getParameter("isrefund")),Integer.valueOf(req.getParameter("o_id")) );
			resp.getWriter().write(flag+"");
		}
		
		if(req.getParameter("isevaluate")!=null){
			flag = mod.ModifyOrderStatus(Integer.valueOf(req.getParameter("isevaluate")), null,Integer.valueOf(req.getParameter("o_id")) );
			resp.getWriter().write(flag+"");
		}
	}

}
