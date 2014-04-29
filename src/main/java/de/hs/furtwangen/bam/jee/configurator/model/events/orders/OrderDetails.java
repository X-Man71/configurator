package de.hs.furtwangen.bam.jee.configurator.model.events.orders;

import java.util.Collections;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class OrderDetails {

  private UUID key;
  private Date dateTimeOfSubmission;
  private Map<String, Integer> orderItems;
  
  @NotNull
	@NotEmpty
	private String name;
	
	@NotNull
	@NotEmpty
	private String prename;
	
	@NotNull
	@NotEmpty
	private String streetHouseNumber;
	
	@NotNull
	@NotEmpty
	private String town;

	@NotNull
	@NotEmpty
	private String postcode;
	
	@NotNull
	@NotEmpty
	private String mailAdress;
  

  public OrderDetails() {
    key = null;
  }

  public OrderDetails(UUID key) {
    this.key = key;
  }

  public Date getDateTimeOfSubmission() {
    return this.dateTimeOfSubmission;
  }

  public void setDateTimeOfSubmission(Date dateTimeOfSubmission) {
    this.dateTimeOfSubmission = dateTimeOfSubmission;
  }

  public Map<String, Integer> getOrderItems() {
    return orderItems;
  }

  public void setOrderItems(Map<String, Integer> orderItems) {
    if (orderItems == null) {
      this.orderItems = Collections.emptyMap();
    } else {
      this.orderItems = Collections.unmodifiableMap(orderItems);
    }
  }

  public UUID getKey() {
    return key;
  }

  public void setKey(UUID key) {
    this.key = key;
  }

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getPrename() {
	return prename;
}

public void setPrename(String prename) {
	this.prename = prename;
}

public String getStreetHouseNumber() {
	return streetHouseNumber;
}

public void setStreetHouseNumber(String streetHouseNumber) {
	this.streetHouseNumber = streetHouseNumber;
}

public String getTown() {
	return town;
}

public void setTown(String town) {
	this.town = town;
}

public String getPostcode() {
	return postcode;
}

public void setPostcode(String postcode) {
	this.postcode = postcode;
}

public String getMailAdress() {
	return mailAdress;
}

public void setMailAdress(String mailAdress) {
	this.mailAdress = mailAdress;
}

@Override
public String toString() {
	return "OrderDetails [key=" + key + ", dateTimeOfSubmission="
			+ dateTimeOfSubmission + ", orderItems=" + orderItems + ", name="
			+ name + ", prename=" + prename + ", streetHouseNumber="
			+ streetHouseNumber + ", town=" + town + ", postcode=" + postcode
			+ ", mailAdress=" + mailAdress + "]";
}
  
  

}