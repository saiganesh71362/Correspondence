package com.main.swaggerconfiguration;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CorrespondenceSwaggerConfiguration
{
	@Bean
	public GroupedOpenApi controllerApi()
	{
	        return GroupedOpenApi.builder()
	                .group("Correspondence")
	                .packagesToScan("com.main.controller") // Specify the package to scan
	                .build();
	 }


}
