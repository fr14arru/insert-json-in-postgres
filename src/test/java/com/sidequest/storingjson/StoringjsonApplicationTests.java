package com.sidequest.storingjson;

import com.sidequest.storingjson.domain.User;
import com.sidequest.storingjson.services.JsonEntityByteArrayCompressedGzipService;
import com.sidequest.storingjson.services.JsonEntityByteArrayCompressedSnappyService;
import com.sidequest.storingjson.services.JsonEntityByteArrayCompressedZstdService;
import com.sidequest.storingjson.services.JsonEntityByteArrayService;
import com.sidequest.storingjson.services.JsonEntityJsonService;
import com.sidequest.storingjson.services.JsonEntityJsonbService;
import com.sidequest.storingjson.services.JsonEntityRawTextService;
import org.instancio.Instancio;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
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

    private static List<User> usersList;

    @BeforeAll
    static void beforeAll() {
        postgres.start();

        usersList = buildUsers(100000);
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
    JsonEntityByteArrayCompressedGzipService jsonEntityByteArrayCompressedGzipService;

    @Autowired
    JsonEntityByteArrayCompressedSnappyService jsonEntityByteArrayCompressedSnappyService;

    @Autowired
    JsonEntityByteArrayCompressedZstdService jsonEntityByteArrayCompressedZstdService;

    @Test
    void test_saveAll10000JsonEntitiesRawText() {

        jsonEntityRawTextService.saveAll(usersList);
    }

    @Test
    void test_saveAll10000JsonEntitiesJsonb() {

        jsonEntityJsonbService.saveAll(usersList);
    }

    @Test
    void test_saveAll10000JsonEntitiesJson() {

        jsonEntityJsonService.saveAll(usersList);
    }

    @Test
    void test_saveAll10000JsonEntitiesByteArray() {

        jsonEntityByteArrayService.saveAll(usersList);
    }

    @Test
    void test_saveAll10000JsonEntitiesByteArrayCompressedGzip() {

        jsonEntityByteArrayCompressedGzipService.saveAll(usersList);

        List<User> usersResult = jsonEntityByteArrayCompressedGzipService.findAll();

        Assertions.assertNotNull(usersResult);
    }


    @Test
    void test_saveAll10000JsonEntitiesByteArrayCompressedZstd() {

        jsonEntityByteArrayCompressedZstdService.saveAll(usersList);

        List<User> usersResult = jsonEntityByteArrayCompressedZstdService.findAll();

        Assertions.assertNotNull(usersResult);
    }

    @Test
    void test_saveAll10000JsonEntitiesByteArrayCompressedSnappy() {

        jsonEntityByteArrayCompressedSnappyService.saveAll(usersList);

        List<User> usersResult = jsonEntityByteArrayCompressedSnappyService.findAll();

        Assertions.assertNotNull(usersResult);
    }

    private static List<User> buildUsers(Integer limit) {

        return Instancio.of(User.class).
                stream()
                .limit(limit)
                .toList();
    }
}
