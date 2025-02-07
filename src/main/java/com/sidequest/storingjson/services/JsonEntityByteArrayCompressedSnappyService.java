package com.sidequest.storingjson.services;

import com.alibaba.fastjson.JSON;
import com.sidequest.storingjson.domain.User;
import com.sidequest.storingjson.entities.JsonEntityByteArrayCompressedSnappy;
import com.sidequest.storingjson.repositories.JsonEntityByteArrayCompressedSnappyRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.xerial.snappy.SnappyInputStream;
import org.xerial.snappy.SnappyOutputStream;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class JsonEntityByteArrayCompressedSnappyService {

    private final JsonEntityByteArrayCompressedSnappyRepository jsonEntityByteArrayCompressedSnappyRepository;

    @Transactional
    public void saveAll(List<User> users) {

        List<JsonEntityByteArrayCompressedSnappy> entities = new ArrayList<>();

        long startTimeBuildingEntities = System.currentTimeMillis();

        users.forEach(user -> {

            try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(JSON.toJSONBytes(user));
                 ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {

                snappy(byteArrayInputStream, byteArrayOutputStream);

                JsonEntityByteArrayCompressedSnappy jsonEntityByteArrayCompressedSnappy =
                        new JsonEntityByteArrayCompressedSnappy();
                jsonEntityByteArrayCompressedSnappy.setJson(byteArrayOutputStream.toByteArray());

                entities.add(jsonEntityByteArrayCompressedSnappy);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        long endTimeBuildingEntities = System.currentTimeMillis();

        long resultTimeBuildingEntities = endTimeBuildingEntities - startTimeBuildingEntities;

        log.info("Finished building {} random bytearray compressed Snappy objects in {} milliseconds", entities.size(), resultTimeBuildingEntities);

        long startTime = System.currentTimeMillis();

        jsonEntityByteArrayCompressedSnappyRepository.saveAll(entities);

        long endTime = System.currentTimeMillis();

        long resultTime = endTime - startTime;

        log.info("Finished storing {} random bytearray compressed Snappy objects in {} milliseconds", entities.size(), resultTime);
    }

    private void snappy(InputStream inputStream, OutputStream outputStream) throws IOException {

        SnappyOutputStream snappyOutputStream = new SnappyOutputStream(outputStream);

        byte[] allBytes = inputStream.readAllBytes();

        snappyOutputStream.write(allBytes);

        snappyOutputStream.close();
    }

    public List<User> findAll() {

        List<User> result = new ArrayList<>();

        long startTimeQuery = System.currentTimeMillis();

        List<JsonEntityByteArrayCompressedSnappy> entities = jsonEntityByteArrayCompressedSnappyRepository.findAll();

        long endTimeQuery = System.currentTimeMillis();

        long resultTimeQuery = endTimeQuery - startTimeQuery;

        log.info("Time querying {} random bytearray compressed Snappy objects in {} milliseconds", entities.size(), resultTimeQuery);

        long startTimeMappingEntities = System.currentTimeMillis();

        entities.forEach(entity -> {

            try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(entity.getJson());
                 ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {

                unSnappy(byteArrayInputStream, byteArrayOutputStream);

                User user = JSON.parseObject(byteArrayOutputStream.toString(StandardCharsets.UTF_8), User.class);

                result.add(user);

            } catch (IOException e) {

                throw new RuntimeException(e);
            }
        });

        long endTimeMappingEntities = System.currentTimeMillis();

        long resultTimeMappingEntities = endTimeMappingEntities - startTimeMappingEntities;

        log.info("Time mapping {} random bytearray compressed zstd objects in {} milliseconds", entities.size(), resultTimeMappingEntities);

        return result;
    }

    private void unSnappy(InputStream inputStream, OutputStream outputStream) throws IOException {

        SnappyInputStream snappyInputStream = new SnappyInputStream(inputStream);

        byte[] buffer = new byte[512];

        int bytesRead;

        while ((bytesRead = snappyInputStream.read(buffer)) > -1) {
            outputStream.write(buffer, 0, bytesRead);
        }

        snappyInputStream.close();
    }
}
