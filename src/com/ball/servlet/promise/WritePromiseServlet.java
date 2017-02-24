package com.ball.servlet.promise;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ball.dao.dateball.DateBallDao;
import com.ball.dao.promise.PromiseDao;
import com.ball.impl.dateball.DateBallImpl;
import com.ball.impl.promise.PromiseImpl;
@WebServlet(urlPatterns={"/WritePromiseServlet"})
public class WritePromiseServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PromiseDao pd;
	private DateBallDao dbd;
	public WritePromiseServlet() {
		// TODO Auto-generated constructor stub
		pd = new PromiseImpl();
		dbd = new DateBallImpl();
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String d_id = req.getParameter("d_id");
		String u_id = req.getParameter("u_id");
		if(d_id!=null&&u_id!=null){
			if(dbd.UpdateNumSub(Integer.valueOf(d_id))){
				if(pd.WritePromise(Integer.valueOf(d_id), Integer.valueOf(u_id))){
					resp.getWriter().write("接受成功");
				}else{
					resp.getWriter().write("您已经接受挑战");
					dbd.UpdateNumAdd(Integer.valueOf(d_id));
				}
			}else{
				resp.getWriter().write("非常抱歉，对方需求人数已满");
			}
		}
	}

}
