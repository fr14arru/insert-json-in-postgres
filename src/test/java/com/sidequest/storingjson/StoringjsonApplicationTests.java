package com.sidequest.storingjson;

import com.sidequest.storingjson.domain.User;
import com.sidequest.storingjson.services.JsonEntityByteArrayCompressedService;
import com.sidequest.storingjson.services.JsonEntityJsonService;
import com.sidequest.storingjson.services.JsonEntityJsonbService;
import com.sidequest.storingjson.services.JsonEntityRawTextService;
import com.sidequest.storingjson.services.JsonEntityByteArrayService;
import org.instancio.Instancio;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

@Testcontainers
@SpringBootTest
class StoringjsonApplicationTests {

    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(
            "postgres:16-alpine")
            .withDatabaseName("testdb")
            .withUsername("root")
            .withPassword("root");

    @BeforeAll
    static void beforeAll() {
        postgres.start();
    }

    @AfterAll
    static void afterAll() {
        postgres.stop();
    }

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
        registry.add("spring.jpa.hibernate.ddl-auto", () -> "create");
    }

    @Autowired
    JsonEntityRawTextService jsonEntityRawTextService;

    @Autowired
    JsonEntityJsonbService jsonEntityJsonbService;

    @Autowired
    JsonEntityJsonService jsonEntityJsonService;

    @Autowired
    JsonEntityByteArrayService jsonEntityByteArrayService;

    @Autowired
    JsonEntityByteArrayCompressedService jsonEntityByteArrayCompressedService;

    @Test
    void test_saveAll10000JsonEntitiesRawText() {

        List<User> users = buildUsers(100000);

        jsonEntityRawTextService.saveAllRawText(users);
    }

    @Test
    void test_saveAll10000JsonEntitiesJsonb() {

        List<User> users = buildUsers(100000);

        jsonEntityJsonbService.saveAllJsonb(users);
    }

    @Test
    void test_saveAll10000JsonEntitiesJson() {

        List<User> users = buildUsers(100000);

        jsonEntityJsonService.saveAllJson(users);
    }

    @Test
    void test_saveAll10000JsonEntitiesByteArray() {

        List<User> users = buildUsers(100000);

        jsonEntityByteArrayService.saveAllRawTextByteArray(users);
    }

    @Test
    void test_saveAll10000JsonEntitiesByteArrayCompressed() {

        List<User> users = buildUsers(100000);

        jsonEntityByteArrayCompressedService.saveAllRawTextByteArrayCompressed(users);
    }

    private List<User> buildUsers(Integer limit) {

        return Instancio.of(User.class).
                stream()
                .limit(limit)
                .toList();
    }
}
