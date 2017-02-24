package com.ball.impl.myorder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ball.bean.MyOrder;
import com.ball.connect.SqlConnection;
import com.ball.dao.myorder.MyOrderDao;
import com.ball.dao.seller.BallGroundDao;
import com.ball.dao.user.UserDao;
import com.ball.impl.seller.BallGroundImpl;
import com.ball.impl.user.UserImpl;

public class MyOrderImpl implements MyOrderDao{

	@Override
	public Integer WriteOrder(Integer u_id,Integer p_id, Float o_price, String o_date,String o_starttime,String o_endtime) {
		// TODO Auto-generated method stub
		Connection conn = SqlConnection.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "insert into myorder(u_id,p_id,o_ispay,o_isevaluate,o_isrefund,o_price,o_date,o_starttime,o_endtime) values(?,?,?,?,?,?,?,?,?) ";
		String sql2 = "select @@IDENTITY as id";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, u_id);
			ps.setInt(2, p_id);
			ps.setInt(3, 1);
			ps.setInt(4, 0);
			ps.setInt(5, 0);
			ps.setFloat(6, o_price);
			ps.setString(7, o_date);
			ps.setString(8, o_starttime);
			ps.setString(9, o_endtime);
			int count = ps.executeUpdate();
			if(count>0){
				ps = conn.prepareStatement(sql2);
				rs = ps.executeQuery();
				if(rs.next()){
					return rs.getInt("id");
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			SqlConnection.Close(conn, ps, rs);
		}
		return 0;
	}

	@Override
	public List<MyOrder> GetOrder(Integer u_id) {
		// TODO Auto-generated method stub
		Connection conn = SqlConnection.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from myorder where u_id = ?";
		UserDao ud = new UserImpl();
		BallGroundDao bgd = new BallGroundImpl();
		List<MyOrder> orders = new ArrayList<MyOrder>();
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, u_id);
			rs = ps.executeQuery();
			while(rs.next()){
				orders.add(new MyOrder(rs.getInt("o_id"), ud.QueryUserbyuId(rs.getInt("u_id")), bgd.QueryBallGroundbypId(rs.getInt("p_id")), rs.getTimestamp("o_datetime"), rs.getInt("o_ispay"), rs.getInt("o_isevaluate"), rs.getInt("o_isrefund"), rs.getFloat("o_price"), rs.getString("o_date"), rs.getString("o_starttime"), rs.getString("o_endtime")));
			}
			return orders;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			SqlConnection.Close(conn, ps, rs);
		}
		return null;
	}

	@Override
	public boolean ModifyOrderStatus(Integer o_isevaluate, Integer o_isrefund,
			Integer o_id) {
		// TODO Auto-generated method stub
		Connection conn = SqlConnection.getConnection();
		PreparedStatement ps = null;
		String sql = null;
		if(o_isevaluate!=null){
			sql = "update myorder set o_ispay = 0,o_isevaluate = ? where o_id = ?";
		}
		if(o_isrefund!=null){
			sql = "update myorder set o_ispay = 0,o_isrefund = 1 where o_id = ?";
		}
		
		try {
			ps = conn.prepareStatement(sql);
			if(o_isevaluate!=null){
				ps.setInt(1, o_isevaluate);
				ps.setInt(2, o_id);
			}
			if(o_isrefund!=null){
				ps.setInt(1, o_id);
			}
			int count = ps.executeUpdate();
			if(count > 0){
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			SqlConnection.Close(conn, ps, null);
		}
		
		return false;
	}

	@Override
	public MyOrder queryOrderbyId(Integer o_id) {
		// TODO Auto-generated method stub
		Connection conn = SqlConnection.getConnection();
		PreparedStatement ps = null;
		UserDao ud = new UserImpl();
		BallGroundDao bgd = new BallGroundImpl();
		String sql = "select * from myorder where o_id = ?";
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, o_id);
			rs = ps.executeQuery();
			if(rs.next()){
				return new MyOrder(o_id, ud.QueryUserbyuId(rs.getInt("u_id")), bgd.QueryBallGroundbypId(rs.getInt("p_id")), rs.getTimestamp("o_datetime"), rs.getInt("o_ispay"), rs.getInt("o_isevaluate"), rs.getInt("o_isrefund"), rs.getFloat("o_price"), rs.getString("o_date"), rs.getString("o_starttime"), rs.getString("o_endtime"));
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
	public boolean deleteOrder(Integer o_id) {
		// TODO Auto-generated method stub
		Connection conn = SqlConnection.getConnection();
		PreparedStatement ps = null;
		String sql = "delete from myorder where o_id = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, o_id);
			int count = ps.executeUpdate();
			if(count > 0){
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			SqlConnection.Close(conn, ps, null);
		}
		return false;
	}


}
