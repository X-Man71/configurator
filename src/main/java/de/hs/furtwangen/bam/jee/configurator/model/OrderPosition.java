package de.hs.furtwangen.bam.jee.configurator.model;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "orderPositions")
public class OrderPosition extends BaseEntity {
	
	@ManyToOne(fetch=FetchType.EAGER,cascade = {CascadeType.ALL})
	@JoinColumn(name="user_id")
	private User user;
	
	@ManyToOne(fetch=FetchType.EAGER,cascade = {CascadeType.ALL})
	@JoinColumn(name="table_id")
	private TableCustomer tableCustomer;
	
	@ManyToOne(fetch=FetchType.EAGER,cascade = {CascadeType.ALL})
	@JoinColumn(name="product_id")
	private Product product;
	
	/**
	 * True when waiter has the Order complete
	 */
	
	private boolean registered;
	
	/**
	 * By cook or Barkeeper
	 */	
	private boolean provided;
	
	/**
	 * Changed to true if orderPosition is payed
	 */
	private boolean done;
	

	
	private String comment;
	
	
	@CreatedDate
	@DateTimeFormat(pattern = "HH.mm.ss dd.MM.yyyy")
	private LocalDateTime createdDate;
	
	@LastModifiedDate
	@DateTimeFormat(pattern = "HH.mm.ss dd.MM.yyyy")
	private LocalDateTime modifiedDate;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public TableCustomer getTableCustomer() {
		return tableCustomer;
	}

	public void setTableCustomer(TableCustomer tableCustomer) {
		this.tableCustomer = tableCustomer;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

	public boolean isProvided() {
		return provided;
	}

	public void setProvided(boolean provided) {
		this.provided = provided;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public LocalDateTime getModifiedDate() {
		return modifiedDate;
	}

	public boolean isRegistered() {
		return registered;
	}

	public void setRegistered(boolean registered) {
		this.registered = registered;
	}
	
	
	
	

}
