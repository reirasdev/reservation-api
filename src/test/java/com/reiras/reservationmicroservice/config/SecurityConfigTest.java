package com.reiras.reservationmicroservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity
@Order(99)
public class SecurityConfigTest extends SecurityConfig {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/reservations/**").hasIpAddress("127.0.0.1");
		super.configure(http);
	}

}
