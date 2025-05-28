package com.krishimart.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.krishimart.model.OrderItem;
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
