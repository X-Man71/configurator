package de.hs.furtwangen.bam.jee.configurator.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import de.hs.furtwangen.bam.jee.configurator.model.Product;
import de.hs.furtwangen.bam.jee.configurator.model.ProductType;
import de.hs.furtwangen.bam.jee.configurator.service.ProductManagementService;

@Controller
@RequestMapping(value = "/management/product")
public class ProductManagementController {

	@Autowired
	private ProductManagementService productManagementService;

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addUserPage(Model model) {		
		model.addAttribute("pageHeader", "management.product.form.add.pageHeader");
		Product product = new Product();
		product.setProductType(new ProductType());
		
		model.addAttribute("product", product);
		model.addAttribute("productTypeOptions", productManagementService.findAllProductType());
		model.addAttribute("availableOptions", getBooleanArray());

		return "/management/product/form";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addUser(@Valid @ModelAttribute("product") Product product,
			BindingResult bindingResult, RedirectAttributes redirectAttributes,
			Model model) {

		return "/management/product/form";
	}
	
	public boolean[] getBooleanArray()
	{
		boolean[] array = new boolean[2];
		array[0] = true;
		array[1] = false;
		return array;
	}
}
