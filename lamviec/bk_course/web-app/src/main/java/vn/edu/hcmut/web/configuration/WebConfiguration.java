package vn.edu.hcmut.web.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

import lombok.var;

@Configuration
public class WebConfiguration {
	@Bean
    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource source = new ResourceBundleMessageSource();
        source.setBasenames("messages/validation");
        source.setDefaultEncoding("UTF-8");
        source.setCacheSeconds(3600);
        source.setUseCodeAsDefaultMessage(true);
        return source;
    }
}
