package org.springframework.samples.petclinic.web.domain;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class CustomerInfo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@NotNull
	@NotEmpty
	private String name;
	
	@NotNull
	@NotEmpty
	private String prename;
	
	@NotNull
	@NotEmpty
	private String streetHouseNumber;
	
	@NotNull
	@NotEmpty
	private String town;

	@NotNull
	@NotEmpty
	private String postcode;
	
	@NotNull
	@NotEmpty
	private String mailAdress;

	

	public String getPrename() {
		return prename;
	}

	public void setPrename(String prename) {
		this.prename = prename;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStreetHouseNumber() {
		return streetHouseNumber;
	}

	public void setStreetHouseNumber(String streetHouseNumber) {
		this.streetHouseNumber = streetHouseNumber;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getMailAdress() {
		return mailAdress;
	}

	public void setMailAdress(String mailAdress) {
		this.mailAdress = mailAdress;
	}

	@Override
	public String toString() {
		return "CustomerInfo [name=" + name + ", prename=" + prename
				+ ", streetHouseNumber=" + streetHouseNumber + ", town=" + town
				+ ", postcode=" + postcode + ", mailAdress=" + mailAdress + "]";
	}
	
	
	

}