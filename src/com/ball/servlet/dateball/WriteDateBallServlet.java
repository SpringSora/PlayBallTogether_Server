package com.ball.servlet.dateball;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Decoder.BASE64Decoder;

import com.ball.bean.BallGround;
import com.ball.bean.DateBall;
import com.ball.dao.dateball.DateBallDao;
import com.ball.dao.seller.BallGroundDao;
import com.ball.dao.user.UserDao;
import com.ball.impl.dateball.DateBallImpl;
import com.ball.impl.seller.BallGroundImpl;
import com.ball.impl.user.UserImpl;

@WebServlet(urlPatterns={"/WriteDateBallServlet"})
public class WriteDateBallServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DateBallDao dbd;
	
	public WriteDateBallServlet() {
		// TODO Auto-generated constructor stub
		dbd = new DateBallImpl();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String photo = req.getParameter("photo");
		Integer u_id = Integer.valueOf(req.getParameter("u_id"));
		String title = req.getParameter("title");
		String info = req.getParameter("info");
		Integer type = Integer.valueOf(req.getParameter("type"));
		String location = req.getParameter("location");
		Integer num = Integer.valueOf(req.getParameter("num"));
		String date = req.getParameter("date");
		String start = req.getParameter("start");
		String end = req.getParameter("end");
		String city = req.getParameter("city");
		String picPath = null;
		String picName = null;
		Integer p_id = null;
		int d_id = 0;
		if(req.getParameter("p_id")!=null){
			p_id = Integer.valueOf(req.getParameter("p_id"));
		}
		if(null!=photo&&!"".equals(photo)){
            byte[] photoimg = new BASE64Decoder().decodeBuffer(photo);
            for (int i = 0; i < photoimg.length; ++i) {  
                if (photoimg[i] < 0) {  
                    photoimg[i] += 256;  
                }  
            }
            File file = new File(req.getSession().getServletContext().getRealPath("/DateBallUpLoad/"+"u"+u_id));
            if(!file.exists()){
            	file.mkdirs();
            }
            picName = System.currentTimeMillis()+".png";
            File photofile = new File(req.getSession().getServletContext().getRealPath("/DateBallUpLoad/"+"u"+u_id),picName);
            FileOutputStream out = new FileOutputStream(photofile);
            out.write(photoimg);
            out.flush();
            out.close();
            picPath = "/DateBallUpLoad/u"+u_id+"/"+picName;
		}
		DateBall dateBall = new DateBall(picPath, title, info, type, location, num, date, start, end,city);
		d_id = dbd.WriteDateBall(u_id,dateBall,p_id);
		if(d_id!=0){
			resp.getWriter().write(d_id+"");
		}else{
			resp.getWriter().write("false");
		}
		
/*		System.out.println("u_id="+u_id);
		System.out.println("title="+title);
		System.out.println("info="+info);
		System.out.println("type="+type);
		System.out.println("num="+num);
		System.out.println("date="+date);
		System.out.println("start="+start);
		System.out.println("end="+end);
		System.out.println("picPath"+picPath);
		System.out.println("p_id"+p_id);
		System.out.println("lng="+lng.toString());
		System.out.println("lat="+lat.toString());*/
		
	}

}
