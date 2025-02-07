package com.sidequest.storingjson.services;

import com.sidequest.storingjson.domain.User;
import com.sidequest.storingjson.entities.JsonEntityJson;
import com.sidequest.storingjson.repositories.JsonEntityJsonRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class JsonEntityJsonService {

    private final JsonEntityJsonRepository jsonEntityJsonRepository;

    @Transactional
    public void saveAll(List<User> users) {

        List<JsonEntityJson> entities = new ArrayList<>();

        long startTimeBuildingEntities = System.currentTimeMillis();

        users.forEach(user -> {

            JsonEntityJson jsonEntityJson = new JsonEntityJson();
            jsonEntityJson.setUser(user);

            entities.add(jsonEntityJson);
        });

        long endTimeBuildingEntities = System.currentTimeMillis();

        long resultTimeBuildingEntities = endTimeBuildingEntities - startTimeBuildingEntities;

        log.info("Finished building {} random JSON objects in {} milliseconds", entities.size(), resultTimeBuildingEntities);

        long startTime = System.currentTimeMillis();

        jsonEntityJsonRepository.saveAll(entities);

        long endTime = System.currentTimeMillis();

        long resultTime = endTime - startTime;

        log.info("Finished storing {} random JSON objects in {} milliseconds", entities.size(), resultTime);
    }
}
