package com.sidequest.storingjson.services;

import com.alibaba.fastjson.JSON;
import com.sidequest.storingjson.domain.User;
import com.sidequest.storingjson.entities.JsonEntityByteArrayCompressedGzip;
import com.sidequest.storingjson.repositories.JsonEntityByteArrayCompressedGzipRepository;
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
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

@Service
@RequiredArgsConstructor
@Slf4j
public class JsonEntityByteArrayCompressedGzipService {

    private final JsonEntityByteArrayCompressedGzipRepository jsonEntityByteArrayCompressedGzipRepository;

    @Transactional
    public void saveAll(List<User> users) {

        List<JsonEntityByteArrayCompressedGzip> entities = new ArrayList<>();

        long startTimeBuildingEntities = System.currentTimeMillis();

        users.forEach(user -> {

            try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(JSON.toJSONBytes(user));
                 ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {

                gzip(byteArrayInputStream, byteArrayOutputStream);

                JsonEntityByteArrayCompressedGzip jsonEntityByteArrayCompressedGzip =
                        new JsonEntityByteArrayCompressedGzip();
                jsonEntityByteArrayCompressedGzip.setJson(byteArrayOutputStream.toByteArray());

                entities.add(jsonEntityByteArrayCompressedGzip);

            } catch (Exception e) {

                log.error("Error serializing user object", e);
            }
        });

        long endTimeBuildingEntities = System.currentTimeMillis();

        long resultTimeBuildingEntities = endTimeBuildingEntities - startTimeBuildingEntities;

        log.info("Finished building {} random bytearray compressed gzip objects in {} milliseconds", entities.size(), resultTimeBuildingEntities);

        long startTime = System.currentTimeMillis();

        jsonEntityByteArrayCompressedGzipRepository.saveAll(entities);

        long endTime = System.currentTimeMillis();

        long resultTime = endTime - startTime;

        log.info("Finished storing {} random bytearray compressed gzip objects in {} milliseconds", entities.size(), resultTime);
    }

    private void gzip(InputStream inputStream, OutputStream outputStream) throws IOException {

        GZIPOutputStream gzipOs = new GZIPOutputStream(outputStream);

        byte[] allBytes = inputStream.readAllBytes();

        gzipOs.write(allBytes, 0, allBytes.length);

        gzipOs.close();
    }

    public List<User> findAll() {

        List<User> result = new ArrayList<>();

        long startTimeQuery = System.currentTimeMillis();

        List<JsonEntityByteArrayCompressedGzip> entities = jsonEntityByteArrayCompressedGzipRepository.findAll();

        long endTimeQuery = System.currentTimeMillis();

        long resultTimeQuery = endTimeQuery - startTimeQuery;

        log.info("Time querying {} random bytearray compressed gzip objects in {} milliseconds", entities.size(), resultTimeQuery);

        long startTimeMappingEntities = System.currentTimeMillis();

        entities.forEach(entity -> {

            try (ByteArrayInputStream bais = new ByteArrayInputStream(entity.getJson());
                 ByteArrayOutputStream baos = new ByteArrayOutputStream()) {

                unzip(bais, baos);

                byte[] objectBytes = baos.toByteArray();

                String json = new String(objectBytes, StandardCharsets.UTF_8);

                User user = JSON.parseObject(json, User.class);

                result.add(user);

            } catch (IOException e) {

                throw new RuntimeException("Error decompressing user data", e);
            }
        });

        long endTimeMappingEntities = System.currentTimeMillis();

        long resultTimeMappingEntities = endTimeMappingEntities - startTimeMappingEntities;

        log.info("Time mapping {} random bytearray compressed gzip objects in {} milliseconds", entities.size(), resultTimeMappingEntities);

        return result;
    }

    private static void unzip(InputStream inputStream, OutputStream outputStream) throws IOException {

        GZIPInputStream gzipIs = new GZIPInputStream(inputStream);

        byte[] buffer = new byte[512];

        int bytesRead;

        while ((bytesRead = gzipIs.read(buffer)) > -1) {
            outputStream.write(buffer, 0, bytesRead);
        }

        gzipIs.close();
    }
}
