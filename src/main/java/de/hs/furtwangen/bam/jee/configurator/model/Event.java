package de.hs.furtwangen.bam.jee.configurator.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * Event entity.
 *
 * @author Oliver Rövekamp
 */
@Entity
public class Event implements Serializable {

	private static final long serialVersionUID = -8602561360329162570L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    protected Integer id;
	
	@Column(name = "name")
	private String name;

	@Column(name = "date")
	@DateTimeFormat(pattern = "dd.MM.YYYY")
	private Date date;

	@OneToOne(cascade = CascadeType.ALL)
	private Location location;

	@OneToOne(cascade = CascadeType.ALL)
	private Catering catering;

	@OneToOne(cascade = CascadeType.ALL)
	private Audio audio;

	@OneToOne(cascade = CascadeType.ALL)
	private Light light;

	@OneToOne(cascade = CascadeType.ALL)
	private Rigging rigging;

	@OneToOne(cascade = CascadeType.ALL)
	private Security security;

	@OneToOne(cascade = CascadeType.ALL)
	private Specialty specialty;
	
	@ManyToOne
    @JoinColumn(name = "user_userId")
    private User user;

	public Event() {
		this.location = new Location();
		this.catering = new Catering();
		this.audio = new Audio();
		this.light = new Light();
		this.security = new Security();
		this.rigging = new Rigging();
		this.specialty = new Specialty();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
		System.out.println("Location "+location.getName());
		this.location = location;
	}

	public Catering getCatering() {
		return catering;
	}

	public void setCatering(Catering catering) {
		System.out.println("Catering "+catering.getEatingType());
		this.catering = catering;
	}

	public Audio getAudio() {
		return audio;
	}

	public void setAudio(Audio audio) {
		System.out.println("Audio "+audio.getName());
		audio.setEvent(this);
		this.audio = audio;
	}

	public Light getLight() {
		return light;
	}

	public void setLight(Light light) {
		System.out.println("Lights "+light.getName());
		this.light = light;
	}

	public Rigging getRigging() {
		return rigging;
	}

	public void setRigging(Rigging rigging) {
		System.out.println("Rigging "+rigging.getName());
		this.rigging = rigging;
	}

	public Security getSecurity() {
		return security;
	}

	public void setSecurity(Security security) {
		System.out.println("Security "+security.getName());
		this.security = security;
	}

	public Specialty getSpecialty() {
		return specialty;
	}

	public void setSpecialty(Specialty specialty) {
		System.out.println("Specialty "+specialty.getComment()+" "+specialty.getSubject());
		this.specialty = specialty;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
