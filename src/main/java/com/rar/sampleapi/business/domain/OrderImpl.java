package com.rar.sampleapi.business.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.rar.sampleapi.db.domain.Order;
import com.rar.sampleapi.db.domain.OrderItem;

public class OrderImpl implements IOrder {

	private Order order;
	
	public OrderImpl(Order pedido) {
		this.order = pedido;
	}
	
	@Override
	public String getTitle() {
		return this.order.getTitle();
	}
	
	@Override
	public Date getCreateAt() {
		return this.order.getCreateAt();
	}
	
	@Override
	public BigDecimal getAmount() {
		return this.order.getAmount();
	}

	@Override
	public List<? extends IOrderItem> getItems() {
		List<IOrderItem> itens = new ArrayList<IOrderItem>();
		
		for (OrderItem item : order.getItems()) {
			itens.add(new OrderItemImpl(item));			
		}
		
		return itens;
	}
	
}
