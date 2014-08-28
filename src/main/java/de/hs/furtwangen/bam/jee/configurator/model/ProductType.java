package de.hs.furtwangen.bam.jee.configurator.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "productTypes")
public class ProductType extends BaseEntity {
	
	@NotNull(message = "{error.producttype.productTypeName.null}")
	@NotEmpty(message = "{error.producttype.productTypeName.empty}")
	@Size(max = 50, message = "{error.producttype.productTypeName.max}")
	@Column(name = "productTypeName", length = 50)
	private String productTypeName;
	
	@OneToMany(mappedBy="productType")
	private List<Product> productList;

	public String getProductTypeName() {
		return productTypeName;
	}

	public void setProductTypeName(String productTypeName) {
		this.productTypeName = productTypeName;
	}

	public List<Product> getProductList() {
		return productList;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}
	
	

}
