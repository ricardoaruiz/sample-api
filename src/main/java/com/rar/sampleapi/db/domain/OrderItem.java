/**
 * 
 */
package com.rar.sampleapi.db.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * @author rick
 *
 */
@Entity(name="PEDIDO_ITEM")
public class OrderItem implements Serializable {

	private static final long serialVersionUID = -5471232340639364449L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String description;
	
	private int quantity;
	
	private BigDecimal amount;
	
	private BigDecimal totalAmount;
	
	@ManyToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="pedido_id")
	private Order order;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String descricao) {
		this.description = descricao;
	}
	
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantidade) {
		this.quantity = quantidade;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal valor) {
		this.amount = valor;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal total) {
		this.totalAmount = total;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order pedido) {
		this.order = pedido;
	}
		
}
