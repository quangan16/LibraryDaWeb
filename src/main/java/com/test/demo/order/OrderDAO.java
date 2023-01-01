package com.test.demo.order;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {
	private String jdbcURL = "jdbc:mysql://localhost:3306/library";
	private String jdbcUsername = "root";
	private String jdbcPassword = "1610";

    
    
    private static final String SELECT_ORDER_BY_ID =  "select * from orders where id=?";
    private static final String ADD_ORDER = "INSERT INTO orders(bookcode,content,time,starPoint,username) VALUES(?,?,?,?,?)";
    
    
	public OrderDAO() {
    }

    protected Connection getConnection() {
        Connection connection = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return connection;
    }
    
    public boolean insertOrderBook(Order order) {
		String sql = "INSERT INTO orders (booktitle,quantity,ordertime,username) VALUES(?,?,?,?)";
		java.sql.Date date = new java.sql.Date(new java.util.Date().getTime());
		try {
			Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, order.getBookTitle());
			preparedStatement.setInt(2, order.getQuantity());
			preparedStatement.setDate(3, date);
			preparedStatement.setString(4, order.getUsername());
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	// get list order by user name
	public List<Order> getOrderByUsername(String username) {

		String sql = "select * from orders where username=?";
		List<Order> orders = new ArrayList<Order>();
		try {
			Connection connection = this.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, username);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int ID = resultSet.getInt("id");
				String bookTitle = resultSet.getString("booktitle");
				int quantity = resultSet.getInt("quantity");
				Date orderTime = resultSet.getDate("orderTime");
				String username1 = resultSet.getString("username");

				orders.add(new Order(ID, bookTitle, quantity, orderTime, username1));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orders;
	}

	// delete a order

	public boolean deleteOrder(int id) {
		String sql = "delete from orders where id= ?";
		boolean result = false;

		try {
			Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, Integer.valueOf(id));
			preparedStatement.executeUpdate();
			preparedStatement.close();
			connection.close();
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	// get a order
	public Order getOrderByID(int id) {
		Order order= new Order();
		final String sql = "SELECT * FROM orders WHERE id=?";
		try {
			Connection connection = this.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				String bookTitle = resultSet.getString("bookTitle");
				int quantity = resultSet.getInt("quantity");
				Date ordertime = resultSet.getDate("ordertime");
				String username = resultSet.getString("username");
				order.setBookTitle(bookTitle);
				order.setQuantity(quantity);
				order.setOrderTime(ordertime);
				order.setUsername(username);
			}
			order.setID(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return order;
	}
    
    
}
