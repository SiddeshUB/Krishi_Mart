package com.krishimart.controller;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.krishimart.model.Product;
import com.krishimart.model.User;
import com.krishimart.service.ProductService;
import com.krishimart.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;
    
    @Autowired
    private UserService userService;
    
    @GetMapping("/view")
    public String viewProducts(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login"; // User not logged in
        }
        Long userId = user.getId();
        List<Product> products = productService.getAllProducts();

        //  Add this to extract unique categories
        Set<String> categories = products.stream()
                                         .map(Product::getCategory)
                                         .collect(Collectors.toSet());

        model.addAttribute("products", products);
        model.addAttribute("userId", userId);
        model.addAttribute("categories", categories); // ✅ Add to model

        String role = userService.getUserRoleById(userId);
        model.addAttribute("role", role);

        return "product";
    }


    // API: Get All Products (Optional)
    @GetMapping
    @ResponseBody
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    // API: Get Products by Seller
    @GetMapping("/seller/{sellerId}")
    @ResponseBody
    public List<Product> getSellerProducts(@PathVariable Long sellerId) {
        return productService.getProductsBySeller(sellerId);
    }

    // Upload Product Form Submission
    @PostMapping("/upload")
    public String uploadProduct(@ModelAttribute Product product) {
        productService.saveProduct(product);
        return "redirect:/seller/uploadPage?sellerId=" + product.getSeller().getId();
    }
    @GetMapping("/search")
    public String searchProducts(@RequestParam("query") String query, Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        Long userId = user.getId();

        if (userId == null) {
            return "redirect:/login";
        }

        List<Product> results = productService.searchProducts(query);

        // Extract categories from search result
        Set<String> categories = results.stream()
                                        .map(Product::getCategory)
                                        .collect(Collectors.toSet());

        model.addAttribute("products", results);
        model.addAttribute("userId", userId);
        model.addAttribute("categories", categories); // ✅ Add to model

        String role = userService.getUserRoleById(userId);
        model.addAttribute("role", role);

        return "product"; // reuse the same product listing page
    }
}
