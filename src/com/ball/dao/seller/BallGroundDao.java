package com.ball.dao.seller;

import java.util.List;

import com.ball.bean.BallGround;

public interface BallGroundDao {
	
	/**
	 * 
	 * @param ballGround
	 */
	public boolean writeBallGroundInfo(BallGround ballGround);
	/**
	 * 
	 * @param s_id
	 * @return ��������
	 */
	public BallGround QueryBallGroundInfobyId(int s_id);
	
	/**
	 * 
	 * @param ballGround
	 * @return
	 */
	public boolean UpdateBallGroundInfo(BallGround ballGround);
	
	/**
	 * 
	 */
	public BallGround QueryBallGroundbypId(Integer p_id);
	
	public List<BallGround> getBallGrounds(Integer page,Integer item,Integer type,String city);
	
	public List<BallGround> SearchBallGrounds(Integer page,Integer item,String city,String key);
}
