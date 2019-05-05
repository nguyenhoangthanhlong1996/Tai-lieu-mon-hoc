package vn.edu.hcmut.admin.configuration;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Configuration
@ConfigurationProperties(prefix = "secretkey")
@Setter
@Getter
public class EncryptionConfiguration {
	private Map<String, String> desProperties = new HashMap<String, String>();
	private Map<String, String> rsaProperties = new HashMap<String, String>();
	private Map<String, String> aesProperties = new HashMap<String, String>();
	
	@Bean()
	public Map<String, String> DESProperties(){
		return this.desProperties;
	}
	
	@Bean
	public Map<String, String> RSAProperties(){
		return this.rsaProperties;
	}
	
	@Bean
	public Map<String, String> AESProperties(){
		return this.aesProperties;
	}
}