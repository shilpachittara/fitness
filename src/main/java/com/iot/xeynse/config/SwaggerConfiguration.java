package com.iot.xeynse.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;/*
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;*/
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;

/**
 * Configuration for swagger
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).globalOperationParameters(
                Arrays.asList(
                        new ParameterBuilder()
                                .name("Authorization")
                                .modelRef(new ModelRef("string"))
                                .parameterType("header")
                                .required(false)
                                .defaultValue("Bearer JWT")
                                .build()))
                .apiInfo(myApiInfo()).select()
                .apis(RequestHandlerSelectors.basePackage("com.iot.xeynse"))
                .paths(PathSelectors.regex("/.*")).build();
    }

    private ApiInfo myApiInfo() {
        return new ApiInfoBuilder().title("Xeynse user login").version("v1")
                .description("All APIs related to Xeynse user login").build();
    }
}
