package de.hs.furtwangen.bam.jee.configurator.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "loans")
public class Loan {
	
	@Id
	@Column(name = "requestId")
	private Long requestId;

	@Column(name = "status")
	private String status;	
	
	@Column(name = "amount")
	private double amount;
	
	@Column(name = "term")
	private int term;
	
	@Column(name = "quoteRate")
	private double quoteRate;
	
	@Column(name = "responseTime")
	private Date responseTime;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	public Long getRequestId() {
		return requestId;
	}

	public void setRequestId(Long requestId) {
		this.requestId = requestId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public int getTerm() {
		return term;
	}

	public void setTerm(int term) {
		this.term = term;
	}

	public double getQuoteRate() {
		return quoteRate;
	}

	public void setQuoteRate(double quoteRate) {
		this.quoteRate = quoteRate;
	}

	public Date getResponseTime() {
		return responseTime;
	}

	public void setResponseTime(Date responseTime) {
		this.responseTime = responseTime;
	}
	
	
	
	
}