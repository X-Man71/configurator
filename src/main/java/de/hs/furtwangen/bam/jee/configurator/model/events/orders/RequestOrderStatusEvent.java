package de.hs.furtwangen.bam.jee.configurator.model.events.orders;

import java.util.UUID;

import de.hs.furtwangen.bam.jee.configurator.events.RequestReadEvent;

public class RequestOrderStatusEvent extends RequestReadEvent {
  private UUID key;

  public RequestOrderStatusEvent(UUID key) {
    this.key = key;
  }

  public UUID getKey() {
    return key;
  }
}
