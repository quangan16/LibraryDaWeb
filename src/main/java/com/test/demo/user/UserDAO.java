package com.test.demo.user;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
	private String DB_URL = "jdbc:mysql://localhost:3306/library";
	private String DB_USERNAME = "root";
	private String DB_PASSWORD = "1610";
	
	private static final String SELECT_ALL_USERS = "SELECT * FROM user";
	private static final String SELECT_USER_BY_ID = "SELECT * FROM user WHERE ID = ?";
	private static final String SELECT_USER_BY_NAME = "SELECT * FROM user WHERE full_name = ?";
	private static final String INSERT_USER_DATA = "INSERT INTO user (full_name, age, phone_number,username, password, role) VALUE(?,?,?,?,?, user)";
	private static final String SELECT_USER_BY_USERNAME = "SELECT * FROM user WHERE username = ?";
	
	public UserDAO() {
		
	}
	//Thiet lap ket noi den he thong quan tri csdl mySQL
	public Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
		}catch(SQLException e){
			e.printStackTrace();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	
	
	public List<User> getAllUser() {
		List<User> users = new ArrayList<>();
		try (	Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement(SELECT_ALL_USERS);
			){
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				int ID = rs.getInt("ID");
				String full_name = rs.getString("full_name");
				int age = rs.getInt("age");
				String phoneNum = rs.getString("phone_number");
				String username = rs.getString("username");
				String password = rs.getString("password");
				String role = rs.getString("role");
				users.add(new User(ID, full_name, age, phoneNum, username, password, role));
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return users;
		
	}
	
	public boolean checkUserExistByName(String name) {
		boolean userExisted = false;
		try (Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement(SELECT_USER_BY_NAME);
			){
				ps.setString(1, name);
				ResultSet rs = ps.executeQuery();
				while(rs.next()) {
					userExisted = true;
				}
				
			}catch(SQLException e) {
				e.printStackTrace();
			}
		return userExisted;
		
	}
	
	public User getUserByID(int ID) {
		User user = new User();
		try (Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement(SELECT_USER_BY_ID);
		){
			ps.setInt(1, ID);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				user.setID(rs.getInt("ID")); 
				user.setName("full_name");
				user.setAge(rs.getInt("age"));
				user.setPhoneNum(rs.getString("phone_number"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setRole(rs.getString("role"));
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		
		
		return user;
	}
	
	public User GetUserByName(String name) {
		User user = new User();
		try (Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement(SELECT_USER_BY_NAME);
			){
				ps.setString(1, name);
				ResultSet rs = ps.executeQuery();
				while(rs.next()) {
					user.setID(rs.getInt("ID")); 
					user.setName("full_name");
					user.setAge(rs.getInt("age"));
					user.setPhoneNum(rs.getString("phone_number"));
					user.setUsername(rs.getString("username"));
					user.setPassword(rs.getString("password"));
					user.setRole(rs.getString("role"));
				}
				
			}catch(SQLException e) {
				e.printStackTrace();
			}
		return user;
		
	}

	public User GetUserByUserName(String username) {
		User user = new User();
		try (Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement(SELECT_USER_BY_USERNAME);
			){
				ps.setString(1, username);
				ResultSet rs = ps.executeQuery();
				while(rs.next()) {
					user.setID(rs.getInt("ID")); 
					user.setName("full_name");
					user.setAge(rs.getInt("age"));
					user.setPhoneNum(rs.getString("phone_number"));
					user.setUsername(rs.getString("username"));
					user.setPassword(rs.getString("password"));
					user.setRole(rs.getString("role"));
				}
				
			}catch(SQLException e) {
				e.printStackTrace();
			}
		return user;
		
	}
	public void insertUser(User user) throws SQLException{
		//try-with-resource statement will auto close the connection.
		try(Connection connection = getConnection();
				PreparedStatement ps = connection.prepareStatement(INSERT_USER_DATA))
		{
			
			ps.setString(1, user.getName());
			ps.setInt(2, user.getAge());
			ps.setString(3, user.getPhoneNum());
			ps.setString(4, user.getUsername());
			ps.setString(5, user.getPassword());
			
			ps.executeUpdate();
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	
	
	public User checkLogin(String username, String password) {
		
		User user = new User();
		String sql = "SELECT * FROM user WHERE username = ? AND password = ?";
		try {
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				user.setName(rs.getString("full_name"));
				user.setAge(rs.getInt("age"));
				user.setID(rs.getInt("id"));
				user.setPhoneNum(rs.getString("phone_number"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setRole(rs.getString("role"));
				return user;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
