package de.hs.furtwangen.bam.jee.configurator.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Transient;

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
    
	@Column(name = "lightsNeeded")
	@Basic(optional = false)
	private boolean lightsNeeded;
	
	@Column(name = "lightType")
	private String lightType;
	
	@Transient
	public List<String> lightTypeSelection;
	
	public Light(){
		lightTypeSelection = new ArrayList<>();
		lightTypeSelection.add("Bühnenbeleuchtung");
		lightTypeSelection.add("Raumbeleuchtung");
		lightTypeSelection.add("Beides");
		
	}
	
	@Column(name = "roomSize" )
    private Integer roomSize;
	
	@Column(name = "stageSize" )
    private Integer stageSize;

	public boolean isLightsNeeded() {
		return lightsNeeded;
	}

	public void setLightsNeeded(boolean lightsNeeded) {
		this.lightsNeeded = lightsNeeded;
	}
	
	public void setEvent(Event event) {
        this.event = event;
    }

    public Event getEvent() {
        return this.event;
    }
    
    

	public String getLightType() {
		return lightType;
	}

	public void setLightType(String lightType) {
		this.lightType = lightType;
	}

	public List<String> getLightTypeSelection() {
		return lightTypeSelection;
	}

	public void setLightTypeSelection(List<String> lightTypeSelection) {
		this.lightTypeSelection = lightTypeSelection;
	}

	public Integer getRoomSize() {
		return roomSize;
	}

	public void setRoomSize(Integer roomSize) {
		this.roomSize = roomSize;
	}

	public Integer getStageSize() {
		return stageSize;
	}

	public void setStageSize(Integer stageSize) {
		this.stageSize = stageSize;
	}
	
    
    

}