package com.krishimart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.krishimart.Repository.ProductRepository;
import com.krishimart.model.Product;

@Service
public class ProductService {
	@Autowired
	private ProductRepository productRepository;
	
	public Product  saveProduct(Product product) {
		return productRepository.save(product);
	}
	public List<Product> getProductsBySeller(Long sellerId) {
        return productRepository.findBySellerId(sellerId);
    }
	public List<Product> getAllProducts() {
	    return productRepository.findAll();
	}
	public List<Product> searchProducts(String keyword) {
	    return productRepository.searchByKeyword(keyword);
	}

}
