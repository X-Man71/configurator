package de.hs.furtwangen.bam.jee.configurator.model.events.orders;

import java.util.UUID;

import de.hs.furtwangen.bam.jee.configurator.events.UpdatedEvent;

public class OrderUpdatedEvent extends UpdatedEvent {

  private UUID key;
  private OrderDetails orderDetails;

  public OrderUpdatedEvent(UUID key, OrderDetails orderDetails) {
    this.key = key;
    this.orderDetails = orderDetails;
  }

  public OrderUpdatedEvent(UUID key) {
    this.key = key;
  }

  public UUID getKey() {
    return key;
  }

  public OrderDetails getOrderDetails() {
    return orderDetails;
  }

  public static OrderUpdatedEvent notFound(UUID key) {
    OrderUpdatedEvent ev = new OrderUpdatedEvent(key);
    ev.entityFound=false;
    return ev;
  }
}
