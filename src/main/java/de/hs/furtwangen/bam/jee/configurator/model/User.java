package de.hs.furtwangen.bam.jee.configurator.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User extends BaseEntity {

	@Column(name = "firstname")
	private String firstName;
	
	@Column(name = "lastname")
	private String lastName;

	@Column(unique = true)
	private String username;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "consumerRateNumber")
	private int consumerRateNumber;
	
	@Column(name = "consumerRateLetter")
	private String consumerRateLetter;
	
	@Column(name = "consumerRateValideUntil")
	private Date consumerRateValideUntil;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_authority", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "authority_id"))
	private List<Authority> authoritiesList = new ArrayList<Authority>();
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.EAGER)
	private Set<Loan> loanSet = new HashSet<>();

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getConsumerRateNumber() {
		return consumerRateNumber;
	}

	public void setConsumerRateNumber(int consumerRateNumber) {
		this.consumerRateNumber = consumerRateNumber;
	}

	public String getConsumerRateLetter() {
		return consumerRateLetter;
	}

	public void setConsumerRateLetter(String consumerRateLetter) {
		this.consumerRateLetter = consumerRateLetter;
	}

	public Date getConsumerRateValideUntil() {
		return consumerRateValideUntil;
	}

	public void setConsumerRateValideUntil(Date consumerRateValideUntil) {
		this.consumerRateValideUntil = consumerRateValideUntil;
	}

	public List<Authority> getAuthoritiesList() {
		return authoritiesList;
	}

	public void setAuthoritiesList(List<Authority> authoritiesList) {
		this.authoritiesList = authoritiesList;
	}

	public Set<Loan> getLoanSet() {
		return loanSet;
	}

	public void setLoanSet(Set<Loan> loanSet) {
		this.loanSet = loanSet;
	}
	
	public void add(Authority authority){
		this.authoritiesList.add(authority);
	}

	}
