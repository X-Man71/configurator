package de.hs.furtwangen.bam.jee.configurator.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity(name = "users")
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
	
	@Column(name = "corporation")
	private String corporation;
	
	@Column(name = "gender")
	private String gender;
	
	@Column(name = "residence")
	private String residence;
	
	@Column(name = "street")
	private String street;
	
	@Column(name = "zipCode")
	private String zipCode;
	
	@Column(name = "phone")
	private String phone;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_authority", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "authority_id"))
	private List<Authority> authoritiesList = new ArrayList<Authority>();

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

	public String getCorporation() {
		return corporation;
	}

	public void setCorporation(String corporation) {
		this.corporation = corporation;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getResidence() {
		return residence;
	}

	public void setResidence(String residence) {
		this.residence = residence;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public List<Authority> getAuthoritiesList() {
		return authoritiesList;
	}
	
	public void add(Authority authority){
		authoritiesList.add(authority);
	}

	public void setAuthoritiesList(List<Authority> authoritiesList) {
		this.authoritiesList = authoritiesList;
	}

	@Override
	public String toString() {
		return "User [firstName=" + firstName + ", lastName=" + lastName
				+ ", username=" + username + ", password=" + password
				+ ", email=" + email + ", corporation=" + corporation
				+ ", gender=" + gender + ", residence=" + residence
				+ ", street=" + street + ", zipCode=" + zipCode + ", phone="
				+ phone + ", authoritiesList=" + authoritiesList + "]";
	}
	
	

	
	
}
