package com.ball.webservlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


import com.ball.bean.Seller;
import com.ball.bean.SellerInfo;
import com.ball.dao.seller.SellerDao;
import com.ball.dao.seller.SellerInfoDao;
import com.ball.impl.seller.SellerImpl;
import com.ball.impl.seller.SellerInfoImpl;
import com.ball.uploadpic.MultipartWrapper;

@WebServlet(urlPatterns={"/SellerInfoServlet"})
public class SellerInfoServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5156421374329011486L;
	private SellerDao sd;
	private SellerInfoDao sid;
	private String Seller_id;
	public SellerInfoServlet(){
		sd = new SellerImpl();
		sid = new SellerInfoImpl();
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		doGet(req, resp);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		Seller_id = req.getParameter("Seller_id");
		try {
			String SellerInfoPath = Seller_id+"/Lincence";
			UpLoad(req, resp,SellerInfoPath);
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
	}
	
	public void UpLoad(HttpServletRequest req, HttpServletResponse resp,String SellerIdFile) throws FileUploadException, IOException{
		if (ServletFileUpload.isMultipartContent(req)) {
			req = new MultipartWrapper(req,SellerIdFile);
		}
		req.setCharacterEncoding("utf-8");
		String SellerName = req.getParameter("SellerName");
		String SellerPhone = req.getParameter("SellerPhone");
		String SellerMail = req.getParameter("SellerMail");
		String LicenceNum = req.getParameter("LicenceNum");
		String LicenceName = req.getParameter("LicenceName");
		String LicenceDate = req.getParameter("LicenceDate");
		String SellerIdCard = req.getParameter("SellerIdCard");
		String flag = req.getParameter("licencedate");
		String LicencePicPath = null;
		String idCardPicPath = null;
		if(!"".equals(req.getParameter("licence"))){
			LicencePicPath = "UpLoad/"+SellerIdFile+"/"+req.getParameter("licence");
		}
		if(!"".equals(req.getParameter("idcard"))){
			idCardPicPath = "UpLoad/"+SellerIdFile+"/"+req.getParameter("idcard");
		}
		
		Seller seller = sd.QuerySellerbyId(Integer.valueOf(Seller_id.replaceAll("s", "")));
		Date date = null;
		if(!"1".equals(flag)&&!"".equals(LicenceDate)){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				date = sdf.parse(LicenceDate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		SellerInfo sellerInfo = new SellerInfo(seller,SellerName, SellerPhone, SellerMail, LicenceNum, LicenceName, date, LicencePicPath, SellerIdCard, idCardPicPath);
		int s_id = sid.WriteSellerInfo(sellerInfo);
		
		try {
			req.setAttribute("s_id", s_id);
			req.getRequestDispatcher("BallGroundManagement.jsp").forward(req, resp);
		} catch (ServletException e) {
			e.printStackTrace();
		}
	}
}
