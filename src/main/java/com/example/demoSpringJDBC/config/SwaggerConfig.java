package com.example.demoSpringJDBC.config;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.StringVendorExtension;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.DocExpansion;
import springfox.documentation.swagger.web.ModelRendering;
import springfox.documentation.swagger.web.OperationsSorter;
import springfox.documentation.swagger.web.TagsSorter;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;

// http://localhost:8080/swagger-ui/
@Configuration
public class SwaggerConfig
{
    private ApiInfo apiInfo()
    {
        Contact contact = new Contact("numan karaaslan", "numankaraaslan.html", "numan4634@hotmail.com");
        String descr = "This rest api provides OBS endpoints...";
        List<VendorExtension> extensions = new ArrayList<>();
        extensions.add(new StringVendorExtension("vendor name", "vendor value"));
        String version = "V1.0";
        String title = "Öğrenci Bilgi Sistemi Rest api";
        String terms = "terms.html";
        ApiInfo apiInfo = new ApiInfoBuilder().contact(contact).description(descr).extensions(extensions).termsOfServiceUrl(terms).title(title).version(version).build();
        return apiInfo;
    }

    @Bean public Docket api()
    {
        Docket docket = new Docket(DocumentationType.SWAGGER_2);
        docket.apiInfo(apiInfo());
        //docket.groupName("docket group name");
        docket.host("localhost:8080");
        //docket = docket.select().apis(RequestHandlerSelectors.basePackage("com.example.demoSpringJDBC")).paths(PathSelectors.any()).build();
        docket = docket.select().apis(RequestHandlerSelectors.any()).paths(PathSelectors.any()).build();
        return docket;
        //return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select().apis(RequestHandlerSelectors.any()).paths(PathSelectors.any()).build();
    }

    /**
     * SwaggerUI information
     */
    @Bean public UiConfiguration uiConfig()
    {
        return UiConfigurationBuilder.builder().deepLinking(true).displayOperationId(false).defaultModelsExpandDepth(1).defaultModelExpandDepth(1).defaultModelRendering(ModelRendering.EXAMPLE).displayRequestDuration(false).docExpansion(DocExpansion.NONE).filter(false).maxDisplayedTags(null).operationsSorter(OperationsSorter.ALPHA).showExtensions(false).tagsSorter(TagsSorter.ALPHA).supportedSubmitMethods(UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS).validatorUrl(null).build();
    }
}