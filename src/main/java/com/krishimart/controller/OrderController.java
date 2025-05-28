package com.krishimart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.krishimart.model.CartItem;
import com.krishimart.model.Order;
import com.krishimart.service.CartService;
import com.krishimart.service.OrderService;

import jakarta.servlet.http.HttpSession;

@Controller
public class OrderController {

    @Autowired private OrderService orderService;
    @Autowired private CartService cartService;

    @PostMapping("/order/place")
    public String placeOrder(@RequestParam Long userId, Model model) {
        List<CartItem> cartItems = cartService.getUserCart(userId);
        Order order = orderService.placeOrder(userId, cartItems);
        cartService.clearCart(userId); // Optional: clear cart after placing order
        model.addAttribute("order", order);
        return "ordersuccess"; // A JSP/Thymeleaf page confirming the order
    }
    @GetMapping("/ordersuccess")
    public String orderSuccess() {
        return "ordersuccess";
    }
}
