package de.hs.furtwangen.bam.jee.configurator.web;

import java.util.ArrayList;
import java.util.List;

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
    public List<OrderPositionWeb> greeting(HelloMessage message) throws Exception {
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
        
        List<OrderPositionWeb> listOrderPositionWeb = new ArrayList<OrderPositionWeb>();
        
       for(OrderPosition orderPosition : serverService.findByRegisteredTrueAndIdGreaterThanOrderByIdDesc(Long.parseLong(message.getName())))
       {
    	   OrderPositionWeb orderPositionWeb = new OrderPositionWeb();
           orderPositionWeb.setId(orderPosition.getId());
           orderPositionWeb.setProductname(orderPosition.getProduct().getProductname());
           orderPositionWeb.setComment(orderPosition.getComment());
           orderPositionWeb.setCreatedDate(orderPosition.getCreatedDate());
           orderPositionWeb.setDone(orderPosition.isDone());
           orderPositionWeb.setProvided(orderPosition.isProvided());
           orderPositionWeb.setRegistered(orderPosition.isRegistered());
           orderPositionWeb.setSize(orderPosition.getProduct().getSize());
           orderPositionWeb.setUsername(orderPosition.getUser().getUsername());
           listOrderPositionWeb.add(orderPositionWeb);
       }
       
       for(OrderPositionWeb positionWeb : listOrderPositionWeb){
    	   System.out.println(positionWeb.getProductname()+" "+positionWeb.getCreatedDate());
       }
        
        return listOrderPositionWeb;
    }

}
