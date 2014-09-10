package de.hs.furtwangen.bam.jee.configurator.web.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import de.hs.furtwangen.bam.jee.configurator.model.BaseEntity;

public class OrderPositionWeb extends BaseEntity {
	
	private String productname;

	private String size;
	
	private BigDecimal price;	
	
	private String username;
	
	private boolean registered;
	
	private boolean provided;
	
	private boolean done;
		
	private String comment;
	
	private LocalDateTime createdDate;
	
	private String createdDateString;
	

	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public boolean isRegistered() {
		return registered;
	}

	public void setRegistered(boolean registered) {
		this.registered = registered;
	}

	public boolean isProvided() {
		return provided;
	}

	public void setProvided(boolean provided) {
		this.provided = provided;
	}

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
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
	
	public String getCreatedDateString() {
		return createdDateString;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
		this.createdDateString = createdDate.toString();
	}
	
	

}
