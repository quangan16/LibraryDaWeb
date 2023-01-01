package com.test.demo.comment;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.test.demo.book.Book;
import com.test.demo.book.BookDAO;
import com.test.demo.user.User;
import com.test.demo.user.UserDAO;
@Controller
@CrossOrigin
@RequestMapping("/Library")
public class CommentController {
	private BookDAO bookDAO = new BookDAO();
	private UserDAO userDAO = new UserDAO();
	private CommentDAO commentDAO = new CommentDAO();
	
	@GetMapping("/{username}/comment/{bookcode}")
	public String getComment(Model model, @PathVariable int bookcode, @PathVariable String username) {
		
		model.addAttribute("bookcode", bookcode);
		Book book = bookDAO.selectBook(bookcode);
		
		model.addAttribute("username", username);
		User user = userDAO.GetUserByUserName(username);
		List<Comment> comments = commentDAO.getCommentsByBookID(book.getBookcode());
		model.addAttribute("book", book);
		model.addAttribute("user", user);
		model.addAttribute("username", user.getUsername());
		model.addAttribute("comments", comments);
		return "book-user-detail";
		
	}
	
	@PostMapping("/{username}/comment/{bookcode}")
	public String createComment(Model model, @PathVariable int bookcode, @PathVariable String username,
			@RequestParam(name = "content") String content, @RequestParam(name = "rating") String rating) {
		model.addAttribute("bookcode", bookcode);
		Book book = bookDAO.selectBook(Integer.valueOf(bookcode));

		model.addAttribute("username", username);
		User user = userDAO.GetUserByUserName(username);

		Comment comment = new Comment();
		
		comment.setBookCode(bookcode);
		comment.setContent(content);
		comment.setStarPoint(Float.parseFloat(rating));
		comment.setUsername(username);
		
		commentDAO.addComment(comment);
		

		model.addAttribute("book", book);
		model.addAttribute("user", user);
		model.addAttribute("content", content);
		
		return "redirect:/Library/{username}/comment/{bookcode}";
	}
}
