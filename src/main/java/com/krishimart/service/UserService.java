package com.krishimart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.krishimart.Repository.UserRepository;
import com.krishimart.Repository.SellerRepository;
import com.krishimart.model.User;
import com.krishimart.model.Seller;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SellerRepository sellerRepository;

    public User registerUser(User user) {
        User savedUser = userRepository.save(user);

        if ("SELLER".equalsIgnoreCase(savedUser.getRole())) {
            Seller seller = new Seller();
            seller.setName(savedUser.getFullName());
            seller.setEmail(savedUser.getEmail());
            sellerRepository.save(seller);
        }

        return savedUser;
    }

    public User login(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
    public String getUserRoleById(Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        return (user != null) ? user.getRole() : "buyer"; // Default to buyer
    }
}
