package com.krishimart.controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import jakarta.servlet.http.HttpSession;
@ControllerAdvice
public class GlobalController {
    @ModelAttribute("loggedIn")
    public boolean addLoggedInToModel(HttpSession session) {
        return session.getAttribute("user") != null;
    }
}
