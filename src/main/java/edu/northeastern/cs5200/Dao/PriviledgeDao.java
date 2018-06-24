package edu.northeastern.cs5200.Dao;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;

import java.sql.SQLException;


import edu.northeastern.cs5200.model.Privelege;


public class PriviledgeDao {

	String URL = "jdbc:mysql://localhost\\:3306/hw3_tondare_Rachana_Summer_2018";
	String USERNAME = "root";
	String PASSWORD = "admin";
	
	public void assignWebsitePrivelege(int developerId, int websiteId, int privelegeId) {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			String personSql = "INSERT INTO websitePriveledge (priveledge, devid, websiteid) VALUES (?,?,?)";
			statement = connection.prepareStatement(personSql);
			String privelege = Privelege.values()[privelegeId].name();
			statement.setString(1, privelege);
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
	
	public void assignPagePrivelege(int developerId, int pageId, int privelegeId) {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			String personSql = "INSERT INTO pagePriveledge (priveledge, devid, pageid) VALUES (?,?,?)";
			statement = connection.prepareStatement(personSql);
			String privelege = Privelege.values()[privelegeId].name();
			statement.setString(1, privelege);
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
	
	public void deleteWebsitePrivelege(int developerId, int websiteId, int privelegeId) {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			String personSql = "DELETE from websitePriveledge where devid=? and websiteid=? and priveledge=?";
			statement = connection.prepareStatement(personSql);
			String privelege = Privelege.values()[privelegeId].name();
			statement.setInt(1, developerId);
			statement.setInt(2, websiteId);
			statement.setString(3, privelege);
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

	public void deletePagePrivelege(int developerId, int pageId, int privelegeId) {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			String personSql = "DELETE from pagePriveledge where devid=? and pageid=? and priveledge=?";
			statement = connection.prepareStatement(personSql);
			String privelege = Privelege.values()[privelegeId].name();
			statement.setInt(1, developerId);
			statement.setInt(2, pageId);
			statement.setString(3, privelege);
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
}

