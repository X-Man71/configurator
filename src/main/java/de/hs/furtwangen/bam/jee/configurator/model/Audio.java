package de.hs.furtwangen.bam.jee.configurator.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

/**
 * Audio entity.
 *
 * @author Oliver Rövekamp
 */
@Entity
public class Audio implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name = "audioId")
    private Integer id;
	
	@Column(name = "name")
	private String name;
	
	@OneToOne
	@PrimaryKeyJoinColumn
    private Event event;
    
    public void setEvent(Event event) {
    	this.event = event;
    }

    public Event getEvent() {
        return this.event;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}