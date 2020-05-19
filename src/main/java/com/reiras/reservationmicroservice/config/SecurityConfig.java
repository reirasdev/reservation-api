package com.reiras.reservationmicroservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private static final String[] PUBLIC_MATCHERS_GET = { 
			"/reservations/**", 
			"/v2/api-docs",
			"/swagger-resources/**", 
			"/swagger-ui.html" 
			};

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests().antMatchers(HttpMethod.GET, PUBLIC_MATCHERS_GET).permitAll();
		http.authorizeRequests().antMatchers(HttpMethod.POST, "/**").denyAll();
		http.authorizeRequests().antMatchers(HttpMethod.PUT, "/**").denyAll();
		http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/**").denyAll();
	}
}
