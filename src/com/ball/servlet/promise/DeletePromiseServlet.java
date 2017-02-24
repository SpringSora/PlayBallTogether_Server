package com.ball.servlet.promise;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ball.dao.dateball.DateBallDao;
import com.ball.dao.myorder.MyOrderDao;
import com.ball.dao.promise.PromiseDao;
import com.ball.impl.dateball.DateBallImpl;
import com.ball.impl.myorder.MyOrderImpl;
import com.ball.impl.promise.PromiseImpl;

@WebServlet(urlPatterns={"/DeletePromiseServlet"})
public class DeletePromiseServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PromiseDao pd;
	private MyOrderDao mod;
	private DateBallDao dbd;
	public DeletePromiseServlet() {
		// TODO Auto-generated constructor stub
		pd = new PromiseImpl();
		mod = new MyOrderImpl();
		dbd = new DateBallImpl();
		
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String u_id = req.getParameter("u_id");
		String d_id = req.getParameter("d_id");
		Integer o_id = null;
		if(u_id!=null&&d_id!=null){
			o_id = pd.selectOrderid(Integer.valueOf(d_id), Integer.valueOf(u_id));
			if(o_id!=null){
				if(pd.DeletePromise(Integer.valueOf(d_id), Integer.valueOf(u_id))){
					if(mod.deleteOrder(o_id)){
						if(dbd.UpdateNumAdd(Integer.valueOf(d_id))){
							resp.getWriter().write("true");
						}
					}else{
						resp.getWriter().write("false");
					}
				}else{
					resp.getWriter().write("false");
				}
			}else{
				resp.getWriter().write("false");
			}
		}else{
			resp.getWriter().write("false");
		}
	}
	
}
