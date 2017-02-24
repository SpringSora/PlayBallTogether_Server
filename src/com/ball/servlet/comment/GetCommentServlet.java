package com.ball.servlet.comment;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ball.bean.Comment;
import com.ball.dao.comment.CommentDao;
import com.ball.impl.comment.CommentImpl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@WebServlet(urlPatterns={"/GetCommentServlet"})
public class GetCommentServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1422354568345126842L;
	private CommentDao cd;
	private List<Comment> comments;
	public GetCommentServlet() {
		// TODO Auto-generated constructor stub
		cd = new CommentImpl();
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(req.getParameter("p_id")!=null&&req.getParameter("page")!=null&&req.getParameter("item")!=null){
			comments = cd.getComments(Integer.valueOf(req.getParameter("p_id")), Integer.valueOf(req.getParameter("page")), Integer.valueOf(req.getParameter("item")));
			if(comments!=null){
				Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
				resp.getWriter().write(gson.toJson(comments));
			}else{
				resp.getWriter().write("false");
			}
		}
	
	}

}
