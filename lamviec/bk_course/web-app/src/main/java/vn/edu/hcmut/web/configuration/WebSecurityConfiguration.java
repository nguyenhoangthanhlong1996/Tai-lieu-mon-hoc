package vn.edu.hcmut.web.configuration;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import vn.edu.hcmut.core.filter.CORSFilter;
import vn.edu.hcmut.core.filter.XSSFilter;

@Configuration
@EnableWebSecurity
@Order(1)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService usersService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests()
				.antMatchers("/", "/home", "/errorPage", "/xacNhanEmail","/activeAccount", "/course-detail/**", "/register", "/registerSuccessful", "/api/**", "/login",
						"/logout", "/css/**", "/js/**", "/images/**", "/fonts/**", "/icon-fonts/**", "/**/favicon.ico")
				.permitAll().anyRequest().authenticated();
		http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");
		http.authorizeRequests().and().formLogin()//
				.loginProcessingUrl("/security_check_login") // Submit URL
				.loginPage("/login")//
				.defaultSuccessUrl("/payments")//
				.failureUrl("/login?error=true")//
				.usernameParameter("username")//
				.passwordParameter("password")
				.and().logout().logoutUrl("/logout").logoutSuccessUrl("/logoutSuccessful");
		http.authorizeRequests().and() //
				.rememberMe().tokenRepository(this.persistentTokenRepository()) //
				.tokenValiditySeconds(60 * 60); // 1h
		http.addFilterBefore(new CORSFilter(), (Class<? extends Filter>) ChannelProcessingFilter.class)
				.addFilterBefore(new XSSFilter(), (Class<? extends Filter>) ChannelProcessingFilter.class);

	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		// Sét đặt dịch vụ để tìm kiếm User trong Database.
		// Và sét đặt PasswordEncoder.
		auth.userDetailsService(usersService).passwordEncoder(passwordEncoder());

	}

	// Token stored in Memory (Of Web Server).
	@Bean
	public PersistentTokenRepository persistentTokenRepository() {
		InMemoryTokenRepositoryImpl memory = new InMemoryTokenRepositoryImpl();
		return memory;
	}

	// Token stored in Table (Persistent_Logins)
	// @Bean
	// public PersistentTokenRepository persistentTokenRepository() {
	// JdbcTokenRepositoryImpl db = new JdbcTokenRepositoryImpl();
	// db.setDataSource(this.dataSource);
	// return db;
	// }
}
