package de.hs.furtwangen.bam.jee.configurator.model.events.orders;

import de.hs.furtwangen.bam.jee.configurator.events.CreateEvent;
/**
 * {@link CreateOrderEvent} encapsulate the {@link OrderDetails} Class. 
 * @author Chris
 *
 */
public class CreateOrderEvent extends CreateEvent {
  private OrderDetails details;

  public CreateOrderEvent(OrderDetails details) {
    this.details = details;
  }

  public OrderDetails getDetails() {
    return details;
  }
}
