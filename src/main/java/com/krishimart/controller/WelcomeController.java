package com.krishimart.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.krishimart.model.User;
import jakarta.servlet.http.HttpSession;

@Controller
public class WelcomeController {
	@GetMapping("/")
	public String home(HttpSession session, Model model) {
	    User user = (User) session.getAttribute("user");
	    boolean loggedIn = user != null;
	    model.addAttribute("loggedIn", loggedIn);
	    return "welcome";
	}
}
