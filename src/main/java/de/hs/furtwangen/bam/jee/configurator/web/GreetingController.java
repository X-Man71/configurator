package de.hs.furtwangen.bam.jee.configurator.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import de.hs.furtwangen.bam.jee.configurator.model.OrderPosition;
import de.hs.furtwangen.bam.jee.configurator.service.ServeService;
import de.hs.furtwangen.bam.jee.configurator.web.domain.OrderPositionWeb;

@Controller
public class GreetingController {
	
	@Autowired
	private ServeService serverService;


    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public OrderPositionWeb greeting(HelloMessage message) throws Exception {
        Thread.sleep(3000); // simulated delay
        
        /*OrderPositionWeb orderPositionWeb = new OrderPositionWeb();
        orderPositionWeb.setId(1L);
        orderPositionWeb.setProductname("Judas");
        orderPositionWeb.setComment("comment");
        orderPositionWeb.setCreatedDate(LocalDateTime.now());
        orderPositionWeb.setDone(true);
        orderPositionWeb.setProvided(true);
        orderPositionWeb.setRegistered(true);
        orderPositionWeb.setSize("size");
        orderPositionWeb.setUsername("username");*/
        
        OrderPositionWeb orderPositionWeb = new OrderPositionWeb();
        
       for(OrderPosition orderPosition : serverService.findByRegisteredTrueAndIdGreaterThanOrderByIdDesc(Long.parseLong(message.getName())))
       {
    	   
           orderPositionWeb.setId(orderPosition.getId());
           orderPositionWeb.setProductname(orderPosition.getProduct().getProductname());
           orderPositionWeb.setComment(orderPosition.getComment());
           orderPositionWeb.setCreatedDate(orderPosition.getCreatedDate());
           orderPositionWeb.setDone(orderPosition.isDone());
           orderPositionWeb.setProvided(orderPosition.isProvided());
           orderPositionWeb.setRegistered(orderPosition.isRegistered());
           orderPositionWeb.setSize(orderPosition.getProduct().getSize());
           orderPositionWeb.setUsername(orderPosition.getUser().getUsername());
       }
        
        return orderPositionWeb;
    }

}
