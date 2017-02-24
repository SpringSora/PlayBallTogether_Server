package com.ball.impl.seller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ball.bean.BallGround;
import com.ball.bean.Seller;
import com.ball.connect.SqlConnection;
import com.ball.dao.comment.CommentDao;
import com.ball.dao.seller.BallGroundDao;
import com.ball.dao.seller.SellerDao;
import com.ball.impl.comment.CommentImpl;

public class BallGroundImpl implements BallGroundDao{

	@Override
	public boolean writeBallGroundInfo(BallGround ballGround) {
		// TODO Auto-generated method stub
		Connection conn = SqlConnection.getConnection();
		PreparedStatement ps = null;
		String sql = "insert into playground(p_name,p_lng,p_lat,p_type,p_info,s_id,p_num,p_left,p_price,p_phone,p_city,p_address,p_pic1,p_pic2,p_pic3) value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, ballGround.getGroundName());
			ps.setDouble(2, ballGround.getLng());
			ps.setDouble(3, ballGround.getLat());
			ps.setInt(4, ballGround.getBallType());
			ps.setString(5, ballGround.getGroundInfo());
			ps.setInt(6, ballGround.getSeller().getSeller_id());
			ps.setInt(7, ballGround.getGroundNum());
			ps.setInt(8, ballGround.getGroundNum());
			ps.setFloat(9, ballGround.getGroundPrice());
			ps.setString(10, ballGround.getGroundPhone());
			ps.setString(11, ballGround.getCity());
			ps.setString(12, ballGround.getAddress());
			ps.setString(13, ballGround.getBallGroundPic1Path());
			ps.setString(14, ballGround.getBallGroundPic2Path());
			ps.setString(15, ballGround.getBallGroundPic3Path());
			int count = ps.executeUpdate();
			if(count > 0){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			SqlConnection.Close(conn, ps, null);
		}
		return false;
	}

	@Override
	public BallGround QueryBallGroundInfobyId(int s_id) {
		// TODO Auto-generated method stub
		Seller seller = null;
		Connection conn = SqlConnection.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from playground where s_id = ?";
		SellerDao sd = new SellerImpl();
		seller = sd.QuerySellerbyId(s_id);
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, s_id);
			rs = ps.executeQuery();
			if(rs.next()){
				int p_id = rs.getInt("p_id");
				String GroundName = rs.getString("p_name");
				double lng = rs.getDouble("p_lng");
				double lat = rs.getDouble("p_lat");
				int BallType = rs.getInt("p_type");
				String GroundInfo = rs.getString("p_info");
				int GroundNum = rs.getInt("p_num");
				int GroundLeft = rs.getInt("p_left");
				float GroundPrice = rs.getFloat("p_price");
				String GroundPhone = rs.getString("p_phone");
				String Address = rs.getString("p_address");
				String City = rs.getString("p_city");
				String BallGroundPic1Path = rs.getString("p_pic1");
				String BallGroundPic2Path = rs.getString("p_pic2");
				String BallGroundPic3Path = rs.getString("p_pic3");
				BallGround ballGround = new BallGround(p_id, GroundName, BallType, GroundNum,GroundLeft, GroundPrice, GroundInfo, GroundPhone, lng, lat,Address ,City, seller, BallGroundPic1Path, BallGroundPic2Path, BallGroundPic3Path);
				return ballGround;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			SqlConnection.Close(conn, ps, rs);
		}
		return null;
	}

	@Override
	public boolean UpdateBallGroundInfo(BallGround ballGround) {
		// TODO Auto-generated method stub
		Connection conn = SqlConnection.getConnection();
		PreparedStatement ps = null;
		String sql = "update playground set p_info = ?,p_num = ?,p_left = ?,p_price = ?,p_phone = ?,p_pic1 = ?,p_pic2 = ?,p_pic3 = ?,business = ? where s_id = ?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, ballGround.getGroundInfo());
			ps.setInt(2, ballGround.getGroundNum());
			ps.setInt(3, ballGround.getGroundLeft());
			ps.setFloat(4, ballGround.getGroundPrice());
			ps.setString(5, ballGround.getGroundPhone());
			ps.setString(6, ballGround.getBallGroundPic1Path());
			ps.setString(7, ballGround.getBallGroundPic2Path());
			ps.setString(8, ballGround.getBallGroundPic3Path());
			ps.setInt(9, ballGround.getIsBusiness());
			ps.setInt(10, ballGround.getSeller().getSeller_id());
			int count = ps.executeUpdate();
			if(count>0){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public BallGround QueryBallGroundbypId(Integer p_id) {
		// TODO Auto-generated method stub
		Connection conn = SqlConnection.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from playground where p_id = ?";
		SellerDao sd = new SellerImpl();
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, p_id);
			rs = ps.executeQuery();
			if(rs.next()){
				return new BallGround(p_id, rs.getString("p_name"), rs.getInt("p_type"), rs.getInt("p_num"), rs.getInt("p_left"), rs.getFloat("p_price"), rs.getString("p_info"), rs.getString("p_phone"), rs.getDouble("p_lng"), rs.getDouble("p_lat"),rs.getString("p_address"), rs.getString("p_city"), sd.QuerySellerbyId(rs.getInt("s_id")), rs.getString("p_pic1"), rs.getString("p_pic2"), rs.getString("p_pic3"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			SqlConnection.Close(conn, ps, rs);
		}
		return null;
	}

	@Override
	public List<BallGround> getBallGrounds(Integer page, Integer item,
			Integer type, String city) {
		// TODO Auto-generated method stub
		Connection conn = SqlConnection.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = null;
		List<BallGround> ballGrounds = new ArrayList<BallGround>();
		SellerDao sd = new SellerImpl();
		CommentDao cd = new CommentImpl();
		if(type!=null&&type!=0){
			sql = "select * from playground where p_city = ? && p_type = ? limit ?,?";
			try {
				ps = conn.prepareStatement(sql);
				ps.setString(1, city);
				ps.setInt(2, type);
				ps.setInt(3, (page-1)*item);
				ps.setInt(4, item);
				rs = ps.executeQuery();
				while(rs.next()){
					BallGround ballGround = new BallGround(rs.getInt("p_id"), rs.getString("p_name"), rs.getInt("p_type"), rs.getInt("p_num"), rs.getInt("p_left"), rs.getFloat("p_price"), rs.getString("p_info"), rs.getString("p_phone"), rs.getDouble("p_lng"), rs.getDouble("p_lat"),rs.getString("p_address") ,rs.getString("p_city"), sd.QuerySellerbyId(rs.getInt("s_id")), rs.getString("p_pic1"), rs.getString("p_pic2"), rs.getString("p_pic3"));
					ballGround.setComments(cd.getComments(rs.getInt("p_id"),1,10));
					ballGrounds.add(ballGround);
				}
				return ballGrounds;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				SqlConnection.Close(conn, ps, rs);
			}
		}else{
			sql = "select * from playground where p_city = ? limit ?,?";
			try {
				ps = conn.prepareStatement(sql);
				ps.setString(1, city);
				ps.setInt(2, (page-1)*item);
				ps.setInt(3, item);
				rs = ps.executeQuery();
				while(rs.next()){
					BallGround ballGround = new BallGround(rs.getInt("p_id"), rs.getString("p_name"), rs.getInt("p_type"), rs.getInt("p_num"), rs.getInt("p_left"), rs.getFloat("p_price"), rs.getString("p_info"), rs.getString("p_phone"), rs.getDouble("p_lng"), rs.getDouble("p_lat"),rs.getString("p_address") ,rs.getString("p_city"), sd.QuerySellerbyId(rs.getInt("s_id")), rs.getString("p_pic1"), rs.getString("p_pic2"), rs.getString("p_pic3"));
					ballGround.setComments(cd.getComments(rs.getInt("p_id"),1,10));
					ballGrounds.add(ballGround);
				}
				return ballGrounds;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				SqlConnection.Close(conn, ps, rs);
			}
		}
		return null;
	}

	@Override
	public List<BallGround> SearchBallGrounds(Integer page, Integer item,
			String city, String key) {
		// TODO Auto-generated method stub
		Connection conn = SqlConnection.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		SellerDao sd = new SellerImpl();
		CommentDao cd = new CommentImpl();
		String sql = "select * from playground where p_city = ? AND p_name LIKE"+"'%"+key+"%' limit ?,?";
		List<BallGround> ballGrounds = new ArrayList<BallGround>();
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, city);
			ps.setInt(2,(page-1)*item);
			ps.setInt(3, item);
			rs = ps.executeQuery();
			while(rs.next()){
				BallGround ballGround = new BallGround(rs.getInt("p_id"), rs.getString("p_name"), rs.getInt("p_type"), rs.getInt("p_num"), rs.getInt("p_left"), rs.getFloat("p_price"), rs.getString("p_info"), rs.getString("p_phone"), rs.getDouble("p_lng"), rs.getDouble("p_lat"),rs.getString("p_address") ,rs.getString("p_city"), sd.QuerySellerbyId(rs.getInt("s_id")), rs.getString("p_pic1"), rs.getString("p_pic2"), rs.getString("p_pic3"));
				ballGround.setComments(cd.getComments(rs.getInt("p_id"),1,10));
				ballGrounds.add(ballGround);
			}
			return ballGrounds;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			SqlConnection.Close(conn, ps, rs);
		}
		return null;
	}

}
