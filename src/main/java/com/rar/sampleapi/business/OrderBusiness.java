package com.rar.sampleapi.business;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.rar.sampleapi.business.domain.IOrder;
import com.rar.sampleapi.business.domain.OrderImpl;
import com.rar.sampleapi.business.exception.OrderAlreadyExistsException;
import com.rar.sampleapi.business.exception.OrderNotFoundException;
import com.rar.sampleapi.db.domain.Order;
import com.rar.sampleapi.db.domain.OrderItem;
import com.rar.sampleapi.rest.validation.ValidationMessageKeyEnum;
import com.rar.sampleapi.service.OrderService;

@Component
public class OrderBusiness {

	@Autowired
	private OrderService orderService;
	
	/**
	 * Cria um novo pedido
	 * @param pedido
	 */
	public void create(IOrder pedido) {
		if (orderService.existsByTitulo(pedido.getTitle())) {
			throw new OrderAlreadyExistsException(
					ValidationMessageKeyEnum.ORDER_ALREADY_EXISTS.getKey(),
					pedido.getTitle()); 
		}			
		orderService.create(fromBusinessToDomain(pedido));
	}
	
	/**
	 * Lista todos os pedidos
	 * @return
	 */
	public List<IOrder> listAll() {
		List<IOrder> orders = orderService.listAll().stream()
			.map( (order) -> {
				return new OrderImpl(order);
			}).collect(Collectors.toList());	
		
		return orders;		
	}

	/**
	 * Busca um pedido por seu id
	 * @param id
	 * @return
	 */
	public IOrder getOrder(Long id) {
		Order order = orderService.findById(id);
		return order != null ? new OrderImpl(order) : null;
	}

	/**
	 * Remove um pedido
	 * @param id
	 */
	public void delete(Long id) {		
		if(!orderService.existsById(id)) {
			throw new OrderNotFoundException(ValidationMessageKeyEnum.ORDER_NOT_FOUND.getKey());
		}
		orderService.delete(id);		
	}

	/**
	 * Converte um businessDomain para um entityDomain
	 * @param order
	 * @return Order
	 */
	private Order fromBusinessToDomain(IOrder order) {
		Order orderEntity = new Order();
		BeanUtils.copyProperties(order, orderEntity);
			
		if(!CollectionUtils.isEmpty(order.getItems())) {
			
			List<OrderItem> orders = order.getItems().stream()
				.map( tmpOrder -> {
					OrderItem itemEntity = new OrderItem();
					BeanUtils.copyProperties(tmpOrder, itemEntity);
					return itemEntity;
				}).collect(Collectors.toList());
			
			orderEntity.setItems(orders);
			
		}
		return orderEntity;
	}
	
}
