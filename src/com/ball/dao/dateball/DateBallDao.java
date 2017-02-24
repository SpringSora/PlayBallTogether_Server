package com.ball.dao.dateball;

import java.util.List;

import javax.ejb.Init;

import com.ball.bean.DateBall;

public interface DateBallDao {
	/**
	 * 
	 * @param dateball
	 * @return
	 */
	public Integer WriteDateBall(Integer u_id,DateBall dateball,Integer p_id);
	
	public List<DateBall> getListDateBall(int page,int item,String city,Integer type);
	
	public List<DateBall> getMyDateBall(Integer page,Integer item,Integer u_id);
	
	public boolean UpdateNumSub(Integer d_id);
	
	public boolean UpdateNumAdd(Integer d_id);
	
	public Integer SelectNum(Integer d_id);
	
	public boolean UpdateSeccess(Integer d_id);
	
	public DateBall queryDateBallbyId(Integer d_id);
	
	public boolean UpdateO_id(Integer d_id,Integer o_id);
	
	public Integer selectO_id(Integer d_id);
	
	public boolean deleteDateBall(Integer d_id);
}
