/**
 * 
 */
package com.rar.sampleapi.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rar.sampleapi.db.domain.Order;

/**
 * @author rick
 *
 */
public interface OrderRepository extends JpaRepository<Order, Long>{

	boolean existsByTitle(String titulo);
		
}
