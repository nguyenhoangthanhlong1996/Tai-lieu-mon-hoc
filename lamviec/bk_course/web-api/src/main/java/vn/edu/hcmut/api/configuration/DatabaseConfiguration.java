package vn.edu.hcmut.api.configuration;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class DatabaseConfiguration {
	@Bean(name = "dataSource")
	@Primary
	@ConfigurationProperties(prefix = "datasource.db-extra-courses")
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}
}
