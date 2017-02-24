package com.ball.servlet.promise;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ball.bean.Promise;
import com.ball.dao.promise.PromiseDao;
import com.ball.impl.promise.PromiseImpl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
@WebServlet(urlPatterns={"/GetPromiseServlet"})
public class GetPromiseServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PromiseDao pd;
	private List<Promise> promises;
	public GetPromiseServlet() {
		// TODO Auto-generated constructor stub
		pd = new PromiseImpl();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String u_id = req.getParameter("u_id");
		String page = req.getParameter("page");
		String item = req.getParameter("item");
		if(u_id!=null&&page!=null&&item!=null){
			promises = pd.GetPromise(Integer.valueOf(u_id), Integer.valueOf(page), Integer.valueOf(item));
			if(promises!=null){
				Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-hh HH:mm:ss").create();
				resp.getWriter().write(gson.toJson(promises));

			}else{
				resp.getWriter().write("false");
			}
		}
	}

}
