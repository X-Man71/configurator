package de.hs.furtwangen.bam.jee.configurator.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "products")
public class Product extends BaseEntity implements Serializable {	

	private static final long serialVersionUID = -6067549695351920068L;

	@NotNull(message = "{error.product.productname.null}")
	@NotEmpty(message = "{error.product.productname.empty}")
	@Size(max = 50, message = "{error.product.productname.max}")
	@Column(name = "productname", length = 50)
	private String productname;
	
	/**
	 * productname and size should be together unique
	 * size can be small, medium or 0.5l, 0.33l
	 */
	@NotNull(message = "{error.product.size.null}")
	@NotEmpty(message = "{error.product.size.empty}")
	@Size(max = 50, message = "{error.product.size.max}")
	@Column(name = "size", length = 50)
	private String size;
	
	@NotNull(message = "{error.product.price.null}")
	@Digits(message = "{error.product.price.faction}", integer=5, fraction=2)
	@Column(name = "price")
	private BigDecimal price;
	
	@Column(name = "available", length = 50)
	private boolean available;	
	
	/**
	 * Types are Eating and Drinking
	 * This variable deciedes if we send the Product to Cook or Barkeeper
	 */
	@ManyToOne(fetch=FetchType.EAGER,cascade = {CascadeType.ALL})
	@JoinColumn(name="producttype_id")
	private ProductType productType;	


	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}
	
	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public ProductType getProductType() {
		return productType;
	}

	public void setProductType(ProductType productType) {
		this.productType = productType;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}
	
	
	
}
