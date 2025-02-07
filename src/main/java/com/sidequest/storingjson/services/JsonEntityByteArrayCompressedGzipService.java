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
    public void saveAllRawTextByteArrayCompressed(List<User> users) {

        List<JsonEntityByteArrayCompressedGzip> entities = new ArrayList<>();

        long startTimeBuildingEntities = System.currentTimeMillis();

        users.forEach(user -> {

            try (
                    ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(JSON.toJSONBytes(user));
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {

                gzip(byteArrayInputStream, byteArrayOutputStream);

                JsonEntityByteArrayCompressedGzip jsonEntityByteArrayCompressedGzip = new JsonEntityByteArrayCompressedGzip();
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

        log.info("Starting to store {} random bytearray compressed gzip objects", entities.size());

        jsonEntityByteArrayCompressedGzipRepository.saveAll(entities);

        long endTime = System.currentTimeMillis();

        long resultTime = endTime - startTime;

        log.info("Finished storing {} random bytearray compressed gzip objects in {} milliseconds", entities.size(), resultTime);
    }

    public static void gzip(InputStream is, OutputStream os) throws IOException {
        GZIPOutputStream gzipOs = new GZIPOutputStream(os);
        byte[] allBytes = is.readAllBytes();
        gzipOs.write(allBytes, 0, allBytes.length);
        gzipOs.close();
    }

    public List<User> findAll() {

        List<User> result = new ArrayList<>();

        List<JsonEntityByteArrayCompressedGzip> entities = jsonEntityByteArrayCompressedGzipRepository.findAll();

        for (JsonEntityByteArrayCompressedGzip entity : entities) {

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
        }

        return result;
    }

    public static void unzip(InputStream is, OutputStream os) throws IOException {
        GZIPInputStream gzipIs = new GZIPInputStream(is);
        byte[] buffer = new byte[512];
        int bytesRead;
        while ((bytesRead = gzipIs.read(buffer)) > -1) {
            os.write(buffer, 0, bytesRead);
        }
        gzipIs.close();
    }
}
