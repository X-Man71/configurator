package de.hs.furtwangen.bam.jee.configurator.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.samples.petclinic.web.domain.MenuItem;
import org.springframework.stereotype.Service;
@Service
public class MenuServiceHandler {
	
	public List<MenuItem> getAllMenuItem()
	{
		List<MenuItem> listMenuItems = new ArrayList<MenuItem>();
		MenuItem menuItem1 = new MenuItem();
		menuItem1.setId("1");
		menuItem1.setName("Produkt1");
		listMenuItems.add(menuItem1);
		
		MenuItem menuItem2 = new MenuItem();
		menuItem2.setId("1");
		menuItem2.setName("Produkt2");
		listMenuItems.add(menuItem2);
		return listMenuItems;
	}

}
