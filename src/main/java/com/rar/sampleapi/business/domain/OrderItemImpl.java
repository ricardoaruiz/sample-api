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
		return hasItem() ? this.item.getDescription() : null;
	}

	@Override
	public int getQuantity() {
		return hasItem() ? this.item.getQuantity() : 0;
	}

	@Override
	public BigDecimal getAmount() {
		return hasItem() ? this.item.getAmount() : null;
	}

	@Override
	public BigDecimal getTotalAmount() {
		return hasItem() ? this.item.getTotalAmount() : null;
	}
	
	private boolean hasItem() {
		return this.item != null;
	}

}
