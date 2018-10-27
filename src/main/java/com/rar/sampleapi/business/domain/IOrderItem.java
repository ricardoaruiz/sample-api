package com.rar.sampleapi.business.domain;

import java.math.BigDecimal;

public interface IOrderItem {

	String getDescription();

	int getQuantity();

	BigDecimal getAmount();
	
	BigDecimal getTotalAmount();

}
