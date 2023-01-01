
package com.test.demo.book;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.test.demo.comment.Comment;
import com.test.demo.comment.CommentDAO;
import com.test.demo.user.User;
import com.test.demo.user.UserDAO;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.*;

@Controller
@CrossOrigin
@RequestMapping("/Library")
public class BookTestController {

	private BookDAO bookDAO = new BookDAO();
	private UserDAO userDAO = new UserDAO();
	private CommentDAO commentDAO = new CommentDAO();
	@GetMapping("/guest/books")
	public String getBooksAsGuest(Model model) throws IOException{
		List<Book> books = bookDAO.selectAllBooks();
		model.addAttribute("books", books);
		return "books-guest";
				
	}
	
	@GetMapping("/books")
	public String getBooks(Model model) throws IOException{
		List<Book> books = bookDAO.selectAllBooks();
		model.addAttribute("books", books);
		return "books";
				
	}
	
	@GetMapping("/{username}/books")
	public String getBooksAsUser(Model model,@PathVariable String username) throws IOException{
		User u = userDAO.GetUserByUserName(username);
		System.out.println(u.getUsername());
		List<Book> books = bookDAO.selectAllBooks();
		model.addAttribute("username", u.getUsername());
		model.addAttribute("user", u);
		model.addAttribute("books", books);
		return "book-user";
				
	}
	
	
	@GetMapping("/book/{bookcode}")
	public String getBookDetailView(Model model, @PathVariable String bookcode) {
		model.addAttribute("bookcode", bookcode);
		System.out.println(bookcode);
		Book book = bookDAO.selectBook(Integer.valueOf(bookcode));
		System.out.println(book.getPhoto());
		model.addAttribute("book", book);
		return "book-detail-view";			
	}
	
	@GetMapping("/user/{bookcode}")
	public String getBookDetailByUser(Model model, @PathVariable String bookcode) {
		model.addAttribute("bookcode", bookcode);
		System.out.println(bookcode);
		Book book = bookDAO.selectBook(Integer.valueOf(bookcode));
		System.out.println(book.getPhoto());
		model.addAttribute("book", book);
		return "book-user-detail";			
	}
	
	@GetMapping("{username}/bookdetail/{bookcode}")
	public String getUserBook(Model model, @PathVariable String username, @PathVariable int bookcode) {
		
		Book book = bookDAO.selectBook(bookcode);
		User user = userDAO.GetUserByUserName(username);
		
		System.out.println(user.getUsername());
		List<Comment> comments = commentDAO.getCommentsByBookID(book.getBookcode());
		model.addAttribute("username", user.getUsername());
		model.addAttribute("bookcode", bookcode);
		model.addAttribute("book", book);
		model.addAttribute("user", user);
		model.addAttribute("comments", comments);
		return "book-user-detail";	
	}
	
	
	@GetMapping("/addbook/{bookcode}")
	public String getBookDetailAdd(Model model, @PathVariable String bookcode) {
		model.addAttribute("bookcode", bookcode);
		System.out.println(bookcode);
		Book book = bookDAO.selectBook(Integer.valueOf(bookcode));
		
		model.addAttribute("book", book);
		
		return "book-detail-add";			
	}
	
	@GetMapping("/editbook/{bookcode}")
	public String getBookDetailModify(Model model, @PathVariable String bookcode) {
		model.addAttribute("bookcode", bookcode);
		System.out.println(bookcode);
		Book book = bookDAO.selectBook(Integer.valueOf(bookcode));
		
		model.addAttribute("book", book);
		return "book-detail-modify";			
	}
	
	@PostMapping("/addbook/save/{bookcode}")
	public String addBook(Book book, Model model,  @RequestParam("pic") MultipartFile photo )throws SQLException{
		if(photo.isEmpty()) {
			bookDAO.insertBook(book);
			System.out.println("empty");
		}else {
			Path path = Paths.get("uploads");
			if(photo.isEmpty()) {
				System.out.println("empty");
			}else {
				System.out.println(path.resolve(photo.getOriginalFilename()));
			}
			try {
				InputStream inputStream = photo.getInputStream();
				Files.copy(inputStream, path.resolve(photo.getOriginalFilename()),StandardCopyOption.REPLACE_EXISTING);
				book.setPhoto(photo.getOriginalFilename().toLowerCase());
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			bookDAO.insertBook(book);
			
		}
		
		return "redirect:/Library/books";
	}
	
	@PutMapping("/editbook/save/{bookcode}")
	public String update(Book book, Model model,  @RequestParam("pic") MultipartFile photo) throws SQLException{
		Path path = Paths.get("uploads");
		if(photo.isEmpty()) {
			bookDAO.updateBookWithoutImage(book);
			System.out.println("empty");
		}else {
			System.out.println(path.resolve(photo.getOriginalFilename()));
			try {
				InputStream inputStream = photo.getInputStream();
				Files.copy(inputStream, path.resolve(photo.getOriginalFilename()),StandardCopyOption.REPLACE_EXISTING);
				if(!(photo.isEmpty())) {
					book.setPhoto(photo.getOriginalFilename().toLowerCase());
				}
				
			}catch(Exception e) {
				e.printStackTrace();
			}
			bookDAO.updateBook(book);
		}
		
		
		
		return "redirect:/Library/books";
	}
	
	@PostMapping("/book/delete/{bookcode}")
	public void seleceDeleteBook(@PathVariable String bookcode, Model model) throws SQLException{
		model.addAttribute("temp", bookcode);
		System.out.println(bookcode);
		
	}
	
	@DeleteMapping("/book/delete/{bookcode}")
	public String deleteBook(@PathVariable String bookcode) throws SQLException{
		bookDAO.deleteBook(Integer.valueOf(bookcode));
		System.out.println(bookcode);
		return "redirect:/Library/books";
	}
	
}


	



