package com.sidequest.storingjson.services;

import com.sidequest.storingjson.domain.User;
import com.sidequest.storingjson.entities.JsonEntityByteArray;
import com.sidequest.storingjson.repositories.JsonEntityByteArrayRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.zip.GZIPOutputStream;

@Service
@RequiredArgsConstructor
@Slf4j
public class JsonEntityByteArrayService {

    private final JsonEntityByteArrayRepository jsonEntityByteArrayRepository;

    @Transactional
    public void saveAllRawTextByteArray(List<User> users) {

        List<JsonEntityByteArray> entities = new ArrayList<>();

        long startTimeBuildingEntities = System.currentTimeMillis();

        users.forEach(user -> {

            try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

                 ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream)) {

                objectOutputStream.writeObject(user);
                objectOutputStream.flush();

                JsonEntityByteArray jsonEntityByteArray = new JsonEntityByteArray();
                jsonEntityByteArray.setJson(byteArrayOutputStream.toByteArray());

                entities.add(jsonEntityByteArray);

            } catch (Exception e) {

                log.error("Error serializing user object", e);
            }
        });

        long endTimeBuildingEntities = System.currentTimeMillis();

        long resultTimeBuildingEntities = endTimeBuildingEntities - startTimeBuildingEntities;

        log.info("Finished building {} random bytearray objects in {} milliseconds", entities.size(), resultTimeBuildingEntities);

        long startTime = System.currentTimeMillis();

        log.info("Starting to store {} random bytearray objects", entities.size());

        jsonEntityByteArrayRepository.saveAll(entities);

        long endTime = System.currentTimeMillis();

        long resultTime = endTime - startTime;

        log.info("Finished storing {} random bytearray objects in {} milliseconds", entities.size(), resultTime);
    }

    public User findUserById(Long id) {

        User result = null;

        Optional<JsonEntityByteArray> opEntity = jsonEntityByteArrayRepository.findById(id);

        if (opEntity.isPresent()) {

            JsonEntityByteArray jsonEntityByteArray = opEntity.get();
            byte[] byteArray = jsonEntityByteArray.getJson();

            try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArray);
                 ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream)) {

                result = (User) objectInputStream.readObject();

            } catch (Exception e) {
                log.error("Error deserializing user object", e);
            }
        }

        return result;
    }


}
