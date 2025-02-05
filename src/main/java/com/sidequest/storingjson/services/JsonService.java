package com.sidequest.storingjson.services;

import com.alibaba.fastjson.JSON;
import com.sidequest.storingjson.domain.User;
import com.sidequest.storingjson.entities.JsonEntityByteArray;
import com.sidequest.storingjson.entities.JsonEntityJson;
import com.sidequest.storingjson.entities.JsonEntityJsonb;
import com.sidequest.storingjson.entities.JsonEntityRawText;
import com.sidequest.storingjson.repositories.JsonEntityByteArrayRepository;
import com.sidequest.storingjson.repositories.JsonEntityJsonRepository;
import com.sidequest.storingjson.repositories.JsonEntityJsonbRepository;
import com.sidequest.storingjson.repositories.JsonEntityRawTextRepository;
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
@Transactional
@Slf4j
public class JsonService {

    private final JsonEntityRawTextRepository jsonEntityRawTextRepository;
    private final JsonEntityJsonbRepository jsonEntityJsonbRepository;
    private final JsonEntityJsonRepository jsonEntityJsonRepository;
    private final JsonEntityByteArrayRepository jsonEntityByteArrayRepository;

    public void saveAllRawText(List<User> users) {

        List<JsonEntityRawText> entities = new ArrayList<>();

        users.forEach(user -> {

            JsonEntityRawText jsonEntityRawText = new JsonEntityRawText();
            jsonEntityRawText.setJson(JSON.toJSONString(user));

            entities.add(jsonEntityRawText);
        });

        long startTime = System.currentTimeMillis();

        log.info("Starting to store {} random JSON objects", entities.size());

        jsonEntityRawTextRepository.saveAll(entities);

        long endTime = System.currentTimeMillis();

        long resultTime = endTime - startTime;

        log.info("Finished storing {} random JSON objects in {} milliseconds", entities.size(), resultTime);
    }

    public void saveAllJsonb(List<User> users) {

        List<JsonEntityJsonb> entities = new ArrayList<>();

        users.forEach(user -> {

            JsonEntityJsonb jsonEntityJsonb = new JsonEntityJsonb();
            jsonEntityJsonb.setUser(user);

            entities.add(jsonEntityJsonb);
        });

        long startTime = System.currentTimeMillis();

        log.info("Starting to store {} random JSON objects", entities.size());

        jsonEntityJsonbRepository.saveAll(entities);

        long endTime = System.currentTimeMillis();

        long resultTime = endTime - startTime;

        log.info("Finished storing {} random JSON objects in {} milliseconds", entities.size(), resultTime);
    }

    public void saveAllJson(List<User> users) {

        List<JsonEntityJson> entities = new ArrayList<>();

        users.forEach(user -> {

            JsonEntityJson jsonEntityJson = new JsonEntityJson();
            jsonEntityJson.setUser(user);

            entities.add(jsonEntityJson);
        });

        long startTime = System.currentTimeMillis();

        log.info("Starting to store {} random JSON objects", entities.size());

        jsonEntityJsonRepository.saveAll(entities);

        long endTime = System.currentTimeMillis();

        long resultTime = endTime - startTime;

        log.info("Finished storing {} random JSON objects in {} milliseconds", entities.size(), resultTime);
    }

    public void saveAllRawTextByteArray(List<User> users) {

        List<JsonEntityByteArray> entities = new ArrayList<>();

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

    public void saveAllRawTextByteArrayCompressed(List<User> users) {

        List<JsonEntityByteArray> entities = new ArrayList<>();

        long startDateCompression = System.currentTimeMillis();

        users.forEach(user -> {

            try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                 GZIPOutputStream gzipOut = new GZIPOutputStream(byteArrayOutputStream);
                 ObjectOutputStream objectOut = new ObjectOutputStream(gzipOut)) {

                objectOut.writeObject(user);
                objectOut.flush();

                JsonEntityByteArray jsonEntityByteArray = new JsonEntityByteArray();
                jsonEntityByteArray.setJson(byteArrayOutputStream.toByteArray());

                entities.add(jsonEntityByteArray);

            } catch (Exception e) {

                log.error("Error serializing user object", e);
            }
        });

        long endTimeCompression = System.currentTimeMillis();

        long resultTimeCompression = endTimeCompression - startDateCompression;

        log.info("Finished compressing {} random bytearray objects in {} milliseconds", entities.size(), resultTimeCompression);

        long startTime = System.currentTimeMillis();

        log.info("Starting to store {} random bytearray compressed objects", entities.size());

        jsonEntityByteArrayRepository.saveAll(entities);

        long endTime = System.currentTimeMillis();

        long resultTime = endTime - startTime;

        log.info("Finished storing {} random bytearray compressed objects in {} milliseconds", entities.size(), resultTime);
    }
}
