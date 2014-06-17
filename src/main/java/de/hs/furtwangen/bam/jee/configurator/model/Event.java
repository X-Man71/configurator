package de.hs.furtwangen.bam.jee.configurator.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;
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
    @DateTimeFormat (pattern="dd.MM.YYYY")
    private Date date;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "event")
    private List<Location> locations;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "event")
    private List<Catering> caterings;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "event")
    private Set<Audio> audios;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "event")
    private Set<Light> lights;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "event")
    private Set<Rigging> riggings;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "event")
    private Set<Security> securities;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "event")
    private Set<Specialty> specialties;
    
    public Event(){
    	locations = new ArrayList<Location>();
    	caterings = new ArrayList<Catering>();
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
    
    public void addLocation(Location location) {
    	locations.add(location);
    }

    public List<Location> getLocations() {
		return locations;
	}

    public void setLocations(List<Location> locations) {
		this.locations = locations;
	}
    
    public void addCatering(Catering catering) {
    	caterings.add(catering);
    }


	public List<Catering> getCaterings() {
		return caterings;
	}


	public void setCaterings(List<Catering> caterings) {
		this.caterings = caterings;
	}


	// AUDIOS
    protected void setAudiosInternal(Set<Audio> audios) {
        this.audios = audios;
    }

    protected Set<Audio> getAudiosInternal() {
        if (this.audios == null) {
            this.audios = new HashSet<Audio>();
        }
        return this.audios;
    }

    public List<Audio> getAudios() {
        List<Audio> sortedAudios = new ArrayList<Audio>(getAudiosInternal());
        PropertyComparator.sort(sortedAudios, new MutableSortDefinition("name", true, true));
        return Collections.unmodifiableList(sortedAudios);
    }

    public void addAudio(Audio audio) {
    	getAudiosInternal().add(audio);
    }

    public Audio getAudio(String name) {
        return getAudio(name, false);
    }

    public Audio getAudio(String name, boolean ignoreNew) {
        name = name.toLowerCase();
        for (Audio audio : getAudiosInternal()) {
            if (!ignoreNew || !audio.isNew()) {
                String compName = audio.getName();
                compName = compName.toLowerCase();
                if (compName.equals(name)) {
                    return audio;
                }
            }
        }
        return null;
    }
    
  

    // LIGHTS
    protected void setLightsInternal(Set<Light> lights) {
        this.lights = lights;
    }

    protected Set<Light> getLightsInternal() {
        if (this.lights == null) {
            this.lights = new HashSet<Light>();
        }
        return this.lights;
    }

    public List<Light> getLights() {
        List<Light> sortedLights = new ArrayList<Light>(getLightsInternal());
        PropertyComparator.sort(sortedLights, new MutableSortDefinition("name", true, true));
        return Collections.unmodifiableList(sortedLights);
    }

    public void addLight(Light light) {
    	getLightsInternal().add(light);
    }

    public Light getLight(String name) {
        return getLight(name, false);
    }

    public Light getLight(String name, boolean ignoreNew) {
        name = name.toLowerCase();
        for (Light light : getLightsInternal()) {
            if (!ignoreNew || !light.isNew()) {
                String compName = light.getName();
                compName = compName.toLowerCase();
                if (compName.equals(name)) {
                    return light;
                }
            }
        }
        return null;
    }
    
    // RIGGINGS
    protected void setRiggingsInternal(Set<Rigging> riggings) {
        this.riggings = riggings;
    }

    protected Set<Rigging> getRiggingsInternal() {
        if (this.riggings == null) {
            this.riggings = new HashSet<Rigging>();
        }
        return this.riggings;
    }

    public List<Rigging> getRiggings() {
        List<Rigging> sortedRiggings = new ArrayList<Rigging>(getRiggingsInternal());
        PropertyComparator.sort(sortedRiggings, new MutableSortDefinition("name", true, true));
        return Collections.unmodifiableList(sortedRiggings);
    }

    public void addRigging(Rigging rigging) {
    	getRiggingsInternal().add(rigging);
    }

    public Rigging getRigging(String name) {
        return getRigging(name, false);
    }

    public Rigging getRigging(String name, boolean ignoreNew) {
        name = name.toLowerCase();
        for (Rigging rigging : getRiggingsInternal()) {
            if (!ignoreNew || !rigging.isNew()) {
                String compName = rigging.getName();
                compName = compName.toLowerCase();
                if (compName.equals(name)) {
                    return rigging;
                }
            }
        }
        return null;
    }
    
    // SECURITIES
    protected void setSecuritiesInternal(Set<Security> securities) {
        this.securities = securities;
    }

    protected Set<Security> getSecuritiesInternal() {
        if (this.securities == null) {
            this.securities = new HashSet<Security>();
        }
        return this.securities;
    }

    public List<Security> getSecurities() {
        List<Security> sortedSecurities = new ArrayList<Security>(getSecuritiesInternal());
        PropertyComparator.sort(sortedSecurities, new MutableSortDefinition("name", true, true));
        return Collections.unmodifiableList(sortedSecurities);
    }

    public void addSecurity(Security security) {
    	getSecuritiesInternal().add(security);
    }

    public Security getSecurity(String name) {
        return getSecurity(name, false);
    }

    public Security getSecurity(String name, boolean ignoreNew) {
        name = name.toLowerCase();
        for (Security security : getSecuritiesInternal()) {
            if (!ignoreNew || !security.isNew()) {
                String compName = security.getName();
                compName = compName.toLowerCase();
                if (compName.equals(name)) {
                    return security;
                }
            }
        }
        return null;
    }
    
    public void addSpecialty(Specialty specialty) {
    	getSpecialtiesInternal().add(specialty);
    }

    protected void setSpecialtiesInternal(Set<Specialty> specialties) {
        this.specialties = specialties;
    }

    protected Set<Specialty> getSpecialtiesInternal() {
        if (this.specialties == null) {
            this.specialties = new HashSet<Specialty>();
        }
        return this.specialties;
    }

    public List<Specialty> getSpecialties() {
        List<Specialty> sortedSpecialties = new ArrayList<Specialty>(getSpecialtiesInternal());
        PropertyComparator.sort(sortedSpecialties, new MutableSortDefinition("name", true, true));
        return Collections.unmodifiableList(sortedSpecialties);
    }
    
    @Override
    public String toString() {
        return new ToStringCreator(this)

                .append("id", this.getId())
                .append("new", this.isNew())
                .append("date", this.date)
                .toString();
    }
}
