package vn.edu.hcmut.admin.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

import lombok.var;

@Configuration
public class WebConfiguration {
	@Bean
    public ResourceBundleMessageSource messageSource() {
        var source = new ResourceBundleMessageSource();
        source.setBasenames("messages/validation");
        source.setDefaultEncoding("UTF-8");
        source.setCacheSeconds(3600);
        source.setUseCodeAsDefaultMessage(true);
        return source;
    }
//	@Bean
//    public MessageSource messageSource() {
//        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
//        messageSource.setBasename("classpath:validation");
//        messageSource.setDefaultEncoding("UTF-8");
//        messageSource.setCacheSeconds(3600);
//        return messageSource;
//    }
//	@Bean
//	public LocalValidatorFactoryBean getValidator() {
//	    LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
//	    bean.setValidationMessageSource(messageSource());
//	    return bean;
//	}
}
