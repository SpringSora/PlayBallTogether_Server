package com.ball.servlet.modify;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ball.bean.User;
import com.ball.dao.user.UserDao;
import com.ball.impl.user.UserImpl;

import Decoder.BASE64Decoder;

@WebServlet(urlPatterns={"/UpLoadPicServlet"})
public class UpLoadPicServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UserDao ud;
	public UpLoadPicServlet() {
		// TODO Auto-generated constructor stub
		ud = new UserImpl();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	        String photo = req.getParameter("photo"); 
	        String u_id = req.getParameter("u_id");
	     // 对base64数据进行解码 生成 字节数组，不能直接用Base64.decode（）；进行解密  
            byte[] photoimg = new BASE64Decoder().decodeBuffer(photo);  
            for (int i = 0; i < photoimg.length; ++i) {  
                if (photoimg[i] < 0) {  
                    // 调整异常数据  
                    photoimg[i] += 256;  
                }  
            }
            File file = new File(req.getSession().getServletContext().getRealPath("/UserUpLoad/"+"u"+u_id));
            if(!file.exists()){
            	file.mkdirs();
            }
            File photofile = new File(req.getSession().getServletContext().getRealPath("/UserUpLoad/"+"u"+u_id),"pic.png");
            FileOutputStream out = new FileOutputStream(photofile);
            out.write(photoimg);
            out.flush();
            out.close();
            String path = "/UserUpLoad/u"+u_id+"/"+"pic.png";
            if(ud.UpdatePic(Integer.valueOf(u_id), path)){
            	resp.getWriter().write(path);
            }else{
            	resp.getWriter().write("");
            }
            
	} 
}
