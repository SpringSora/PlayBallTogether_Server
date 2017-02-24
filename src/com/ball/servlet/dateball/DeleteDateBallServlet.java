package com.ball.servlet.dateball;

import java.io.IOException;
import java.util.List;

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

@WebServlet(urlPatterns={"/DeleteDateBallServlet"})
public class DeleteDateBallServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DateBallDao dbd;
	private MyOrderDao mod;
	private PromiseDao pd;
	private List<Integer> o_ids;
	public DeleteDateBallServlet() {
		// TODO Auto-generated constructor stub
		dbd = new DateBallImpl();
		mod = new MyOrderImpl();
		pd = new PromiseImpl();
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String d_id = req.getParameter("d_id");
		System.out.println(d_id);
		Integer o_id = null;
		if(d_id!=null){
			o_ids = pd.selectOrdersId(Integer.valueOf(d_id));
			
			if(o_ids!=null&&o_ids.size()!=0){
				for (int i = 0; i < o_ids.size(); i++) {
					mod.deleteOrder(o_ids.get(i));
				}
			}
			
			pd.DeletePromise(Integer.valueOf(d_id));
			
			o_id = dbd.selectO_id(Integer.valueOf(d_id));
			if(o_id!=null){
				mod.deleteOrder(o_id);
				dbd.deleteDateBall(Integer.valueOf(d_id));
			}else{
				dbd.deleteDateBall(Integer.valueOf(d_id));
			}
		}
	}

}
