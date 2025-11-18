package org.example.gestionfoyer.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Gestion Foyer Universitaire")
                        .description("Documentation de l'API REST pour la gestion de foyer universitaire")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Karim Feki")
                                .email("feki.karim28@gmail.com")
                                .url("https://www.linkedin.com/in/karimfeki/")));
    }
}