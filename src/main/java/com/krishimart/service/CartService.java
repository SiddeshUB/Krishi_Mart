package com.krishimart.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.krishimart.Repository.CartItemRepository;
import com.krishimart.Repository.ProductRepository;
import com.krishimart.model.CartItem;
import com.krishimart.model.Product;
import com.krishimart.model.User;

import jakarta.transaction.Transactional;

@Service
public class CartService {

    @Autowired
    private CartItemRepository cartRepo;

    @Autowired
    private ProductRepository productRepo;

    // Add product to user's cart
    public void addToCart(Long userId, Long productId, int quantity) {
        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        User user = new User(userId); // Assuming User constructor sets ID

        // Check if the product already exists in the cart for this user
        Optional<CartItem> existingCartItem = cartRepo.findByUserIdAndProductId(userId, productId);

        if (existingCartItem.isPresent()) {
            // If it exists, increase the quantity
            CartItem cartItem = existingCartItem.get();
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
            cartRepo.save(cartItem);
        } else {
        	 System.out.println("New item added to cart with quantity: " + quantity);
            // Else, create a new cart item
            CartItem cartItem = new CartItem();
            cartItem.setProduct(product);
            cartItem.setUser(user);
            cartItem.setQuantity(quantity);
            cartRepo.save(cartItem);
        }
    }


    // Get all cart items for a user
    public List<CartItem> getUserCart(Long userId) {
        return cartRepo.findByUserId(userId);
    }

    // Calculate total price of cart
    public double calculateTotalAmount(List<CartItem> cartItems) {
        return cartItems.stream()
                .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
                .sum();
    }

    // Remove one item from cart
    public void removeCartItem(Long cartItemId) {
        cartRepo.deleteById(cartItemId);
    }

    @Transactional
    public void clearCart(Long userId) {
        cartRepo.deleteByUserId(userId);
    }
    @Transactional
    public void checkout(Long userId) {
        // Logic to process payment and place order could go here
        clearCart(userId); // After placing order
    }
}
