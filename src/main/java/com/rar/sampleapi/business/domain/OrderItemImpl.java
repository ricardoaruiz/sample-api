package com.rar.sampleapi.business.domain;

import java.math.BigDecimal;

import com.rar.sampleapi.db.domain.OrderItem;

public class OrderItemImpl implements IOrderItem {

	private OrderItem item; 
	
	public OrderItemImpl(OrderItem item) {
		this.item = item;
	}

	@Override
	public String getDescription() {
		return this.item.getDescription();
	}

	@Override
	public int getQuantity() {
		return this.item.getQuantity();
	}

	@Override
	public BigDecimal getAmount() {
		return this.item.getAmount();
	}

	@Override
	public BigDecimal getTotalAmount() {
		return this.item.getTotalAmount();
	}

}
