package de.hs.furtwangen.bam.jee.configurator.model.events.orders;

import java.util.UUID;

import de.hs.furtwangen.bam.jee.configurator.events.RequestReadEvent;

public class RequestOrderDetailsEvent extends RequestReadEvent {
  private UUID key;

  public RequestOrderDetailsEvent(UUID key) {
    this.key = key;
  }

  public UUID getKey() {
    return key;
  }
}
