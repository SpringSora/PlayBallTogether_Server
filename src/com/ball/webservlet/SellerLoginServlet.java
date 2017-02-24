package com.ball.webservlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ball.bean.BallGround;
import com.ball.bean.Seller;
import com.ball.bean.SellerInfo;
import com.ball.dao.seller.BallGroundDao;
import com.ball.dao.seller.SellerDao;
import com.ball.dao.seller.SellerInfoDao;
import com.ball.impl.seller.BallGroundImpl;
import com.ball.impl.seller.SellerImpl;
import com.ball.impl.seller.SellerInfoImpl;
import com.ball.md5.MD5;

@WebServlet(urlPatterns={"/SellerLoginServlet"})
public class SellerLoginServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private SellerDao sd;
	private SellerInfoDao sid;
	private Seller seller;
	private SellerInfo sellerInfo;
	private BallGround ballGround;
	private BallGroundDao bgd;
	public SellerLoginServlet() {
		// TODO Auto-generated constructor stub
		sd = new SellerImpl();
		sid = new SellerInfoImpl();
		bgd = new BallGroundImpl();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String account = req.getParameter("Seller_Account");
		String password = req.getParameter("Seller_Password");
		seller = sd.QuerySellerbyAccount(account);
		if(seller!=null&&seller.getPassword().equals(MD5.GetMD5Code(password))){
			sellerInfo = sid.QuerySellerInfobyId(seller.getSeller_id());
			ballGround = bgd.QueryBallGroundInfobyId(seller.getSeller_id());
			req.getSession().setAttribute("sellerInfo", sellerInfo);
			req.getSession().setAttribute("ballGround", ballGround);
			req.getRequestDispatcher("/SellerBallGroundInfo.jsp").forward(req, resp);
		}else{
			req.setAttribute("flag", "false");
			req.getRequestDispatcher("/SellerLogin.jsp").forward(req, resp);
		}
	}
}
