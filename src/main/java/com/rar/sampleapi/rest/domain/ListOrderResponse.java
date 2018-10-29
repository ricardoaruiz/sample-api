package com.rar.sampleapi.rest.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.rar.sampleapi.business.domain.IOrder;

public class ListOrderResponse {
		
	private List<OrderResponseData> orders = new ArrayList<OrderResponseData>();
		
	public ListOrderResponse(List<IOrder> orders) {
		
		this.orders = orders.stream()
			.map( order -> {
				return new OrderResponseData(order);
			}).collect(Collectors.toList());

	}

	public List<OrderResponseData> getOrders() {
		return orders;
	}	
	
}
