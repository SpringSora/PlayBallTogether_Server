package com.ball.dao.seller;

import com.ball.bean.Seller;

public interface SellerDao {
	/**
	 * ���˺��Ƿ�ע��
	 * @param account
	 * @return true �Ѿ���ע��,falseû�б�ע��
	 */
	public boolean SellerisRegister(String account);
	/**
	 * �̼�ע��
	 * @param seller
	 * @return 0ע��ʧ��,����ע��ɹ�
	 */
	public int SellerRegister(Seller seller);

	/**
	 * ͨ��id��ѯSeller����
	 * @param s_id
	 * @return Seller ����
	 */
	public Seller QuerySellerbyId(int s_id);
	/**
	 * ͨ���˺Ų�ѯSeller����
	 * @param s_account
	 * @return
	 */
	public Seller QuerySellerbyAccount(String s_account);
}
