package com.sidequest.storingjson.services;

import com.alibaba.fastjson.JSON;
import com.sidequest.storingjson.domain.User;
import com.sidequest.storingjson.entities.JsonEntityRawText;
import com.sidequest.storingjson.repositories.JsonEntityRawTextRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class JsonEntityRawTextService {

    private final JsonEntityRawTextRepository jsonEntityRawTextRepository;

    @Transactional
    public void saveAll(List<User> users) {

        List<JsonEntityRawText> entities = new ArrayList<>();

        long startTimeBuildingEntities = System.currentTimeMillis();

        users.forEach(user -> {

            JsonEntityRawText jsonEntityRawText = new JsonEntityRawText();
            jsonEntityRawText.setJson(JSON.toJSONString(user));

            entities.add(jsonEntityRawText);
        });

        long endTimeBuildingEntities = System.currentTimeMillis();

        long resultTimeBuildingEntities = endTimeBuildingEntities - startTimeBuildingEntities;

        log.info("Finished building {} random raw text objects in {} milliseconds", entities.size(), resultTimeBuildingEntities);

        long startTime = System.currentTimeMillis();

        jsonEntityRawTextRepository.saveAll(entities);

        long endTime = System.currentTimeMillis();

        long resultTime = endTime - startTime;

        log.info("Finished storing {} random raw text objects in {} milliseconds", entities.size(), resultTime);
    }
}
