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
    private Date date;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "event")
    private Set<Location> locations;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "event")
    private Set<Audio> audios;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "event")
    private Set<Catering> caterings;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "event")
    private Set<Light> lights;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "event")
    private Set<Rigging> riggings;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "event")
    private Set<Security> securities;
    
    public Event(){
    	locations = new HashSet<Location>();
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
    
    // LOCATIONS
    protected void setLocationsInternal(Set<Location> locations) {
        this.locations = locations;
    }

    protected Set<Location> getLocationsInternal() {
        if (this.locations == null) {
            this.locations = new HashSet<Location>();
        }
        return this.locations;
    }

    public List<Location> getLocations() {
        List<Location> sortedLocations = new ArrayList<Location>(getLocationsInternal());
        PropertyComparator.sort(sortedLocations, new MutableSortDefinition("name", true, true));
        return Collections.unmodifiableList(sortedLocations);
    }

    public void addLocation(Location location) {
    	getLocationsInternal().add(location);
    }

    public Location getLocation(String name) {
        return getLocation(name, false);
    }

    public Location getLocation(String name, boolean ignoreNew) {
        name = name.toLowerCase();
        for (Location location : getLocationsInternal()) {
            if (!ignoreNew || !location.isNew()) {
                String compName = location.getName();
                compName = compName.toLowerCase();
                if (compName.equals(name)) {
                    return location;
                }
            }
        }
        return null;
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
    
    // CATERINGS
    protected void setCateringsInternal(Set<Catering> caterings) {
        this.caterings = caterings;
    }

    protected Set<Catering> getCateringsInternal() {
        if (this.caterings == null) {
            this.caterings = new HashSet<Catering>();
        }
        return this.caterings;
    }

    public List<Catering> getCaterings() {
        List<Catering> sortedCaterings = new ArrayList<Catering>(getCateringsInternal());
        PropertyComparator.sort(sortedCaterings, new MutableSortDefinition("name", true, true));
        return Collections.unmodifiableList(sortedCaterings);
    }

    public void addCatering(Catering catering) {
    	getCateringsInternal().add(catering);
    }

    public Catering getCatering(String name) {
        return getCatering(name, false);
    }

    public Catering getCatering(String name, boolean ignoreNew) {
        name = name.toLowerCase();
        for (Catering catering : getCateringsInternal()) {
            if (!ignoreNew || !catering.isNew()) {
                String compName = catering.getName();
                compName = compName.toLowerCase();
                if (compName.equals(name)) {
                    return catering;
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
    
    @Override
    public String toString() {
        return new ToStringCreator(this)

                .append("id", this.getId())
                .append("new", this.isNew())
                .append("date", this.date)
                .toString();
    }
}
