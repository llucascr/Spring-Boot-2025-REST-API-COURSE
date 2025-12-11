package br.com.llucascr.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("REST API's RESTful")
                        .version("v1")
                        .description("REST API's RESTful description")
                        .termsOfService("Termos de serviço")
                        .license(new License()
                                .name("APACHE 2.0")
                                .url("ulr licence")
                        )
                );
    }

}
