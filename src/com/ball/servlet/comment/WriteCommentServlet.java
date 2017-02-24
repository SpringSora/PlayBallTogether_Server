package com.ball.servlet.comment;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ball.dao.comment.CommentDao;
import com.ball.dao.myorder.MyOrderDao;
import com.ball.impl.comment.CommentImpl;
import com.ball.impl.myorder.MyOrderImpl;
@WebServlet(urlPatterns={"/WriteCommentServlet"})
public class WriteCommentServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CommentDao  cd;
	private MyOrderDao mod;
	private boolean flag1;
	private boolean flag2;
	public WriteCommentServlet() {
		// TODO Auto-generated constructor stub
		cd = new CommentImpl();
		mod = new MyOrderImpl();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(req.getParameter("u_id")!=null&&req.getParameter("p_id")!=null&&req.getParameter("content")!=null&&req.getParameter("isevaluate")!=null&&req.getParameter("o_id")!=null){
			flag1 = cd.WriteComment(Integer.valueOf(req.getParameter("u_id")), Integer.valueOf(req.getParameter("p_id")), req.getParameter("content"));
			flag2 = mod.ModifyOrderStatus(Integer.valueOf(req.getParameter("isevaluate")), null, Integer.valueOf(req.getParameter("o_id")));
		}
		
		if(flag1&&flag2){
			resp.getWriter().write("true");
		}else{
			resp.getWriter().write("false");
		}
	}

}
