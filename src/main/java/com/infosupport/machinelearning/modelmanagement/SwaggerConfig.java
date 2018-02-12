package com.infosupport.machinelearning.modelmanagement;

import com.infosupport.machinelearning.modelmanagement.DocumentedEndpoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import static springfox.documentation.builders.PathSelectors.regex;

/**
 * Defines the configuration for the swagger documentation
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    /**
     * Defines the basic settings for the API documentation
     *
     * @return Returns the Docket instance for generating documentation
     */
    @Bean
    public Docket apiDocumentation() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(DocumentedEndpoint.class))
                .paths(regex("/.*")).build();
    }

    /**
     * Defines information about the API.
     * Please refer to resources/introduction.html for the text to publish as description for the service.
     *
     * @return API information object including contact details
     */
    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Model management service",
                introductionText(),
                "1.0", "https://infosupport.com/about", contactDetails(), "Apache-2.0",
                "https://www.apache.org/licenses/LICENSE-2.0", new ArrayList<>());
    }

    /**
     * Defines the contact information to get support and for general questions about the service.
     *
     * @return Contact information
     */
    private Contact contactDetails() {
        return new Contact("Info Support", "http://ai-experiments.io", "ai@infosupport.com");
    }

    private String introductionText() {
        try {
            InputStream introductionFileStream = SwaggerConfig.class.getResourceAsStream("/introduction.html");
            BufferedReader reader = new BufferedReader(new InputStreamReader(introductionFileStream));
            StringBuilder outputBuilder = new StringBuilder();

            for(String line; (line = reader.readLine()) != null;) {
                outputBuilder.append(line);
            }

            return outputBuilder.toString();
        } catch (IOException e) {
            return "";
        }
    }

    /**
     * Defines the basic settings for the swagger UI implementation.
     *
     * @return Returns the UI configuration instance to use
     */
    @Bean
    public UiConfiguration uiConfig() {
        return UiConfigurationBuilder.builder()
                .deepLinking(true)
                .displayOperationId(false)
                .defaultModelsExpandDepth(1)
                .defaultModelExpandDepth(1)
                .defaultModelRendering(ModelRendering.EXAMPLE)
                .displayRequestDuration(false)
                .docExpansion(DocExpansion.LIST)
                .filter(false)
                .operationsSorter(OperationsSorter.ALPHA)
                .showExtensions(false)
                .validatorUrl(null)
                .build();
    }
}
