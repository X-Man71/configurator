package de.hs.furtwangen.bam.jee.configurator.events.menu;


import java.util.List;

import de.hs.furtwangen.bam.jee.configurator.events.ReadEvent;

public class AllMenuItemsEvent extends ReadEvent {
  private List<MenuItemDetails> menuItemDetails;

  public AllMenuItemsEvent(List<MenuItemDetails> menuItemDetails) {
    this.menuItemDetails = menuItemDetails;
  }

  public List<MenuItemDetails> getMenuItemDetails() {
    return menuItemDetails;
  }
}
