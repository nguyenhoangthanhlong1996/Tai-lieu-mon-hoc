package vn.edu.hcmut.admin.configuration;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
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
@EnableGlobalMethodSecurity(prePostEnabled=true)
@Order(1)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService usersService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		//http.authorizeRequests().antMatchers("/**").permitAll();
		http.authorizeRequests().antMatchers("/asset/**", "/login", "/logout", "/css/**", "/js/**", "/images/**",
				"/fonts/**", "/icon-fonts/**", "/**/favicon.ico").permitAll().anyRequest().authenticated();
		http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");
		http.authorizeRequests().and().formLogin()//
				.loginProcessingUrl("/security_check_login").loginPage("/login")//
				.defaultSuccessUrl("/").failureUrl("/login?error=true").usernameParameter("username")
				.passwordParameter("password").and()
				.logout().logoutUrl("/logout")
				.logoutSuccessUrl("/logoutSuccessful");
		http.authorizeRequests().and().rememberMe().tokenRepository(this.persistentTokenRepository())
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
		auth.userDetailsService(usersService).passwordEncoder(passwordEncoder());

	}

	@Bean
	public PersistentTokenRepository persistentTokenRepository() {
		InMemoryTokenRepositoryImpl memory = new InMemoryTokenRepositoryImpl();
		return memory;
	}
}
