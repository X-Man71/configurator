package de.hs.furtwangen.bam.jee.configurator.events.menu;

import de.hs.furtwangen.bam.jee.configurator.events.RequestReadEvent;

public class RequestMenuItemDetailsEvent extends RequestReadEvent {
  private String id;

  public RequestMenuItemDetailsEvent(String id) {
    this.id = id;
  }

  public String getId() {
    return id;
  }
}
