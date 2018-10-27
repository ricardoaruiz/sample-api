/**
 * 
 */
package com.rar.sampleapi.service;

import java.util.List;

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
	private OrderRepository pedidoRepository;
	
	public List<Order> listAll() {
		return pedidoRepository.findAll();
	}
	
	public void inserir(Order pedido) {
		pedidoRepository.save(pedido);
	}
	
	public boolean existsByTitulo(String titulo) {
		return pedidoRepository.existsByTitle(titulo);
	}
	
}
