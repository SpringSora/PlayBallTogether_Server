package com.ball.dao.comment;

import java.util.List;

import com.ball.bean.Comment;

public interface CommentDao {
	/**
	 * 
	 * @param u_id
	 * @param p_id
	 * @param connent
	 * @return
	 */
	public boolean WriteComment(Integer u_id,Integer p_id,String connent);
	
	public List<Comment> getComments(Integer p_id,Integer page,Integer item);
}
