package de.hs.furtwangen.bam.jee.configurator.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Security entity.
 *
 * @author Oliver Rövekamp
 */
@Entity
@Table(name = "securities")
public class Security extends BaseEntity implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;
    
	@Column(name = "name" )
	private String name;
	
	@Column(name = "numberOfProtectees" )
    private int numberOfProtectees;

    protected void setEvent(Event event) {
        this.event = event;
    }

    public Event getEvent() {
        return this.event;
    }

	public int getNumberOfProtectees() {
		return numberOfProtectees;
	}

	public void setNumberOfProtectees(int numberOfProtectees) {
		this.numberOfProtectees = numberOfProtectees;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}