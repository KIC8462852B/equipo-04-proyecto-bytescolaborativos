package com.motorRecomendacionesAPI.motorRecomendaciones.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

/**
 * Configuración inicial de Swagger UI / OpenAPI.
 * Permite visualizar la documentación de la API en:
 * http://localhost:8080/api/swagger-ui.html
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Motor de Recomendaciones API")
                        .description("Backend del proyecto Motor de Recomendaciones")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Equipo 04 - Bytes Colaborativos")
                                .url("https://github.com/KIC8462852B/equipo-04-proyecto-bytescolaborativos")
                                .email("lurdanhuszar@protonmail.ch / albertocruz8133@proton.me / adrias.9000@gmail.com")));
    }
}
