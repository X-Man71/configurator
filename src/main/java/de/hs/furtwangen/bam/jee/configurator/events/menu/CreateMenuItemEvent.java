package de.hs.furtwangen.bam.jee.configurator.events.menu;


import de.hs.furtwangen.bam.jee.configurator.events.CreateEvent;

public class CreateMenuItemEvent extends CreateEvent {

  private MenuItemDetails details;

  public CreateMenuItemEvent(MenuItemDetails details) {
    this.details = details;
  }

  public MenuItemDetails getDetails() {
    return details;
  }
}
