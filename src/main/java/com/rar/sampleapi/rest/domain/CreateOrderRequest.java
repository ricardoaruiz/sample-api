/**
 * 
 */
package com.rar.sampleapi.rest.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.rar.sampleapi.business.domain.IOrder;
import com.rar.sampleapi.business.domain.IOrderItem;
import com.rar.sampleapi.rest.validation.ValidationMessageKeyEnum;

/**
 * @author rick
 *
 */
public class CreateOrderRequest implements IOrder {

	@JsonProperty("titulo")
	@NotBlank(message=ValidationMessageKeyEnum.Constants.REQUIRED_FIELD)
	private String title;
	
	@JsonProperty("criacao")
	@JsonFormat(shape=Shape.STRING, pattern="dd-MM-yyyy", locale = "pt-BR", timezone = "Brazil/East")
	@NotNull(message=ValidationMessageKeyEnum.Constants.REQUIRED_FIELD)
	private Date createAt;
	
	@Valid
	@JsonProperty("itens")
	@NotEmpty(message=ValidationMessageKeyEnum.Constants.ORDER_ITEMS_REQUIRED)
	private List<CreateOrderItemRequestData> items;
			
	@Override
	public String getTitle() {
		return this.title;
	}
	
	@Override
	public Date getCreateAt() {
		return this.createAt;
	}
	
	@Override
	public BigDecimal getAmount() {
		BigDecimal total = BigDecimal.ZERO;
		for (CreateOrderItemRequestData item : items) {
			total = total.add(item.getAmount().multiply(BigDecimal.valueOf(item.getQuantity())));
		}
		return total;
	}
	
	@Override
	public List<? extends IOrderItem> getItems() {
		return this.items;
	}

}
