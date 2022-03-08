package com.alta.bank.configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfiguration {

    @Value("${info.application.version}")
    private String version;

    @Value("${info.application.description}")
    private String description;

    @Value("${info.application.name}")
    private String name;

    @Bean
    public OpenAPI openApi() {
        return new OpenAPI()
            .info(buildApiInfo());
    }

    private Info buildApiInfo() {
        return new Info()
            .title(name)
            .description(description)
            .version(version);
    }

}
