package de.hs.furtwangen.bam.jee.configurator.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "bank")
public class Bank extends BaseEntity {
	
	@Column(name = "bankName")
	private String bankName;
	
	@Column(name = "minTerm")
	private Integer minTerm;
	
	@Column(name = "maxTerm")
	private Integer maxTerm;
	
	@Column(name = "minAmount")
	private Double minAmount;
	
	@Column(name = "maxAmount")
	private Double maxAmount;
	
	@Column(name = "minConsumerRate")
	private Integer minConsumerRate;
	
	@Column(name = "maxConsumerRate")
	private Integer maxConsumerRate;
	
	@Column(name = "bankType")
	private String bankType;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "bank", fetch = FetchType.EAGER)
	private Set<Loan> loanSet = new HashSet<>();

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public Integer getMinTerm() {
		return minTerm;
	}

	public void setMinTerm(Integer minTerm) {
		this.minTerm = minTerm;
	}

	public Integer getMaxTerm() {
		return maxTerm;
	}

	public void setMaxTerm(Integer maxTerm) {
		this.maxTerm = maxTerm;
	}

	public Double getMinAmount() {
		return minAmount;
	}

	public void setMinAmount(Double minAmount) {
		this.minAmount = minAmount;
	}

	public Double getMaxAmount() {
		return maxAmount;
	}

	public void setMaxAmount(Double maxAmount) {
		this.maxAmount = maxAmount;
	}

	public Integer getMinConsumerRate() {
		return minConsumerRate;
	}

	public void setMinConsumerRate(Integer minConsumerRate) {
		this.minConsumerRate = minConsumerRate;
	}

	public Integer getMaxConsumerRate() {
		return maxConsumerRate;
	}

	public void setMaxConsumerRate(Integer maxConsumerRate) {
		this.maxConsumerRate = maxConsumerRate;
	}

	public String getBankType() {
		return bankType;
	}

	public void setBankType(String bankType) {
		this.bankType = bankType;
	}

	public Set<Loan> getLoanSet() {
		return loanSet;
	}

	public void setLoanSet(Set<Loan> loanSet) {
		this.loanSet = loanSet;
	}

		

}
