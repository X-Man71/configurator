package de.hs.furtwangen.bam.jee.configurator.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;

@Entity
@Table(name = "loans")
public class Loan extends NamedEntity {

	@Column(name = "status")
	private String status;
	
	@Column(name = "rate")
	private double rate;
	
	@Column(name = "requestNo")
	private int requestNo;
	
	@Column(name = "quoteNo")
	private int quoteNo;
	
	@Autowired
	@ManyToOne(fetch=FetchType.LAZY)
    // @JoinColumn("bank_id") not working in spring
	private Bank bank;
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public int getRequestNo() {
		return requestNo;
	}

	public void setRequestNo(int requestNo) {
		this.requestNo = requestNo;
	}

	public int getQuoteNo() {
		return quoteNo;
	}

	public void setQuoteNo(int quoteNo) {
		this.quoteNo = quoteNo;
	}

	public Bank getBank() {
		return bank;
	}

	public void setBank(Bank bank) {
		this.bank = bank;
	}
	
}
