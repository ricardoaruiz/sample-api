/**
 * 
 */
package com.rar.sampleapi.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rar.sampleapi.business.OrderBusiness;
import com.rar.sampleapi.business.domain.IOrder;
import com.rar.sampleapi.rest.domain.CreateOrderRequest;
import com.rar.sampleapi.rest.domain.ListOrderResponse;

/**
 * @author rick
 *
 */
@RestController("/pedido")
public class OrderRestController {

	@Autowired
	private OrderBusiness orderBusiness;
	
	@GetMapping
	public ResponseEntity<ListOrderResponse> list() {	
		
		List<IOrder> listarTodos = orderBusiness.listAll();
		ListOrderResponse response = new ListOrderResponse(listarTodos);
		
		return CollectionUtils.isEmpty(listarTodos) ? 
				ResponseEntity.noContent().build() : 
					ResponseEntity.ok(response);
	}
	
	@PostMapping
	public ResponseEntity<Void> create(@RequestBody @Valid CreateOrderRequest pedido) {	
		
		orderBusiness.create(pedido);		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
}
