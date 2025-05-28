package com.krishimart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.krishimart.model.User;
import com.krishimart.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {
	@Autowired
	private UserService userService;
	
	@GetMapping("/register")
	public String showRegisterForm(Model model) {
		model.addAttribute("user",new User());
		return "register";
	}
	@PostMapping("/register")
	public String register(@ModelAttribute("user") User user) {
		userService.registerUser(user);
		return "redirect:/login";
	}
	@GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }
	@GetMapping("/contact")
    public String showContact() {
        return "contact";
    }
	@GetMapping("/about")
    public String showAbout() {
        return "about";
    }
	@PostMapping("/contact")
    public String submitContactForm(@RequestParam("name") String name,
                                    @RequestParam("email") String email,
                                    @RequestParam("message") String message,
                                    Model model) {

        // Log or save contact message (you can later save this to DB)
        System.out.println("Contact Form Received:");
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("Message: " + message);

        // Send confirmation to the user
        model.addAttribute("confirmation", "Thank you for contacting us, " + name + ". We'll get back to you soon!");
        return "contact"; // reloads the same page with a success message
    }
	@PostMapping("/login")
	public String login(@RequestParam String email,
	                    @RequestParam String password,
	                    HttpSession session,
	                    Model model) {
	    User user = userService.login(email, password);
	    if (user != null) {
	        session.setAttribute("user", user);
	        session.setAttribute("loggedIn", true); // ✅ Add this line
	        return "redirect:/";
	    }
	    model.addAttribute("error", "Invalid Credentials");
	    return "login";
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
	    session.invalidate(); 
	    return "redirect:/";
	}
}
