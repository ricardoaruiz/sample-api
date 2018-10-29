/**
 * 
 */
package com.rar.sampleapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rar.sampleapi.db.domain.Order;
import com.rar.sampleapi.db.repository.OrderRepository;

/**
 * @author rick
 *
 */
@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	public List<Order> listAll() {
		return orderRepository.findAll();
	}
	
	public Order findById(Long id) {
		Optional<Order> order = orderRepository.findById(id);
		return order.isPresent() ? order.get() : null;
	}
	
	public void create(Order pedido) {
		orderRepository.save(pedido);
	}

	public void delete(Long id) {
		orderRepository.deleteById(id);
	}
	
	public boolean existsById(Long id) {
		return orderRepository.existsById(id);
	}
	
	public boolean existsByTitulo(String titulo) {
		return orderRepository.existsByTitle(titulo);
	}
	
}
