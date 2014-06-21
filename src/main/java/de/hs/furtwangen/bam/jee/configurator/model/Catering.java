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
import javax.persistence.Table;

/**
 * Catering entity.
 *
 * @author Oliver Rövekamp
 */
@Entity
@Table(name = "caterings")
public class Catering implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cateringId")
    private Integer id;

	private boolean eating;
	
	private String eatingType;
	
	private int eatingPeople;
	
	private boolean drinking;
	
	private int drinkingPeople;
	
	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
    private Event event;
	
    public boolean isEating() {
		return eating;
	}

	public void setEating(boolean eating) {
		this.eating = eating;
	}

	public String getEatingType() {
		return eatingType;
	}

	public void setEatingType(String eatingType) {
		this.eatingType = eatingType;
	}

	public int getEatingPeople() {
		return eatingPeople;
	}

	public void setEatingPeople(int eatingPeople) {
		this.eatingPeople = eatingPeople;
	}

	public boolean isDrinking() {
		return drinking;
	}

	public void setDrinking(boolean drinking) {
		this.drinking = drinking;
	}

	public int getDrinkingPeople() {
		return drinkingPeople;
	}

	public void setDrinkingPeople(int drinkingPeople) {
		this.drinkingPeople = drinkingPeople;
	}

	protected void setEvent(Event event) {
        this.event = event;
    }

    public Event getEvent() {
        return this.event;
    }

}