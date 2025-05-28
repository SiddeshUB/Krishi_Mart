package com.krishimart.Repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.krishimart.model.Seller;
public interface SellerRepository extends JpaRepository<Seller, Long> {
	Optional<Seller> findByEmail(String email);
}
