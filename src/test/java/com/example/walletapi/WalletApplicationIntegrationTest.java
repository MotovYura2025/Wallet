package com.example.walletapi;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.containers.PostgreSQLContainer;

@Testcontainers
@SpringBootTest
public class WalletApplicationIntegrationTest {

    // Переопределение хоста для Testcontainers
    static {
        System.setProperty("TESTCONTAINERS_HOST_OVERRIDE", "host.docker.internal");
    }

    @Container
    public static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15")
            .withDatabaseName("testdb")
            .withUsername("test")
            .withPassword("test");

    @Test
    public void contextLoads() {
        // Ваши тесты здесь, например проверка поднятого контейнера БД
        System.out.println("Postgres URL: " + postgres.getJdbcUrl());
    }
}
