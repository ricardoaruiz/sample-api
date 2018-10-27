package com.rar.sampleapi.rest.domain;

import java.util.ArrayList;
import java.util.List;

import com.rar.sampleapi.business.domain.IOrder;

public class ListOrderResponse {
		
	private List<ListOrderResponseData> orders = new ArrayList<ListOrderResponseData>();
		
	public ListOrderResponse(List<IOrder> orders) {	
		for (IOrder order : orders) {
			this.orders.add(new ListOrderResponseData(order));
		}
	}

	public List<ListOrderResponseData> getOrders() {
		return orders;
	}	
	
}
