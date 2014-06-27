package de.hs.furtwangen.bam.jee.configurator.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity(name = "authority")
public class Authority extends BaseEntity {
	
	@NotNull
	@NotEmpty
	@Column(name = "name", unique = true)
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
