package vn.edu.hcmut.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.autoconfigure.context.MessageSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@SpringBootApplication(scanBasePackages = { "vn.edu.hcmut.*" })
@EnableAutoConfiguration(exclude = { //  
        //DataSourceAutoConfiguration.class, //
        //DataSourceTransactionManagerAutoConfiguration.class, //
		//MessageSourceAutoConfiguration.class,
        HibernateJpaAutoConfiguration.class })
public class WebAdminApplication {
	public static void main(String[] args) {
		SpringApplication.run(WebAdminApplication.class, args);
	}

}
