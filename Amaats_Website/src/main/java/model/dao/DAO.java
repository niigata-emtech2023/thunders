package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.entity.SweetsBean;
import model.entity.UserBean;

public class DAO {
	
	/**
	 * ログイン認証を行う
	 * @param user_id ユーザID
	 * @param password ユーザパスワード
	 * @return ログイン認証の成否
	 */
	public boolean login(String user_id, String password) {
		
		String sql = "SELECT password FROM m_user WHERE user_id = ?";
		boolean accept = false;
		
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			
			pstmt.setString(1, user_id);
			
			ResultSet res = pstmt.executeQuery();
			
			res.next();
			accept = password.equals(res.getString("password"));
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
			
		}
		
		return accept;
		
	}
	
	/**
	 * 全商品情報を取得する
	 * @return 商品情報のリスト
	 */
	public List<SweetsBean> getAllSweetsInfo() {
		
		String sql = "SELECT * FROM m_sweets";
		List<SweetsBean> sweetsInfoList = null;
		
		try (Connection con = ConnectionManager.getConnection();
				Statement stmt = con.createStatement();
				ResultSet res = stmt.executeQuery(sql)) {
			
			sweetsInfoList = new ArrayList<SweetsBean>();
			while (res.next()) {
				
				SweetsBean sb = new SweetsBean();
				sb.setSweets_id(res.getString("sweets_id"));
				sb.setName(res.getString("name"));
				sb.setPrice(res.getInt("price"));
				sweetsInfoList.add(sb);
				
			}
			
		} catch (SQLException | ClassNotFoundException e) {
			
			e.printStackTrace();
			
		}
		
		return sweetsInfoList;
		
	}
	
	public List<UserBean> getAllUserInfo() {
		String sql = "SELECT * FROM m_user";
		List<UserBean> userInfoList = null;
	
		try (Connection con = ConnectionManager.getConnection();
				Statement stmt = con.createStatement();
				ResultSet res = stmt.executeQuery(sql)) {
			
			userInfoList = new ArrayList<UserBean>();
			while (res.next()) {
				
				UserBean ub = new UserBean();
				ub.setUser_id(res.getString("user_id"));
				userInfoList.add(ub);
				
			}
			
		} catch (SQLException | ClassNotFoundException e) {
			
			e.printStackTrace();
			
		}
		
		return userInfoList;
		
	}
	
	public List<SweetsBean> search(String keyword) throws SQLException, ClassNotFoundException {
		
		String sql = "SELECT * FROM m_sweets WHERE name LIKE ?";
		String sword = "%" + keyword + "%";
		List<SweetsBean> sbList = new ArrayList<SweetsBean>();
		
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			
			pstmt.setString(1, sword);
			
			ResultSet res = pstmt.executeQuery();
			
			while (res.next()) {
				
				SweetsBean sb = new SweetsBean();
				
				sb.setSweets_id(res.getString("sweets_id"));
				sb.setName(res.getString("name"));
				sb.setPrice(res.getInt("price"));
				sbList.add(sb);
				
			}
			
		}
		
		return sbList;
	}

}
