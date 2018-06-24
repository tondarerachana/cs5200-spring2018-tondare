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


import edu.northeastern.cs5200.model.Developer;
import edu.northeastern.cs5200.model.Person;



public class DeveloperDao {

	String URL = "jdbc:mysql://localhost\\:3306/hw3_tondare_Rachana_Summer_2018?useSSL=false";
	String USERNAME = "root";
	String PASSWORD = "admin";

	public int createDeveloper(Developer developer) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		int result = 0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			String personSql = "INSERT INTO person (firstName, lastName, username, password, email, dob) VALUES (?,?,?,?,?,?)";
			statement = connection.prepareStatement(personSql, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, developer.getFirstName());
			statement.setString(2, developer.getLastName());
			statement.setString(3, developer.getUsername());
			statement.setString(4, developer.getPassword());
			statement.setString(5, developer.getEmail());
			statement.setDate(6, new Date(developer.getDob().getTime()));
			statement.executeUpdate();
			rs = statement.getGeneratedKeys();
			rs.next();
			int personid = rs.getInt(1);
			String developerSql = "INSERT INTO developer (personid, developerKey) VALUES (?,?)";
			statement = connection.prepareStatement(developerSql, Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, personid);
			statement.setString(2, developer.getDeveloperKey());
			statement.executeUpdate();
			rs = statement.getGeneratedKeys();
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

	public Collection<Developer> findAllDevelopers() {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		Collection<Developer> devs = new ArrayList<Developer>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			// Person p = new Person("Siru", "Muru", "sir", "mur", "sir@sir.com", new
			// Date(0));
			// Developer d = new Developer(1, "123");
			String sql = "select * from developer, person where person.id = personid;";
			statement = connection.prepareStatement(sql);
			rs = statement.executeQuery();
			while (rs.next()) {
				Person p = new Person(rs.getString("firstName"), rs.getString("lastName"), rs.getString("username"),
						rs.getString("password"), rs.getString("email"), rs.getDate("dob"));
				Developer d = new Developer(p, rs.getString("developerKey"));
				d.setId(rs.getInt("id"));
				devs.add(d);
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
		return devs;
	}

	public Developer findDeveloperByID(int developerId) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		Developer d = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			String sql = "select * from developer, person where developer.id=? and person.id=personid;";
			statement = connection.prepareStatement(sql);
			statement.setInt(1, developerId);
			rs = statement.executeQuery();
			if (rs.next()) {
				Person p = new Person(rs.getString("firstName"), rs.getString("lastName"), rs.getString("username"),
						rs.getString("password"), rs.getString("email"), rs.getDate("dob"));
				d = new Developer(p, rs.getString("developerKey"));
				d.setId(rs.getInt("id"));
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
		return d;
	}

	public Developer findDeveloperByUsername(String username) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		Developer d = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			String sql = "select * from developer, person where person.id=personid and person.username=?";
			statement = connection.prepareStatement(sql);
			statement.setString(1, username);
			rs = statement.executeQuery();
			if (rs.next()) {
				Person p = new Person(rs.getString("firstName"), rs.getString("lastName"), rs.getString("username"),
						rs.getString("password"), rs.getString("email"), rs.getDate("dob"));
				d = new Developer(p, rs.getString("developerKey"));
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
		return d;
	}

	public Developer findDeveloperByCredentials(String username, String password) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		Developer d = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			String sql = "select * from developer, person where person.username = ? and person.password = ? and person.id = personid;";
			statement = connection.prepareStatement(sql);
			statement.setString(1, username);
			statement.setString(2, password);
			rs = statement.executeQuery();
			if (rs.next()) {
				Person p = new Person(rs.getString("firstName"), rs.getString("lastName"), rs.getString("username"),
						rs.getString("password"), rs.getString("email"), rs.getDate("dob"));
				d = new Developer(p, rs.getString("developerKey"));
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
		return d;
	}

	public int updateDeveloper(int developerId, Developer developer) {
		Connection connection = null;
		PreparedStatement statement = null;
		PreparedStatement pstatement = null;
		int rv = 0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			String sql = "UPDATE developer SET developerKey = ? where developerId = ?";
			statement = connection.prepareStatement(sql);
			statement.setString(1, "234");
			statement.setInt(2, developerId);
			statement.executeUpdate();
			try (ResultSet rs = statement.getResultSet()) {
				if (rs.next()) {
					int pid = rs.getInt("personId");
					String psql = "UPDATE person SET firstName=?,lastName=?,username=?,password=?,email=?,dob=? where personid = ?";
					pstatement = connection.prepareStatement(psql);
					pstatement.setString(1, developer.getFirstName());
					pstatement.setString(2, developer.getLastName());
					pstatement.setString(3, developer.getUsername());
					pstatement.setString(4, developer.getPassword());
					pstatement.setString(5, developer.getEmail());
					pstatement.setDate(6, (Date) developer.getDob());
					pstatement.setInt(7, pid);
					pstatement.executeUpdate();
				}
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
		return rv;
	}

	

	public int deleteDeveloper(int developerId) {
		Connection connection = null;
		PreparedStatement sstatement = null;
		PreparedStatement statement = null;
		PreparedStatement pstatement = null;
		ResultSet rs = null;
		int rv = 0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			String sql = "select * from developer where id = ?";
			sstatement = connection.prepareStatement(sql);
			sstatement.setInt(1, developerId);
			rs = sstatement.executeQuery(sql);
			int pid = 0;
			if (rs.next()) {
				pid = rs.getInt("personid");
			}
			String dsql = "DELETE from developer where id = ?";
			statement = connection.prepareStatement(dsql);
			statement.setInt(1, developerId);
			statement.executeUpdate();
			String d2sql = "DELETE from person where id = ?";
			pstatement = connection.prepareStatement(d2sql);
			pstatement.setInt(1, pid);
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