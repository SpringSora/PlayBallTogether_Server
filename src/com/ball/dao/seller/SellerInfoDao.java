package com.ball.dao.seller;

import com.ball.bean.SellerInfo;

public interface SellerInfoDao {
	/**
	 * 录入商家基本信息
	 * @param sellerInfo
	 * @return 返回0失败
	 */
	public int WriteSellerInfo(SellerInfo sellerInfo);
	
	/**
	 * 通过s_id查询商家信息
	 * @param s_id
	 * @return SellerInfo
	 */
	public SellerInfo QuerySellerInfobyId(int s_id);
}
