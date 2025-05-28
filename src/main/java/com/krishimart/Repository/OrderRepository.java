package com.krishimart.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.krishimart.model.Order;
public interface OrderRepository extends JpaRepository<Order, Long> {
	
}
