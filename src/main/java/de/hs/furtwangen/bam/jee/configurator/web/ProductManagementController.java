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

import de.hs.furtwangen.bam.jee.configurator.Exception.DuplicateException;
import de.hs.furtwangen.bam.jee.configurator.model.Product;
import de.hs.furtwangen.bam.jee.configurator.model.ProductType;
import de.hs.furtwangen.bam.jee.configurator.service.ProductManagementService;
import de.hs.furtwangen.bam.jee.configurator.util.BooleanArray;

@Controller
@RequestMapping(value = "/management/product")
public class ProductManagementController {

	@Autowired
	private ProductManagementService productManagementService;
	
	
	@RequestMapping(value = "/table", method = RequestMethod.GET)
	public String tableProduct(Model model) {
		model.addAttribute("pageHeader", "management.product.table.pageHeader");	
		
		model.addAttribute("products", productManagementService.findAllProduct().getContent());

		return "/management/product/table";
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addProductPage(Model model) {		
		model.addAttribute("pageHeader", "management.product.form.add.pageHeader");
		Product product = new Product();
		product.setProductType(new ProductType());
		
		model.addAttribute("product", product);
		model.addAttribute("productTypeOptions", productManagementService.findAllProductType());
		model.addAttribute("availableOptions", BooleanArray.getBooleanArray());

		return "/management/product/form";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addProduct(@Valid @ModelAttribute("product") Product product,
			BindingResult bindingResult, RedirectAttributes redirectAttributes,
			Model model) {
		model.addAttribute("pageHeader", "management.product.form.add.pageHeader");
		model.addAttribute("product", product);
		model.addAttribute("productTypeOptions", productManagementService.findAllProductType());
		model.addAttribute("availableOptions", BooleanArray.getBooleanArray());		
		
		if (bindingResult.hasErrors()) {
			return "/management/product/form";
		}
		
		try {
			productManagementService.add(product);
		} catch (DuplicateException e) {
			model.addAttribute("formError", "management.product.form.add.duplicateException");
			return "/management/product/form";
		}
		
		model.addAttribute("formSuccessful", "management.product.form.add.formSuccessful");

		return "/management/product/form";
	}
	
	@RequestMapping(value = "/edit",method = RequestMethod.GET)
	public String editProductPage(Model model)
	{
		//TODO
		return "management/product/edit";
	}
	
	@RequestMapping(value = "/edit",method = RequestMethod.POST)
	public String editProduct(@Valid @ModelAttribute("product") Product product,
			BindingResult bindingResult, RedirectAttributes redirectAttributes,
			Model model)
	{
		//TODO
		return "management/product/edit";
	}
	
	
}
