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
	
	@Column(name = "name")
	private String name;

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
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
