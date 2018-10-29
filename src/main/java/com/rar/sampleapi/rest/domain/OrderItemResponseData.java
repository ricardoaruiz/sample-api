package com.rar.sampleapi.rest.domain;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.rar.sampleapi.business.domain.IOrderItem;

@JsonPropertyOrder({ "descricao", "quantidade", "valor", "total" })
public class OrderItemResponseData {

	private IOrderItem item;

	public OrderItemResponseData(IOrderItem item) {
		this.item = item;
	}
	
	@JsonProperty("descricao")
	public String getDescription() {
		return this.item.getDescription();
	}
	
	@JsonProperty("quantidade")
	public int getQuantity() {
		return this.item.getQuantity();
	}
	
	@JsonProperty("valor")
	public BigDecimal getAmount() {
		return this.item.getAmount();
	}
	
	@JsonProperty("total")
	public BigDecimal getTotalAmount() {
		return this.item.getTotalAmount();
	}

}
