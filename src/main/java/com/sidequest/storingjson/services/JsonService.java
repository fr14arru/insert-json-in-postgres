package com.sidequest.storingjson.services;

import com.sidequest.storingjson.entities.JsonEntity;
import com.sidequest.storingjson.repositories.JsonRepository;
import com.sidequest.storingjson.utils.JsonUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class JsonService {

    private final JsonRepository jsonRepository;

    public void saveAll10000() {

        List<JsonEntity> randomJsonStringList = new ArrayList<>();

        for (int i = 0 ; i < 10000; i++) {

            JsonEntity entity = new JsonEntity();
            entity.setJson(JsonUtils.getRandomJson());

            randomJsonStringList.add(entity);
        }


        long startTime = System.currentTimeMillis();

        log.info("Starting to store 10000 random JSON strings");

        jsonRepository.saveAll(randomJsonStringList);

        long endTime = System.currentTimeMillis();

        long resultTime = endTime - startTime;

        log.info("Finished storing 10000 random JSON strings in {} milliseconds", resultTime);
    }
}
