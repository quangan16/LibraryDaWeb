
package com.test.demo.book;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {
	private String jdbcURL = "jdbc:mysql://localhost:3306/library";
	private String jdbcUsername = "root";
	private String jdbcPassWord = "aannn1610";
	// define sql statements
	private static final String SELECT_ALL_BOOKS = "select * from book";
	private static final String SELECT_BOOK_BY_ID = "select * from book where bookcode = ?";
	private static final String SELECT_BOOK_BY_TITLE = "select * from book where title = ?";
	private static final String INSERT_BOOKS_SQL = "INSERT INTO book (title, author,description,releaseDate,pageNumbers,category, image) VALUES( ?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE_BOOKS_SQL = "UPDATE book SET title=?, author=?, description=?, releaseDate=?, pageNumbers=?, category=?, image=? WHERE bookcode=?";
	private static final String UPDATE_BOOKS_NO_IMG_SQL = "UPDATE book SET title=?, author=?, description=?, releaseDate=?, pageNumbers=?, category=? WHERE bookcode=?";
	private static final String DELETE_BOOKS_SQL = "DELETE FROM book WHERE bookcode = ?";

	public BookDAO() {
	}

	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassWord);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return connection;
	}

	public List<Book> selectAllBooks(){
		//using try-with-resources to avoid closing resource (boiler plate code)
		List<Book> books = new ArrayList<>();
		//Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				//Step 2: Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_BOOKS);)
		{
	
				//Step 3: Execute the query of update query
				
				ResultSet rs = preparedStatement.executeQuery();
				//Step 4: Process the ResultSet object
				while(rs.next()) {
					int bookcode = rs.getInt("bookcode");
					String title = rs.getString("title");
					String author = rs.getString("author");
					String description = rs.getString("description");
					String releaseDate = rs.getString("releaseDate");
					int pageNumbers = rs.getInt("pageNumbers");
					String category = rs.getString("category");
					String image = rs.getString("image");
					int price = rs.getInt("price");
					books.add(new Book(bookcode, title, author,description,releaseDate,pageNumbers,category,image, price));
				}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return books;
	}
	
	public Book selectBook(int id) {
		Book book = new Book();
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BOOK_BY_ID);)
		{
			preparedStatement.setInt(1, id);
			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				book.setBookcode(rs.getInt("bookcode"));
				book.setTitle(rs.getString("title"));
				book.setAuthor(rs.getString("author"));
				book.setDescription(rs.getString("description"));
				book.setReleaseDate(rs.getString("releaseDate"));
				book.setPageNumbers(rs.getInt("pageNumbers"));
				book.setCategory(rs.getString("category"));
				book.setPhoto(rs.getString("image"));
				book.setPrice(rs.getInt("price"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return book;
	}
	
	public void insertBook(Book book) throws SQLException{
		//try-with-resource statement will auto close the connection.
		try(Connection connection = getConnection();
				PreparedStatement ps = connection.prepareStatement(INSERT_BOOKS_SQL))
		{
			
			ps.setString(1, book.getTitle());
			ps.setString(2, book.getAuthor());
			ps.setString(3, book.getDescription());
			ps.setString(4, book.getReleaseDate());
			ps.setInt(5, book.getPageNumbers());
			ps.setString(6, book.getCategory());
			ps.setString(7, book.getPhoto());
			
			ps.executeUpdate();
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	public void updateBook(Book book) throws SQLException{
		//try-with-resource statement will auto close the connection.
		try(Connection connection = getConnection();
				PreparedStatement ps = connection.prepareStatement(UPDATE_BOOKS_SQL))
		{
			
			ps.setString(1, book.getTitle());
			ps.setString(2, book.getAuthor());
			ps.setString(3, book.getDescription());
			ps.setString(4, book.getReleaseDate());
			ps.setInt(5, book.getPageNumbers());
			ps.setString(6, book.getCategory());
			ps.setString(7, book.getPhoto());
			ps.setInt(8, book.getBookcode());
			ps.executeUpdate();
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	public void updateBookWithoutImage(Book book) throws SQLException{
		//try-with-resource statement will auto close the connection.
		try(Connection connection = getConnection();
				PreparedStatement ps = connection.prepareStatement(UPDATE_BOOKS_NO_IMG_SQL))
		{
			
			ps.setString(1, book.getTitle());
			ps.setString(2, book.getAuthor());
			ps.setString(3, book.getDescription());
			ps.setString(4, book.getReleaseDate());
			ps.setInt(5, book.getPageNumbers());
			ps.setString(6, book.getCategory());
			ps.setInt(7, book.getBookcode());
			ps.executeUpdate();
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	public void deleteBook(int id) throws SQLException{
		//try-with-resource statement will auto close the connection.
		try(Connection connection = getConnection();
				PreparedStatement ps = connection.prepareStatement(DELETE_BOOKS_SQL))
		{
			ps.setInt(1, id);
			ps.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	public Book getBookByTitle(String title) {
		Book book = new Book();
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BOOK_BY_TITLE);)
		{
			preparedStatement.setString(1, title);
			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				book.setBookcode(rs.getInt("bookcode"));
				book.setTitle(rs.getString("title"));
				book.setAuthor(rs.getString("author"));
				book.setDescription(rs.getString("description"));
				book.setReleaseDate(rs.getString("releaseDate"));
				book.setPageNumbers(rs.getInt("pageNumbers"));
				book.setCategory(rs.getString("category"));
				book.setPhoto(rs.getString("image"));
				book.setPrice(rs.getInt("price"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return book;
	}
	
}