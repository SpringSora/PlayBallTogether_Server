package com.ball.dao.myorder;

import java.util.List;

import com.ball.bean.MyOrder;


public interface MyOrderDao {
	/**
	 * 
	 * @param u_id
	 * @param p_id
	 * @param o_price
	 * @param o_date
	 * @param o_starttime
	 * @param o_endtime
	 * @return
	 */
	public Integer WriteOrder(Integer u_id,Integer p_id, Float o_price,String o_date ,String o_starttime,String o_endtime);
	/**
	 * 
	 * @param u_id
	 * @return
	 */
	public List<MyOrder> GetOrder(Integer u_id);
	
	public boolean ModifyOrderStatus(Integer o_isevaluate,Integer o_isrefund,Integer o_id);
	
	public MyOrder queryOrderbyId(Integer o_id);
	
	public boolean deleteOrder(Integer o_id);
}
