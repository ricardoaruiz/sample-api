package com.rar.sampleapi.business.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.rar.sampleapi.db.domain.Order;

public class OrderImpl implements IOrder {

	private Order order;
	
	public OrderImpl(Order pedido) {
		this.order = pedido;
	}
	
	@Override
	public String getTitle() {
		return hasOrder() ? this.order.getTitle() : "";
	}
	
	@Override
	public Date getCreateAt() {
		return hasOrder() ? this.order.getCreateAt() : null;
	}
	
	@Override
	public BigDecimal getAmount() {
		return hasOrder() ? this.order.getAmount() : null;
	}

	@Override
	public List<? extends IOrderItem> getItems() {
		final List<IOrderItem> itens = new ArrayList<IOrderItem>();
		if(hasOrder()) {		
			order.getItems().forEach(item -> {
				itens.add(new OrderItemImpl(item));
			});
		}		
		return itens;
	}

	private boolean hasOrder() {
		return this.order != null;
	}
}
