package com.ravi.springmvcrest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

/* Created by: Venkata Ravichandra Cherukuri
   Created on: 5/17/2020 */
@EnableSwagger2
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .build()
                .pathMapping("/")
                .apiInfo(metaData());
    }

    private ApiInfo metaData(){
        Contact contact = new Contact("Venkata RaviChandra Cherukuri", "https://github.com/vcheruk2", "vcheruk2@ncsu.edu");

        return new ApiInfo(
                "REST API - Vendors",
                "Spring 5 based REST APIs for Vendors. Contains categories, customers & Vendors.",
                "1.0",
                "Free to use",
                contact,
                "Apache License Version 2.0",
                "https://www.apache.org/license/LICENSE-2.0",
                new ArrayList<>());
    }
}
