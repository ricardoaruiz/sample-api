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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rar.sampleapi.business.OrderBusiness;
import com.rar.sampleapi.business.domain.IOrder;
import com.rar.sampleapi.rest.domain.CreateOrderRequest;
import com.rar.sampleapi.rest.domain.GetOrderResponse;
import com.rar.sampleapi.rest.domain.ListOrderResponse;

/**
 * @author rick
 *
 */
@RestController()
@RequestMapping("/pedido")
public class OrderRestController {

	@Autowired
	private OrderBusiness orderBusiness;
	
	/**
	 * Listar todos os pedidos
	 * @return Lista de pedidos
	 */
	@GetMapping
	public ResponseEntity<ListOrderResponse> list() {	
		
		List<IOrder> listarTodos = orderBusiness.listAll();
		ListOrderResponse response = new ListOrderResponse(listarTodos);
		
		return CollectionUtils.isEmpty(listarTodos) ? 
				ResponseEntity.noContent().build() : 
					ResponseEntity.ok(response);
	}
	
	/**
	 * Buscar um pedido específico pelo seu id
	 * @param id
	 * @return Pedido
	 */
	@GetMapping("/{id}")
	public ResponseEntity<GetOrderResponse> get(@PathVariable Long id) {
		
		IOrder order = orderBusiness.getOrder(id);
		
		return order == null ? 
				ResponseEntity.noContent().build() :
					ResponseEntity.ok(new GetOrderResponse(order));
		
	}
	
	/**
	 * Cria um novo pedido
	 * @param pedido
	 * @return Void
	 */
	@PostMapping
	public ResponseEntity<Void> create(@RequestBody @Valid CreateOrderRequest pedido) {	
		
		orderBusiness.create(pedido);		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	/**
	 * Remove o pedido do id informado caso exista
	 * @param id
	 * @return
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		
		orderBusiness.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}
