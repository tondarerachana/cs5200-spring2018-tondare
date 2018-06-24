package edu.northeastern.cs5200.Dao;

import java.util.Collection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import edu.northeastern.cs5200.model.HeadingWidget;
import edu.northeastern.cs5200.model.HtmlWidget;
import edu.northeastern.cs5200.model.ImageWidget;
import edu.northeastern.cs5200.model.Page;
import edu.northeastern.cs5200.model.Widget;
import edu.northeastern.cs5200.model.YoutubeWidget;


public class WidgetDao {

	String URL = "jdbc:mysql://localhost\\:3306/hw3_tondare_Rachana_Summer_2018";
	String USERNAME = "root";
	String PASSWORD = "admin";
	
	public int createWidgetForPage(int pageId, Widget widget) {
		Connection connection = null;
		PreparedStatement statement = null;
		int widgetId = 0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			String personSql = "INSERT INTO widget"
					+ " (name, width, height, cssClass, cssStyle, text, widget.order, pageid)"
					+ " VALUES (?,?,?,?,?,?,?,?);";
			statement = connection.prepareStatement(personSql, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, widget.getName());
			statement.setInt(2, widget.getWidth());
			statement.setInt(3, widget.getHeight());
			statement.setString(4, widget.getCssClass());
			statement.setString(5, widget.getCssStyle());
			statement.setString(6, widget.getText());
			statement.setInt(7, widget.getOrder());
			statement.setInt(8, pageId);
			statement.executeUpdate();
			ResultSet rs = statement.getGeneratedKeys();
			rs.next();
			widgetId = rs.getInt(1);
			widget.setId(widgetId);
			switch (widget.getType()) {
				case "youtube":
					YoutubeWidget yw = (YoutubeWidget) widget;
					String ysql = "UPDATE widget SET type=?,url=?,shareable=?,expandable=? where id=?";
					statement = connection.prepareStatement(ysql);
					statement.setString(1, yw.getType());
					statement.setString(2, yw.getUrl());
					statement.setBoolean(3, yw.getShareable());
					statement.setBoolean(4, yw.getExpandable());
					statement.setInt(5, widgetId);
					statement.executeUpdate();
					break;
				case "heading":
					HeadingWidget hw = (HeadingWidget) widget;
					String hsql = "UPDATE widget SET type=?,size=? where id=?";
					PreparedStatement hstatement = connection.prepareStatement(hsql);
					hstatement.setString(1, hw.getType());
					hstatement.setInt(2, hw.getSize());
					hstatement.setInt(3, widgetId);
					hstatement.executeUpdate();
					break;
				case "image":
					ImageWidget iw = (ImageWidget) widget;
					String isql = "UPDATE widget SET type=?,src=? where id=?";
					PreparedStatement istatement = connection.prepareStatement(isql);
					istatement.setString(1, iw.getType());
					istatement.setString(2, iw.getSrc());
					istatement.setInt(3, widgetId);
					istatement.executeUpdate();
					break;
				default:
					HtmlWidget htw = (HtmlWidget) widget;
					String htsql = "UPDATE widget SET type=?,html=? where id=?";
					PreparedStatement htstatement = connection.prepareStatement(htsql);
					htstatement.setString(1, htw.getType());
					htstatement.setString(2, htw.getHtml());
					htstatement.setInt(3, widgetId);
					htstatement.executeUpdate();
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
		return widgetId;
	}

	public Collection<Widget> findAllWidgets() {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		Collection<Widget> widgets = new ArrayList<Widget>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			String sql = "select * from widget";
			statement = connection.prepareStatement(sql);
			rs = statement.executeQuery();
			while (rs.next()) {
				int pid = rs.getInt("pageid");
				PageDao pdao = new PageDao();
				Page p = pdao.findPageById(pid);
				Widget w = new Widget(p, rs.getString("name"), rs.getInt("width"), rs.getInt("height"),
						rs.getString("cssClass"), rs.getString("cssStyle"), rs.getString("text"), rs.getInt("order"));
				w.setId(rs.getInt("id"));
				widgets.add(w);
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
		return widgets;

	}

	public Widget findWidgetById(int widgetId) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		Widget widget = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			String sql = "select * from widget where id = ?";
			statement = connection.prepareStatement(sql);
			statement.setInt(1, widgetId);
			rs = statement.executeQuery();
			if (rs.next()) {
				int pid = rs.getInt("pageid");
				PageDao pdao = new PageDao();
				Page p = pdao.findPageById(pid);
				widget = new Widget(p, rs.getString("name"), rs.getInt("width"), rs.getInt("height"),
						rs.getString("cssClass"), rs.getString("cssStyle"), rs.getString("text"), rs.getInt("order"));
				widget.setId(rs.getInt("id"));
				Page p1 = widget.getPage();
				p1.setId(pid);
				widget.setPage(p1);
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
		return widget;
	}

	public Collection<Widget> findWidgetsForPage(int pageId) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		Collection<Widget> widgets = new ArrayList<Widget>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			String sql = "select * from widget where pageid = ?";
			statement = connection.prepareStatement(sql);
			statement.setInt(1, pageId);
			rs = statement.executeQuery();
			while (rs.next()) {
				int pid = rs.getInt("pageid");
				PageDao pdao = new PageDao();
				Page p = pdao.findPageById(pid);
				Widget w = new Widget(p, rs.getString("name"), rs.getInt("width"), rs.getInt("height"),
						rs.getString("cssClass"), rs.getString("cssStyle"), rs.getString("text"), rs.getInt("order"));
				w.setId(rs.getInt("id"));
				Page p1 = w.getPage();
				p1.setId(pid);
				w.setPage(p1);
				widgets.add(w);
				
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
		return widgets;
	}

	public int updateWidget(int widgetId, Widget widget) {
		Connection connection = null;
		PreparedStatement pstatement = null;
		int rv = 0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			String psql = "UPDATE widget SET name=?,width=?,height=?,cssClass=?,cssStyle=?,text=?,widget.order=? where id = ?";
			pstatement = connection.prepareStatement(psql);
			pstatement.setString(1, widget.getName());
			pstatement.setInt(2, widget.getWidth());
			pstatement.setInt(3, widget.getHeight());
			pstatement.setString(4, widget.getCssClass());
			pstatement.setString(5, widget.getCssStyle());
			pstatement.setString(6, widget.getText());
			pstatement.setInt(7, widget.getOrder());
			pstatement.setInt(8, widgetId);
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

	public int deleteWidget(int widgetId) {
		Connection connection = null;
		PreparedStatement pstatement = null;
		int rv = 0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			String psql = "DELETE from widget where id = ?";
			pstatement = connection.prepareStatement(psql);
			pstatement.setInt(1, widgetId);
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

