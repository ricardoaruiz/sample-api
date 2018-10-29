package com.rar.sampleapi.rest.domain;

import com.rar.sampleapi.business.domain.IOrder;

public class GetOrderResponse {

	private OrderResponseData order;
	
	public GetOrderResponse(IOrder order) {
		this.order = new OrderResponseData(order);
	}

	public OrderResponseData getOrder() {
		return hasOrder() ? order : null;
	}
	
	private boolean hasOrder() {
		return this.order != null;
	}

}
