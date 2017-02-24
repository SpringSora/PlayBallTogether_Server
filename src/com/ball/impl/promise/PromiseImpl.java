package com.ball.impl.promise;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.spec.PSource;

import com.ball.bean.Promise;
import com.ball.bean.User;
import com.ball.connect.SqlConnection;
import com.ball.dao.dateball.DateBallDao;
import com.ball.dao.myorder.MyOrderDao;
import com.ball.dao.promise.PromiseDao;
import com.ball.dao.user.UserDao;
import com.ball.impl.dateball.DateBallImpl;
import com.ball.impl.myorder.MyOrderImpl;
import com.ball.impl.user.UserImpl;

public class PromiseImpl implements PromiseDao{

	@Override
	public boolean WritePromise(Integer d_id, Integer u_id) {
		// TODO Auto-generated method stub
		Connection conn = SqlConnection.getConnection();
		PreparedStatement ps = null;
		String sql = "insert into promise(d_id,u_id) values(?,?)";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, d_id);
			ps.setInt(2, u_id);
			int Count = ps.executeUpdate();
			if(Count > 0){
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
	public List<Promise> GetPromise(Integer u_id,Integer page,Integer item) {
		// TODO Auto-generated method stub
		Connection conn = SqlConnection.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from promise where u_id = ? limit ?,?";
		UserDao ud = new UserImpl();
		DateBallDao dbd = new DateBallImpl();
		MyOrderDao mod = new MyOrderImpl();
		List<Promise> promises = new ArrayList<Promise>();
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, u_id);
			ps.setInt(2, (page - 1)*item);
			ps.setInt(3, item);
			rs = ps.executeQuery();
			while(rs.next()){
				promises.add(new Promise(ud.QueryUserbyuId(u_id),dbd.queryDateBallbyId(rs.getInt("d_id")), mod.queryOrderbyId(rs.getInt("o_id")),rs.getTimestamp("pro_currenttime")));
			}
			return promises;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			SqlConnection.Close(conn, ps, rs);
		}
		return null;
	}

	@Override
	public List<User> getUsers(Integer d_id) {
		// TODO Auto-generated method stub
		Connection conn = SqlConnection.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<User> users = new ArrayList<User>();
		UserDao ud = new UserImpl();
		String sql = "select u_id from promise where d_id = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, d_id);
			rs = ps.executeQuery();
			while(rs.next()){
				users.add(ud.QueryUserbyuId(rs.getInt("u_id")));
			}
			return users;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			SqlConnection.Close(conn, ps, rs);
		}
		return null;
	}

	@Override
	public boolean UpdateO_id(Integer u_id, Integer d_id, Integer o_id) {
		// TODO Auto-generated method stub
		Connection conn = SqlConnection.getConnection();
		PreparedStatement ps = null;
		String sql = "update promise set o_id = ? where u_id = ? AND d_id = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, o_id);
			ps.setInt(2, u_id);
			ps.setInt(3, d_id);
			int count = ps.executeUpdate();
			if(count>0){
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
	public Integer selectOrderid(Integer d_id, Integer u_id) {
		// TODO Auto-generated method stub
		Connection conn = SqlConnection.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select o_id from promise where u_id = ? and d_id = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, u_id);
			ps.setInt(2, d_id);
			rs = ps.executeQuery();
			if(rs.next()){
				return rs.getInt("o_id");
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
	public boolean DeletePromise(Integer d_id, Integer u_id) {
		// TODO Auto-generated method stub
		Connection conn = SqlConnection.getConnection();
		PreparedStatement ps = null;
		String sql = "delete from promise where u_id = ? and d_id = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, u_id);
			ps.setInt(2, d_id);
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
	public boolean DeletePromise(Integer d_id) {
		// TODO Auto-generated method stub
		Connection conn = SqlConnection.getConnection();
		PreparedStatement ps = null;
		String sql = "delete from promise where d_id = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, d_id);
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
	public List<Integer> selectOrdersId(Integer d_id) {
		// TODO Auto-generated method stub
		Connection conn = SqlConnection.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Integer> o_ids = new ArrayList<Integer>();
		String sql = "select o_id from promise where d_id = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, d_id);
			rs = ps.executeQuery();
			while(rs.next()){
				o_ids.add(rs.getInt("o_id"));
			}
			return o_ids;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			SqlConnection.Close(conn, ps, rs);
		}
		return null;
	}

}
