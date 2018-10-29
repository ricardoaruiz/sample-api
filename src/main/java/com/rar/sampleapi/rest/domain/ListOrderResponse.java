package com.rar.sampleapi.rest.domain;

import java.util.ArrayList;
import java.util.List;

import com.rar.sampleapi.business.domain.IOrder;

public class ListOrderResponse {
		
	private List<OrderResponseData> orders = new ArrayList<OrderResponseData>();
		
	public ListOrderResponse(List<IOrder> orders) {
		orders.forEach(order -> {
			this.orders.add(new OrderResponseData(order));
		});
	}

	public List<OrderResponseData> getOrders() {
		return orders;
	}	
	
}
