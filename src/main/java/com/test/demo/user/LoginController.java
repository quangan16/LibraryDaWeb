package com.test.demo.user;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@CrossOrigin
@RequestMapping("/Library")
public class LoginController {
	UserDAO userDao = new UserDAO();

	// get trang login
	@GetMapping("/guest/login")
	public String getLogin(Model model) throws IOException {

		return "login";
	}

	// post form login
	@PostMapping("/guest/login/authenticate")
	public String submitFormLogin(@ModelAttribute(name = "user") User user, Model model,  RedirectAttributes redirectAttributes, HttpSession session) {
		String username = user.getUsername();
		String password = user.getPassword();
		user = userDao.checkLogin(username, password);
		System.out.println(username);
		System.out.println(password);
		
		if (user!=null && user.getRole().equals("admin")) {
			
			return "redirect:/Library/books";
			
		} 
		
		else if(user!=null &&user.getRole().equals("user")){
			session.setAttribute("username", user.getUsername());
			
//			redirectAttributes.addFlashAttribute("user2", user);
			model.addAttribute("user", user);
			return "redirect:/Library/"+ user.getUsername()+ "/books";
		}
		
		else {
			model.addAttribute("logError", "logError");
			
			return "login";
		}

	}

	// get trang sign-up
	@GetMapping("/guest/signup")
	public String getSignup() throws IOException {
		return "sign-up";
	}

	// luu thong tin nguoi dang ky
	@PostMapping("guest/signup/save")
	public String addUser(@ModelAttribute(name = "user1") User user1, Model model) throws SQLException {
		String fullName = user1.getName();
		int age = user1.getAge();
		String phoneNum = user1.getName();
		String username = user1.getUsername();
		String password = user1.getPassword();
		if (userDao.checkUserExistByName(fullName)) {
			model.addAttribute("userExisted", true);
			return "sign-up";
			
		} else {
			model.addAttribute("userExisted", false);
			userDao.insertUser(new User(fullName, age, phoneNum, username, password));
			return "redirect:/Library/guest/login";
		}

	}

}
