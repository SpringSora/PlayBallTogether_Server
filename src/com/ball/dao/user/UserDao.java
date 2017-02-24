package com.ball.dao.user;

import java.util.List;

import com.ball.bean.User;

public interface UserDao {
	/**
	 * �û���½
	 * @param u_account
	 * @param u_password
	 * @return
	 */
	public User UserLogin(String u_account,String u_password);
	
	/**
	 * ����Ƿ��д˵绰��ע��
	 * @param phone
	 * @return true���Ǳ�ע�� falseû��
	 */
	public boolean UserCheckPhone(String phone);
	/**
	 * ע��
	 * @param phone
	 * @param password
	 * @return
	 */
	public User UserRegister(String phone,String password);
	/**
	 * ͨ��phone��id
	 * @param phone
	 * @return
	 */
	public Integer QueryuIdbyPhone(String phone);
	/**
	 * 
	 * @param u_id
	 * @return
	 */
	public User QueryUserbyuId(Integer u_id);
	
	public boolean UpdateNickName(Integer u_id,String NickName);
	
	public boolean UpdatePhone(Integer u_id,String Phone);
	
	public boolean UpdatePassword(Integer u_id,String newPassword,String oldPassword);
	
	public boolean UpdateSex(Integer u_id,String Sex);
	
	public boolean UpdateAge(Integer u_id,Integer Age);
	
	public boolean UpdateConnect(Integer u_id,String Connect);
	
	public boolean UpdateSign(Integer u_id,String sign);
	
	public boolean UpdateHobby(Integer u_id,String Hobby);
	
	public boolean UpdatePic(Integer u_id,String picpath);
	
	public List<User> SearchUser(Integer page,Integer item, String key);
}
