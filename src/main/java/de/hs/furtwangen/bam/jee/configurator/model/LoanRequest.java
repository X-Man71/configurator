package de.hs.furtwangen.bam.jee.configurator.model;

import java.util.UUID;

public class LoanRequest {
	
	private String requestId;
	
	private Long userId;
	
	private Double amount;
	
	private Integer term;
	
	private String ssn;
	
	public LoanRequest(){
		requestId = UUID.randomUUID().toString();
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Integer getTerm() {
		return term;
	}

	public void setTerm(Integer term) {
		this.term = term;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	@Override
	public String toString() {
		return "LoanRequest [requestId=" + requestId + ", userId=" + userId
				+ ", amount=" + amount + ", term=" + term + ", ssn=" + ssn
				+ "]";
	}
	
	
	
	

}
