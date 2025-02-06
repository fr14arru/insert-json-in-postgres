package com.sidequest.storingjson.services;

import com.sidequest.storingjson.domain.User;
import com.sidequest.storingjson.entities.JsonEntityByteArrayCompressed;
import com.sidequest.storingjson.repositories.JsonEntityByteArrayCompressedRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPOutputStream;

@Service
@RequiredArgsConstructor
@Slf4j
public class JsonEntityByteArrayCompressedService {

    private final JsonEntityByteArrayCompressedRepository jsonEntityByteArrayCompressedRepository;

    @Transactional
    public void saveAllRawTextByteArrayCompressed(List<User> users) {

        List<JsonEntityByteArrayCompressed> entities = new ArrayList<>();

        long startTimeBuildingEntities = System.currentTimeMillis();

        users.forEach(user -> {

            try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                 GZIPOutputStream gzipOut = new GZIPOutputStream(byteArrayOutputStream);
                 ObjectOutputStream objectOut = new ObjectOutputStream(gzipOut)) {

                objectOut.writeObject(user);
                objectOut.flush();

                JsonEntityByteArrayCompressed jsonEntityByteArrayCompressed = new JsonEntityByteArrayCompressed();
                jsonEntityByteArrayCompressed.setJson(byteArrayOutputStream.toByteArray());

                entities.add(jsonEntityByteArrayCompressed);

            } catch (Exception e) {

                log.error("Error serializing user object", e);
            }
        });

        long endTimeBuildingEntities = System.currentTimeMillis();

        long resultTimeBuildingEntities = endTimeBuildingEntities - startTimeBuildingEntities;

        log.info("Finished building {} random bytearray compressed objects in {} milliseconds", entities.size(), resultTimeBuildingEntities);

        long startTime = System.currentTimeMillis();

        log.info("Starting to store {} random bytearray compressed objects", entities.size());

        jsonEntityByteArrayCompressedRepository.saveAll(entities);

        long endTime = System.currentTimeMillis();

        long resultTime = endTime - startTime;

        log.info("Finished storing {} random bytearray compressed objects in {} milliseconds", entities.size(), resultTime);
    }
}
