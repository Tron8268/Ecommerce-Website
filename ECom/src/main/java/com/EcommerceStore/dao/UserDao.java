package com.EcommerceStore.dao;

import java.sql.*;

import com.EcommerceStore.model.User;

public class UserDao {
	private Connection connect;
	private String query;
	private PreparedStatement pst;
	private ResultSet rs;
	
	public UserDao(Connection connect) {
		this.connect = connect;
	}
	
	public User userLogin(String email, String password) {
		User user = null;
		try {
			query = "select * from user where email=? and password=?";
			pst = this.connect.prepareStatement(query);
			pst.setString(1, email);
			pst.setString(2, password);
			rs = pst.executeQuery();
			
			if(rs.next()) {
				user = new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
			}
		} catch (Exception e) {
			
		}
		return user;
	}
	
	public boolean userRegister(String name, String email, String password) {
		boolean result = false;
		try {
			query = "insert into user(name, email, password) values(?, ?, ?)";
			pst = this.connect.prepareStatement(query);
			pst.setString(1, name);
			pst.setString(2, email);
			pst.setString(3, password);
			
			pst.executeUpdate();
			result = true;
			
		} catch (Exception e) {
			
		}
		return result;
	}
}
