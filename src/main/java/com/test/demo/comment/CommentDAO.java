package com.test.demo.comment;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class CommentDAO {
	private String jdbcURL = "jdbc:mysql://localhost:3306/library";
	private String jdbcUsername = "root";
	private String jdbcPassword = "1610";

    
    
    private static final String SELECT_COMMENT_BY_BOOKID =  "select * from comment where bookcode=?";
    private static final String ADD_COMMENT = "INSERT INTO comment(bookcode,content,time,starPoint,username) VALUES(?,?,?,?,?)";
    
    
	public CommentDAO() {
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
	
    public List<Comment> getCommentsByBookID(int bookCode) {
		String sql = SELECT_COMMENT_BY_BOOKID;
		List<Comment> comments = new ArrayList<Comment>();

		try {
			Connection connection = this.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, bookCode);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int ID = resultSet.getInt("ID");
				String username = resultSet.getString("username");
				String content = resultSet.getString("content");
				float starPoint = resultSet.getFloat("starPoint");
				Date time = resultSet.getDate("time");
				comments.add(new Comment(ID,  content, username, bookCode, time, starPoint));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return comments;
	}
	
	public boolean addComment(Comment comment) {
		String sql =  ADD_COMMENT;
		java.sql.Date date = new java.sql.Date(new java.util.Date().getTime());
		
		try {
			Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, comment.getBookCode());
			preparedStatement.setString(2, comment.getContent());
			preparedStatement.setDate(3, date);
			preparedStatement.setFloat(4, comment.getStarPoint());
			preparedStatement.setString(5, comment.getUsername());
			preparedStatement.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	
}
