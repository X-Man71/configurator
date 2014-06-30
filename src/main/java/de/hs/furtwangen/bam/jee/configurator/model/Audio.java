package de.hs.furtwangen.bam.jee.configurator.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Transient;

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
	
	@OneToOne
	@PrimaryKeyJoinColumn
    private Event event;
	
	@Column(name = "audioNeeded")
	@Basic(optional = false)
	private boolean audioNeeded;
	
	@Column(name = "audioType")
	private String audioType;
	
	@Transient
	public List<String> audioTypeSelection;
	
	public Audio(){
		audioTypeSelection = new ArrayList<>();
		audioTypeSelection.add("Sprachbeschallung");
		audioTypeSelection.add("Disco");
		audioTypeSelection.add("Rock n Roll");
	}
	
	@Column(name = "roomSize")
	private Integer roomSize;
	
	@Column(name = "numberGuests")
	private Integer numberGuests;
    
    public boolean isAudioNeeded() {
		return audioNeeded;
	}

	public void setAudioNeeded(boolean audioNeeded) {
		this.audioNeeded = audioNeeded;
	}

	public void setEvent(Event event) {
    	this.event = event;
    }

    public Event getEvent() {
        return this.event;
    }

	public String getAudioType() {
		return audioType;
	}

	public void setAudioType(String audioType) {
		this.audioType = audioType;
	}

	public Integer getRoomSize() {
		return roomSize;
	}

	public void setRoomSize(Integer roomSize) {
		this.roomSize = roomSize;
	}

	public Integer getNumberGuests() {
		return numberGuests;
	}

	public void setNumberGuests(Integer numberGuests) {
		this.numberGuests = numberGuests;
	}

	public List<String> getAudioTypeSelection() {
		return audioTypeSelection;
	}

	public void setAudioTypeSelection(List<String> audioTypeSelection) {
		this.audioTypeSelection = audioTypeSelection;
	}

	

}