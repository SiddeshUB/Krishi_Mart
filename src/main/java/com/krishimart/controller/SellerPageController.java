package com.krishimart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.krishimart.model.Product;
import com.krishimart.model.Seller;

@Controller
@RequestMapping("/seller")
public class SellerPageController {

    // Upload Product Page
    @GetMapping("/uploadPage")
    public String showUploadPage(@RequestParam(required = false) Long sellerId, Model model) {
        Product product = new Product();
        Seller seller = new Seller();
        seller.setId(sellerId);
        product.setSeller(seller);

        model.addAttribute("product", product);
        model.addAttribute("sellerId", sellerId);
        return "uploadProduct"; // uploadProduct.jsp
    }
}
