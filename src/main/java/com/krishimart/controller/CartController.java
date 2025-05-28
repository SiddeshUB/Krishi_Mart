package com.krishimart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.krishimart.model.CartItem;
import com.krishimart.service.CartService;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    // Add Product to Cart
    @PostMapping("/add")
    public String addToCart(@RequestParam Long userId,
                            @RequestParam Long productId,
                            @RequestParam(defaultValue = "1") int quantity) {
        cartService.addToCart(userId, productId, quantity);
        return "redirect:/products/view?userId=" + userId;
    }
    // View Cart Page
    @GetMapping("/view")
    public String viewCart(@RequestParam Long userId, Model model) {
        List<CartItem> cartItems = cartService.getUserCart(userId);
        double totalAmount = cartService.calculateTotalAmount(cartItems);

        model.addAttribute("cartItems", cartItems);
        model.addAttribute("totalAmount", totalAmount);
        model.addAttribute("userId", userId); // Required to retain context for further actions

        return "cart"; // Maps to cart.html
    }

    // Remove single item (optional)
    @PostMapping("/remove")
    public String removeItem(@RequestParam Long userId, @RequestParam Long cartItemId) {
        cartService.removeCartItem(cartItemId);
        return "redirect:/cart/view?userId=" + userId;
    }

    // Clear all items in cart
    @PostMapping("/clear")
    public String clearCart(@RequestParam Long userId) {
        cartService.clearCart(userId);
        return "redirect:/cart/view?userId=" + userId;
    }

    // Checkout endpoint (optional placeholder)
    @PostMapping("/checkout")
    public String checkout(@RequestParam Long userId, Model model) {
        cartService.checkout(userId);
        model.addAttribute("message", "Checkout successful!");
        return "redirect:/products/view?userId=" + userId;
    }
}
