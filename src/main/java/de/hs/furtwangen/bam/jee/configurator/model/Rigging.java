package de.hs.furtwangen.bam.jee.configurator.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Rigging entity.
 *
 * @author Oliver Rövekamp
 */
@Entity
@Table(name = "riggings")
public class Rigging extends NamedEntity {

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