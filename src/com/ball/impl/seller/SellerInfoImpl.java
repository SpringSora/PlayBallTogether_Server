package com.ball.impl.seller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ball.bean.Seller;
import com.ball.bean.SellerInfo;
import com.ball.connect.SqlConnection;
import com.ball.dao.seller.SellerInfoDao;

public class SellerInfoImpl implements SellerInfoDao{
	
	public int WriteSellerInfo(SellerInfo sellerInfo) {
		Connection conn = SqlConnection.getConnection();
		PreparedStatement ps = null;
		String sql = "insert into sellerinfo(s_id,sellername,sellerphone,sellermail,licencenumber,licencename,licencepic,validtime,idcard,idcardpic)"
				+ "values(?,?,?,?,?,?,?,?,?,?)";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, sellerInfo.getSeller().getSeller_id());
			ps.setString(2, sellerInfo.getSellerName());
			ps.setString(3, sellerInfo.getSellerPhone());
			ps.setString(4, sellerInfo.getSellerMail());
			ps.setString(5, sellerInfo.getLicenceNum());
			ps.setString(6, sellerInfo.getLicenceName());
			ps.setString(7, sellerInfo.getLicencePicPath());
			ps.setDate(8, (Date) sellerInfo.getLicenceDate());
			ps.setString(9, sellerInfo.getIdCard());
			ps.setString(10, sellerInfo.getIdCardPicPath());
			int count = ps.executeUpdate();
			if(count>0){
				return sellerInfo.getSeller().getSeller_id();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			SqlConnection.Close(conn, ps, null);
		}
		return 0;
	}

	@Override
	public SellerInfo QuerySellerInfobyId(int s_id) {
		// TODO Auto-generated method stub
		Connection conn = SqlConnection.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from sellerinfo where s_id = ?";
		SellerImpl si = new SellerImpl();
		Seller seller = si.QuerySellerbyId(s_id);
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, s_id);
			rs = ps.executeQuery();
			if(rs.next()){
				String sellerName = rs.getString("sellername");
				String sellerPhone = rs.getString("sellerphone");
				String sellerMail = rs.getString("sellermail");
				String licenceNum = rs.getString("licencenumber");
				String licenceName = rs.getString("licencename");
				String licencepic = rs.getString("licencepic");
				java.util.Date validtime = rs.getDate("validtime");
				String idCard = rs.getString("idcard");
				String idcardpic = rs.getString("idcardpic");
				SellerInfo sellerInfo = new SellerInfo(seller, sellerName, sellerPhone, sellerMail, licenceNum, licenceName, validtime, licencepic, idCard, idcardpic);
				return sellerInfo;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			SqlConnection.Close(conn, ps, rs);
		}
		return null;
	}
}
