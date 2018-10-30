package com.rar.sampleapi.business.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface IOrder {

	String getTitle();
	
	Date getCreateAt();
	
	Date getRevisionAt();
	
	BigDecimal getAmount();
	
	List<? extends IOrderItem> getItems();
	
}
