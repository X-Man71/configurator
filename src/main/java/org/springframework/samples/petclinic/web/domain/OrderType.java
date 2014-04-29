package org.springframework.samples.petclinic.web.domain;



public enum OrderType {
	Bestellung("Bestellung"),
	Informationen("Informationen"),
	Angebot("Angebot");
	
	private final String name;
	
	 private OrderType(final String name) {
	        this.name = name;
	    }
  
    
    public String getName() {
		return name;
	}


	public static final OrderType[] ALL = { Bestellung,
		Informationen,
		Angebot};
}
