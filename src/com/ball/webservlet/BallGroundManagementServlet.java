package com.ball.webservlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;



import com.ball.bean.BallGround;
import com.ball.bean.Seller;
import com.ball.bean.SellerInfo;
import com.ball.dao.seller.BallGroundDao;
import com.ball.dao.seller.SellerDao;
import com.ball.dao.seller.SellerInfoDao;
import com.ball.impl.seller.BallGroundImpl;
import com.ball.impl.seller.SellerImpl;
import com.ball.impl.seller.SellerInfoImpl;
import com.ball.uploadpic.MultipartWrapper;

@WebServlet(urlPatterns={"/BallGroundManagementServlet"})
public class BallGroundManagementServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Seller seller;
	private SellerDao sd;
	private SellerInfoDao sid;
	private String id;
	private BallGroundDao bgd;
	private SellerInfo sellerInfo;
	
	public BallGroundManagementServlet() {
		// TODO Auto-generated constructor stub
		sd = new SellerImpl();
		bgd = new BallGroundImpl();
		sid = new SellerInfoImpl();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		id = req.getParameter("id");
		if(id.contains("s")){
			//���̼ҹ���
			String BallGroundInfoPath = id+"/BallGroundPic";
			try {
				UpLoad(req, resp, BallGroundInfoPath);
			} catch (FileUploadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			//���̼ҹ���
			String BallGroundInfoPath = id+"/BallGrounPic";
			
			System.out.println("����Ա����");
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		doGet(req, resp);
	}
	
	public void UpLoad(HttpServletRequest req,HttpServletResponse resp,String SellerIdFile) throws FileUploadException, IOException{
		if(ServletFileUpload.isMultipartContent(req)){
			req = new MultipartWrapper(req, SellerIdFile);
		}
		String GroundName = req.getParameter("GroundName");
		String BallType = req.getParameter("BallType");
		String GroundNum = req.getParameter("GroundNum");
		String GroundPrice = req.getParameter("GroundPrice");
		String GroundPhone = req.getParameter("GroundPhone");
		String GroundInfo = req.getParameter("GroundInfo");
		double lng = Double.valueOf(req.getParameter("lng"));
		double lat = Double.valueOf(req.getParameter("lat"));
		String address = req.getParameter("address");
		String city = req.getParameter("city");
		String BallGroundPic1Path = null;
		String BallGroundPic2Path = null;
		String BallGroundPic3Path = null;
		String pic1Name = req.getParameter("pic1");
		String pic2Name = req.getParameter("pic2");
		String pic3Name = req.getParameter("pic3");
		if(!"".equals(pic1Name)){
			BallGroundPic1Path = "UpLoad/"+SellerIdFile+"/"+pic1Name;
		}
		if(!"".equals(pic2Name)){
			BallGroundPic2Path = "UpLoad/"+SellerIdFile+"/"+pic2Name;
		}
		if(!"".equals(pic3Name)){
			BallGroundPic3Path = "UpLoad/"+SellerIdFile+"/"+pic3Name;
		}
		if(id.contains("s")){
			seller = sd.QuerySellerbyId(Integer.valueOf(id.replaceAll("s", "")));
			sellerInfo = sid.QuerySellerInfobyId(Integer.valueOf(id.replace("s", "")));
		}
		
		BallGround ballGround = new BallGround(GroundName, Integer.valueOf(BallType), Integer.valueOf(GroundNum),Integer.valueOf(GroundNum), Float.valueOf(GroundPrice), GroundInfo, GroundPhone, lng, lat,address ,city, seller,BallGroundPic1Path,BallGroundPic2Path,BallGroundPic3Path,0);
		if(bgd.writeBallGroundInfo(ballGround)){
			try {
				req.getSession().setAttribute("ballGround", ballGround);
				req.getSession().setAttribute("sellerInfo", sellerInfo);
				req.getRequestDispatcher("/SellerBallGroundInfo.jsp").forward(req, resp);
			} catch (ServletException e) {
				e.printStackTrace();
			}
		}
		
/*		System.out.println(GroundName);
		System.out.println(BallType);
		System.out.println(GroundNum);
		System.out.println(GroundPrice);
		System.out.println(GroundInfo);
		System.out.println(lng);
		System.out.println(lat);
		System.out.println(city);
		System.out.println(BallGroundPic1Path);
		System.out.println(BallGroundPic2Path);
		System.out.println(BallGroundPic3Path);*/
	}
}
