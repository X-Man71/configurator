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
 * Light entity.
 *
 * @author Oliver Rövekamp
 */
@Entity
public class Light implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "lightId")
    private Integer id;
	
	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
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

	public void setEvent(Event event) {
        this.event = event;
    }

    public Event getEvent() {
        return this.event;
    }

}