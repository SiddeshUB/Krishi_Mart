package com.krishimart.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
@Entity
@Table(name = "orders") 
public class Order {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Long userId;
	private BigDecimal totalAmount;
	private Timestamp orderDate;
	private String status;
	
	 @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	    private List<OrderItem> items;

	public Long getId() {
		return id;
	}

	public Long getUserId() {
		return userId;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public Timestamp getOrderDate() {
		return orderDate;
	}

	public String getStatus() {
		return status;
	}

	public List<OrderItem> getItems() {
		return items;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public void setOrderDate(Timestamp orderDate) {
		this.orderDate = orderDate;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setItems(List<OrderItem> items) {
		this.items = items;
	}
	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}
	 
	 
}
