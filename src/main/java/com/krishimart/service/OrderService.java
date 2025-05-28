package com.krishimart.service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.krishimart.Repository.OrderRepository;
import com.krishimart.model.CartItem;
import com.krishimart.model.Order;
import com.krishimart.model.OrderItem;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public Order placeOrder(Long userId, List<CartItem> cartItems) {
        Order order = new Order();
        order.setUserId(userId);
        order.setStatus("Placed");
        order.setOrderDate(new Timestamp(System.currentTimeMillis()));

        BigDecimal total = BigDecimal.ZERO;
        List<OrderItem> orderItems = new ArrayList<>();

        for (CartItem cartItem : cartItems) {
            OrderItem item = new OrderItem();
            item.setProductId(cartItem.getProduct().getId());
            item.setQuantity(cartItem.getQuantity());

            // Convert double to BigDecimal correctly
            double priceDouble = cartItem.getProduct().getPrice();
            BigDecimal price = BigDecimal.valueOf(priceDouble);

            item.setPrice(price);
            item.setOrder(order);

            total = total.add(price.multiply(BigDecimal.valueOf(cartItem.getQuantity())));
            orderItems.add(item);
        }

        order.setTotalAmount(total);
        order.setItems(orderItems);

        return orderRepository.save(order);
    }
}
