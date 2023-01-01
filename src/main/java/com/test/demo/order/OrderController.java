package com.test.demo.order;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.test.demo.book.Book;
import com.test.demo.book.BookDAO;
import com.test.demo.comment.CommentDAO;
import com.test.demo.user.User;
import com.test.demo.user.UserDAO;


@Controller
@CrossOrigin
@RequestMapping("/Library")
public class OrderController {
	
	private BookDAO bookDAO = new BookDAO();
	private UserDAO userDAO = new UserDAO();
	private OrderDAO orderDAO = new OrderDAO();
	
	@PostMapping("/{username}/add-order-book")
	public String addOrderBook(Model model, @RequestParam(name = "title") String title,
			@RequestParam int quantity, @PathVariable String username) {
		
        
		Order od = new Order( title, quantity,  username);

		
		orderDAO.insertOrderBook(od);
		User u = userDAO.GetUserByUserName(username);
		List<Order> books = orderDAO.getOrderByUsername(username); 
		model.addAttribute("user", u);
		model.addAttribute("books", books);
		model.addAttribute("order", od);
		return "redirect:/Library/{username}/cart";
	}
	
//	@PostMapping("{username}/confirm-order")
//	public String confirmOrder(Model model,@PathVariable int id, @PathVariable String username, @RequestParam(name = "title") String title, requser ) {
//		User u = userDAO.GetUserByUserName(username);
//		
//		Book book = bookDAO.getBookByTitle(title);
//		int totalPrice = order.getQuantity() * book.getPrice();
//		model.addAttribute("user", u);
//		model.addAttribute("book", book);
//		model.addAttribute("order", order);
//		model.addAttribute("totalPrice", totalPrice);
//		return "book-order-confirm";
//	}

	@GetMapping("/{username}/cart")
	public String getOrderBook(Model model, @PathVariable String username) {
		
		List<Order> orders = orderDAO.getOrderByUsername(username);
		User u = userDAO.GetUserByUserName(username);
		System.out.println(u.getUsername());
		model.addAttribute("orders", orders);
		model.addAttribute("user", u);
		model.addAttribute("username", u.getUsername());
		return "book-order-list";
	}

	@DeleteMapping("{username}/order/delete/{id}")
	public String deleteOrderBook(Model model,@PathVariable("username") String username, @PathVariable int id ) {
		
		orderDAO.deleteOrder(id);
		System.out.println(username);
		User u = userDAO.GetUserByUserName(username);
		System.out.println(u.getUsername());
		List<Order> orders = orderDAO.getOrderByUsername(username);
		model.addAttribute("user", u);
		model.addAttribute("orders", orders);
		return "redirect:/Library/{username}/cart";
	}

	@GetMapping("{username}/detail-order/{id}")
	public String getOrder(Model model, @PathVariable int id, @PathVariable String username) {
		User u = userDAO.GetUserByUserName(username);
		Order order = orderDAO.getOrderByID(id);
		Book book = bookDAO.getBookByTitle(order.getBookTitle());
		int totalPrice = order.getQuantity() * book.getPrice();
		model.addAttribute("user", u);
		model.addAttribute("book", book);
		model.addAttribute("order", order);
		model.addAttribute("totalPrice", totalPrice);
		return "book-order-detail";
	}
	
	
}
