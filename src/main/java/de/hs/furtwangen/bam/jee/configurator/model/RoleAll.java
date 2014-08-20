package de.hs.furtwangen.bam.jee.configurator.model;


public enum RoleAll {

		Manager("Manager"), 
		Waiter("Waiter"), 
		Cook("Cook"),
		Barkeeper("Barkeeper");

	    
	    public static final RoleAll[] ALL = { 	Manager, Waiter,Cook,Barkeeper };
	    
	    private RoleAll(final String name) {
	        this.name = name;
	    }
	    
		private String name;
		
}
