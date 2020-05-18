package com.reiras.reservationmicroservice.config;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Header;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	private final ResponseMessage m403 = simpleMessage(403, "Forbidden");
	private final ResponseMessage m404 = simpleMessage(404, "Object not found");
	private final ResponseMessage m422 = simpleMessage(422, "Validation Error");
	private final ResponseMessage m500 = simpleMessage(500, "Generic Error");

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				
				.useDefaultResponseMessages(true)
				.globalResponseMessage(RequestMethod.POST, Arrays.asList(customMessage201("New entity created"), m403, m422, m500))
				.globalResponseMessage(RequestMethod.PUT, Arrays.asList(customMessage201("Entity updated"), m403, m404, m422, m500))
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.reiras.reservationmicroservice.controller"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		return new ApiInfo(
				"Microservice for reservation management",
				"This API is part of B&B management system",
				"Version 1.0",
				"https://github.com/reirasdev/reservationmicroservice",
				new Contact("Raphael Eiras", "https://www.linkedin.com/in/reirasdev/", "reiras.dev@gmail.com"),
				"Allowed copy for students and development professionals",
				"https://github.com/reirasdev/reservationmicroservice",
				Collections.emptyList()
		);
	}

	private ResponseMessage simpleMessage(int code, String msg) {
		return new ResponseMessageBuilder().code(code).message(msg).build();
	}

	private ResponseMessage customMessage201(String msg) {

		Map<String, Header> map = new HashMap<>();
		map.put("location", new Header("location", "URI for the managed entity", new ModelRef("string")));

		return new ResponseMessageBuilder().code(201).message(msg).headersWithDescription(map).build();
	}
}