package de.hs.furtwangen.bam.jee.configurator.web;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import de.hs.furtwangen.bam.jee.configurator.model.Product;
import de.hs.furtwangen.bam.jee.configurator.service.ServeService;
import de.hs.furtwangen.bam.jee.configurator.util.BooleanArray;
import de.hs.furtwangen.bam.jee.configurator.web.domain.ProductAmountAndComment;
import de.hs.furtwangen.bam.jee.configurator.web.domain.ProductOrder;

@Controller
@RequestMapping(value = "/serve/add")
public class ServeAddController {
	
	@Autowired
	private ServeService serveService;
	
	@RequestMapping(value = "/chooseTable", method = RequestMethod.GET)
	public String chooseTablePage(Model model){
		model.addAttribute("pageHeader", "serve.add.chooseTable.pageHeader");
		model.addAttribute("tableCustomers", serveService.findAllTableCustomer());
		
		return "serve/add/chooseTable";
	}
	
	@RequestMapping(value = "/chooseTable/{tableId}",method = RequestMethod.GET)
	public String chooseProductPage(@PathVariable Long tableId, Model model){
		model.addAttribute("pageHeader", "serve.add.chooseProduct.pageHeader");
		List<ProductOrder> productOrderList = new ArrayList<>();
		
		for(Product product :serveService.findAllProduct())
		{
			ProductOrder productOrder = new ProductOrder();
			productOrder.setId(product.getId());
			productOrder.setProductname(product.getProductname());
			productOrder.setPrice(product.getPrice());
			productOrder.setSize(product.getSize());
			productOrder.setTableId(tableId);
			productOrder.setAmount(1);
			productOrderList.add(productOrder);
		}
		model.addAttribute("products", productOrderList);
		model.addAttribute("tableId", tableId);
		
		return "serve/add/chooseProduct"; 
	}
	
	@RequestMapping(value = "/chooseTable/{tableId}/chooseProduct/{productId}",method = RequestMethod.GET)
	public String saveProductforTablePage(@PathVariable Long tableId,@PathVariable Long productId, Model model){
		
		System.out.println(tableId+" "+productId);
		model.addAttribute("pageHeader", "serve.add.chooseAmountAndComment.pageHeader");
		
		model.addAttribute("product", serveService.findOneProduct(productId));
		model.addAttribute("tableCustomer", serveService.findOneTableCustomer(tableId));
		ProductAmountAndComment productAmountAndComment = new ProductAmountAndComment();
		productAmountAndComment.setAmount(1);
		productAmountAndComment.setForall(false);
		model.addAttribute("productAmountAndComment", productAmountAndComment);
		model.addAttribute("forAllOptions", BooleanArray.getBooleanArray());
		
		return "serve/add/chooseAmountAndComment";			
	}
	
	@RequestMapping(value = "/chooseTable/{tableId}/chooseProduct/{productId}",method = RequestMethod.POST)
	public String saveProductforTable(@PathVariable Long tableId,@PathVariable Long productId, 
			@Valid @ModelAttribute("productAmountAndComment") ProductAmountAndComment productAmountAndComment,
			BindingResult bindingResult, RedirectAttributes redirectAttributes,
			Model model){
		
		model.addAttribute("pageHeader", "serve.add.chooseAmountAndComment.pageHeader");
		
		model.addAttribute("product", serveService.findOneProduct(productId));
		model.addAttribute("tableCustomer", serveService.findOneTableCustomer(tableId));
		model.addAttribute("productAmountAndComment", new ProductAmountAndComment());		
		
		serveService.saveOrderPosition(productId,tableId, productAmountAndComment);
		
		//redirectAttributes.addFlashAttribute(attributeName, attributeValue)
		
		return "redirect:/serve/add/chooseTable";
	}
	
	@RequestMapping(value = "/chooseTable/{tableId}/chooseProduct/{productId}/default",method = RequestMethod.POST)
	public String saveProductforTableDefault(@PathVariable Long tableId,@PathVariable Long productId,Model model){
		
		ProductAmountAndComment productAmountAndComment = new ProductAmountAndComment();
		productAmountAndComment.setAmount(1);
		productAmountAndComment.setForall(true);
		
		serveService.saveOrderPosition(productId,tableId, productAmountAndComment);
		
		return "redirect:/serve/add/chooseTable";
	}
	
	
	@RequestMapping(value = "/saveOrderPosition/default",method = RequestMethod.POST)
	public String saveOrder(@Valid @ModelAttribute("productOrder") ProductOrder productOrder,
			BindingResult bindingResult, RedirectAttributes redirectAttributes,
			Model model){
		
		System.out.println(productOrder.getId()+" "+productOrder.getTableId()+" "+productOrder.getAmount());
		
	
		
		return "redirect:/serve/add/chooseTable";
	}
	

}
