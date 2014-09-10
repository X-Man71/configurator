package de.hs.furtwangen.bam.jee.configurator.web;

import java.time.LocalDateTime;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import de.hs.furtwangen.bam.jee.configurator.web.domain.OrderPositionWeb;

@Controller
public class GreetingController {


    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public OrderPositionWeb greeting(HelloMessage message) throws Exception {
        Thread.sleep(3000); // simulated delay
        
        OrderPositionWeb orderPositionWeb = new OrderPositionWeb();
        orderPositionWeb.setProductname("Judas");
        orderPositionWeb.setComment("comment");
        orderPositionWeb.setCreatedDate(LocalDateTime.now());
        orderPositionWeb.setDone(true);
        orderPositionWeb.setProvided(true);
        orderPositionWeb.setRegistered(true);
        orderPositionWeb.setSize("size");
        orderPositionWeb.setUsername("username");
        
        return orderPositionWeb;
    }

}
