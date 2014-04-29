package de.hs.furtwangen.bam.jee.configurator.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity(name = "authorities")
public class Authorities {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull
	@NotEmpty
	@Column(name = "authoritie", unique = true)
	private String authoritie;

	public String getAuthoritie() {
		return authoritie;
	}

	public void setAuthoritie(String authoritie) {
		this.authoritie = authoritie;
	}
	
	

}
