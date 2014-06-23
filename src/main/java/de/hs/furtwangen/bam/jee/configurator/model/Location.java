package de.hs.furtwangen.bam.jee.configurator.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

/**
 * Location entity.
 * 
 * @author Oliver Rövekamp
 */
@Entity
public class Location implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "locationId")
    private Integer id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "street")
	private String street;
	
	@Column(name = "town")
	private String town;
	
	@Column(name = "type")
	private String type;

	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	private Event event;
	
	public Location() {
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public Event getEvent() {
		return this.event;
	}

}
