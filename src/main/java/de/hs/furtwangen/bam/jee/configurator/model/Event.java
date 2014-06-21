package de.hs.furtwangen.bam.jee.configurator.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.core.style.ToStringCreator;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * Event entity.
 *
 * @author Oliver Rövekamp
 */
@Entity
@Table(name = "events")
public class Event extends BaseEntity implements Serializable {

	private static final long serialVersionUID = -8602561360329162570L;

	@Column(name = "name")
	private String name;

	@Column(name = "date")
	@NotEmpty
	@DateTimeFormat(pattern = "dd.MM.YYYY")
	private Date date;

	@OneToOne
	@JoinColumn(name="locationId")
	private Location location;

	@OneToOne
	@JoinColumn(name="cateringId")
	private Catering catering;

	@OneToOne
	@JoinColumn(name="audioId")
	private Audio audio;

	@OneToOne
	@JoinColumn(name="lightId")
	private Light light;

	@OneToOne
	@JoinColumn(name="riggingId")
	private Rigging rigging;

	@OneToOne
	@JoinColumn(name="securityId")
	private Security security;

	@OneToOne
	@JoinColumn(name="specialtyId")
	private Specialty specialty;

	public Event() {
		this.location = new Location();
		this.catering = new Catering();
		this.audio = new Audio();
		this.light = new Light();
		this.security = new Security();
		this.rigging = new Rigging();
		this.specialty = new Specialty();
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Catering getCatering() {
		return catering;
	}

	public void setCatering(Catering catering) {
		this.catering = catering;
	}

	public Audio getAudio() {
		return audio;
	}

	public void setAudio(Audio audio) {
		this.audio = audio;
	}

	public Light getLight() {
		return light;
	}

	public void setLight(Light light) {
		this.light = light;
	}

	public Rigging getRigging() {
		return rigging;
	}

	public void setRigging(Rigging rigging) {
		this.rigging = rigging;
	}

	public Security getSecurity() {
		return security;
	}

	public void setSecurity(Security security) {
		this.security = security;
	}

	public Specialty getSpecialty() {
		return specialty;
	}

	public void setSpecialty(Specialty specialty) {
		this.specialty = specialty;
	}

	@Override
	public String toString() {
		return new ToStringCreator(this)

		.append("id", this.getId()).append("new", this.isNew())
				.append("date", this.date).toString();
	}
}
