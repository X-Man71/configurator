package de.hs.furtwangen.bam.jee.configurator.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Location entity.
 * 
 * @author Oliver Rövekamp
 */
@Entity
@Table(name = "locations")
public class Location extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	
	public Location() {
		super();
		System.out.println("Constructor Location");
	}

	@Column(name = "name")
	private String name;
	
	private String street;
	
	private String town;
	
	private String type;

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

	@ManyToOne
	@JoinColumn(name = "event_id")
	private Event event;

	protected void setEvent(Event event) {
		this.event = event;
	}

	public Event getEvent() {
		return this.event;
	}

}
