package com.ball.bean;

import java.util.Date;

public class SellerInfo {
	Seller seller;
	String sellerName;
	String sellerPhone;
	String sellerMail;
	String licenceNum;
	String licenceName;
	Date licenceDate;
	String licencePicPath;
	String idCard;
	String idCardPicPath;
	public SellerInfo(Seller seller, String sellerName, String sellerPhone,
			String sellerMail, String licenceNum, String licenceName,
			Date licenceDate, String licencePicPath, String idCard,
			String idCardPicPath) {
		super();
		this.seller = seller;
		this.sellerName = sellerName;
		this.sellerPhone = sellerPhone;
		this.sellerMail = sellerMail;
		this.licenceNum = licenceNum;
		this.licenceName = licenceName;
		this.licenceDate = licenceDate;
		this.licencePicPath = licencePicPath;
		this.idCard = idCard;
		this.idCardPicPath = idCardPicPath;
	}
	public Seller getSeller() {
		return seller;
	}
	public void setSeller(Seller seller) {
		this.seller = seller;
	}
	public String getSellerName() {
		return sellerName;
	}
	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}
	public String getSellerPhone() {
		return sellerPhone;
	}
	public void setSellerPhone(String sellerPhone) {
		this.sellerPhone = sellerPhone;
	}
	public String getSellerMail() {
		return sellerMail;
	}
	public void setSellerMail(String sellerMail) {
		this.sellerMail = sellerMail;
	}
	public String getLicenceNum() {
		return licenceNum;
	}
	public void setLicenceNum(String licenceNum) {
		this.licenceNum = licenceNum;
	}
	public String getLicenceName() {
		return licenceName;
	}
	public void setLicenceName(String licenceName) {
		this.licenceName = licenceName;
	}
	public Date getLicenceDate() {
		return licenceDate;
	}
	public void setLicenceDate(Date licenceDate) {
		this.licenceDate = licenceDate;
	}
	public String getLicencePicPath() {
		return licencePicPath;
	}
	public void setLicencePicPath(String licencePicPath) {
		this.licencePicPath = licencePicPath;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public String getIdCardPicPath() {
		return idCardPicPath;
	}
	public void setIdCardPicPath(String idCardPicPath) {
		this.idCardPicPath = idCardPicPath;
	}

	
}
