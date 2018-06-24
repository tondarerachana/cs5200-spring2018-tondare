package edu.northeastern.cs5200.Dao;

import java.util.Collection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import edu.northeastern.cs5200.model.Page;
import edu.northeastern.cs5200.model.Website;

public class PageDao {
	
	String URL = "jdbc:mysql://localhost\\:3306/hw3_tondare_Rachana_Summer_2018";
	String USERNAME = "root";
	String PASSWORD = "admin";
	
	public int createPageForWebsite(int websiteId, Page page) {
		Connection connection = null;
		PreparedStatement statement = null;
		int result = 0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			String personSql = "INSERT INTO page (title, description, created, updated, views, websiteid) VALUES (?,?,?,?,?,?)";
			statement = connection.prepareStatement(personSql, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, page.getTitle());
			statement.setString(2, page.getDescription());
			statement.setDate(3, new Date(page.getCreated().getTime()));
			statement.setDate(4, new Date(page.getCreated().getTime()));
			statement.setInt(5,  page.getViews());
			statement.setInt(6, websiteId);
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

	public Collection<Page> findAllPages() {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		Collection<Page> pages = new ArrayList<Page>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			String sql = "select * from page";
			statement = connection.prepareStatement(sql);
			rs = statement.executeQuery();
			while (rs.next()) {
				int wid = rs.getInt("websiteid");
				WebsiteDao wdao = new WebsiteDao();
				Website w = wdao.findWebsiteById(wid);
				Page p = new Page(w, rs.getString("title"), rs.getString("description"),
						rs.getDate("created"), rs.getDate("updated"), rs.getInt("views"));
				p.setId(rs.getInt("id"));
				pages.add(p);
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
		return pages;
	}

	public Page findPageById(int pageId) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		Page page = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			String sql = "select * from page where id = ?";
			statement = connection.prepareStatement(sql);
			statement.setInt(1, pageId);
			rs = statement.executeQuery();
			if (rs.next()) {
				int wid = rs.getInt("websiteid");
				WebsiteDao wdao = new WebsiteDao();
				Website w = wdao.findWebsiteById(wid);
				page = new Page(w, rs.getString("title"), rs.getString("description"),
						rs.getDate("created"), rs.getDate("updated"), rs.getInt("views"));
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
		return page;
	}
	
	public Collection<Page> findPagesForWebsite(int websiteId) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		Collection<Page> pages = new ArrayList<Page>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			String sql = "select * from page where websiteid = ?";
			statement = connection.prepareStatement(sql);
			statement.setInt(1, websiteId);
			rs = statement.executeQuery();
			while (rs.next()) {
				int wid = rs.getInt("websiteid");
				WebsiteDao wdao = new WebsiteDao();
				Website w = wdao.findWebsiteById(wid);
				Page p = new Page(w, rs.getString("title"), rs.getString("description"),
						rs.getDate("created"), rs.getDate("updated"), rs.getInt("views"));
				pages.add(p);
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
		return pages;
	}
	
	public int updatePage(int pageId, Page page) {
		Connection connection = null;
		PreparedStatement pstatement = null;
		int rv = 0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			String psql = "UPDATE page SET title=?,description=?,created=?,updated=?,views=? where id = ?";
			pstatement = connection.prepareStatement(psql);
			pstatement.setString(1, page.getTitle());
			pstatement.setString(2, page.getDescription());
			pstatement.setDate(3, (Date) page.getCreated());
			pstatement.setDate(4, (Date) page.getUpdated());
			pstatement.setInt(5, page.getViews());
			pstatement.setInt(6, pageId);
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
	
	public int deletePage(int pageId) {
		Connection connection = null;
		PreparedStatement pstatement = null;
		int rv = 0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			String psql = "DELETE from page where id = ?";
			pstatement = connection.prepareStatement(psql);
			pstatement.setInt(1, pageId);
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
}
