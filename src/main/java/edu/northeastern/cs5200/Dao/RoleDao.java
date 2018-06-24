package edu.northeastern.cs5200.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import edu.northeastern.cs5200.model.Role;


public class RoleDao {

	String URL = "jdbc:mysql://localhost\\:3306/hw3_tondare_Rachana_Summer_2018";
	String USERNAME = "root";
	String PASSWORD = "admin";
	
	public void assignWebsiteRole(int developerId, int websiteId, int roleId) {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			String personSql = "INSERT INTO websiteRole (role, devid, websiteid) VALUES (?,?,?)";
			statement = connection.prepareStatement(personSql);
			String role = Role.values()[roleId].name();
			statement.setString(1, role);
			statement.setInt(2, developerId);
			statement.setInt(3, websiteId);
			statement.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public void assignPageRole(int developerId, int pageId, int roleId) {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			String personSql = "INSERT INTO pageRole (role, devid, pageid) VALUES (?,?,?)";
			statement = connection.prepareStatement(personSql);
			String role = Role.values()[roleId].name();
			statement.setString(1, role);
			statement.setInt(2, developerId);
			statement.setInt(3, pageId);
			statement.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void deleteWebsiteRole(int developerId, int websiteId, int roleId) {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			String personSql = "DELETE from websiteRole where role=? and devid=? and websiteid=?";
			statement = connection.prepareStatement(personSql);
			String role = Role.values()[roleId].name();
			statement.setString(1, role);
			statement.setInt(2, developerId);
			statement.setInt(3, websiteId);
			statement.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void deletePageRole(int developerId, int pageId, int roleId) {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			String personSql = "DELETE from pageRole where role=? and devid=? and pageid=?";
			statement = connection.prepareStatement(personSql);
			String role = Role.values()[roleId].name();
			statement.setString(1, role);
			statement.setInt(2, developerId);
			statement.setInt(3, pageId);
			statement.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public Role getPageRole(int developerId, int pageId) {
		Connection connection = null;
		PreparedStatement statement = null;
		Role role = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			String personSql = "SELECT * from pageRole where devid=? and pageid=?";
			statement = connection.prepareStatement(personSql);
			statement.setInt(1, developerId);
			statement.setInt(2, pageId);
			System.out.println(statement);
			ResultSet rs = statement.executeQuery();
			rs.next();
			role = Role.valueOf(rs.getString("role").toUpperCase());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return role;
	}
}
