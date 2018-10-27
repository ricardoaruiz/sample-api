package com.rar.sampleapi.business.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface IOrder {

	String getTitle();
	
	Date getCreateAt();
	
	BigDecimal getAmount();
	
	List<? extends IOrderItem> getItems();
	
}
