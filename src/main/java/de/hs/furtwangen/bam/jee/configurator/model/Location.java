package de.hs.furtwangen.bam.jee.configurator.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Location entity.
 *
 * @author Oliver Rövekamp
 */
@Entity
@Table(name = "locations")
public class Location extends NamedEntity {

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;
    

    protected void setEvent(Event event) {
        this.event = event;
    }

    public Event getEvent() {
        return this.event;
    }

}
