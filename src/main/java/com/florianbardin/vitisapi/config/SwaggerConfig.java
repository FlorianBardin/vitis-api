package com.florianbardin.vitisapi.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Vitis API",
                description = "First version of a CRUD API about wineries and wine, made with Spring Boot.",
                version = "1.0.0"
        ),
        servers = {
                @Server(
                        description = "Local server",
                        url = "http://localhost:8080/"
                )
        }
)
public class SwaggerConfig {
}
