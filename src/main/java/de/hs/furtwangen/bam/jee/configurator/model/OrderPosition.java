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
	
	@ManyToOne(fetch=FetchType.EAGER,cascade = {CascadeType.PERSIST})
	@JoinColumn(name="user_id")
	private User user;
	
	@ManyToOne(fetch=FetchType.EAGER,cascade = {CascadeType.PERSIST})
	@JoinColumn(name="table_id")
	private TableCustomer tableCustomer;
	
	@ManyToOne(fetch=FetchType.EAGER,cascade = {CascadeType.PERSIST})
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

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public void setModifiedDate(LocalDateTime modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((comment == null) ? 0 : comment.hashCode());
		result = prime * result
				+ ((createdDate == null) ? 0 : createdDate.hashCode());
		result = prime * result + (done ? 1231 : 1237);
		result = prime * result
				+ ((modifiedDate == null) ? 0 : modifiedDate.hashCode());
		result = prime * result + (provided ? 1231 : 1237);
		result = prime * result + (registered ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderPosition other = (OrderPosition) obj;
		if (comment == null) {
			if (other.comment != null)
				return false;
		} else if (!comment.equals(other.comment))
			return false;
		if (createdDate == null) {
			if (other.createdDate != null)
				return false;
		} else if (!createdDate.equals(other.createdDate))
			return false;
		if (done != other.done)
			return false;
		if (modifiedDate == null) {
			if (other.modifiedDate != null)
				return false;
		} else if (!modifiedDate.equals(other.modifiedDate))
			return false;
		if (provided != other.provided)
			return false;
		if (registered != other.registered)
			return false;
		return true;
	}
	
	
	
	
	
	
	
	

}
