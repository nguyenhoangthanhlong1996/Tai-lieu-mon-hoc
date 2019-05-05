package vn.edu.hcmut.admin.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
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

	// Hibernate
	@Bean("sessionFactory")
	public LocalSessionFactoryBean getSessionFactory() {
		LocalSessionFactoryBean session = new LocalSessionFactoryBean();
		session.setDataSource(dataSource());
		session.setPackagesToScan("vn.edu.hcmut.core.model.hibernate");
		session.setHibernateProperties(hibernateProperties());
		return session;
	}

	@Bean("transactionManager")
	public HibernateTransactionManager getTransactionManager() {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(getSessionFactory().getObject());
		return transactionManager;
	}

	//
	private Properties hibernateProperties() {
		Properties prop = new Properties();
		prop.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
		prop.setProperty("hibernate.show_sql", "false");
		prop.setProperty("hibernate.c3p0.min_size", "5");
		prop.setProperty("hibernate.c3p0.max_size", "20");
		prop.setProperty("hibernate.c3p0.acquire_increment", "5");
		prop.setProperty("hibernate.c3p0.timeout", "1800");
		return prop;
	}
}
