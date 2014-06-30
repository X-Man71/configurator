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
 * Rigging entity.
 *
 * @author Oliver Rövekamp
 */
@Entity
public class Rigging implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "riggingId")
    private Integer id;
	
	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
    private Event event;
	
	@Column(name = "riggingNeeded")
	@Basic(optional = false)
	private boolean riggingNeeded;

	@Column(name = "riggingType")
	private String riggingType;
	
	@Transient
	public List<String> riggingTypeSelection;
	
	public Rigging(){
		riggingTypeSelection = new ArrayList<>();
		riggingTypeSelection.add("mobile Bühne");
		riggingTypeSelection.add("Front-Truss");
		riggingTypeSelection.add("Back-Truss");
		riggingTypeSelection.add("Front- & Back-Truss");
		riggingTypeSelection.add("Ground-Support");		
	}
	
	@Column(name = "stageSize")
	private String stageSize;
	
	public void setEvent(Event event) {
        this.event = event;
    }

    public boolean isRiggingNeeded() {
		return riggingNeeded;
	}

	public void setRiggingNeeded(boolean riggingNeeded) {
		this.riggingNeeded = riggingNeeded;
	}

	public Event getEvent() {
        return this.event;
    }

	public String getRiggingType() {
		return riggingType;
	}

	public void setRiggingType(String riggingType) {
		this.riggingType = riggingType;
	}

	public List<String> getRiggingTypeSelection() {
		return riggingTypeSelection;
	}

	public void setRiggingTypeSelection(List<String> riggingTypeSelection) {
		this.riggingTypeSelection = riggingTypeSelection;
	}

	public String getStageSize() {
		return stageSize;
	}

	public void setStageSize(String stageSize) {
		this.stageSize = stageSize;
	}

}