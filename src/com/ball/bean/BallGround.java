package com.ball.bean;

import java.util.List;

/**
 * @author Springsora
 *
 */
public class BallGround {
	//��id;
	int p_id;
	//������
	String GroundName;
	//������
	int BallType;
	//������
	int GroundNum;
	//��ʣ������
	int GroundLeft;
	//�򳡼۸�
	float GroundPrice;
	//����Ϣ
	String GroundInfo;
	//�򳡵绰
	String GroundPhone;
	//�򳡾���
	double lng;
	//��γ��
	double lat;
	//�����ڳ���
	String city;
	//��λ��
	String address;
	//�����
	Seller seller;
	//���ͼƬ1·��
	String BallGroundPic1Path;
	//���ͼƬ2·��
	String BallGroundPic2Path;
	//���ͼƬ3·��
	String BallGroundPic3Path;
	//�Ƿ�ʼӪҵ
	int isBusiness;
	
	private List<Comment> comments;
	
	
	public BallGround(String groundName, int ballType, int groundNum,
			float groundPrice, String groundInfo, String groundPhone,
			double lng, double lat, String city, Seller seller,
			String ballGroundPic1Path, String ballGroundPic2Path,
			String ballGroundPic3Path,int isBusiness) {
		super();
		GroundName = groundName;
		BallType = ballType;
		GroundNum = groundNum;
		GroundPrice = groundPrice;
		GroundInfo = groundInfo;
		GroundPhone = groundPhone;
		this.lng = lng;
		this.lat = lat;
		this.city = city;
		this.seller = seller;
		BallGroundPic1Path = ballGroundPic1Path;
		BallGroundPic2Path = ballGroundPic2Path;
		BallGroundPic3Path = ballGroundPic3Path;
		this.isBusiness = isBusiness;
	}

	public BallGround(String groundName, int ballType, int groundNum,
			int groundLeft, float groundPrice, String groundInfo,
			String groundPhone, double lng, double lat,String address ,String city,
			Seller seller, String ballGroundPic1Path,
			String ballGroundPic2Path, String ballGroundPic3Path,int isBusiness) {
		super();
		GroundName = groundName;
		BallType = ballType;
		GroundNum = groundNum;
		GroundLeft = groundLeft;
		GroundPrice = groundPrice;
		GroundInfo = groundInfo;
		GroundPhone = groundPhone;
		this.lng = lng;
		this.lat = lat;
		this.address = address;
		this.city = city;
		this.seller = seller;
		BallGroundPic1Path = ballGroundPic1Path;
		BallGroundPic2Path = ballGroundPic2Path;
		BallGroundPic3Path = ballGroundPic3Path;
		this.isBusiness = isBusiness;
	}

	public BallGround(int p_id, String groundName, int ballType, int groundNum,
			int groundLeft, float groundPrice, String groundInfo,
			String groundPhone, double lng, double lat,String address ,String city,
			Seller seller, String ballGroundPic1Path,
			String ballGroundPic2Path, String ballGroundPic3Path) {
		super();
		this.p_id = p_id;
		GroundName = groundName;
		BallType = ballType;
		GroundNum = groundNum;
		GroundLeft = groundLeft;
		GroundPrice = groundPrice;
		GroundInfo = groundInfo;
		GroundPhone = groundPhone;
		this.lng = lng;
		this.lat = lat;
		this.address  = address;
		this.city = city;
		this.seller = seller;
		BallGroundPic1Path = ballGroundPic1Path;
		BallGroundPic2Path = ballGroundPic2Path;
		BallGroundPic3Path = ballGroundPic3Path;
	}







	public String getGroundName() {
		return GroundName;
	}
	public void setGroundName(String groundName) {
		GroundName = groundName;
	}
	public int getBallType() {
		return BallType;
	}
	public void setBallType(int ballType) {
		BallType = ballType;
	}
	public int getGroundNum() {
		return GroundNum;
	}
	public void setGroundNum(int groundNum) {
		GroundNum = groundNum;
	}
	public float getGroundPrice() {
		return GroundPrice;
	}
	public void setGroundPrice(float groundPrice) {
		GroundPrice = groundPrice;
	}
	public String getGroundInfo() {
		return GroundInfo;
	}
	public void setGroundInfo(String groundInfo) {
		GroundInfo = groundInfo;
	}
	public String getGroundPhone() {
		return GroundPhone;
	}
	public void setGroundPhone(String groundPhone) {
		GroundPhone = groundPhone;
	}
	public double getLng() {
		return lng;
	}
	public void setLng(double lng) {
		this.lng = lng;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public Seller getSeller() {
		return seller;
	}
	public void setSeller(Seller seller) {
		this.seller = seller;
	}
	public String getBallGroundPic1Path() {
		return BallGroundPic1Path;
	}
	public void setBallGroundPic1Path(String ballGroundPic1Path) {
		BallGroundPic1Path = ballGroundPic1Path;
	}
	public String getBallGroundPic2Path() {
		return BallGroundPic2Path;
	}
	public void setBallGroundPic2Path(String ballGroundPic2Path) {
		BallGroundPic2Path = ballGroundPic2Path;
	}
	public String getBallGroundPic3Path() {
		return BallGroundPic3Path;
	}
	public void setBallGroundPic3Path(String ballGroundPic3Path) {
		BallGroundPic3Path = ballGroundPic3Path;
	}
	
	public List<Comment> getComments() {
		return comments;
	}
	
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}



	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getP_id() {
		return p_id;
	}



	public void setP_id(int p_id) {
		this.p_id = p_id;
	}







	public int getGroundLeft() {
		return GroundLeft;
	}







	public void setGroundLeft(int groundLeft) {
		GroundLeft = groundLeft;
	}







	public int getIsBusiness() {
		return isBusiness;
	}







	public void setIsBusiness(int isBusiness) {
		this.isBusiness = isBusiness;
	}
	
	
	
	
	
}
