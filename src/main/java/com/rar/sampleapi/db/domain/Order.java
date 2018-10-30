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
	
	@Temporal(TemporalType.DATE)
	private Date createAt;
	
	@Temporal(TemporalType.DATE)
	private Date revisionAt;
	
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

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date creatAt) {
		this.createAt = creatAt;
	}

	public Date getRevisionAt() {
		return revisionAt;
	}

	public void setRevisionAt(Date revisionAt) {
		this.revisionAt = revisionAt;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public List<OrderItem> getItems() {
		return items;
	}

	public void setItems(List<OrderItem> items) {
		this.items = items;
	}
}
