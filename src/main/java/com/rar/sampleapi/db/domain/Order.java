/**
 * 
 */
package com.rar.sampleapi.db.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author rick
 *
 */
@Entity(name="PEDIDO")
public class Order implements Serializable{

	private static final long serialVersionUID = -8925512520933957571L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String title;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date creatAt;
	
	private BigDecimal amount;
		
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="pedido_id")
	private List<OrderItem> items;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String titulo) {
		this.title = titulo;
	}

	public Date getCreateAt() {
		return creatAt;
	}

	public void setCreateAt(Date criacao) {
		this.creatAt = criacao;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal valor) {
		this.amount = valor;
	}

	public List<OrderItem> getItems() {
		return items;
	}

	public void setItems(List<OrderItem> itens) {
		this.items = itens;
	}	
}
