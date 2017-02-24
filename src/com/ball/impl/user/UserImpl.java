package com.ball.impl.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ball.bean.User;
import com.ball.connect.SqlConnection;
import com.ball.dao.user.UserDao;

public class UserImpl implements UserDao{

	@Override
	public User UserLogin(String u_account, String u_password) {
		// TODO Auto-generated method stub
		Connection conn = SqlConnection.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from user where u_phone = ? || u_nickname= ? ";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, u_account);
			ps.setString(2, u_account);
			rs = ps.executeQuery();
			if(rs.next()){
				return new User(rs.getInt("u_id"), rs.getString("u_phone"), rs.getString("u_password"), rs.getString("u_nickname"), rs.getString("u_pic"), rs.getString("u_sex"), rs.getInt("u_age"), rs.getString("signature"), rs.getString("u_playtype"), rs.getString("u_name"));
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
	public boolean UserCheckPhone(String phone) {
		// TODO Auto-generated method stub
		Connection conn = SqlConnection.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from user where u_phone = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, phone);
			rs = ps.executeQuery();
			if(rs.next()){
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			SqlConnection.Close(conn, ps, rs);
		}
		return false;
	}

	@Override
	public User UserRegister(String phone, String password) {
		// TODO Auto-generated method stub
		Connection conn = SqlConnection.getConnection();
		PreparedStatement ps = null;
		String sql = "insert into user(u_phone,u_password) values(?,?)";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, phone);
			ps.setString(2, password);
			int count = ps.executeUpdate();
			if(count>0){
				return new User(null, phone, password, null, null, null, null, null, null, null);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			SqlConnection.Close(conn, ps, null);
		}
		
		return null;
	}
	
	public Integer QueryuIdbyPhone(String phone){
		Connection conn = SqlConnection.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select u_id from user where u_phone = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, phone);
			rs = ps.executeQuery();
			if(rs.next()){
				return rs.getInt("u_id");
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
	public boolean UpdateNickName(Integer u_id,String NickName) {
		// TODO Auto-generated method stub
		Connection conn = SqlConnection.getConnection();
		PreparedStatement ps = null;
		String sql = "update user set u_nickname = ? where u_id = ?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, NickName);
			ps.setInt(2, u_id);
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
	public boolean UpdatePhone(Integer u_id, String Phone) {
		// TODO Auto-generated method stub
		Connection conn = SqlConnection.getConnection();
		PreparedStatement ps = null;
		String sql = "update user set u_phone = ? where u_id = ?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, Phone);
			ps.setInt(2, u_id);
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
	public boolean UpdatePassword(Integer u_id, String newPassword,String oldPassword) {
		// TODO Auto-generated method stub
		Connection conn = SqlConnection.getConnection();
		PreparedStatement ps = null;
		String sql = "update user set u_password = ? where u_id = ? && u_password = ?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, newPassword);
			ps.setInt(2, u_id);
			ps.setString(3, oldPassword);
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
	public boolean UpdateSex(Integer u_id, String Sex) {
		// TODO Auto-generated method stub
		Connection conn = SqlConnection.getConnection();
		PreparedStatement ps = null;
		String sql = "update user set u_sex = ? where u_id = ?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, Sex);
			ps.setInt(2, u_id);
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
	public boolean UpdateAge(Integer u_id, Integer Age) {
		// TODO Auto-generated method stub
		Connection conn = SqlConnection.getConnection();
		PreparedStatement ps = null;
		String sql = "update user set u_age = ? where u_id = ?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, Age);
			ps.setInt(2, u_id);
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
	public boolean UpdateConnect(Integer u_id, String Connect) {
		// TODO Auto-generated method stub
		Connection conn = SqlConnection.getConnection();
		PreparedStatement ps = null;
		String sql = "update user set u_name = ? where u_id = ?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, Connect);
			ps.setInt(2, u_id);
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
	public boolean UpdateSign(Integer u_id, String sign) {
		// TODO Auto-generated method stub
		Connection conn = SqlConnection.getConnection();
		PreparedStatement ps = null;
		String sql = "update user set signature = ? where u_id = ?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, sign);
			ps.setInt(2, u_id);
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
	public boolean UpdateHobby(Integer u_id, String Hobby) {
		// TODO Auto-generated method stub
		Connection conn = SqlConnection.getConnection();
		PreparedStatement ps = null;
		String sql = "update user set u_playtype = ? where u_id = ?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, Hobby);
			ps.setInt(2, u_id);
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
	public boolean UpdatePic(Integer u_id, String picpath) {
		// TODO Auto-generated method stub
		Connection conn = SqlConnection.getConnection();
		PreparedStatement ps = null;
		String sql = "update user set u_pic = ? where u_id = ?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, picpath);
			ps.setInt(2, u_id);
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
	public User QueryUserbyuId(Integer u_id) {
		// TODO Auto-generated method stub
		Connection conn = SqlConnection.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from user where u_id = ?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, u_id);
			rs = ps.executeQuery();
			if(rs.next()){
				return  new User(u_id, rs.getString("u_phone"), null, rs.getString("u_nickname"), rs.getString("u_pic"), rs.getString("u_sex"), rs.getInt("u_age"), rs.getString("signature"), rs.getString("u_playtype"), rs.getString("u_name"));
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
	public List<User> SearchUser(Integer page, Integer item, String key) {
		// TODO Auto-generated method stub
		Connection conn = SqlConnection.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<User> users = new ArrayList<User>();
		String sql = "select * from user where u_nickname LIKE "+"'%"+key+"%' limit ?,?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, (page - 1)*item);
			ps.setInt(2, item);
			rs = ps.executeQuery();
			while(rs.next()){
				users.add(new User(rs.getInt("u_id"), rs.getString("u_phone"), null, rs.getString("u_nickname"), rs.getString("u_pic"), rs.getString("u_sex"), rs.getInt("u_age"), rs.getString("signature"), rs.getString("u_playtype"), rs.getString("u_name")));
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

}
