package de.hs.furtwangen.bam.jee.configurator.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import de.hs.furtwangen.bam.jee.configurator.service.StartPageService;

@Controller
public class StartPageController {

	@Autowired
	private StartPageService startPageService;	
	
	@Resource(name="gitProperties")
	private Properties gitProperties;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String startPage(Model model) {

		model.addAttribute("tableCustomers",
				startPageService.findAllTableCustomer());
		return "home";
	}
	
	
	@RequestMapping(value = "/version", method = RequestMethod.GET)
	public String git(Model model) {
		
		List<PropertyBean> listPropertyBean = new ArrayList<>();		
		
		for(String gitKey : gitProperties.stringPropertyNames())
		{
			PropertyBean propertyBean = new PropertyBean();			
			propertyBean.setKey(gitKey);
			propertyBean.setValue(gitProperties.getProperty(gitKey));
			listPropertyBean.add(propertyBean);
		}
		
		model.addAttribute("listPropertyBean",
				listPropertyBean);		

		return "version";
	}
	
	class PropertyBean{
		String key;
		
		String value;

		public String getKey() {
			return key;
		}

		public void setKey(String key) {
			this.key = key;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}
		
		
	}

}
