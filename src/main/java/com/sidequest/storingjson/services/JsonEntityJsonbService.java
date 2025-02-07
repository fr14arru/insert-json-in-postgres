package com.sidequest.storingjson.services;

import com.sidequest.storingjson.domain.User;
import com.sidequest.storingjson.entities.JsonEntityJsonb;
import com.sidequest.storingjson.repositories.JsonEntityJsonbRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class JsonEntityJsonbService {

    private final JsonEntityJsonbRepository jsonEntityJsonbRepository;

    @Transactional
    public void saveAll(List<User> users) {

        List<JsonEntityJsonb> entities = new ArrayList<>();

        long startTimeBuildingEntities = System.currentTimeMillis();

        users.forEach(user -> {

            JsonEntityJsonb jsonEntityJsonb = new JsonEntityJsonb();
            jsonEntityJsonb.setUser(user);

            entities.add(jsonEntityJsonb);
        });

        long endTimeBuildingEntities = System.currentTimeMillis();

        long resultTimeBuildingEntities = endTimeBuildingEntities - startTimeBuildingEntities;

        log.info("Finished building {} random JSONB objects in {} milliseconds", entities.size(), resultTimeBuildingEntities);

        long startTime = System.currentTimeMillis();

        jsonEntityJsonbRepository.saveAll(entities);

        long endTime = System.currentTimeMillis();

        long resultTime = endTime - startTime;

        log.info("Finished storing {} random JSONB objects in {} milliseconds", entities.size(), resultTime);
    }
}
