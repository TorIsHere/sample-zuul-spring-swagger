package com.torishere.helloservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Autowired
    private BuildProperties buildProperties;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.torishere.helloservice"))
                .paths(PathSelectors.any())
                .build().apiInfo(new ApiInfo("Goodbye Service Api Documentation", "Automatically generated swagger document", buildProperties.getVersion(), "TOS", new Contact("Kittikorn Ariyasuk", "torishere.github.io", "kittikorn.a@gmail.com"), "", "", Collections.emptyList()));
    }
}