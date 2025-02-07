package com.sidequest.storingjson.services;

import com.alibaba.fastjson.JSON;
import com.github.luben.zstd.ZstdInputStream;
import com.github.luben.zstd.ZstdOutputStream;
import com.sidequest.storingjson.domain.User;
import com.sidequest.storingjson.entities.JsonEntityByteArrayCompressedZstd;
import com.sidequest.storingjson.repositories.JsonEntityByteArrayCompressedZstdRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
public class JsonEntityByteArrayCompressedZstdService {

    private final JsonEntityByteArrayCompressedZstdRepository jsonEntityByteArrayCompressedZstdRepository;

    @Transactional
    public void saveAllRawTextByteArrayCompressedZstd(List<User> users) {

        List<JsonEntityByteArrayCompressedZstd> entities = new ArrayList<>();

        long startTimeBuildingEntities = System.currentTimeMillis();

        users.forEach(user -> {

            try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(JSON.toJSONBytes(user));
                 ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {

                zstd(byteArrayInputStream, byteArrayOutputStream);

                JsonEntityByteArrayCompressedZstd jsonEntityByteArrayCompressedZstd =
                        new JsonEntityByteArrayCompressedZstd();
                jsonEntityByteArrayCompressedZstd.setJson(byteArrayOutputStream.toByteArray());

                entities.add(jsonEntityByteArrayCompressedZstd);

            } catch (IOException e) {

                throw new RuntimeException(e);
            }

        });

        long endTimeBuildingEntities = System.currentTimeMillis();

        long resultTimeBuildingEntities = endTimeBuildingEntities - startTimeBuildingEntities;

        log.info("Finished building {} random bytearray compressed Zstd objects in {} milliseconds", entities.size(), resultTimeBuildingEntities);

        long startTime = System.currentTimeMillis();

        jsonEntityByteArrayCompressedZstdRepository.saveAll(entities);

        long endTime = System.currentTimeMillis();

        long resultTime = endTime - startTime;

        log.info("Finished storing {} random bytearray compressed Zstd objects in {} milliseconds", entities.size(), resultTime);
    }

    private void zstd(InputStream inputStream, OutputStream outputStream) throws IOException {

        ZstdOutputStream zstdOutputStream = new ZstdOutputStream(outputStream);

        byte[] allBytes = inputStream.readAllBytes();

        zstdOutputStream.write(allBytes);

        zstdOutputStream.close();
    }

    public List<User> findAll() {

        List<User> result = new ArrayList<>();

        long startTimeQuery = System.currentTimeMillis();

        List<JsonEntityByteArrayCompressedZstd> entities = jsonEntityByteArrayCompressedZstdRepository.findAll();

        long endTimeQuery = System.currentTimeMillis();

        long resultTimeQuery = endTimeQuery - startTimeQuery;

        log.info("Time querying {} random bytearray compressed zstd objects in {} milliseconds", entities.size(), resultTimeQuery);

        long startTimeMappingEntities = System.currentTimeMillis();

        entities.forEach(entity -> {

            try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(entity.getJson());
                 ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {

                unZstd(byteArrayInputStream, byteArrayOutputStream);

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

    private void unZstd(InputStream inputStream, OutputStream outputStream) throws IOException {

        ZstdInputStream zstdInputStream = new ZstdInputStream(inputStream);

        byte[] buffer = new byte[512];

        int bytesRead;

        while ((bytesRead = zstdInputStream.read(buffer)) > -1) {
            outputStream.write(buffer, 0, bytesRead);
        }

        zstdInputStream.close();
    }
}
