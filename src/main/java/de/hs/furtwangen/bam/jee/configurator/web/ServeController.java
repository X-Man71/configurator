package de.hs.furtwangen.bam.jee.configurator.web;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
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
import de.hs.furtwangen.bam.jee.configurator.web.domain.OrderPositionModel;
import de.hs.furtwangen.bam.jee.configurator.web.domain.OrderPositionWeb;
import de.hs.furtwangen.bam.jee.configurator.web.domain.ProductOrder;

@Controller
@RequestMapping(value = "/serve/order")
public class ServeController {

	@Autowired
	private ServeService serveService;

	@RequestMapping(value = "/table/{tableId}", method = RequestMethod.GET)
	public String selectActionPage(@PathVariable Long tableId, Model model) {
		model.addAttribute("tableId", tableId);
		return "serve/order/select";
	}

	@RequestMapping(value = "/table/{tableId}/add", method = RequestMethod.GET)
	public String chooseProductPage(@PathVariable Long tableId, Model model) {
		List<ProductOrder> products = new ArrayList<>();		
		model.addAttribute("pageHeader", "serve.order.add.chooseProduct.pageHeader");
		for (Product product : serveService.findAllProduct()) 
		{
			ProductOrder productOrder = new ProductOrder();
			productOrder.setId(product.getId());
			productOrder.setProductname(product.getProductname());
			productOrder.setPrice(product.getPrice());
			productOrder.setSize(product.getSize());
			productOrder.setTableId(tableId);
			products.add(productOrder);
		}		
		for(ProductOrder productOrder : products)
		{
			Integer amount =serveService.countByTableCustomerAndProductAndRegisteredFalse(tableId,productOrder.getId());
			productOrder.setAmount(amount);			
		}			
		
		model.addAttribute("products", products);
		model.addAttribute("tableId", tableId);
		model.addAttribute("amountOptions", amountOption());

		return "serve/order/add/chooseProduct";
	}

	public Integer[] amountOption() {
		Integer[] amountOptions = new Integer[100];
		for (int i = 1; i <= 100; i++) {
			amountOptions[i - 1] = i;
		}
		return amountOptions;
	}

	@RequestMapping(value = "/table/{tableId}/add", method = RequestMethod.POST)
	public String saveOrder(@PathVariable Long tableId,
			@Valid @ModelAttribute("productOrder") ProductOrder productOrder,
			BindingResult bindingResult, RedirectAttributes redirectAttributes,
			Model model) {		
		serveService.saveOrderPosition(productOrder.getId(), tableId);
		redirectAttributes.addFlashAttribute("addSuccessful",
				"serve.add.chooseTable.productSaved");
		return "redirect:/serve/order/table/{tableId}/add";
	}
	
	@RequestMapping(value = "/table/{tableId}/delete", method = RequestMethod.POST)
	public String deleteOrder(@PathVariable Long tableId,
			@Valid @ModelAttribute("productOrder") ProductOrder productOrder,
			BindingResult bindingResult, RedirectAttributes redirectAttributes,
			Model model) {
		serveService.deleteOrderPosition(productOrder.getId(), tableId);
		redirectAttributes.addFlashAttribute("deleteSuccessful",
				"serve.order.add.chooseProduct.deleteSuccessful");
		return "redirect:/serve/order/table/{tableId}/add";
	}
	
	@RequestMapping(value = "/table/{tableId}/aggregationComment", method = RequestMethod.GET)
	public String submitOrderPage(@PathVariable Long tableId, Model model) {				
		List<OrderPosition> orderPositionList = new ArrayList<OrderPosition>();		
		for(OrderPosition orderPosition : serveService
				.findByTableCustomerAndRegisteredFalse(tableId))
		{
			orderPositionList.add(orderPosition);
		}
		
		OrderPositionModel orderPositionModel = new OrderPositionModel();
		orderPositionModel.setListOrderPositions(orderPositionList);
		
		model.addAttribute("orderPositionModel", orderPositionModel);		

		return "serve/order/add/aggregationComment";
	}
	
	

	@RequestMapping(value = "/table/{tableId}/aggregationComment/withoutComment", method = RequestMethod.POST)
	public String submitOrderWithoutComment(@PathVariable Long tableId,
			@Valid @ModelAttribute("productOrder") ProductOrder productOrder,
			BindingResult bindingResult, RedirectAttributes redirectAttributes,
			Model model) {		
		
			serveService.submitOrderFromTable(tableId);
		
		return "redirect:/";
	}
	
	@RequestMapping(value = "/table/{tableId}/aggregationComment/withComment", method = RequestMethod.POST)
	public String submitOrderWithComment(@PathVariable Long tableId,
			@Valid @ModelAttribute("orderPositionModel") OrderPositionModel orderPositionModel,
			BindingResult bindingResult, RedirectAttributes redirectAttributes,
			Model model) {			
		serveService.submitOrderFromTableWithComment(tableId, orderPositionModel);
		return "redirect:/";
	}

	@RequestMapping(value = "/table/{tableId}/delete", method = RequestMethod.GET)
	public String chooseOrderPositionPage(@PathVariable Long tableId,
			Model model) {
		model.addAttribute("pageHeader", "serve.order.delete.table.pageHeader");		
		model.addAttribute("orderPositions",
		  serveService.findByTableCustomerAndDoneFalse(tableId));	
		model.addAttribute("tableId", tableId);
		return "serve/order/delete/table";
	}

	@RequestMapping(value = "/table/{tableId}/delete/deleteOrderPosition", method = RequestMethod.POST)
	public String deleteOrderPosition(
			@PathVariable Long tableId,
			@Valid @ModelAttribute("orderPosition") OrderPosition orderPosition,
			BindingResult bindingResult, RedirectAttributes redirectAttributes,
			Model model) {
		serveService.deleteOrderPosition(orderPosition);		
		return "redirect:/serve/order/table/{tableId}/delete";
	}
	
	@RequestMapping(value = "/produce", method = RequestMethod.GET)
	public String producePage(Model model)
	{
		List<OrderPositionWeb> listOrderPositionWeb = new ArrayList<OrderPositionWeb>();
		
		for(OrderPosition orderPosition : serveService.findByRegisteredTrueOrderByIdDesc()){
			OrderPositionWeb orderPositionWeb = new  OrderPositionWeb();
			orderPositionWeb.setComment(orderPosition.getComment());
			orderPositionWeb.setCreatedDate(orderPosition.getCreatedDate());
			orderPositionWeb.setDone(orderPosition.isDone());
			orderPositionWeb.setId(orderPosition.getId());
			orderPositionWeb.setPrice(orderPosition.getProduct().getPrice());
			orderPositionWeb.setProductname(orderPosition.getProduct().getProductname());
			orderPositionWeb.setProvided(orderPosition.isProvided());
			orderPositionWeb.setRegistered(orderPosition.isRegistered());
			orderPositionWeb.setSize(orderPosition.getProduct().getSize());
			orderPositionWeb.setUsername(orderPosition.getUser().getUsername());
			orderPositionWeb.setVersion(orderPosition.getVersion());
			listOrderPositionWeb.add(orderPositionWeb);
		}
		
		model.addAttribute("orderPositions", listOrderPositionWeb);
		
		return "serve/order/produce/table";
	}
	
	
	@RequestMapping(value = "/produce/done", method = RequestMethod.POST)
	public String produceDone(Model model)
	{
		//TODO
		return "redirect:serve/order/produce";
	}
	
	
	@MessageMapping("/hello")
	@SendTo("/topic/greetings")
	//@SubscribeMapping("/topic/greetings")
	public List<OrderPositionWeb> greeting(HelloMessage message)
			throws Exception {

		List<OrderPositionWeb> listOrderPositionWeb = new ArrayList<OrderPositionWeb>();

		for (OrderPosition orderPosition : serveService
				.findByRegisteredTrueAndIdGreaterThanOrderByIdDesc(Long
						.parseLong(message.getName()))) {
			OrderPositionWeb orderPositionWeb = new OrderPositionWeb();
			orderPositionWeb.setId(orderPosition.getId());
			orderPositionWeb.setProductname(orderPosition.getProduct()
					.getProductname());
			orderPositionWeb.setComment(orderPosition.getComment());
			orderPositionWeb.setCreatedDate(orderPosition.getCreatedDate());
			orderPositionWeb.setDone(orderPosition.isDone());
			orderPositionWeb.setProvided(orderPosition.isProvided());
			orderPositionWeb.setRegistered(orderPosition.isRegistered());
			orderPositionWeb.setSize(orderPosition.getProduct().getSize());
			orderPositionWeb.setUsername(orderPosition.getUser().getUsername());
			listOrderPositionWeb.add(orderPositionWeb);
		}

		for (OrderPositionWeb positionWeb : listOrderPositionWeb) {
			System.out.println(positionWeb.getProductname() + " "
					+ positionWeb.getCreatedDate());
		}

		return listOrderPositionWeb;
	}
}
