package com.ball.dao.seller;

import com.ball.bean.SellerInfo;

public interface SellerInfoDao {
	/**
	 * ¼���̼һ�����Ϣ
	 * @param sellerInfo
	 * @return ����0ʧ��
	 */
	public int WriteSellerInfo(SellerInfo sellerInfo);
	
	/**
	 * ͨ��s_id��ѯ�̼���Ϣ
	 * @param s_id
	 * @return SellerInfo
	 */
	public SellerInfo QuerySellerInfobyId(int s_id);
}
