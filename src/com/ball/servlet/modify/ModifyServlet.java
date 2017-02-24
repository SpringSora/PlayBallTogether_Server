package com.ball.servlet.modify;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ball.dao.user.UserDao;
import com.ball.impl.user.UserImpl;

@WebServlet(urlPatterns={"/ModifyServlet"})

public class ModifyServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private UserDao ud;
	
	public ModifyServlet(){
		ud = new UserImpl();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String type = req.getParameter("Modify_type");
		switch (Integer.valueOf(type)) {
			case 0:
				if(ud.UpdateNickName(Integer.valueOf(req.getParameter("u_id")), req.getParameter(type))){
					resp.getWriter().write("true");
				}else{
					resp.getWriter().write("false");
				}
				break;
			case 1:
				if(ud.UpdatePhone(Integer.valueOf(req.getParameter("u_id")), req.getParameter(type))){
					resp.getWriter().write("true");
				}else{
					resp.getWriter().write("false");
				}
				break;
			case 2:
				if(ud.UpdatePassword(Integer.valueOf(req.getParameter("u_id")), req.getParameter(type),req.getParameter("OldPassword"))){
					
					resp.getWriter().write("true");
				}else{
					resp.getWriter().write("false");
				}
				break;
			case 3:
				if(ud.UpdateSex(Integer.valueOf(req.getParameter("u_id")), req.getParameter(type))){
					resp.getWriter().write("true");
				}else{
					resp.getWriter().write("false");
				}
				break;
			case 4:
				if(ud.UpdateAge(Integer.valueOf(req.getParameter("u_id")), Integer.valueOf(req.getParameter(type)))){
					resp.getWriter().write("true");
				}else{
					resp.getWriter().write("false");
				}
				break;
			case 5:
				if(ud.UpdateConnect(Integer.valueOf(req.getParameter("u_id")), req.getParameter(type))){
					resp.getWriter().write("true");
				}else{
					resp.getWriter().write("false");
				}
				break;
			case 6:
				if(ud.UpdateSign(Integer.valueOf(req.getParameter("u_id")), req.getParameter(type))){
					resp.getWriter().write("true");
				}else{
					resp.getWriter().write("false");
				}
				break;
			case 7:
				if(ud.UpdateHobby(Integer.valueOf(req.getParameter("u_id")), req.getParameter(type))){
					resp.getWriter().write("true");
				}else{
					resp.getWriter().write("false");
				}
				break;
		default:
			break;
		}
	}
	
}
