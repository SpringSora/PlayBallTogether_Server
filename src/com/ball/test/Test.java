package com.ball.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ball.connect.SqlConnection;


public class  Test
{
public static void main(String[] args) 
{
	Connection conn = SqlConnection.getConnection();
	PreparedStatement ps = null;
	ResultSet rs = null;
	String sql1 = "insert into test1 (t_object) values (?);";
	String sql2 = "select @@IDENTITY as id";
	try {
		ps = conn.prepareStatement(sql1);
		ps.setInt(1, 1);
		int cout = ps.executeUpdate();
		ps = conn.prepareStatement(sql2);
		rs = ps.executeQuery();
		if(rs.next()){
			System.out.println(rs.getInt("id"));
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally{
		SqlConnection.Close(conn, ps, rs);
	}
	
}
}