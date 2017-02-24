package com.ball.impl.comment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;

import com.ball.bean.BallGround;
import com.ball.bean.Comment;
import com.ball.connect.SqlConnection;
import com.ball.dao.comment.CommentDao;
import com.ball.dao.seller.BallGroundDao;
import com.ball.dao.user.UserDao;
import com.ball.impl.seller.BallGroundImpl;
import com.ball.impl.user.UserImpl;

public class CommentImpl implements CommentDao{

	@Override
	public boolean WriteComment(Integer u_id, Integer p_id, String connent) {
		// TODO Auto-generated method stub
		Connection conn  = SqlConnection.getConnection();
		PreparedStatement ps = null;
		String sql = "insert into comment(u_id,p_id,c_content) values(?,?,?)";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, u_id);
			ps.setInt(2, p_id);
			ps.setString(3, connent);
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
	public List<Comment> getComments(Integer p_id,Integer page,Integer item) {
		// TODO Auto-generated method stub
		List<Comment> comments = new  ArrayList<Comment>();
		Connection conn = SqlConnection.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from comment where p_id = ? order by c_id desc limit ?,?";
		BallGroundDao bgd = new BallGroundImpl();
		UserDao ud = new UserImpl();
		BallGround ballGround = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, p_id);
			ps.setInt(2, (page - 1)*item);
			ps.setInt(3, item);
			rs = ps.executeQuery();
			ballGround = bgd.QueryBallGroundbypId(p_id);
			while(rs.next()){
				comments.add(new Comment(rs.getInt("c_id"), ud.QueryUserbyuId(rs.getInt("u_id")),ballGround , rs.getTimestamp("c_currenttime"), rs.getString("c_content")));
			}
			return comments;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			SqlConnection.Close(conn, ps, rs);
		}
		return null;
	}

}
