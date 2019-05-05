package vn.edu.hcmut.api.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		http.csrf().disable().authorizeRequests()
//		.antMatchers("/").permitAll()
//		.antMatchers("/home").permitAll()
//		.antMatchers("/test").permitAll()
//		.antMatchers(HttpMethod.POST, "/login").permitAll()
//		.anyRequest().authenticated()
//		.and()
//		.formLogin().loginPage("/login").failureUrl("/loginfailure")
//		.and()
//		.exceptionHandling().authenticationEntryPoint(unauthorizedHandler).accessDeniedPage("/403")
//		.and()
//		.logout().logoutSuccessUrl("/logout")
//		.and()
//		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		//http
		//.addFilterBefore(new IPRemoteFilter(generalConfiguration), (Class<? extends Filter>) ChannelProcessingFilter.class)
//		.addFilterBefore(new CORSFilter(), (Class<? extends Filter>) ChannelProcessingFilter.class)
//		.addFilterBefore(new XSSFilter(), (Class<? extends Filter>) ChannelProcessingFilter.class)
//		.addFilterBefore(new JWTLoginFilter("/login", authenticationManager()),	UsernamePasswordAuthenticationFilter.class)
//		.addFilterBefore(new JWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
	}
	/*
	 * 403 - Access Denied
	 * 502 - Token Expired
	 * 401 - Info IP error
	 * 400 - Info Login error
	 */
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
