package com.ball.webservlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ball.dao.seller.SellerDao;
import com.ball.impl.seller.SellerImpl;

@WebServlet(urlPatterns={"/AccountCheckServlet"})
public class AccountCheckServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4815248498229461083L;
	private SellerDao sd;
	private static final int SUCCESS = 200;
	private static final int FAIL = 201;
	
	public AccountCheckServlet() {
		super();
		sd = new SellerImpl();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String account = req.getParameter("account");
		//返回值为true就是已经被人注册
		if(sd.SellerisRegister(account)){
			resp.getWriter().write(FAIL+"");
		}else{
			resp.getWriter().write(SUCCESS+"");
		}
	}
	
}
