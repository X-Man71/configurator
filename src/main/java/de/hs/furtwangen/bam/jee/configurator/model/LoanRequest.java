package de.hs.furtwangen.bam.jee.configurator.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name = "loanRequests")
@Table(name = "loanrequests")
public class LoanRequest extends BaseEntity {

	@Column(name = "requestid")
	private String requestId = UUID.randomUUID().toString();

	@Column(name = "creditvalue")
	private double creditValue;

	@Column(name = "duration")
	private int duration;

	@Column(name = "ssn")
	private String SSN;

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public double getCreditValue() {
		return creditValue;
	}

	public void setCreditValue(double creditValue) {
		this.creditValue = creditValue;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getSSN() {
		return SSN;
	}

	public void setSSN(String sSN) {
		SSN = sSN;
	}

}
