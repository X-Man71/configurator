package de.hs.furtwangen.bam.jee.configurator.web;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class WelcomeController
{

  @RequestMapping(value = "/", method = RequestMethod.GET)
  public String intro(Model model)
  {  
    return "home";
  }
}
