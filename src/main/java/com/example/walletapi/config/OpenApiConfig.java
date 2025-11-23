package com.example.walletapi.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Wallet API",
                version = "1.0",
                description = "API для управления кошельками",
                contact = @Contact(name = "Юра", email = "yuraggmotoff@gmail.com")
        ),
        servers = {
                @Server(url = "http://localhost:8080"),
                @Server(url = "https://staging.example.com"), // дополнительный сервер для тестирования
                @Server(url = "https://production.example.com") // основной сервер для продакшена
        }
)
public class OpenApiConfig {
}
