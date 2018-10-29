package com.rar.sampleapi.rest.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.rar.sampleapi.business.domain.IOrder;
import com.rar.sampleapi.business.domain.IOrderItem;

@JsonPropertyOrder({ "titulo", "criacao", "valor", "itens" })
public class OrderResponseData {

	private IOrder order;

	public OrderResponseData(IOrder pedido) {
		this.order = pedido;
	}

	@JsonProperty("titulo")
	public String getTitle() {
		return this.order.getTitle();
	}
	
	@JsonProperty("criacao")
	@JsonFormat(shape=Shape.STRING, pattern="dd-MM-yyyy", locale = "pt-BR", timezone = "Brazil/East")
	public Date getCreateAt() {
		return this.order.getCreateAt();
	}
	
	@JsonProperty("valor")
	public BigDecimal getAmount() {
		return this.order.getAmount();
	}
	
	@JsonProperty("itens")
	public List<OrderItemResponseData> getItems() {
		List<OrderItemResponseData> items = new ArrayList<OrderItemResponseData>();
		
		for (IOrderItem item : this.order.getItems()) {
			items.add(new OrderItemResponseData(item));
		}
		
		return items;
	}
	
	
}
