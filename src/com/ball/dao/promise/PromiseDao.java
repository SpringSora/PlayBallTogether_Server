package com.ball.dao.promise;

import java.util.List;

import com.ball.bean.Promise;
import com.ball.bean.User;

public interface PromiseDao {
	public boolean WritePromise(Integer d_id,Integer u_id);
	
	public List<Promise> GetPromise(Integer u_id,Integer page,Integer item);
	
	public List<User> getUsers(Integer d_id);
	
	public boolean UpdateO_id(Integer u_id,Integer d_id,Integer o_id);
	
	public Integer selectOrderid(Integer d_id,Integer u_id);
	
	public boolean DeletePromise(Integer d_id,Integer u_id);
	
	public boolean DeletePromise(Integer d_id);
	
	public List<Integer> selectOrdersId(Integer d_id);
}
