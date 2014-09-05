package de.hs.furtwangen.bam.jee.configurator.web.domain;

import de.hs.furtwangen.bam.jee.configurator.model.Product;

public class ProductOrder extends Product {
	
	private static final long serialVersionUID = 1L;
	
	private Integer amount;
	
	private Long tableId;

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Long getTableId() {
		return tableId;
	}

	public void setTableId(Long tableId) {
		this.tableId = tableId;
	}
	
	
	
	

}
