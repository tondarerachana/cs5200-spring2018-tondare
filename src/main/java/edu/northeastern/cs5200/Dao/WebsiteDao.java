package edu.northeastern.cs5200.Dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import edu.northeastern.cs5200.model.Website;



public class WebsiteDao {
	String URL = "jdbc:mysql://localhost\\:3306/hw3_tondare_Rachana_Summer_2018";
	String USERNAME = "root";
	String PASSWORD = "admin";

	public int createWebsiteForDeveloper(int developerId, Website website) {
		Connection connection = null;
		PreparedStatement statement = null;
		int result = 0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			String personSql = "INSERT INTO website (name, description, created, updated, visits, developerid) VALUES (?,?,?,?,?,?)";
			statement = connection.prepareStatement(personSql, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, website.getName());
			statement.setString(2, website.getDescription());
			statement.setDate(3, new Date(website.getCreated().getTime()));
			statement.setDate(4, new Date(website.getUpdated().getTime()));
			statement.setInt(5, website.getVisits());
			statement.setInt(6, developerId);
			statement.executeUpdate();
			ResultSet rs = statement.getGeneratedKeys();
			rs.next();
			result = rs.getInt(1);
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
		return result;
	}

	
	public Collection<Website> findAllWebsites() {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		Collection<Website> websites = new ArrayList<Website>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			String sql = "select * from website;";
			statement = connection.prepareStatement(sql);
			rs = statement.executeQuery();
			while (rs.next()) {
				Website w = new Website(rs.getString("name"), rs.getString("description"),
						                rs.getDate("created"), rs.getDate("updated"),
						                rs.getInt("visits"));
				w.setId(rs.getInt("id"));
				websites.add(w);
			}
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
		return websites;
	}

	public Collection<Website> findWebsitesForDeveloper(int developerId) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		Collection<Website> websites = new ArrayList<Website>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			String sql = "select * from website where developerid = ?;";
			statement = connection.prepareStatement(sql);
			statement.setInt(1, developerId);
			rs = statement.executeQuery();
			while (rs.next()) {
				Website w = new Website(rs.getString("name"), rs.getString("description"),
						                rs.getDate("created"), rs.getDate("updated"),
						                rs.getInt("visits"));
				websites.add(w);
			}
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
		return websites;
	}
	
	public Website findWebsiteById(int websiteId) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		Website website = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			String sql = "select * from website where id = ?;";
			statement = connection.prepareStatement(sql);
			statement.setInt(1, websiteId);
			rs = statement.executeQuery();
			if (rs.next()) {
				website = new Website(rs.getString("name"), rs.getString("description"),
						                rs.getDate("created"), rs.getDate("updated"),
						                rs.getInt("visits"));
			}
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
		return website;
	}
	
	public int updateWebsite(int websiteId, Website website) {
		Connection connection = null;
		PreparedStatement pstatement = null;
		int rv = 0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			String psql = "UPDATE website SET name=?,description=?,created=?,updated=?,visits=? where id = ?";
			pstatement = connection.prepareStatement(psql);
			pstatement.setString(1, website.getName());
			pstatement.setString(2, website.getDescription());
			pstatement.setDate(3, new Date(website.getCreated().getTime()));
			pstatement.setDate(4, new Date(website.getUpdated().getTime()));
			pstatement.setInt(5, website.getVisits());
			pstatement.setInt(6, websiteId);
			pstatement.executeUpdate();
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
		return rv;
	}

	public int deleteWebsite(int websiteId) {
		Connection connection = null;
		PreparedStatement statement = null;
		int rv = 0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			String dsql = "DELETE from website where id = ?";
			statement = connection.prepareStatement(dsql);
			statement.setInt(1, websiteId);
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
		return rv;
	}
}
