package de.hs.furtwangen.bam.jee.configurator.util;

import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.format.support.FormattingConversionServiceFactoryBean;
import org.springframework.stereotype.Component;

@Component(value = "conversionService")
public class FlowDateConverter extends FormattingConversionServiceFactoryBean {

	@Override
    protected void installFormatters(FormatterRegistry registry) {
      // Register the default date formatter provided by Spring
      registry.addFormatter(new DateFormatter("dd.MM.yyyy"));
    }
	
}
