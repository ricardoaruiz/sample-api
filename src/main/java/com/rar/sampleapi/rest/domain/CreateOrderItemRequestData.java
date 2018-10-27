/**
 * 
 */
package com.rar.sampleapi.rest.domain;

import java.math.BigDecimal;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rar.sampleapi.business.domain.IOrderItem;
import com.rar.sampleapi.rest.validation.ValidationMessageKeyEnum;

/**
 * @author rick
 *
 */
public class CreateOrderItemRequestData implements IOrderItem {

	@JsonProperty("descricao")
	@NotBlank(message=ValidationMessageKeyEnum.Constants.REQUIRED_FIELD)
	private String description;
	
	@JsonProperty("quantidade")
	@Min(value=1, message=ValidationMessageKeyEnum.Constants.MUST_BE_GREATER_THAN_ZERO_FIELD)
	private int quantity;
	
	@JsonProperty("valor")
	@NotNull(message=ValidationMessageKeyEnum.Constants.REQUIRED_FIELD)
	@Min(value=1, message=ValidationMessageKeyEnum.Constants.MUST_BE_GREATER_THAN_ZERO_FIELD)
	private BigDecimal amount;

	@Override
	public String getDescription() {
		return this.description;
	}

	@Override
	public int getQuantity() {
		return this.quantity;
	}

	@Override
	public BigDecimal getAmount() {
		return this.amount;
	}

	@Override
	public BigDecimal getTotalAmount() {
		return this.amount.multiply(BigDecimal.valueOf(this.quantity));
	}

}
