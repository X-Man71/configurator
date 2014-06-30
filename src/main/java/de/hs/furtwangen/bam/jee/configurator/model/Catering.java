package de.hs.furtwangen.bam.jee.configurator.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

/**
 * Catering entity.
 *
 * @author Oliver Rövekamp
 */
@Entity
public class Catering implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cateringId")
    private Integer id;

	@Column(name = "eating")
	@Basic(optional = false)
	private boolean eating;
	
	@Column(name = "eatingType")
	private String eatingType;
	
	@Column(name = "eatingPeople")
	private Integer eatingPeople;
	
	@Column(name = "drinking")
	@Basic(optional = false)
	private boolean drinking;
	
	@Column(name = "drinkingPeople")
	private Integer drinkingPeople;
	
	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
    private Event event;

	public Integer getEatingPeople() {
		return eatingPeople;
	}

	public void setEatingPeople(Integer eatingPeople) {
		this.eatingPeople = eatingPeople;
	}

	public Integer getDrinkingPeople() {
		return drinkingPeople;
	}

	public void setDrinkingPeople(Integer drinkingPeople) {
		this.drinkingPeople = drinkingPeople;
	}

	public String getEatingType() {
		return eatingType;
	}

	public void setEatingType(String eatingType) {
		this.eatingType = eatingType;
	}

	public boolean isDrinking() {
		return drinking;
	}

	public void setDrinking(boolean drinking) {
		this.drinking = drinking;
	}

	public void setEvent(Event event) {
        this.event = event;
    }

    public Event getEvent() {
        return this.event;
    }

	public boolean isEating() {
		return eating;
	}

	public void setEating(boolean eating) {
		this.eating = eating;
	}

}