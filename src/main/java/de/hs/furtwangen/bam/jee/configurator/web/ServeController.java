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

import de.hs.furtwangen.bam.jee.configurator.model.OrderPosition;
import de.hs.furtwangen.bam.jee.configurator.model.Product;
import de.hs.furtwangen.bam.jee.configurator.service.ServeService;
import de.hs.furtwangen.bam.jee.configurator.web.domain.ProductOrder;

@Controller
@RequestMapping(value = "/serve/order")
public class ServeController {
	
	@Autowired
	private ServeService serveService;	
	
	@RequestMapping(value = "/table/{tableId}", method = RequestMethod.GET)
	public String selectActionPage(@PathVariable Long tableId,Model model) 
	{		
		model.addAttribute("tableId", tableId);
		return "serve/order/select";
	}	
	
	
	@RequestMapping(value = "/table/{tableId}/add",method = RequestMethod.GET)
	public String chooseProductPage(@PathVariable Long tableId, Model model)
	{
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
		model.addAttribute("amountOptions", amountOption());
		
		return "serve/order/add/chooseProduct"; 
	}
	
	public Integer[] amountOption()
	{
		Integer[] amountOptions = new Integer[100];
		
		for(int i = 1; i<=100;i++)
		{
			amountOptions[i-1] = i;
		}
		return amountOptions;
	}
	
	@RequestMapping(value = "/table/{tableId}/add",method = RequestMethod.POST)
	public String saveOrder(@Valid @ModelAttribute("productOrder") ProductOrder productOrder,
			BindingResult bindingResult, RedirectAttributes redirectAttributes,
			Model model){
		
		System.out.println("ProductId "+productOrder.getId()+" "+productOrder.getTableId()+" "+productOrder.getAmount());
		
		serveService.saveOrderPosition(productOrder.getId(), productOrder.getTableId(), productOrder.getAmount());
		
		redirectAttributes.addFlashAttribute("changeSuccessful", "serve.add.chooseTable.productSaved");
	
		
		return "redirect:/serve/order/table/{tableId}/add";
	}	
	
	
	@RequestMapping(value = "/table/{tableId}/delete",method = RequestMethod.GET)
	public String deleteOrderTable(@PathVariable Long tableId, Model model)
	{
		model.addAttribute("orderPositions", serveService.findByTableCustomerAndDoneFalse(tableId));
		model.addAttribute("tableId", tableId);
	
		
		return "serve/order/delete/table";
	}	
	
	@RequestMapping(value = "/table/{tableId}/delete",method = RequestMethod.POST)
	public String deleteOrder(@PathVariable Long tableId, @Valid @ModelAttribute("orderPosition") OrderPosition orderPosition,
			BindingResult bindingResult, RedirectAttributes redirectAttributes,
			Model model)
	{
		System.out.println("orderPosition "+orderPosition.getId());
		
		model.addAttribute("orderPositions", serveService.findByTableCustomerAndDoneFalse(tableId));
		model.addAttribute("tableId", tableId);
	
		
		return "serve/order/delete/table";
	}	
	
	
	
	
	
	
		
	@RequestMapping(value = "/delete/chooseTable", method = RequestMethod.GET)
	public String chooseTablePage(Model model) {
		model.addAttribute("pageHeader",
				"serve.order.delete.chooseTable.pageHeader");
		model.addAttribute("tableCustomers",
				serveService.findAllTableCustomer());

		return "serve/order/delete/chooseTable";
	}

	@RequestMapping(value = "/delete/chooseTable/{tableId}", method = RequestMethod.GET)
	public String chooseOrderPositionPage(@PathVariable Long tableId,
			Model model) {
		model.addAttribute("pageHeader", "serve.order.delete.table.pageHeader");
		/*model.addAttribute("orderPositions",
				serveService.findByTableCustomerAndUser(tableId));*/
		model.addAttribute("tableId", tableId);
		return "serve/order/delete/table";
	}

	@RequestMapping(value = "/delete/chooseTable/{tableId}/deleteOrderPosition", method = RequestMethod.POST)
	public String deleteOrderPosition(
			@PathVariable Long tableId,
			@Valid @ModelAttribute("orderPosition") OrderPosition orderPosition,
			BindingResult bindingResult, RedirectAttributes redirectAttributes,
			Model model) {
		model.addAttribute("pageHeader", "serve.order.delete.table.pageHeader");
	
		System.out.println("OrderPosition "+orderPosition.getId());
		
		return "serve/order/delete/table";
	}
}
