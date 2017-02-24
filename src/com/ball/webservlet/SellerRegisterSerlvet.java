package com.ball.webservlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ball.bean.Seller;
import com.ball.dao.seller.SellerDao;
import com.ball.impl.seller.SellerImpl;

@WebServlet(urlPatterns={"/SellerRegisterSerlvet"})
public class SellerRegisterSerlvet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2123892325909357995L;
	private SellerDao sd;
	public SellerRegisterSerlvet() {
		super();
		sd = new SellerImpl();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String Seller_Account = req.getParameter("Seller_Account");
		String Seller_Password = req.getParameter("Seller_Password");
		Seller seller = new Seller(Seller_Account, Seller_Password);
		int s_id = 0;
		s_id = sd.SellerRegister(seller);
		if(s_id!=0){
			req.setAttribute("s_id", s_id);
			req.getRequestDispatcher("ModifySellerBallGroundInfo.jsp").forward(req, resp);
		}else{
			resp.getWriter().println("×¢²áÊ§°Ü");
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
	}
}
