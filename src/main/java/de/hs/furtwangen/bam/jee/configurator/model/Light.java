package de.hs.furtwangen.bam.jee.configurator.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Light entity.
 *
 * @author Oliver Rövekamp
 */
@Entity
@Table(name = "lights")
public class Light extends BaseEntity implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;
    
	@Column(name="name")
	private String name;
	
	@Column(name="numberOfLights")
	private int numberOfLights;

    public int getNumberOfLights() {
		return numberOfLights;
	}

	public void setNumberOfLights(int numberOfLights) {
		this.numberOfLights = numberOfLights;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	protected void setEvent(Event event) {
        this.event = event;
    }

    public Event getEvent() {
        return this.event;
    }

}