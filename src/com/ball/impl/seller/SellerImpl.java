package com.ball.impl.seller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import com.ball.bean.Seller;
import com.ball.connect.SqlConnection;
import com.ball.dao.seller.SellerDao;
import com.ball.md5.MD5;

public class SellerImpl implements SellerDao{

	@Override
	public boolean SellerisRegister(String account) {
		// TODO Auto-generated method stub
		Connection conn = SqlConnection.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from seller where s_account = ?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, account);
			rs = ps.executeQuery();
			if(rs.next()){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			SqlConnection.Close(conn, ps, rs);
		}
		return false;
	}

	@SuppressWarnings("resource")
	@Override
	public int SellerRegister(Seller seller) {
		// TODO Auto-generated method stub
		Connection conn = SqlConnection.getConnection();
		PreparedStatement ps = null;
		String sql = "insert into seller(s_account,s_password) values(?,?)";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, seller.getAccount());
			ps.setString(2,MD5.GetMD5Code(seller.getPassword()));
			int count = ps.executeUpdate();
			if(count>0){
				ps = null;
				ResultSet rs = null;
				String newsql = "select s_id from seller where s_account = ?";
				ps = conn.prepareStatement(newsql);
				ps.setString(1, seller.getAccount());
				rs = ps.executeQuery();
				if(rs.next()){
					return rs.getInt("s_id");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			SqlConnection.Close(conn, ps, null);
		}
		return 0;
	}

	@Override
	public Seller QuerySellerbyId(int s_id) {
		// TODO Auto-generated method stub
		Connection conn = SqlConnection.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from seller where s_id = ?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, s_id);
			rs = ps.executeQuery();
			if(rs.next()){
				String account = rs.getString("s_account");
				String password = rs.getString("s_password");
				Seller seller = new Seller(s_id, account, password);
				return seller;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			SqlConnection.Close(conn, ps, rs);
		}
		return null;
	}

	@Override
	public Seller QuerySellerbyAccount(String s_account) {
		// TODO Auto-generated method stub
		Connection conn = SqlConnection.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from seller where s_account = ?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, s_account);
			rs = ps.executeQuery();
			if(rs.next()){
				int s_id = rs.getInt("s_id");
				String password = rs.getString("s_password");
				Seller seller = new Seller(s_id, s_account, password);
				return seller;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			SqlConnection.Close(conn, ps, rs);
		}
		return null;
	}


}
