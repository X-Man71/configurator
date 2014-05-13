package de.hs.furtwangen.bam.jee.configurator.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "banks")
public class Bank extends NamedEntity {

	@Column(name = "numberOfLoans")
	private int numberOfLoans;
	
	// the mappedBy attribute might have to be set to the table name, i.e. bankS in order to work - not sure though
	@OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL, mappedBy="bank")
    public List<Loan> loans;

	public int getNumberOfLoans() {
		return numberOfLoans;
	}

	public void setNumberOfLoans(int numberOfLoans) {
		this.numberOfLoans = numberOfLoans;
	}
	
	public List<Loan> getLoans() {
		return loans;
	}
	
	public void setLoans(List<Loan> loans) {
		this.loans = loans;
	}
	
}
