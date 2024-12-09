package com.example.eventmanager.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.examples.Example;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@OpenAPIDefinition
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI baseOpenAPI() {
        ApiResponse badRequest = new ApiResponse().content(new Content().addMediaType("application/json", new io.swagger.v3.oas.models.media.MediaType().addExamples("default", new Example().value("{\"code\" : 400, \"status\" : \"Bad Request\", \"Message\" : \"Bad Request\"}"))));
        ApiResponse internalServerError = new ApiResponse().content(new Content().addMediaType("application/json", new io.swagger.v3.oas.models.media.MediaType().addExamples("default", new Example().value("{\"code\" : 500, \"status\" : \"internalServerError\", \"Message\" : \"internalServerError\"}"))));
        ApiResponse successfulResponse = new ApiResponse().content(new Content().addMediaType("application/json", new io.swagger.v3.oas.models.media.MediaType().addExamples("default", new Example().value("{\"name\":\"string\",\"surname\":\"string\",\"age\":0}"))));
        Components components = new Components();
        components.addResponses("badRequest", badRequest);
        components.addResponses("internalServerError", internalServerError);
        components.addResponses("successfulResponse", successfulResponse);

        Server server = new Server();
        server.setUrl("http://localhost:8080");
        server.setDescription("Development");

        License mitLicense = new License().name("MIT License").url("https://choosealicense.com/licenses/mit/");

        Contact myContact = new Contact();
        myContact.setName("Nicola Clemente");
        myContact.setEmail("nic.clem87@gmail.com");

        Info info = new Info().title("Event Manager Project OpenAPI Docs").version("1.0.0").contact(myContact).description("This API exposes endpoints to event manager exercise.").license(mitLicense);

        return new OpenAPI().components(components).info(info).servers(List.of(server));
    }

}