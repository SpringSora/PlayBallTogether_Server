package com.ball.impl.dateball;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



import com.ball.bean.DateBall;
import com.ball.bean.User;
import com.ball.connect.SqlConnection;
import com.ball.dao.dateball.DateBallDao;
import com.ball.dao.seller.BallGroundDao;
import com.ball.dao.user.UserDao;
import com.ball.impl.seller.BallGroundImpl;
import com.ball.impl.user.UserImpl;

public class DateBallImpl implements DateBallDao{

	@Override
	public Integer WriteDateBall(Integer u_id,DateBall dateball,Integer p_id) {
		// TODO Auto-generated method stub
		Connection conn = SqlConnection.getConnection();
		PreparedStatement ps = null;
		String sql = null;
		String sql2 = "select @@IDENTITY as id";
		ResultSet rs = null;
		if(p_id!=null){
			sql = "insert into dateball(u_id,d_pic,d_title,d_text,d_type,d_location,d_num,d_date,d_starttime,d_endtime,d_city,p_id) values(?,?,?,?,?,?,?,?,?,?,?,?)";
		}else{
			sql = "insert into dateball(u_id,d_pic,d_title,d_text,d_type,d_location,d_num,d_date,d_starttime,d_endtime,d_city) values(?,?,?,?,?,?,?,?,?,?,?)";
		}
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, u_id);
			ps.setString(2, dateball.getD_pic());
			ps.setString(3, dateball.getD_title());
			ps.setString(4, dateball.getD_text());
			ps.setInt(5, dateball.getD_type());
			ps.setString(6, dateball.getD_location());
			ps.setInt(7, dateball.getD_num());
			ps.setString(8, dateball.getD_date());
			ps.setString(9,dateball.getD_starttime());
			ps.setString(10, dateball.getD_endtime());
			ps.setString(11, dateball.getD_city());
			if(p_id!=null){
				ps.setInt(12, p_id);
			}
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
			SqlConnection.Close(conn, ps, null);
		}
		return 0;
	}

	@Override
	public List<DateBall> getListDateBall(int page, int item,String city,Integer type) {
		// TODO Auto-generated method stub
		Connection conn = SqlConnection.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = null;
		UserDao ud = new UserImpl();
		BallGroundDao bgd = new BallGroundImpl();
		List<DateBall> dateBalls = new ArrayList<DateBall>();
		if(type!=null&&type!=0){
			sql = "select * from dateball where d_city = ?&&d_type = ?&&d_success=0 order by d_time desc limit ?,? ";
			try {
				ps = conn.prepareStatement(sql);
				ps.setString(1, city);
				ps.setInt(2, type);
				ps.setInt(3, (page-1)*4);
				ps.setInt(4, item);
				rs = ps.executeQuery();
				while(rs.next()){
					if(rs.getInt("p_id")!=0){
						dateBalls.add(new DateBall(rs.getInt("d_id"), ud.QueryUserbyuId(rs.getInt("u_id")), rs.getString("d_pic"), rs.getString("d_title"), rs.getString("d_text"), Integer.valueOf(rs.getString("d_type")), rs.getString("d_location"), rs.getInt("d_num"), rs.getTimestamp("d_time"), rs.getString("d_date"), rs.getString("d_starttime"), rs.getString("d_endtime"), bgd.QueryBallGroundbypId(rs.getInt("p_id")),rs.getString("d_city"),rs.getInt("d_success")));
					}else{
						dateBalls.add(new DateBall(rs.getInt("d_id"), ud.QueryUserbyuId(rs.getInt("u_id")), rs.getString("d_pic"), rs.getString("d_title"), rs.getString("d_text"), Integer.valueOf(rs.getString("d_type")), rs.getString("d_location"), rs.getInt("d_num"), rs.getTimestamp("d_time"), rs.getString("d_date"), rs.getString("d_starttime"), rs.getString("d_endtime"), null,rs.getString("d_city"),rs.getInt("d_success")));
					}
				}
				return dateBalls;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				SqlConnection.Close(conn, ps, rs);
			}
			
		}else{
			sql = "select * from dateball where d_city = ?&&d_success=0 order by d_time desc limit ?,? ";

			try {
				ps = conn.prepareStatement(sql);
				ps.setString(1, city);
				ps.setInt(2, (page-1)*4);
				ps.setInt(3, item);
				rs = ps.executeQuery();
				while(rs.next()){
					if(rs.getInt("p_id")!=0){
						dateBalls.add(new DateBall(rs.getInt("d_id"), ud.QueryUserbyuId(rs.getInt("u_id")), rs.getString("d_pic"), rs.getString("d_title"), rs.getString("d_text"), Integer.valueOf(rs.getString("d_type")), rs.getString("d_location"), rs.getInt("d_num"), rs.getTimestamp("d_time"), rs.getString("d_date"), rs.getString("d_starttime"), rs.getString("d_endtime"), bgd.QueryBallGroundbypId(rs.getInt("p_id")),rs.getString("d_city"),rs.getInt("d_success")));
					}else{
						dateBalls.add(new DateBall(rs.getInt("d_id"), ud.QueryUserbyuId(rs.getInt("u_id")), rs.getString("d_pic"), rs.getString("d_title"), rs.getString("d_text"), Integer.valueOf(rs.getString("d_type")), rs.getString("d_location"), rs.getInt("d_num"), rs.getTimestamp("d_time"), rs.getString("d_date"), rs.getString("d_starttime"), rs.getString("d_endtime"), null,rs.getString("d_city"),rs.getInt("d_success")));
					}
				}
				return dateBalls;
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
	public List<DateBall> getMyDateBall(Integer page, Integer item, Integer u_id) {
		// TODO Auto-generated method stub
		Connection conn = SqlConnection.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<DateBall> dateBalls = new ArrayList<DateBall>();
		String sql = "select * from dateball where u_id = ? limit ?,?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, u_id);
			ps.setInt(2, (page-1)*item);
			ps.setInt(3, item);
			rs = ps.executeQuery();
			UserDao ud = new UserImpl();
			BallGroundDao bgd = new BallGroundImpl();
			while(rs.next()){
				if(rs.getInt("p_id")!=0){
					dateBalls.add(new DateBall(rs.getInt("d_id"), ud.QueryUserbyuId(rs.getInt("u_id")), rs.getString("d_pic"), rs.getString("d_title"), rs.getString("d_text"), Integer.valueOf(rs.getString("d_type")), rs.getString("d_location"), rs.getInt("d_num"), rs.getTimestamp("d_time"), rs.getString("d_date"), rs.getString("d_starttime"), rs.getString("d_endtime"), bgd.QueryBallGroundbypId(rs.getInt("p_id")),rs.getString("d_city"),rs.getInt("d_success")));
				}else{
					dateBalls.add(new DateBall(rs.getInt("d_id"), ud.QueryUserbyuId(rs.getInt("u_id")), rs.getString("d_pic"), rs.getString("d_title"), rs.getString("d_text"), Integer.valueOf(rs.getString("d_type")), rs.getString("d_location"), rs.getInt("d_num"), rs.getTimestamp("d_time"), rs.getString("d_date"), rs.getString("d_starttime"), rs.getString("d_endtime"), null,rs.getString("d_city"),rs.getInt("d_success")));
				}
			}
			return dateBalls;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			SqlConnection.Close(conn, ps, rs);
		}
		return null;
	}

	@Override
	public boolean UpdateNumSub(Integer d_id) {
		// TODO Auto-generated method stub
		Connection conn = SqlConnection.getConnection();
		PreparedStatement ps = null;
		Integer num = SelectNum(d_id);
		String sql = "Update dateball set d_num = ? where d_id = ?";
		if(num.intValue()!=0){
			try {
				if(num-1 == 0){
					UpdateSeccess(d_id);
				}
				ps = conn.prepareStatement(sql);
				ps.setInt(1, num-1);
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
		}
		return false;
	}

	@Override
	public Integer SelectNum(Integer d_id) {
		// TODO Auto-generated method stub
		Connection conn = SqlConnection.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select d_num from dateball where d_id = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, d_id);
			rs = ps.executeQuery();
			if(rs.next()){
				return rs.getInt("d_num");
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
	public boolean UpdateSeccess(Integer d_id) {
		// TODO Auto-generated method stub
		Connection conn = SqlConnection.getConnection();
		PreparedStatement ps = null;
		String sql = "update dateball set d_success = ? where d_id = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, 1);
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
	public boolean UpdateNumAdd(Integer d_id) {
		// TODO Auto-generated method stub
		Connection conn = SqlConnection.getConnection();
		PreparedStatement ps = null;
		Integer num = SelectNum(d_id);
		String sql = "Update dateball set d_num = ? where d_id = ?";
		String sql1 = "Update dateball set d_num = ? , d_seccess = 0 where d_id = ?";
		if(num.intValue()!=0){
			try {
				ps = conn.prepareStatement(sql);
				ps.setInt(1, num+1);
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
		}else{
			try {
				ps = conn.prepareStatement(sql1);
				ps.setInt(1, num+1);
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
		}
		return false;
	}

	@Override
	public DateBall queryDateBallbyId(Integer d_id) {
		// TODO Auto-generated method stub
		Connection conn = SqlConnection.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from dateball where d_id = ?";
		UserDao ud = new UserImpl();
		BallGroundDao bgd = new BallGroundImpl();
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, d_id);
			rs = ps.executeQuery();
			if(rs.next()){
				if(rs.getInt("p_id")!=0){
					return new DateBall(d_id, ud.QueryUserbyuId(rs.getInt("u_id")), rs.getString("d_pic"), rs.getString("d_title"), rs.getString("d_text"), Integer.valueOf(rs.getString("d_type")), rs.getString("d_location"), rs.getInt("d_num"), rs.getTimestamp("d_time"), rs.getString("d_date"), rs.getString("d_starttime"), rs.getString("d_endtime"), bgd.QueryBallGroundbypId(rs.getInt("p_id")),rs.getString("d_city"),rs.getInt("d_success"));
				}else{
					return new DateBall(d_id, ud.QueryUserbyuId(rs.getInt("u_id")), rs.getString("d_pic"), rs.getString("d_title"), rs.getString("d_text"), Integer.valueOf(rs.getString("d_type")), rs.getString("d_location"), rs.getInt("d_num"), rs.getTimestamp("d_time"), rs.getString("d_date"), rs.getString("d_starttime"), rs.getString("d_endtime"), null,rs.getString("d_city"),rs.getInt("d_success"));
				}			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			SqlConnection.Close(conn, ps, rs);
		}
		return null;
	}

	@Override
	public boolean UpdateO_id(Integer d_id, Integer o_id) {
		// TODO Auto-generated method stub
		Connection conn = SqlConnection.getConnection();
		PreparedStatement ps = null;
		String sql = "update dateball set o_id = ? where d_id = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, o_id);
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
	public Integer selectO_id(Integer d_id) {
		// TODO Auto-generated method stub
		Connection conn = SqlConnection.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select o_id from dateball where d_id = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, d_id);
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
	public boolean deleteDateBall(Integer d_id) {
		// TODO Auto-generated method stub
		Connection conn = SqlConnection.getConnection();
		PreparedStatement ps = null;
		String sql = "delete from dateball where d_id = ?";
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

}
