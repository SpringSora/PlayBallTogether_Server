package com.ball.dao.seller;

import com.ball.bean.Seller;

public interface SellerDao {
	/**
	 * 此账号是否被注册
	 * @param account
	 * @return true 已经被注册,false没有被注册
	 */
	public boolean SellerisRegister(String account);
	/**
	 * 商家注册
	 * @param seller
	 * @return 0注册失败,否则注册成功
	 */
	public int SellerRegister(Seller seller);

	/**
	 * 通过id查询Seller对象
	 * @param s_id
	 * @return Seller 对象
	 */
	public Seller QuerySellerbyId(int s_id);
	/**
	 * 通过账号查询Seller对象
	 * @param s_account
	 * @return
	 */
	public Seller QuerySellerbyAccount(String s_account);
}
