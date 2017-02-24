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

@WebServlet(urlPatterns={"/ModifyGroundInfoServlet"})
public class ModifyGroundInfoServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int s_id;
	private SellerInfo sellerInfo;
	private BallGroundDao bgd;
	private int groundNum;
	private float groundPrice;
	private int groundLeft;
	private String groundPhone;
	private String groundInfo;
	private BallGround ballGround;
	private SellerInfoDao sid;
	private Seller seller;
	private SellerDao sd;
	private Integer isbusiness;
	public ModifyGroundInfoServlet() {
		// TODO Auto-generated constructor stub
		sid = new SellerInfoImpl();
		bgd = new BallGroundImpl();
		sd = new SellerImpl();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		s_id = Integer.valueOf(req.getParameter("s_id"));
		ballGround = bgd.QueryBallGroundInfobyId(s_id);
		sellerInfo = sid.QuerySellerInfobyId(s_id);
		seller = sd.QuerySellerbyId(s_id);
		String BallGroundInfoPath = "s"+s_id+"/BallGroundPic";
		try {
			UpLoad(req, resp, BallGroundInfoPath);
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}
	
	public void UpLoad(HttpServletRequest req,HttpServletResponse resp,String SellerIdFile) throws FileUploadException, IOException, ServletException{
		if(ServletFileUpload.isMultipartContent(req)){
			req = new MultipartWrapper(req, SellerIdFile);
		}
		String GroundNum = req.getParameter("GroundNum");
		String GroundLeft = req.getParameter("GroundLeft");
		String GroundPrice = req.getParameter("GroundPrice");
		String GroundPhone = req.getParameter("GroundPhone");
		String GroundInfo = req.getParameter("GroundInfo");
		String BallGroundPic1Path = null;
		String BallGroundPic2Path = null;
		String BallGroundPic3Path = null;
		String pic1Name = req.getParameter("pic1");
		String pic2Name = req.getParameter("pic2");
		String pic3Name = req.getParameter("pic3");
		String isBusiness = req.getParameter("business");
		if(null!=pic1Name&&!"".equals(pic1Name)){
			BallGroundPic1Path = "UpLoad/"+SellerIdFile+"/"+pic1Name;
		}else{
			BallGroundPic1Path = ballGround.getBallGroundPic1Path();
		}
		if(null!=pic2Name&&!"".equals(pic2Name)){
			BallGroundPic2Path = "UpLoad/"+SellerIdFile+"/"+pic2Name;
		}else{
			BallGroundPic2Path = ballGround.getBallGroundPic2Path();
		}
		if(null!=pic3Name&&!"".equals(pic3Name)){
			BallGroundPic3Path = "UpLoad/"+SellerIdFile+"/"+pic3Name;
		}else{
			BallGroundPic3Path = ballGround.getBallGroundPic3Path();
		}
		if(null!=GroundNum&&!"".equals(GroundNum)){
			groundNum = Integer.valueOf(GroundNum);
		}else {
			groundNum = ballGround.getGroundNum();
		}
		if(null!=GroundLeft&&!"".equals(GroundLeft)){
			groundLeft = Integer.valueOf(GroundLeft);
		}else{
			groundLeft = ballGround.getGroundLeft();
		}
		if(null!=GroundPrice&&!"".equals(GroundPrice)){
			groundPrice = Float.valueOf(GroundPrice);
		}else{
			groundPrice = ballGround.getGroundPrice();
		}
		if(null!=GroundPhone&&!"".equals(GroundPhone)){
			groundPhone = GroundPhone;
		}else{
			groundPhone = ballGround.getGroundPhone();
		}
		if(null!=GroundInfo&&!"".equals(GroundInfo)){
			groundInfo = GroundInfo;
		}else{
			groundInfo = ballGround.getGroundInfo();
		}
		if(null!=isBusiness&&!"".equals(isBusiness)){
			isbusiness = Integer.valueOf(isBusiness);
		}else{
			isbusiness = ballGround.getIsBusiness();
		}
		
		BallGround newballGround = new BallGround(ballGround.getGroundName(), ballGround.getBallType(), groundNum, groundLeft ,groundPrice, groundInfo, groundPhone, ballGround.getLng(), ballGround.getLat(),ballGround.getAddress() ,ballGround.getCity(), seller, BallGroundPic1Path,BallGroundPic2Path,BallGroundPic3Path,isbusiness);
		if(bgd.UpdateBallGroundInfo(newballGround)){
			req.getSession().setAttribute("ballGround", newballGround);
			req.getSession().setAttribute("sellerInfo", sellerInfo);
			req.getRequestDispatcher("/SellerBallGroundInfo.jsp").forward(req, resp);
		}else{
			
		}
	}
}
