package com.krishimart.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.krishimart.model.CartItem;
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
	List<CartItem> findByUserId(Long userId);
	@Modifying
	@Query("DELETE FROM CartItem c WHERE c.user.id = :userId")
	void deleteByUserId(@Param("userId") Long userId);
	Optional<CartItem> findByUserIdAndProductId(Long userId, Long productId);

}
